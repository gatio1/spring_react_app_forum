
import React, { createContext, useState, useContext } from 'react';
import CurrentPage from './state_enum'; // Import the enum for page states

// Create the context
const PageContext = createContext();

// Create the provider to wrap your application
export const PageProvider = ({ children }) => {
  // State to hold the current page
  const [currentPage, setCurrentPage] = useState(''); // Default to 'Login' page

  return (
    <PageContext.Provider value={{ currentPage, setCurrentPage }}>
      {children}
    </PageContext.Provider>
  );
};

// Custom hook to access the currentPage and setCurrentPage in any component
export const usePage = () => {
  return useContext(PageContext);
};