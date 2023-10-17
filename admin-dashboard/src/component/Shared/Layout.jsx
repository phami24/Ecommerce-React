import { React } from 'react'
import { Outlet, Link } from 'react-router-dom'

export default function Layout() {
    return (
        <div>
            <div className='bg-sky-100'>Sidebar</div>
            <div className='bg-teal-200'>Header</div>
            <div>
                <Outlet />
            </div>
        </div>
    )
}
