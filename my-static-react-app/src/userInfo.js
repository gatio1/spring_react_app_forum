import React from 'react'
import CurrentPage from './state_enum';
import { usePage } from './pageContext';
import { Card } from 'primereact/card';
import { Button } from 'primereact/button';
import { useState, useEffect } from 'react';
import axios from 'axios';

const UserInfo = () =>
{

    const [data, setData] = useState([]);
    const [numEntriesFromUser, setNumEntriesFromUser] = useState([]);
    const { currentPage, setCurrentPage, unameVal, passwdVal} = usePage();
    // let numEntriesFromUser = 0;
    const fetchUserInfo = async () =>
    {
        try {
            const authToken = 'Basic ' + btoa(`${unameVal}:${passwdVal}`);
                // Replace this URL with your actual API endpoint
            let response = null;
            if(window.userId !== 0){
                response = await axios.get(window.entryPoint + '/user/getUser', {
                    params: {
                        id: window.userId,
                    },
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization' : authToken, // Set the Authorization header
                    },
                }); 
            }else response.status = 400;
                // Assuming response.data is an array of objects
                if(response.status === 200){
                    setData(response.data);
                }else{
                    if(response.status === 401)
                        setCurrentPage(CurrentPage.Login);
                }
                if(window.userId !== 0){
                    response = await axios.get(
                        window.entryPoint + '/entry/numFromUser', {
                            params: {
                                userId: window.userId,
                            },
                            headers: {
                                'Content-Type': 'application/json',
                                'Authorization' : authToken, // Set the Authorization header
                            },
                        }
                    )
                    if(response.status === 200){
                        setNumEntriesFromUser(response.data);
                        console.log(numEntriesFromUser);
                        console.log("content of data: ", response.data);
                    }else{
                        if(response.status === 401)
                            setCurrentPage(CurrentPage.Login);
                }}
        } catch (error) {
            console.error("There was an error fetching the data!", error);
        };
        
    };
    const handleEditClick = () => //put request for user.
    {
        setCurrentPage(CurrentPage.EditUser);
    };

    const handleEntriesClick = () => //get request for user.
    {
        setCurrentPage(CurrentPage.BrowseUserEntries);
    };
    useEffect(() => {
    fetchUserInfo();
    }, []);  // Empty dependency array means this runs only once when the component mounts
    // fetchUserInfo();

    return(
        <div className="Display-user-container" style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
            <Card title={`Information about ${unameVal}`} style={{ width: '400px' }}>
                    <div className="item">
                            <p>email: {data.emailAddr}</p>
                            <p>username: {data.username}</p>
                            <p>user ID: {data.id}</p>
                            <Button
                                label="edit user"
                                style={{ margin: '10px' }}
                                icon="pi pi-check"
                                onClick={() => handleEditClick(data.id)}
                                className="p-button-primary"
                            />
                            <Button
                                label={`get entries (${numEntriesFromUser})`}
                                style={{ margin: '10px' }}
                                icon="pi pi-check"
                                onClick={() => handleEntriesClick(data.id)}
                                className="p-button-primary"
                            />
                    </div>
            </Card>
        </div>

    )
}
export default UserInfo;