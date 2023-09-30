import React from "react";
import AliceCarousel from "react-alice-carousel";
import "react-alice-carousel/lib/alice-carousel.css";
import { mainCaroselData } from "./MainCaroselData";
import "../HomeCarosel/MainCarosel.css";
import { useNavigate } from "react-router-dom";

const MainCarosel = () => {
  const navigate = useNavigate();
  return (
    <div
      className="relative h-[400px] w-full flex flex-col justify-center items-center p-4"
      style={{ marginBottom: "100px", backgroundColor: "#F8F2ED" }}
    >
      <div
        className="relative"
        style={{
          marginTop: "100px",
          animation: "slideFromBottom 2s forwards",
          left: "40%",
        }}
      >
        <img
          className="max-h-[500px] max-w-[550px] object-contain"
          src="https://i.postimg.cc/43b3DX4B/Screenshot-2023-08-11-174527.png"
          alt=""
        />
      </div>
      <div
        className="absolute bottom-[25%] left-[15%]  transform animate-slideFromRight"
        style={{ animation: "slideFromRight 2s forwards" }}
      >
        <h1 className="font-mar text-6xl font-bold text-green-500 mb-2 ml-4">
          Plant make
        </h1>
        <h1 className="font-mar text-6xl font-bold text-green-500 mb-2 ml-4">
          life better
        </h1>
        <p className="font-san text-3xl text-green-500 mb-2 ml-4">
          Find Your Favorite Plants Here.
        </p>
        <button
          onClick={() => navigate("/products")}
          className="bg-green-500 hover:bg-green-600 text-white font-semibold py-2 mt-5 px-4 rounded-lg ml-4"
        >
          Shop Now
        </button>
      </div>
    </div>
  );
};

export default MainCarosel;
