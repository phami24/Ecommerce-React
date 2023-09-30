import React, { useEffect, useState } from "react";
import MainCarosel from "../../components/HomeCarosel/MainCarosel";
import HomeSectionCard from "../../components/HomeSectionCard/HomeSectionCard";
import HomeSignup from "../../components/HomeSignup/HomeSignup";
import HomeCategories from "../../components/HomeCategories/HomeCategories";
import ProductCard from "../../components/Product/ProductCard";
import { Grid } from "@mui/material";
export default function HomePage({ data, handleClick }) {
  return (
    <div>
      <MainCarosel />
      <div></div>
      <div className="px-20 mr-32">
        <div className="block w-full mx-20">
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-5  gap-4">
            {data.map((item) => (
              <div className="relative" key={item.id}>
                <HomeSectionCard data={item} handleClick={handleClick} />
              </div>
            ))}
          </div>
        </div>
      </div>

      <HomeSignup />
      <HomeCategories />
      {/* <Contact />
      <AboutUs /> */}
    </div>
  );
}
