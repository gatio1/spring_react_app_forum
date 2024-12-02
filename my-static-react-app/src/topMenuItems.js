import CurrentPage from './state_enum';
import { usePage } from './pageContext';

const getMenuItems = (isAuthenticated, setCurrentPage) => {
    if (isAuthenticated) {
        return [
            {
                label: 'Browse',
                icon: 'pi pi-home',
                command: () => setCurrentPage(CurrentPage.Browse)
            },
            {
                label: 'Add entry',
                icon: 'pi pi-star',
                command: () => setCurrentPage(CurrentPage.AddNew)
            },
            {
                label: 'User Info',
                icon: 'pi pi-user',
                command: () => setCurrentPage(CurrentPage.UserInfo)
            },
            {
                label: 'Logout',
                icon: 'pi pi-sign-out',
                command: () => setCurrentPage(CurrentPage.Logout)
            }
        ];
    } else {
        return [
            {
                label: 'Browse',
                icon: 'pi pi-home',
                command: () => setCurrentPage(CurrentPage.Browse)
            },
            {
                label: 'Add entry',
                icon: 'pi pi-star',
                command: () => setCurrentPage(CurrentPage.AddNew)
            },
            {
                label: 'Sign up',
                icon: 'pi pi-user-plus',
                command: () => setCurrentPage(CurrentPage.Signup)
            },
            {
                label: 'Log in',
                icon: 'pi pi-sign-in',
                command: () => setCurrentPage(CurrentPage.Login)
            }
        ];
    }
};

export default getMenuItems;