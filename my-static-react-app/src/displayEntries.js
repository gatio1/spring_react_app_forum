import React from 'react';
import { usePage } from './pageContext';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Panel } from 'primereact/panel';
import { useState, useEffect } from 'react';
import { Button } from 'primereact/button';
import axios from 'axios';
import CurrentPage from './state_enum';



const DisplayEntries = ({ data, handleButtonClick }) =>
{
    return(
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
    )
}

export default DisplayEntries;