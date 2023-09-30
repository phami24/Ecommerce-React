import React, { useState, useEffect } from "react";
import StarOutlinedIcon from "@mui/icons-material/StarOutlined";
import ShoppingCartOutlinedIcon from "@mui/icons-material/ShoppingCartOutlined";
import AddShoppingCartIcon from "@mui/icons-material/AddShoppingCart";
import { Rating } from "@mui/material";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const HomeSectionCard = ({ data, handleClick }) => {
  const navigate = useNavigate();
  const [averageRating, setAverageRating] = useState(0);

  useEffect(() => {
    axios
      .get(`http://localhost:1303/api/ratings/average-rating/${data.id}`)
      .then((res) => {
        console.log(res.data);
        setAverageRating(res.data);
      })
      .catch((err) => console.log(err));
  }, [data.id]);
  return (
    <div>
     <div
        className=" h-[500px] mb-4
      relative overflow-hidden group transition "
      >
        <div
          onClick={() => navigate(`/product/${data.id}`)}
          className="cursor-pointer"
        >
          <div className="w-[260px] group mx-auto flex justify-center items-center">
            <img
              className="max-h[200px] group-hover: scale-110 transform  transition-transform duration-300"
              src={data.imageUrl}
              alt=""
            />
          </div>
          
          <div className="relative h-32 flex flex-col items-center justify-center group-hover:opacity-100 transition-all duration-500 px-4">
            <h1 className="font-roboto text-xl text-[#6D6A6A] relative">
              <a href="#" className="group  text-black">
                {data.title}
                <span class="text-sm block max-w-0 group-hover:max-w-full transition-all duration-1000 h-0.5 bg-black ease-in-out"></span>
              </a>
            </h1>
            <div>
              <Rating name="read-only" value={averageRating} precision={0.5} readOnly />
            </div>
            <p className="  text-lg font-medium  font-san text-black">
              ${data.price / 100}
            </p>
          </div>
        </div>
        <div
            onClick={() => handleClick(data)}
            className="absolute top-0 right-0 p-3 flex text-gray-300 group-hover:text-black transition-all duration-600"
          >
            <ShoppingCartOutlinedIcon
              sx={{ fontSize: "34px" }}
              className="text-xl mx-1"
            />
          </div>
      </div>
      
    </div>
  );
};

export default HomeSectionCard;
