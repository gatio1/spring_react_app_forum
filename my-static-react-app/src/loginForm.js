import axios from 'axios';
import { InputText } from 'primereact/inputtext';
import React, { useState, useEffect } from 'react';

import CurrentPage from './state_enum';
import { usePage } from './pageContext';
import { Button } from 'primereact/button';
import { Password } from 'primereact/password';
import { Card } from 'primereact/card';
import { Toast } from 'primereact/toast';

const LoginForm = () => {
    const [email, setEmail] = useState('');
    const [passwd, setPassword] = useState('');
    const [error, setError] = useState(null);
    const toast = React.useRef(null);

    const { currentPage, setCurrentPage } = usePage(); 

    const handleClick = async (e) => {
        e.preventDefault();
    
        if (email || !passwd) {
            setError('All fields are required');
            toast.current.show({ severity: 'error', summary: 'Error', detail: error, life: 3000 });
            return;
        }
        const requestData = {
            email: email,
            passwd: passwd
          };
        const requestString = JSON.stringify(requestData); 
        let response = 200;
        try {
            response = await axios.post(window.entryPoint + '/user/login', 'json=' + requestString);
            document.write(response);
            if (response.status === 200) {
                toast.current.show({ severity: 'success', summary: 'Sign in Successful', detail: response.data.message, life: 3000 });
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
        <div className="Login-form-container" style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
            <Card title="Authenticate user" style={{ width: '400px' }}>
                <Toast ref={toast} />
                <form onSubmit={handleClick}>
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
                        <Button label="Log in" type="submit" icon="pi pi-user"/>
                    </div>
                </form>
            </Card>
        </div>
    );
};

export default LoginForm;