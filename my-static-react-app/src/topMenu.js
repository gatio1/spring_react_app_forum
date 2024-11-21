import { Menubar } from 'primereact/menubar'; // Replace with TabMenu
import { usePage } from './pageContext';

const TopMenu = () =>
{
    const { setCurrentPage } = usePage();

    const items = [
        {
            label: 'Browse',
            icon: 'pi pi-home'
        },
        {
            label: 'Add entry',
            icon: 'pi pi-star'
        },
        {
            label: 'Sign in',
            icon: 'pi pi-star',
        },
        {
            label: 'Log in',
            icon: 'pi pi-envelope'
        }
    ];
    return <Menubar model={items} />
}

export default TopMenu;