import { useState } from "react";
import { StarIcon } from "@heroicons/react/20/solid";
import { RadioGroup } from "@headlessui/react";
import { useNavigate, useLocation } from "react-router-dom";
import ReactImageMagnify from "react-image-magnify";
import AddShoppingCartIcon from "@mui/icons-material/AddShoppingCart";
import StarOutlinedIcon from "@mui/icons-material/StarOutlined";
import InstagramIcon from "@mui/icons-material/Instagram";
import PinterestIcon from "@mui/icons-material/Pinterest";
import FacebookRoundedIcon from "@mui/icons-material/FacebookRounded";
import "../ProductDetails/ProductDetails.css";
import { TextareaAutosize } from "@mui/base/TextareaAutosize";
import { useEffect } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import {
  Box,
  Button,
  Grid,
  LinearProgress,
  Rating,
  Typography,
} from "@mui/material";
const product = {
  // name: "",
  // price: "$192",
  // href: "#",
  breadcrumbs: [
    // { id: 1, name: "Men", href: "#" },
    // { id: 2, name: "Clothing", href: "#" },
  ],
  images: [
    {
      src: "https://tailwindui.com/img/ecommerce-images/product-page-02-secondary-product-shot.jpg",
      alt: "Two each of gray, white, and black shirts laying flat.",
    },
    {
      src: "https://tailwindui.com/img/ecommerce-images/product-page-02-tertiary-product-shot-01.jpg",
      alt: "Model wearing plain black basic tee.",
    },
    {
      src: "https://tailwindui.com/img/ecommerce-images/product-page-02-tertiary-product-shot-02.jpg",
      alt: "Model wearing plain gray basic tee.",
    },
    {
      src: "https://tailwindui.com/img/ecommerce-images/product-page-02-featured-product-shot.jpg",
      alt: "Model wearing plain white basic tee.",
    },
  ],
  colors: [
    { name: "White", class: "bg-white", selectedClass: "ring-gray-400" },
    { name: "Gray", class: "bg-gray-200", selectedClass: "ring-gray-400" },
    { name: "Black", class: "bg-gray-900", selectedClass: "ring-gray-900" },
  ],
  sizes: [
    { name: "S", inStock: true },
    { name: "M", inStock: true },
    { name: "L", inStock: true },
    { name: "XL", inStock: true },
  ],
  description:
    'The Basic Tee 6-Pack allows you to fully express your vibrant personality with three grayscale options. Feeling adventurous? Put on a heather gray tee. Want to be a trendsetter? Try our exclusive colorway: "Black". Need to add an extra pop of color to your outfit? Our white tee has you covered.',
  highlights: [
    "Hand cut and sewn locally",
    "Dyed with our proprietary colors",
    "Pre-washed & pre-shrunk",
    "Ultra-soft 100% cotton",
  ],
  details:
    'The 6-Pack includes two black, two white, and two heather gray Basic Tees. Sign up for our subscription service and be the first to get new, exciting colors, like our upcoming "Charcoal Gray" limited release.',
};
const reviews = { href: "#", average: 4, totalCount: 117 };

function classNames(...classes) {
  return classes.filter(Boolean).join(" ");
}

