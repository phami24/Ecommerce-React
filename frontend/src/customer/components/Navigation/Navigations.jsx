import { Fragment, useEffect, useState } from "react";
import { Dialog, Popover, Tab, Transition } from "@headlessui/react";
import { Bars3Icon, XMarkIcon } from "@heroicons/react/24/outline";
import RemoveShoppingCartIcon from "@mui/icons-material/RemoveShoppingCart";
import axios from "axios";

import ShoppingCartOutlinedIcon from "@mui/icons-material/ShoppingCartOutlined";
import { navigationData } from "./navigationData";
import { useLocation, useNavigate } from "react-router-dom";
import { NavLink } from "react-router-dom";
import {
  Avatar,
  Box,
  Button,
  Drawer,
  Grid,
  Menu,
  MenuItem,
} from "@mui/material";
import { deepOrange } from "@mui/material/colors";

import AuthModal from "../../Auth/AuthModal";
import { getUser, logout } from "../../../State/Auth/Action";
import { useDispatch, useSelector } from "react-redux";
import AccountCircleIcon from "@mui/icons-material/AccountCircle";

import * as React from "react";

function classNames(...classes) {
  return classes.filter(Boolean).join(" ");
}

export default function Navigation({ size }) {
  const [open, setOpen] = useState(false);
  const navigate = useNavigate();
  const [cart, setCart] = useState(null);

  const [openAuthModel, setOpenAuthModal] = useState(false);
  const [anchorEl, setAnchorEl] = useState(null);
  const openUserMenu = Boolean(anchorEl);
  const jwt = localStorage.getItem("jwt");
  const { auth } = useSelector((store) => store);
  const dispatch = useDispatch();
  const location = useLocation();

  const [quantity, setQuantity] = useState();

  useEffect(() => {
    fetchCart();
  }, []);

  const fetchCart = async () => {
    try {
      const response = await axios.get("http://localhost:/api/cart/get", {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("jwt")}`,
        },
      }); // Adjust the URL based on your API setup
      setCart(response.data);
    } catch (error) {
      console.error("Error fetching cart:", error);
    }
  };

  const [price, setPrice] = useState(0);
  const isLinkActive = (linkPath) => {
    return location.pathname === linkPath ? "active" : "";
  };
  /** method handle cart tab END */

  const [state, setState] = useState({
    top: false,
    left: false,
    bottom: false,
    right: false,
  });

  const toggleDrawer = (anchor, open) => (event) => {
    if (
      event.type === "keydown" &&
      (event.key === "Tab" || event.key === "Shift")
    ) {
      return;
    }

    setState({ ...state, [anchor]: open });
  };

  const list = (anchor) => (
    <Box
      sx={{
        width: 400,
        bgcolor: "depth",
      }}
      role="presentation"
      // onClick={toggleDrawer(anchor, false)}
      onKeyDown={toggleDrawer(anchor, false)}
    >
      <div className=" font-mar m-14 ">
        <div>
          <p className="text-3xl mb-4">Shopping Bag</p>
          <div className="">
            {/* {cart.map((item) => ( */}
            <Grid container className="w-40 group">
              <Grid item xs={12} sm={12} md={3} className="">
                <img
                  className=""
                  src="https://htmldemo.net/phuler/phuler/assets/img/product/product-2.jpg"
                  // {item.imageUrl}
                  alt=""
                />
              </Grid>
              <Grid
                sx={{ display: "flex", alignItems: "center" }}
                item
                xs={12}
                sm={6}
                md={7}
                className=""
              >
                <div className="">
                  {/* <h6 className="">Best Tree of Month</h6> */}
                  <a href="#" class="  text-black ">
                    {/* {item.title} */} Rose
                    <span class="text-sm block max-w-0 group-hover:max-w-full transition-all duration-1000  h-[0.3px] bg-black ease-in-out"></span>
                  </a>
                  <p>
                    $ 12.5
                    {/* {item.price / 100} */}
                  </p>

                  <p className="text-gray-500 text-sm">
                    Quantity: 2{/* {item.amount} */}
                  </p>
                </div>
              </Grid>
              <Grid
                item
                sx={{ float: "right" }}
                xs={12}
                sm={6}
                md={2}
                className=""
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  strokeWidth={1.5}
                  stroke="currentColor"
                  className="w-6 h-6"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M6 18L18 6M6 6l12 12"
                  />
                </svg>
              </Grid>
            </Grid>
            {/* ))} */}
            <div className="flex mt-10">
              <div className=" flex w-full">
                <p className="text-2xl">Total: </p>
              </div>
              <div>
                <p className="text-2xl float-right">{price / 100}</p>
              </div>
            </div>
            <div className="mt-28 space-y-5">
              <div className="">
                <button class=" w-full relative border-2 border-gray-800 bg-transparent px-5 py-2.5 font-medium uppercase text-white transition-colors before:absolute before:top-0 before:left-0 before:-z-10 before:h-full before:w-full before:origin-top-left before:scale-y-100 before:bg-black before:transition-transform before:duration-300 before:content-[''] hover:text-black before:hover:scale-y-0">
                  CHECKOUT
                </button>
              </div>

              <div>
                <button
                  onClick={() => {
                    navigate(`/cart`);
                  }}
                  class="w-full relative border-2 border-gray-800 bg-transparent px-5 py-2.5 font-medium uppercase text-white transition-colors before:absolute before:top-0 before:left-0 before:-z-10 before:h-full before:w-full before:origin-top-left before:scale-y-100 before:bg-black before:transition-transform before:duration-300 before:content-[''] hover:text-black before:hover:scale-y-0"
                >
                  VIEW CART
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </Box>
  );

  const handleUserClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleCloseUserMenu = (event) => {
    setAnchorEl(null);
  };

  const handleOpen = () => {
    setOpenAuthModal(true);
  };
  const handleClose = () => {
    setOpenAuthModal(false);
  };

  const handleCategoryClick = (category, section, items) => {
    navigate(`/${category.id}/${section.id}/${items.name}`);
    // close();
  };

  useEffect(() => {
    if (jwt) {
      dispatch(getUser(jwt));
    }
  }, [jwt, auth.jwt]);
  useEffect(() => {
    if (auth.user) {
      handleClose();
    }
    if (location.pathname === "/login" || location.pathname === "/register") {
      navigate(-1);
    }
  }, [auth.user]);

  const handleLogout = () => {
    dispatch(logout());
    handleCloseUserMenu();
    localStorage.clear();
  };
  const Navbar = () => {
    const activeLink = "bg-blue-100 text-black";
    const normalLink = "";
  };

  return (
    <div className="bg-white">
      {/* Mobile menu */}
      <Transition.Root show={open} as={Fragment}>
        <Dialog as="div" className="relative z-40 lg:hidden" onClose={setOpen}>
          <Transition.Child
            as={Fragment}
            enter="transition-opacity ease-linear duration-300"
            enterFrom="opacity-0"
            enterTo="opacity-100"
            leave="transition-opacity ease-linear duration-300"
            leaveFrom="opacity-100"
            leaveTo="opacity-0"
          >
            <div className="fixed inset-0 bg-black bg-opacity-25" />
          </Transition.Child>

          <div className="fixed inset-0 z-40 flex">
            <Transition.Child
              as={Fragment}
              enter="transition ease-in-out duration-300 transform"
              enterFrom="-translate-x-full"
              enterTo="translate-x-0"
              leave="transition ease-in-out duration-300 transform"
              leaveFrom="translate-x-0"
              leaveTo="-translate-x-full"
            >
              <Dialog.Panel className="relative flex w-full max-w-xs flex-col overflow-y-auto bg-white pb-12 shadow-xl">
                <div className="flex px-4 pb-2 pt-5">
                  <button
                    type="button"
                    className="relative -m-2 inline-flex items-center justify-center rounded-md p-2 text-gray-400"
                    onClick={() => setOpen(false)}
                  >
                    <span className="absolute -inset-0.5" />
                    <span className="sr-only">Close menu</span>
                    <XMarkIcon className="h-6 w-6" aria-hidden="true" />
                  </button>
                </div>

                {/* Links */}
                <Tab.Group as="div" className="mt-2">
                  <div className="border-b border-gray-200">
                    <Tab.List className="-mb-px flex  space-x-8 px-4">
                      {navigationData.categories.map((category) => (
                        <Tab
                          key={category.name}
                          className={({ selected }) =>
                            classNames(
                              selected
                                ? "border-indigo-600 text-indigo-600"
                                : "border-transparent text-gray-900",
                              "flex-1 whitespace-nowrap border-b-2 px-1 py-4 text-base font-medium"
                            )
                          }
                        >
                          {category.name}
                        </Tab>
                      ))}
                    </Tab.List>
                  </div>
                  <Tab.Panels as={Fragment}>
                    {navigationData.categories.map((category) => (
                      <Tab.Panel
                        key={category.name}
                        className="space-y-10 px-4 pb-8 pt-10"
                      >
                        <div className="grid grid-cols-2 gap-x-12">
                          {category.featured.map((item) => (
                            <div
                              key={item.name}
                              className="group relative text-sm"
                            >
                              <div className="aspect-h-1 aspect-w-1 overflow-hidden rounded-lg bg-gray-100 group-hover:opacity-75">
                                <img
                                  src={item.imageSrc}
                                  alt={item.imageAlt}
                                  className="object-cover object-center"
                                />
                              </div>
                              <a
                                href={item.href}
                                className="mt-6 block font-medium text-gray-900"
                              >
                                <span
                                  className="absolute inset-0 z-10"
                                  aria-hidden="true"
                                />
                                {item.name}
                              </a>
                              <p aria-hidden="true" className="mt-1">
                                Shop now
                              </p>
                            </div>
                          ))}
                        </div>
                        {category.sections.map((section) => (
                          <div key={section.name}>
                            <p
                              id={`${category.id}-${section.id}-heading-mobile`}
                              className="font-medium text-gray-900"
                            >
                              {section.name}
                            </p>
                            <ul
                              role="list"
                              aria-labelledby={`${category.id}-${section.id}-heading-mobile`}
                              className="mt-6 flex flex-col space-y-6"
                            >
                              {section.items.map((item) => (
                                <li key={item.name} className="flow-root">
                                  <a
                                    href={item.href}
                                    className="-m-2 block p-2 text-gray-500"
                                  >
                                    {item.name}
                                  </a>
                                </li>
                              ))}
                            </ul>
                          </div>
                        ))}
                      </Tab.Panel>
                    ))}
                  </Tab.Panels>
                </Tab.Group>

                <div className="space-y-6 border-t border-gray-200 px-4 py-6">
                  {navigationData.pages.map((page) => (
                    <div key={page.name} className="flow-root">
                      <a
                        href={page.href}
                        className="-m-2 block p-2 font-medium text-gray-900"
                      >
                        {page.name}
                      </a>
                    </div>
                  ))}
                </div>

                <div className="border-t border-gray-200 px-4 py-6">
                  <a href="#" className="-m-2 flex items-center p-2">
                    <img
                      src="https://tailwindui.com/img/flags/flag-canada.svg"
                      alt=""
                      className="block h-auto w-5 flex-shrink-0"
                    />
                    <span className="ml-3 block text-base font-medium text-gray-900">
                      CAD
                    </span>
                    <span className="sr-only">, change currency</span>
                  </a>
                </div>
              </Dialog.Panel>
            </Transition.Child>
          </div>
        </Dialog>
      </Transition.Root>

      <header className="relative flex px-20 h-[6rem] bg-[#F8F2ED] items-center justify-between">
        <div>
          <button
            type="button"
            className="relative rounded-md bg-white p-2 text-gray-400 lg:hidden"
            onClick={() => setOpen(true)}
          >
            <span className="absolute -inset-0.5" />
            <span className="sr-only">Open menu</span>
            <Bars3Icon className="h-6 w-6" aria-hidden="true" />
          </button>

          {/* Logo */}
          <div
            onClick={() => navigate("/")}
            className="ml-4 flex lg:ml-0 items-center"
          >
            <span className="text-2xl font-semibold text-green-500">Plant</span>
            <span className="text-2xl font-semibold text-brown-500">Nest</span>
          </div>
        </div>
        <div
          style={{
            backgroundColor: "#CDB866",
            width: "400px",
            height: "0.1px",
          }}
        ></div>
        <div className="flex text-large  font-san">
          {/* Flyout menus */}
          <div className="flex cursor-pointer space-x-8">
  <NavLink
    to="/"
    exact
    className="hover:text-orange-500 transition-all duration-75 cursor-pointer"
    activeClassName="text-orange-500"
  >
    HOME
  </NavLink>
  <NavLink
    to="/products"
    className="hover:text-orange-500 transition-all duration-75 cursor-pointer"
    activeClassName="text-orange-500"
  >
    SHOPS
  </NavLink>
  <NavLink
    to="/about"
    className="hover:text-orange-500 transition-all duration-75 cursor-pointer"
    activeClassName="text-orange-500"
  >
    ABOUT US
  </NavLink>
  <NavLink
    to="/contact"
    className="hover:text-orange-500 transition-all duration-75 cursor-pointer"
    activeClassName="text-orange-500"
  >
    CONTACT US
  </NavLink>
</div>

        </div>
        <div
          style={{
            backgroundColor: "#CDB866",
            width: "400px",
            height: "0.1px",
          }}
        ></div>
        <div className=" flex items-center">
          {/*  */}

          <div>
            {auth.user?.firstName ? (
              <Button
                id="basic-button"
                aria-controls={openUserMenu ? "basic-menu" : undefined}
                aria-haspopup="true"
                aria-expanded={openUserMenu ? "true" : undefined}
                onClick={handleUserClick}
              >
                {auth.user.firstName[0].toUpperCase}
                <Avatar sx={{ bgcolor: deepOrange[500] }}>
                  {auth.user.firstName[0]}
                </Avatar>
              </Button>
            ) : (
              <Button
                onClick={handleOpen}
                className="text-sm font-medium text-gray-700 hover:text-gray-800"
              >
                <AccountCircleIcon className="text-gray-600 text-7xl" />
              </Button>
            )}
            <Menu
              id="basic-menu"
              anchorEl={anchorEl}
              open={openUserMenu}
              onClose={handleCloseUserMenu}
              MenuListProps={{
                "aria-labelledby": "basic-button",
              }}
            >
              <MenuItem onClick={handleCloseUserMenu}>Profile</MenuItem>
              <MenuItem onClick={() => navigate("/account/order")}>
                My account
              </MenuItem>
              <MenuItem onClick={handleLogout}>Logout</MenuItem>
            </Menu>
          </div>
          {/*  */}

          {/* Search */}
          {/* <div className="flex lg:ml-6">
  <a href="#" className="p-2 text-gray-400 hover:text-gray-500">
    <span className="sr-only">Search</span>
    <MagnifyingGlassIcon className="h-6 w-6" aria-hidden="true" />
  </a>
</div> */}
          <div className="ml-4 flow-root lg:ml-6">
            <a href="#" className="group -m-2 flex items-center p-2">
              {["right"].map((anchor) => (
                <div className="">
                  <React.Fragment key={anchor}>
                    {
                      <ShoppingCartOutlinedIcon
                        onClick={toggleDrawer(anchor, true)}
                        className="h-8 w-6 flex-shrink-0 text-gray-400 group-hover:text-gray-500"
                        aria-hidden="true"
                      />
                    }
                    <Drawer
                      anchor={anchor}
                      open={state[anchor]}
                      onClose={toggleDrawer(anchor, false)}
                    >
                      <div className="">{list(anchor)}</div>
                    </Drawer>
                  </React.Fragment>
                </div>
              ))}

              <span className="ml-2 text-sm font-medium text-gray-700 group-hover:text-gray-800">
                {size}
              </span>
              <span className="sr-only">items in cart, view bag</span>
            </a>
          </div>
        </div>
      </header>

      <AuthModal handleClose={handleClose} open={openAuthModel} />
    </div>
  );
}
