import React from 'react';
import { useEffect } from 'react';
import { useState } from 'react';
import DynamicDiv from './dynamicDiv';
import AddEntry from './addEntry';
import LoginForm from './loginForm';
import SignupForm from './signupForm';
import CurrentPage from './state_enum'
import BrowseEntries from './browseEntries'

const ScreenContent = () =>
{
    const [currentPage, setCurrentPage] = useState(CurrentPage.Login);
    const dasiprobvam = CurrentPage.Signup;

    const myContent = () => 
        {
            let ret = <SignupForm/>
            console.log('currentPage:', currentPage);
            console.log('CurrentPage.Signup:', CurrentPage.Signup);  // Debug: check enum value
            switch(currentPage) { // Different returns here
                case CurrentPage.Signup:
                    ret = <SignupForm setCurrentPage={setCurrentPage} />;
                break;
                // code block
                case CurrentPage.Login:
                    ret = <LoginForm setCurrentPage={setCurrentPage} />;
                break;
                // code block
                case CurrentPage.AddNew:
                    ret = <AddEntry setCurrentPage={setCurrentPage} />
                break;
                case CurrentPage.Browse:
                    ret = <BrowseEntries setCurrentPage={setCurrentPage} />
                break;
                default:
                    ret = <p>Invalid state. Can't open page.</p>
                break;
            } 
            console.log("Return:", ret);
            return ret;
        }
        const handleStateChange = () => {
            setCurrentPage(CurrentPage.AddNew);  // This will trigger a re-render
          };

        // useEffect(() => {
        //     const handlePageChange = () => {
        //       setCurrentPage(window.currentPage); // Update React state when global state changes
        //     };

        //     window.addEventListener('currentPageChange', handlePageChange);

        //     return () => {
        //         window.removeEventListener('currentPageChange', handlePageChange);
        //       };
        //     }, []); // Empty dependency array to run only on mount

    return(
        <div>
        <DynamicDiv content={myContent()} />
        </div>
    );
}

export default ScreenContent;