export default function ProductDetails({ data, handleClick }) {
  const infoProduct = useParams();

  const [selectedColor, setSelectedColor] = useState(product.colors[0]);
  const [selectedSize, setSelectedSize] = useState(product.sizes[2]);
  const navigate = useNavigate();
  // const location = useLocation();
  const [dataProduct, setData] = useState([]);
  // console.log(dataProduct)
  // const productId = new location.pathname.match(/\/(\d+)$/)[1];

  // const productId = new URLSearchParams(location.search)
  // let asd = productId.getAll("id")
  const path = window.location.pathname.split("/").pop();
  // const {a} = useParams();

  useEffect(() => {
    axios
      .get(`http://localhost:1303/api/products/${path}`)
      .then((res) => {
        setData(res.data);
        console.log(res.data);
      })
      .catch((error) => {
        console.error("Error fetching image data:", error);
        // Handle the error (e.g., set a default image or show an error message)
      });
  }, []);

  const handleAddToCart = () => {
    navigate("/cart");
  };
  const [toggle, setToggle] = useState(1);
  function updateToggle(id) {
    setToggle(id);
  }

  return (
    <div className="bg-white mx-50 lg:px-20">
      <div className="pt-6">
        <nav aria-label="Breadcrumb">
          <ol
            role="list"
            className="mx-auto flex max-w-2xl items-center space-x-2 px-4 sm:px-6 lg:max-w-7xl lg:px-8"
          >
            {product.breadcrumbs.map((breadcrumb) => (
              <li key={breadcrumb.id}>
                <div className="flex items-center">
                  <a
                    href={breadcrumb.href}
                    className="mr-2 text-sm font-medium text-gray-900"
                  >
                    {breadcrumb.name}
                  </a>
                  <svg
                    width={16}
                    height={20}
                    viewBox="0 0 16 20"
                    fill="currentColor"
                    aria-hidden="true"
                    className="h-5 w-4 text-gray-300"
                  >
                    <path d="M5.697 4.34L8.98 16.532h1.327L7.025 4.341H5.697z" />
                  </svg>
                </div>
              </li>
            ))}
            <li className="text-sm">
              <a
                href={product.href}
                aria-current="page" 
                className="font-medium text-gray-500 hover:text-gray-600"
              >
                {product.name}
              </a>
            </li>
          </ol>
        </nav>

        <section className="grid grid-cols-1 lg:grid-cols-2 gap-x-8 gap-y-10 px-4 pt-10">
          {/* Image gallery */}
          <div className="flex flex-col items-center">
            <div className="imageMagniyer overflow-hidden rounded-lg max-w-[30rem] max-h-[35rem]">
              <ReactImageMagnify
                {...{
                  smallImage: {
                    alt: "Wristwatch by Ted Baker London",
                    isFluidWidth: true,
                    src: dataProduct.imageUrl,
                  },
                  largeImage: {
                    src: dataProduct.imageUrl,
                    width: 500,
                    height: 500,
                  },
                  isHintEnabled: true,
                }}
              />
            </div>
            <div className="flex flex-wrap space-x-5 justify-center">
              {product.images.map((item) => (
                <div className="aspect-h-2 aspect-w-3 overflow-hidden rounded-lg max-w-[5rem] max-h-[5rem] mt-4">
                  <img
                    src={item.src}
                    alt={item.alt}
                    className="h-full w-full object-cover object-center"
                  />
                </div>
              ))}
            </div>
          </div>

          {/* Product info */}
          <div
            className="lg:col-span-1 maxt-auto max-w-2x1 px-4 pb-16 sm:px-6 lg:max-w-7x1 lg:px-8
                    lg:pb-24"
          >
            <div className="lg:col-span-2 ">
              <h1 className="text-[62px] font-marsf lg:text-x1 ">
                {dataProduct.title}
              </h1>
              <div className="pt-1">
                <StarOutlinedIcon className="text-[#cc723f]  text-xs " />
                <StarOutlinedIcon className="text-[#cc723f] text-xs" />
                <StarOutlinedIcon className="text-[#cc723f] text-xs" />
                <StarOutlinedIcon className="text-[#cc723f] text-xs" />
                <StarOutlinedIcon className="text-[#cc723f] text-xs" />
                <span className="ml-[10px] font-san text-[#797979] text-[15px]">
                  (1 customer review)
                </span>
              </div>
            </div>

            {/* Options */}
            <div className="mt-4 lg:row-span-3 lg:mt-0">
              <h2 className="sr-only">Product information</h2>
              <div className="flex space-x-5 items-center text-lg lg:text-xl text-gray-900 mt-6">
                <p className="font-semibold text-2xl">$22.00</p>
                <p className="font-san text-[15px] ">
                  Availability:
                  <span className="font-san text-[15px] text-[#797979]">
                    36 in stock
                  </span>
                </p>
              </div>

              {/* Reviews */}
              <div className="mt-6">
                <div className="flex items-center space-x-3">
                  <p className=" text-[15px] text-san text-[#797979] ">
                    This oil showcases a perfectly balanced blend of Arbequina,
                    Arbosana and Koroneiki olives from our California farmers.
                    With a fruity beginning, notes of fresh herbs and lingering
                    pepper this is our go-to finishing oil.
                  </p>
                </div>
              </div>

              <form className="mt-10 flex">
                <div className="flex items-center space-x-2 mr-4">
                  <button className="flex items-center justify-center w-8 h-8 bg-gray-200 text-gray-700 rounded-full focus:outline-none focus:ring hover:bg-gray-300">
                    <span className="font-semibold text-xl leading-none">
                      −
                    </span>
                  </button>
                  <input
                    type="number"
                    className="w-16 px-3 py-1 text-center border border-gray-300 rounded focus:outline-none focus:ring"
                  />
                  <button className="flex items-center justify-center w-8 h-8 bg-gray-200 text-gray-700 rounded-full focus:outline-none focus:ring hover:bg-gray-300">
                    <span className="font-semibold text-xl leading-none">
                      +
                    </span>
                  </button>
                </div>

                <Button
                  variant="contained"
                  color="success"
                  className="flex items-center space-x-1"
                >
                  <AddShoppingCartIcon className="text-xs" />
                  <span className="text-xs">Add to Cart</span>
                </Button>
              </form>
            </div>
            <div className="product_meta my-3 space-y-2 ">
              <span className="sku_wrapper block  space-x-2">
                <span className="text-[15px] font-semibold font-san ">
                  SKU:
                </span>
                <span className="text-[15px] font-normal">39</span>
              </span>
              <span className="posted_in block space-x-2">
                <span className="text-[15px] font-semibold font-san  ">
                  Category:
                </span>
                <span className="text-[15px] font-normal">
                  <a
                    href="https://amfissa.qodeinteractive.com/product-category/kettle/"
                    rel="tag"
                  >
                    Kettle
                  </a>
                </span>
              </span>
              <span className="tagged_as block  space-x-2 ">
                <span className="text-[15px] font-semibold font-san">Tag:</span>
                <span className="qodef-woo-meta-value text-[15px] font-normal">
                  <a
                    href="https://amfissa.qodeinteractive.com/product-tag/ladle/"
                    rel="tag"
                  >
                    Ladle
                  </a>
                </span>
              </span>
            </div>
            <div className="mt-10 space-x-2 flex ">
              <span className=" text-[15px] text-san text-[#797979] ">
                Share:
              </span>
              <div className="flex text-gray-600 space-x-2">
                <FacebookRoundedIcon className="hover:text-blue-600" />
                <InstagramIcon className="hover:text-red-500" />
                <PinterestIcon className="hover:text-red-500" />
              </div>
            </div>
          </div>
        </section>

        {/* ratings and review */}
        <section className="col-6 tab p-5">
          <ul className="flex space-x-4 ml-[165px]">
            <li
              className=" p-4 cursor-pointer hover:bg-gray-100 text-center"
              onClick={() => updateToggle(1)}
            >
              Description
            </li>
            <li
              className=" p-4 cursor-pointer hover:bg-gray-100 text-center"
              onClick={() => updateToggle(2)}
            >
              Reviews (1)
            </li>
          </ul>
          <div
            className="w-full justify-center items-center ml-44"
            style={{
              backgroundColor: "#CDB866",
              width: "1500px",
              height: "1px",
            }}
          ></div>

          <div className={toggle === 1 ? "show-content" : "content"}>
            <p className=" max-w-full ml-[180px]">
              Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam
              nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam
              erat, sed diam voluptua. At vero eos et accusam et justo duo
              dolores et ea rebum. Stet clita kasd gubergren, no sea takimata
              sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit
              amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor
              invidunt ut labore et dolore magna aliquyam erat, sed diam
              voluptua. At vero eos et accusam et justo duo dolores et ea rebum.
              Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum
              dolor sit amet.
            </p>
          </div>
          <div className={toggle === 2 ? "show-content" : "content"}>
            <div className=" max-w-full ml-[180px]">
              <h1 className="font-mar text-[28px] mb-8">
                1 review for Olive Tree Seeds
              </h1>
              <div className="flex space-x-6 ">
                <div>
                  <img
                    src="https://amfissa.qodeinteractive.com/wp-content/uploads/2021/08/spnewimg1-100x100.png"
                    alt=""
                    className="w-[70px] "
                  />
                </div>

                <div className="lg:col-span-2   ">
                  <h1 className="font-san text-[#797979]  text-[18px]">
                    Kelly Barnett
                    <span className=" ml-1 font-san text-[#898989] text-[15px]">
                      September 16, 2021
                    </span>
                  </h1>

                  <div className="mb-2">
                    <StarOutlinedIcon className="text-[#cc723f]  text-xs " />
                    <StarOutlinedIcon className="text-[#cc723f] text-xs" />
                    <StarOutlinedIcon className="text-[#cc723f] text-xs" />
                    <StarOutlinedIcon className="text-[#cc723f] text-xs" />
                    <StarOutlinedIcon className="text-[#cc723f] text-xs" />
                  </div>
                  <span className=" font-san text-[#575757] text-[18px]">
                    At oporteat detraxit tacimates has, pri dicunt facilis
                    inciderint ea, et sea regione viderer. Eos te vero errem
                    dissentiet.
                  </span>
                </div>
              </div>
              <div className="mt-[60px]">
                <div className="my-5">
                  <h1 className="font-marsf text-[28px] my-2 ">Add a review</h1>
                  <p className=" font-san text-[#575757] text-[15px]">
                    Your email address will not be published. Required fields
                    are marked *
                  </p>
                </div>
                <div className="my-5">
                  <h1 className="font-marsf text-[#000000] text-[20px]">
                    Your Rating *
                  </h1>
                  <p>
                    <StarOutlinedIcon className="text-[#cc723f]  text-xs " />
                    <StarOutlinedIcon className="text-[#cc723f] text-xs" />
                    <StarOutlinedIcon className="text-[#cc723f] text-xs" />
                    <StarOutlinedIcon className="text-[#cc723f] text-xs" />
                    <StarOutlinedIcon className="text-[#cc723f] text-xs" />
                  </p>
                </div>
                <div className="w-full space-y-3">
                  <h1 className="font-marsf text-[#000000] text-[20px]">
                    Your Review *
                  </h1>
                  <TextareaAutosize
                    minRows={10}
                    maxRows={12}
                    className="outline-none	 w-full border border-gray-300 rounded-md p-2"
                  />
                </div>
                <div className="flex space-x-20 my-5">
                  <div className="w-[50%] ">
                    <h1 className="font-marsf text-[#000000] my-2 text-[20px]">
                      Name*
                    </h1>
                    <input
                      placeholder="Your Name *"
                      type="text"
                      className=" outline-none		border-solid w-full border border-gray-300 p-3 focus:border-black border:1  p-3"
                    />
                  </div>
                  <div className="w-[50%] ">
                    <h1 className="font-marsf my-2 text-[#000000] text-[20px]">
                      Email*
                    </h1>
                    <input
                      placeholder="Your Email *"
                      type="text"
                      className=" outline-none		border-solid	  w-full border border-gray-300 p-3 focus:border-black border:1  p-3"
                    />
                  </div>
                </div>
                <div className="flex my-5  space-x-2">
                  <input type="checkbox" />
                  <span className="font-san text-[15px] text-[#797979]">
                    Save my name, email, and website in this browser for the
                    next time I comment.
                  </span>
                </div>
                <Button
                  variant="contained"
                  color="success"
                  className="flex items-center space-x-1 px-8 py-4 text-xl"
                >
                  <span>Add to Cart</span>
                </Button>
              </div>
            </div>
          </div>
        </section>

        {/* similar products */}
        {/* <section className="pt-10">
          <h1 className="py-5 font-bold text-lg">Similar Products</h1>
          <div className="flex flex-wrap space-y-5">
            {mens_shirt.map((item) => (
              <HomeSectionCard product={item} />
            ))}
          </div>
        </section> */}
      </div>
    </div>
  );
}
