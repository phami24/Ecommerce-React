import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Layout from './component/Shared/Layout'
import Dashboard from './component/Dashboard'
import Products from './component/Products'
export default function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout />}>
                    <Route index element={<Dashboard />} />
                    <Route path="products" element={<Products />} />
                </Route>
                <Route path="login" element={<div>This is Admin Login Page</div>}></Route>
            </Routes>
        </BrowserRouter>
    )
}
