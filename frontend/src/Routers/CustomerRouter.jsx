import React, { useEffect, useState } from "react";
import { Route, Routes } from "react-router-dom";
import HomePage from "../customer/pages/HomePage/HomePage";
import Cart from "../customer/components/Cart/Cart";
import Navigation from "../customer/components/Navigation/Navigations";
import Footer from "../customer/components/Footer/Footer";
import Product from "../customer/components/Product/Product";
import ProductDetails from "../customer/components/ProductDetails/ProductDetails";
import Checkout from "../customer/components/Checkout/Checkout";
import Order from "../customer/components/Order/Order";
import OrderDetails from "../customer/components/Order/OrderDetails";
import axios from "axios";
import Contact from "../customer/components/Contact/Contact";
import AboutUs from "../customer/components/AboutUs/AboutUs";
import SearchResults from "../customer/components/Product/SearchResults"; // Đã sửa đường dẫn tới SearchResults

const CustomerRouter = () => {
  const [show, setShow] = useState(true);
  const [cart, setCart] = useState([]);

  const [data, setData] = useState([]);
  const [categories, setCategories] = useState([]);
  useEffect(() => {
    axios
      .get("http://localhost:1303/api/products/latest")
      .then((res) => setData(res.data))
      .catch((err) => console.log(err));
  }, []);

  useEffect(() => {
    axios
      .get("http://localhost:1303/api/categories")
      .then((res) => setCategories(res.data))
      .catch((err) => console.log(err));
  }, []);
  const handleClick = (item) => {
    if (cart.indexOf(item) !== -1) return;
    setCart([...cart, item]);
  };

  const handleChange = (item, d) => {
    const ind = cart.indexOf(item);
    const arr = cart;
    arr[ind].amount += d;

    if (arr[ind].amount === 0) arr[ind].amount = 1;
    setCart([...arr]);
  };

  return (
    <div>
      <div>
        <Navigation
          cart={cart}
          setCart={setCart}
          handleChange={handleChange}
          setShow={setShow}
          size={cart.length}
        />
      </div>
      <Routes>
        <Route
          path="/login"
          element={<HomePage data={data} handleClick={handleClick} />}
        ></Route>
        <Route
          path="/register"
          element={<HomePage data={data} handleClick={handleClick} />}
        ></Route>

        {show ? (
          <Route
            path="/"
            element={<HomePage data={data} handleClick={handleClick} />}
          ></Route>
        ) : (
          <Route path="/cart" element={<Cart />}></Route>
        )}
        <Route
          path="/cart"
          element={
            <Cart cart={cart} setCart={setCart} handleChange={handleChange} />
          }
        ></Route>
        <Route path="/contact" element={<Contact />}></Route>
        <Route path="/about" element={<AboutUs />}></Route>
        <Route
          path="/products"
          element={
            <Product categories={categories} handleClick={handleClick} />
          }
        ></Route>

        <Route path="/search/:searchQuery" element={<SearchResults />} />

        <Route path="/product/:id" element={<ProductDetails />}></Route>
        <Route path="/checkout" element={<Checkout />}></Route>
        <Route path="/account/order" element={<Order />}></Route>
        <Route
          path="/account/order/:orderId"
          element={<OrderDetails />}
        ></Route>
      </Routes>
      <div>
        <Footer />
      </div>
    </div>
  );
};

export default CustomerRouter;
