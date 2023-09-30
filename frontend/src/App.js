import "./App.css";
import { Route, Router, Routes } from "react-router-dom";
import CustomerRouter from "./Routers/CustomerRouter";

function App() {
  return (
    <div className="">
      <Routes>
        <Route path='/*' element={<CustomerRouter/>}></Route>
      </Routes>
      
      <div>

        {/* <HomePage /> */}
        {/* <Product/> */}
        {/* <ProductDetails/> */}
        {/* <Cart/> */}
        {/* <Checkout /> */}
        {/* <Order/> */}
        {/* <OrderDetails/> */}
        {/* <OTPForm/> */}
        {/* <LoginForm/> */}
      </div>

    </div>
  );
}

export default App;
