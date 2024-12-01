import { Menubar } from 'primereact/menubar'; // Replace with TabMenu
import { usePage } from './pageContext';
import CurrentPage from './state_enum';
import { useState } from 'react';

const TopMenu = () =>
{
    const { currentPage, setCurrentPage } = usePage();

    const items = [
        {
            label: 'Browse',
            icon: 'pi pi-home',
            command: () => {
                setCurrentPage(CurrentPage.Browse);
            }
        },
        {
            label: 'Add entry',
            icon: 'pi pi-star',
            command: () => {
                setCurrentPage(CurrentPage.AddNew);
            }
        },
        {
            label: 'Sign up',
            icon: 'pi pi-star',
            command: () => {
                setCurrentPage(CurrentPage.Signup);
            }
        },
        {
            label: 'Log in',
            icon: 'pi pi-envelope',
            command: () => {
                setCurrentPage(CurrentPage.Login);
            }
        }
    ];
    return <Menubar model={items} />
}

export default TopMenu;