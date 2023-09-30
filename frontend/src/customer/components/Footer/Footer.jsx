import { Button, Grid, Link, Typography } from "@mui/material";
import React from "react";
import InstagramIcon from "@mui/icons-material/Instagram";
import PinterestIcon from "@mui/icons-material/Pinterest";
import FacebookRoundedIcon from "@mui/icons-material/FacebookRounded";

const Footer = () => {
  return (
    <div className="bg-organic font-mar mx-28 text-xl">
      <Grid
        className="bg-black text-white text-center mt-10"
        container
        sx={{ bgcolor: "white", color: "black", py: 3 }}
      >
        <Grid item sx={12} sm={6} md={6}>
          <div className="ml-40 flex flex-col mt-14  font-mar">
            <div className="float-left">
              <div className="text-left text-2xl mb-3">Get in touch!</div>
              <div className="text-left text-orange-400">
                example@emxample.com
              </div>
            </div>

            <div>
              <img
                src="https://amfissa.qodeinteractive.com/wp-content/uploads/2021/07/footer-img1.png"
                alt=""
              />
            </div>
          </div>
        </Grid>

        <Grid item sx={12} sm={6} md={3}>
          {/* <Typography className="pb-5" variant="h6">
  </Typography> */}
          <div className="font-mar text-left mt-14 ml-8">
            <div className="font-marsf text-[24px] mb-2  mr-10">
              Cxustomer Service
            </div>
            <div className="ml-5 text-lg">
              <ul className="list-outside space-y-2  list-disc">
                <li className="text-orange-500">
                  <p className=" hover:text-orange-500 transition-all duration-75  cursor-pointer font-san text-[18px] text-[#575757]">
                    About
                  </p>
                </li>
                <li className="text-orange-500">
                  <p className="hover:text-orange-500 transition-all duration-75  cursor-pointer font-san text-[18px] text-[#575757]">
                    Shops
                  </p>
                </li>
                <li className="text-orange-500">
                  <p className="hover:text-orange-500 transition-all duration-75  cursor-pointer font-san text-[18px] text-[#575757]">
                    Home
                  </p>
                </li>
                <li className="text-orange-500">
                  <p className="hover:text-orange-500 transition-all duration-75  cursor-pointer font-san text-[18px] text-[#575757]">
                    Blog
                  </p>
                </li>
                <li className="text-orange-500">
                  <p className="hover:text-orange-500 transition-all duration-75  cursor-pointer font-san text-[18px] text-[#575757]">
                    Contact Us
                  </p>
                </li>
              </ul>
            </div>
          </div>
        </Grid>

        <Grid item sx={12} sm={6} md={3}>
          <div className="font-mar text-left  mt-14 ml-8">
            <div className="text-2xl mb-3">Our Socials </div>
            <div className="text-gray-600  font-san text-[16px] mb-4">
              Follow us on social media and keep up with our latest posts
            </div>
            <div className="flex text-gray-600 space-x-2">
              <FacebookRoundedIcon className="hover:text-blue-600" />
              <InstagramIcon className="hover:text-red-500" />
              <PinterestIcon className="hover:text-red-500" />
            </div>
          </div>
        </Grid>
      </Grid>
      <div className="ml-[160px] mt-28 w-full flex items-center">
        <div className="flex items-center space-x-1">
          <span className="text-2xl font-semibold text-green-500">Plant</span>
          <span className="text-2xl font-semibold text-brown-500">Nest</span>
        </div>
        <div className="bg-orange-300 h-[2px] w-[65rem] mx-4"></div>
        <span className="text-[15px] font-san text-[#757575]">
          © 2021 Qode Interactive. All Rights Reserved
        </span>
      </div>
    </div>
  );
};

export default Footer;
