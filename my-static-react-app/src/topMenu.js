import { Menubar } from 'primereact/menubar'; // Replace with TabMenu
import { usePage } from './pageContext';
import CurrentPage from './state_enum';
import { useState } from 'react';
import { useEffect } from 'react';
import getMenuItems from './topMenuItems'

const TopMenu = () =>
{
    const { currentPage, setCurrentPage } = usePage();
    const { unameVal, setUnameVal, passwdVal, setPasswdVal} = usePage();

    const [isAuthenticated, setIsAuthenticated] = useState(false);

    useEffect(() => {
        // Example logic to check if user is logged in (you can replace with your auth logic)
        const userIsLoggedIn = (passwdVal !== "" && unameVal !== ""); // Replace with actual authentication check
        setIsAuthenticated(userIsLoggedIn);
    }, [unameVal, passwdVal]);
    const items = getMenuItems(isAuthenticated, setCurrentPage);
    return <Menubar model={items} />
}

export default TopMenu;