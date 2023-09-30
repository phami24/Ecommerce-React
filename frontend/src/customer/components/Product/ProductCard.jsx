import React, { useEffect, useState } from "react";
import "./ProductCard.css";
import { useNavigate } from "react-router-dom";
import AddShoppingCartIcon from "@mui/icons-material/AddShoppingCart";
import { Rating } from "@mui/material"; // Chỉ import Rating từ @mui/material
import axios from "axios";
import ShoppingCartOutlinedIcon from '@mui/icons-material/ShoppingCartOutlined';

const ProductCard = ({ product, handleClick }) => {
  const navigate = useNavigate();
  const [averageRating, setAverageRating] = useState(0);

  useEffect(() => {
    axios
      .get(`http://localhost:1303/api/ratings/average-rating/${product.id}`)
      .then((res) => {
        console.log(res.data);
        setAverageRating(res.data);
      })
      .catch((err) => console.log(err));
  }, [product.id]);

  return (
    <div className="group relative cursor-pointer transition-shadow">
      <div
        onClick={() => navigate(`/product/${product.id}`)}
        className="relative overflow-hidden hover:shadow-lg"
      >
        <img
          src={product.imageUrl}
          alt=""
          className="object-cover"
          style={{ width: "250px", height: "300px" }}
        />
        
      </div>

      <div className="relative flex flex-col items-center justify-center group-hover:opacity-100 transition-all duration-500 px-14 h-40">
        <h1 className="title font-mar text-xl text-center text-black relative">
          <a href="#" className="group text-black">
            {product.title}
            <span className="text-sm block max-w-0 group-hover:max-w-full transition-all duration-1000 h-0.5 bg-black ease-in-out"></span>
          </a>
        </h1>
        <div>
          <Rating
            name="read-only"
            value={averageRating}
            precision={0.5}
            readOnly
          />
        </div>
        <p className="text-lg font-medium font-san text-black">
          ${product.price}
        </p>
      </div>

      <div
          className="absolute top-0 right-0 p-2 flex text-gray-300 group-hover:text-black transition-all duration-600"
          onClick={() => handleClick(product)}
        >
          <ShoppingCartOutlinedIcon sx={{ fontSize: "34px" }} className="text-xl" />
        </div>
    </div>
  );
};

export default ProductCard;
