import axios from 'axios';

import React, { useState } from "react";
import { Editor } from 'primereact/editor';
import CurrentPage from './state_enum';

import { usePage } from './pageContext';
import { Chips } from 'primereact/chips';
import { Button } from 'primereact/button';
import { Password } from 'primereact/password';
import { Card } from 'primereact/card';
import { Toast } from 'primereact/toast';

const AddEntry = () =>
{
    const [enteredContent, setEnteredContent] = useState('');
    const [enteredTitle, setEnteredTitle] = useState('');
    const [error, setError] = useState(null);
    const toast = React.useRef(null);
    const { currentPage, setCurrentPage } = usePage(); 
    const handleClick = async (e) =>
    {
        e.preventDefault();
    
        if (!enteredContent || enteredTitle) {
            setError('All fields are required');
            toast.current.show({ severity: 'error', summary: 'Error', detail: error, life: 3000 });
            return;
        }
        const authToken = 'Basic ' + btoa(`${window.username}:${window.password}`);
        const entry= {
                title: enteredTitle,
                content: enteredContent 
          };
        const headers = {
            'Content-Type': 'application/json',
            'Authorization' : authToken
            // 'Authorization': 'JWT fefege...'
        };
        const auth = {
            username : window.username,
            password : window.password
        }
        console.log(headers);
        let response = 200;
        try {
            response = await axios.post(window.entryPoint + '/entry/add', entry, {headers : headers, auth : auth});
            // document.write(response);
            if (response.status === 200) {
                toast.current.show({ severity: 'success', summary: 'Entry added successfully', detail: response.data.message, life: 3000 });
                setCurrentPage(CurrentPage.Browse);
            } else {
                toast.current.show({ severity: 'error', summary: 'Sign in Failed', detail: response.data.error, life: 3000 });
            }
        } catch (error) {
            console.error(error);
            toast.current.show({ severity: 'error', summary: 'Request Failed', detail: 'Unable to reach the server', life: 3000 });
        } finally {
        }

    };
    return (
        <div className="card" style={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh'}}>
            <Card title="Enter a post" style={{ width: '70%', align: 'center'}}>
                <Toast ref={toast} />
                <form onSubmit={handleClick}>
                    <div className="p-field p-grid">
                        <label htmlFor="Title" className="p-col-12 p-md-2">Title</label>
                        <div className="p-col-12 p-md-10">
                            <Chips id="Title" value={enteredTitle} onChange={(e) => setEnteredTitle(e.target.value)} placeholder="Enter title" />
                        </div>
                    </div>
                    <div className="p-field p-grid">
                        <label htmlFor="content" className="p-col-12 p-md-2">Content</label>
                        <div className="p-col-12 p-md-10">
                            <Editor value={enteredContent} onTextChange={(e) => setEnteredContent(e.htmlValue)} style={{ height: '320px' }} />
                        </div>
                    </div>
                    <div className="p-field">
                        <label htmlFor="Submit" className="p-col-12 p-md-2"><br/></label>
                        <Button label="Submit" type="submit" icon="pi pi-user"/>
                    </div>
                </form>
            </Card>
        </div>
    )
}
export default AddEntry;