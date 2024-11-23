import axios from 'axios';
import { InputText } from 'primereact/inputtext';
import React, { useState, useEffect } from 'react';

import CurrentPage from './state_enum'
import { usePage } from './pageContext';
import { Button } from 'primereact/button';
import { Password } from 'primereact/password';
import { Card } from 'primereact/card';
import { Toast } from 'primereact/toast';

const SignupForm = ({ setCurrentPage }) => {
    const [uname, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [passwd, setPassword] = useState('');
    const [error, setError] = useState(null);
    const toast = React.useRef(null);


    const handleClick = async (e) => {
        e.preventDefault();
    
        if (!uname || !email || !passwd) {
            setError('All fields are required');
            toast.current.show({ severity: 'error', summary: 'Error', detail: error, life: 3000 });
            return;
        }
        const user = {
            username: uname,
            emailAddr: email,
            passwordString: passwd
        }
        const headers = {
            'Content-Type': 'application/json',
            // 'Authorization': 'JWT fefege...'
        }

        // const requestString = JSON.stringify(requestData); 
        let response = 200;
        try {
            response = await axios.post(window.entryPoint + '/user/addUser', user, {headers : headers});
            if (response.status === 200) {
                toast.current.show({ severity: 'success', summary: 'Sign up Successful', detail: response.data.message, life: 3000 });
                setCurrentPage(CurrentPage.Browse);

            } else {
                toast.current.show({ severity: 'error', summary: 'Sign up Failed', detail: response.data.error, life: 3000 });
            }
        } catch (error) {
            console.error(error);
            toast.current.show({ severity: 'error', summary: 'Request Failed', detail: 'Unable to reach the server', life: 3000 });
        } finally {
        }
    };

    return (
        <div className="signup-form-container" style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
            <Card title="Create user" style={{ width: '400px' }}>
                <Toast ref={toast} />
                <form onSubmit={handleClick}>
                    <div className="p-field p-grid">
                        <label htmlFor="username" className="p-col-12 p-md-2">Username</label>
                        <div className="p-col-12 p-md-10">
                            <InputText id="username" value={uname} onChange={(e) => setUsername(e.target.value)} placeholder="Enter your username" />
                        </div>
                    </div>
                    <div className="p-field p-grid">
                        <label htmlFor="email" className="p-col-12 p-md-2">Email</label>
                        <div className="p-col-12 p-md-10">
                            <InputText id="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="Enter your email" />
                        </div>
                    </div>
                    <div className="p-field p-grid">
                        <label htmlFor="password" className="p-col-12 p-md-2">Password</label>
                        <div className="p-col-12 p-md-10">
                            <Password id="password" value={passwd} onChange={(e) => setPassword(e.target.value)} feedback={false} placeholder="Enter your password" />
                        </div>
                    </div>
                    <div className="p-field">
                        <label htmlFor="Submit" className="p-col-12 p-md-2"><br/></label>
                        <Button label="Sign up" type="submit" icon="pi pi-user"/>
                    </div>
                </form>
            </Card>
        </div>
    );
};

export default SignupForm;