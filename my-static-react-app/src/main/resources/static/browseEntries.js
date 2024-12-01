import React from 'react';
import { usePage } from './pageContext';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Panel } from 'primereact/panel';
import { useState, useEffect } from 'react';
import { Button } from 'primereact/button';
import axios from 'axios';
import CurrentPage from './state_enum';

const BrowseEntries = () =>
{
    const [data, setData] = useState([]);
    useEffect(() => {
        // Function to fetch data from API
        const fetchData = async () => {
            try {
                const authToken = 'Basic ' + btoa(`${window.username}:${window.password}`);
                // Replace this URL with your actual API endpoint
            const response = await axios.get(window.entryPoint + '/entries/getList', {
                params: {
                    page: 0,
                    perPage: 0,
                },
                headers: {
                    // 'Content-Type': 'application/json',
                    'Authorization' : authToken, // Set the Authorization header
                },
            }); 
                // Assuming response.data is an array of objects
                if(response.status === 200){
                    setData(response.data);
                }else{
                    if(response.status === 401)
                        setCurrentPage(CurrentPage.Login);
                }
            } catch (error) {
                console.error("There was an error fetching the data!", error);
            }
        };

        fetchData(); // Call the function to fetch data
    }, []); // Empty dependency array ensures the request is made only once when the component mounts
    //     const jsonData = [
    //     { id: 1, title: 'Panel 1', content: 'This is the content of panel 1.' },
    //     { id: 2, title: 'Panel 2', content: 'This is the content of panel 2.' },
    //     { id: 3, title: 'Panel 3', content: 'This is the content of panel 3.' },
    //     { id: 4, title: 'Panel 4', content: 'This is the content of panel 4.' },
    //     { id: 5, title: 'Panel 5', content: 'This is the content of panel 5.' },
    //     { id: 6, title: 'Panel 6', content: 'This is the content of panel 6.' },
    //     { id: 7, title: 'Panel 7', content: 'This is the content of panel 7.' },
    // ];
     
    const handleButtonClick = (id) => {
        alert(`Button clicked in Panel ${id}`);
    };
    const { currentPage, setCurrentPage } = usePage(); 
    return (
                   <div className="container">
            <h3>Responsive Grid Layout with Panels</h3>

            {/* Grid container using CSS Grid */}
            <div className="grid-container">
                {data.map((item) => (
                    <div key={item.id} className="grid-item">
                        <Panel header={item.title}>
                            <p>{item.content}</p>
                            <Button
                                label="Click Me"
                                icon="pi pi-check"
                                onClick={() => handleButtonClick(item.id)}
                                className="p-button-primary"
                            />
                        </Panel>
                    </div>
                ))}
            </div>
        </div> 
       );
}

export default BrowseEntries;