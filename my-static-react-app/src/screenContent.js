import React from 'react';
import { useEffect } from 'react';
import { useState } from 'react';
import DynamicDiv from './dynamicDiv';
import AddEntry from './addEntry';
import LoginForm from './loginForm';
import SignupForm from './signupForm';
import CurrentPage from './state_enum'
import BrowseEntries from './browseEntries'
import UserInfo from './userInfo'
import { usePage } from './pageContext';
import BrowseUserEntries from './browseUserEntries';
import EditUser from './editUser';


const ScreenContent = () =>
{
    const { currentPage, setCurrentPage } = usePage(); 
    const { unameVal, setUnameVal, passwdVal, setPasswdVal} = usePage();

    const myContent = () => 
        {
            let ret = <SignupForm/>
            console.log('currentPage:', currentPage);
            console.log('CurrentPage.Signup:', CurrentPage.Signup);  // Debug: check enum value
            switch(currentPage) { // Different returns here
                case CurrentPage.Signup:
                    ret = <SignupForm/>;
                break;
                // code block
                case CurrentPage.Login:
                    ret = <LoginForm />;
                break;
                // code block
                case CurrentPage.AddNew:
                    ret = <AddEntry />
                break;
                case CurrentPage.Browse:
                    ret = <BrowseEntries />
                break;
                case CurrentPage.UserInfo:
                    ret = <UserInfo />
                break;
                case CurrentPage.Logout:
                    setUnameVal("");
                    setPasswdVal("");
                    setCurrentPage(CurrentPage.Login);
                break;
                case CurrentPage.BrowseUserEntries:
                    ret = <BrowseUserEntries/>
                break;
                case CurrentPage.EditUser:
                    ret = <EditUser/>
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