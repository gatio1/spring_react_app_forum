import logo from './logo.svg';
import './App.css';
import React, { useState, useEffect } from 'react';
import SigninForm from './signupForm';
import LoginForm from './loginForm'
import TopMenu from './topMenu'
import AddEntry from './addEntry';
import CurrentPage from './state_enum';
import ScreenContent from './screenContent'

import { PageProvider } from './pageContext'; // Import the PageProvider
import { PrimeReactProvider, PrimeReactContext } from 'primereact/api';
import "primereact/resources/themes/lara-light-cyan/theme.css";
import { InputText } from 'primereact/inputtext';
import axios from 'axios';

window.entryPoint = 'http://localhost:8080'

function App() {
  const [message, setMessage] = useState('');
  const [value1, setValue1] = useState('');
  
  // useEffect(() => {
  //   axios.get(window.entryPoint + '/api/hello')
  //     .then(response => {
  //       setMessage(response.data); // Set the response message in state
  //     })
  //     .catch(error => {
  //       console.error('There was an error!', error);
  //     });
  // }, []);
  return (
    <PrimeReactProvider>
      <PageProvider>
        <div className="App">
            <div class="grid-container">
              <TopMenu/>
              <ScreenContent/>

            </div>
        </div>
      </PageProvider>
    </PrimeReactProvider>
  );
}

export default App;
