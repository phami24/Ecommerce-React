import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";


const Cart = ({cart, setCart}) => {
  const navigate = useNavigate();
 

  const handleCheckout = () => {
    navigate("/checkout?step=2");
  };

  const [price, setPrice] = useState(0);

  return (
    <div className="mx-16 my-14">
      <section className="">
        <span className="text-[14px] text-gray-500 ">Home / </span>
        <span className="text-[15px] text-gray-500">Cart</span>{" "}
      </section>

      <div className="font-mar w-full">
        <table className="w-full">
          <thead className="w-full">
            <tr className="text-3xl my-10 ">
              <th className="p-6">Product</th>
              <th>Price</th>
              <th>Quantity</th>
              <th>Subtotal</th>
              <th></th>
            </tr>
          </thead>
          <tbody className="w-full">
            {/* {.map((item) => {
              <tr className="px-6">
              <td className="w-auto border">
                <div className="flex items-center">
                  <img
                    className="w-[4.6rem]"
                    src={item.imageUrl}
                    alt=""
                  />
                  <p className="text-[1.4rem]">{item.title}</p>
                </div>
              </td>

              <td className=" justify-center text-center border text-gray-400 text-[1.4rem]">
                <p clas>$72</p>
              </td>
              <td className=" justify-center text-center border text-[1.4rem]">
                <input
                  className="outline-none border cursor-pointer px-9 w-[6.6rem] h-[3rem]"
                  type="text"
                  name=""
                  id=""
                />
                <InputAdornment>
                </InputAdornment>
              </td>
              <td className=" justify-center text-center border text-gray-400 text-[1.4rem]">
                $72
              </td>
              <td className=" justify-center text-center border text-[1.4rem]">
                <CloseIcon />
              </td>
            </tr>
            })} */}
          </tbody>
        </table>
      </div>

      {/* <div className="lg:grid grid-cols-3 lg:px-16 relative">
        <div className="col-span-2">
          {[1, 1, 1, 1].map((item) => (
            <CartItem />
          ))}
        </div>
        <div className="px-5 sticky top-0 h-[100vh] mt-5 lg:mt-0">
          <div className=" border">
            <p className="uppercase opacity-60 font-bold pb-4">Price Details</p>
            <hr />
            <div className="space-y-3 font-semibold mb-10">
              <div className="flex justify-between pt-3 text-black">
                <span>Price</span>
                <span className="text-green-600">$123</span>
              </div>
              <div className="flex justify-between pt-3 ">
                <span>Discount</span>
                <span className="text-green-600">$23</span>
              </div>
              <div className="flex justify-between pt-3 text-black">
                <span>Delivery</span>
                <span>Free</span>
              </div>
              <div className="transition-transform tran flex justify-between pt-3 text-black font-bold">
                <span>Total Amount</span>
                <span>$123</span>
              </div>
            </div>
            <Button
              onClick={handleCheckout}
              variant="contained"
              className="w-full mt-5"
              sx={{ px: "2rem", py: ".7rem", bgcolor: "#9155fd" }}
            >
              Checkout
            </Button>
          </div>
        </div>
      </div> */}
    </div>
  );
};

export default Cart;
