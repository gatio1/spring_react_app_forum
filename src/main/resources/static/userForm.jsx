import React, { useState, useEffect } from 'react';
import { InputText } from 'primereact/inputtext';
import axios from 'axios';

function App() {
  const [message, setMessage] = useState('');

  useEffect(() => {
    axios.get('http://localhost:8080/api/hello')
      .then(response => {
        setMessage(response.data); // Set the response message in state
      })
      .catch(error => {
        console.error('There was an error!', error);
      });
  }, []);

  return (
    <div className="App">
        <InputText value={value} onChange={(e) => setValue(e.target.value)} />
        <InputText value={value} onChange={(e) => setValue(e.target.value)} />
        <InputText value={value} onChange={(e) => setValue(e.target.value)} />
    </div>
  );
}

export default App;