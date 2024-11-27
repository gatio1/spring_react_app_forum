
import React, { useState } from "react";
import { Editor } from 'primereact/editor';

import { usePage } from './pageContext';
import { Chips } from 'primereact/chips';
import { Button } from 'primereact/button';
import { Password } from 'primereact/password';
import { Card } from 'primereact/card';
import { Toast } from 'primereact/toast';

const AddEntry = () =>
{
    const [text, setText] = useState('');
    const [title, setTitle] = useState('');
    const toast = React.useRef(null);
    const { currentPage, setCurrentPage } = usePage(); 
    const handleClick = () =>
    
    {
        return 0;
    }
    return (
        <div className="card" style={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh'}}>
            <Card title="Enter a post" style={{ width: '70%', align: 'center'}}>
                <Toast ref={toast} />
                <form onSubmit={handleClick}>
                    <div className="p-field p-grid">
                        <label htmlFor="Title" className="p-col-12 p-md-2">Title</label>
                        <div className="p-col-12 p-md-10">
                            <Chips id="Title" value={title} onChange={(e) => setTitle(e.target.value)} placeholder="Enter title" />
                        </div>
                    </div>
                    <div className="p-field p-grid">
                        <label htmlFor="password" className="p-col-12 p-md-2">Password</label>
                        <div className="p-col-12 p-md-10">
                            <Editor value={text} onTextChange={(e) => setText(e.htmlValue)} style={{ height: '320px' }} />
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