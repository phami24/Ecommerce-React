import React, { useEffect, useHistory } from "react";
import ProductCard from "./ProductCard";
import { useState } from "react";
import Slider from "@mui/material/Slider";
import { Radio, RadioGroup } from "@mui/material";
import FilterAltIcon from "@mui/icons-material/FilterAlt";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
import { Stack, Pagination } from "@mui/material";
import "../Product/Product.css";

const sortOptions = [
  // { name: 'Most Popular', href: '#', current: true },
  // { name: 'Best Rating', href: '#', current: false },
  // { name: 'Newest', href: '#', current: false },
  { name: "Sort", value: null, current: true },
  { name: "Price: Low to High", value: "priceLow", current: false },
  { name: "Price: High to Low", value: "priceHigh", current: false },
  { name: "A - Z", value: "az", current: false },
  { name: "Z - A", value: "za", current: false },
];

export default function Product({ categories, handleClick }) {
  const location = useLocation();
  const navigate = useNavigate();

  const [selectedCategory, setSelectedCategory] = useState(null);
  const [products, setProducts] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const productsPerPage = 6;
  const [totalPages, setTotalPages] = useState(1);
  const [minPrice, setMinPrice] = useState(0);
  const [maxPrice, setMaxPrice] = useState(10000);
  const [selectedSort, setSelectedSort] = useState(null);
  const [searchQuery, setSearchQuery] = useState("");

  useEffect(() => {
    const params = {
      pageNumber: currentPage,
      pageSize: productsPerPage,
      category: selectedCategory,
      minPrice: minPrice,
      maxPrice: maxPrice,
      sort: selectedSort,
    };

    axios
      .get("http://localhost:1303/api/products", { params })
      .then((res) => {
        setProducts(res.data);

        setTotalPages(res.data.totalPages);
      })
      .catch((err) => console.log(err));
  }, [currentPage, selectedCategory, minPrice, maxPrice, selectedSort]);
console.log(products)
  const handlePageChange = (event, newPage) => {
    setCurrentPage(newPage);
  };

  const handleFilter = (value) => {
    setSelectedCategory(value);
    updateUrlParams({ category: value });
  };

  const handleChange = (event, newEvent) => {
    setMinPrice(newEvent[0]);
    setMaxPrice(newEvent[1]);
  };

  const handleSort = (value) => {
    setSelectedSort(value);
    updateUrlParams({ sort: value });
  };
  const handleSearchChange = (event) => {
    setSearchQuery(event.target.value);
  };

  const handleSearchSubmit = (event) => {
    event.preventDefault();
    navigate(`/search/${searchQuery}`);
  };

  const updateUrlParams = (paramsObj) => {
    const searchParams = new URLSearchParams(location.search);
    searchParams.set("category", selectedCategory);
    searchParams.set("minPrice", minPrice);
    searchParams.set("maxPrice", maxPrice);
    searchParams.set("sort", selectedSort);

    Object.entries(paramsObj).forEach(([key, value]) => {
      if (value !== null) {
        searchParams.set(key, value);
      } else {
        searchParams.delete(key);
      }
    });

    const query = searchParams.toString();
    navigate({ search: `?${query}` });
  };

  return (
    <div className="bg-white">
      <main className="mx-auto px-4 sm:px-6 lg:px-24">
        <div className="flex items-baseline justify-between border-b border-gray-200 pb-6 pt-24">
          <h1 className="text-4xl font-bold tracking-tight text-gray-900">
            Categories
          </h1>
          <form className="hidden lg:block">
            <div className="space-y-4">
              <select
                className="px-3 py-2 border rounded-md shadow-sm focus:outline-none focus:ring-1 focus:ring-green-500 bg-white text-gray-600" // Customize focus color
                value={selectedSort}
                onChange={(e) => handleSort(e.target.value)}
              >
                {sortOptions.map((option) => (
                  <option
                    key={option.value}
                    value={option.value}
                    className="hover:bg-green-500 hover:text-white" // Add hover styles
                  >
                    {option.name}
                  </option>
                ))}
              </select>
            </div>
          </form>
        </div>
        <section aria-labelledby="products-heading" className="pb-24 pt-6">
          <h2 id="products-heading" className="sr-only">
            Products
          </h2>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-10">
            {/* Filter section */}
            <div className="col-span-1">
              <div className="py-5 flex justify-between items-center">
                <h1 className="text-lg opacity-50 font-bold">
                  Filter <FilterAltIcon />
                </h1>
              </div>
              <form className="hidden lg:block">
                <div className="space-y-4">
                  <h2 className="text-sm font-semibold text-gray-500">
                    Category
                  </h2>
                  <div className="space-y-2">
                    <RadioGroup value={selectedCategory}>
                      {categories.map((category) => (
                        <label
                          key={category.id}
                          className="flex items-center cursor-pointer"
                        >
                          <Radio
                            className="mr-2"
                            value={category.name}
                            onChange={() => handleFilter(category.name)}
                            sx={{
                              color:
                                selectedCategory === category.name
                                  ? "#34D399"
                                  : "#6B7280", // Custom colors for checked and unchecked state
                              "&.Mui-checked": {
                                color: "#34D399",
                              },
                            }}
                          />
                          <span>{category.name}</span>
                        </label>
                      ))}
                    </RadioGroup>
                  </div>
                  <h2 className="text-sm font-semibold text-gray-500">Price</h2>
                  <div className="flex items-center space-x-4">
                    <div className=" items-center space-x-4">
                      <Slider
                        value={[minPrice, maxPrice]}
                        onChange={handleChange}
                        valueLabelDisplay="auto"
                        aria-label="range-slider"
                        min={0}
                        step={50}
                        max={10000}
                        sx={{
                          maxWidth: "250px",
                          width: "250px",
                          color: "#34D399", // Custom color for slider
                        }}
                      />
                      <div className="text-sm text-gray-600 mt-2">
                        <span className="block">Min Price: ${minPrice}</span>
                        <span className="block">Max Price: ${maxPrice}</span>
                      </div>
                    </div>
                  </div>
                </div>
                {/* Search Input */}
                <div className="flex items-center space-x-4 mt-10">
                  <input
                    type="text"
                    placeholder="Search..."
                    className="px-3 py-2 border rounded-md shadow-sm focus:outline-none focus:ring-1 focus:ring-blue-500"
                    value={searchQuery}
                    onChange={handleSearchChange}
                  />
                  <button
                    type="submit"
                    className="px-3 py-2 border rounded-md shadow-sm bg-blue-500 text-white hover:bg-blue-600 focus:outline-none focus:ring-1 focus:ring-blue-500"
                    onClick={handleSearchSubmit}
                  >
                    Search
                  </button>
                </div>
              </form>
            </div>

            {/* Product list */}
            <div className="col-span-2 grid grid-cols-1 md:grid-cols-4 lg:grid-cols-3 xl:grid-cols-3 gap-6">
              {products.length === 0 ? (
                <div className="flex items-center justify-center w-max h-full">
                  <p className="custom-message">No products available.</p>
                </div>
              ) : (
                products.map((item, index) => (
                  <div
                    key={index}
                    className="w-full md:w-[calc(100%/4)] lg:w-[calc(100%/3)] xl:w-[calc(100%/3)] flex items-center justify-center"
                  >
                    <ProductCard product={item} handleClick={handleClick} />
                  </div>
                ))
              )}
              {/* Pagination */}
              {products.length > 0 && searchQuery === "" && (
                <div className="col-span-2 flex items-center justify-center mt-6">
                  <Stack spacing={2} direction="row">
                    <Pagination
                      count={totalPages}
                      page={currentPage}
                      onChange={handlePageChange}
                      variant="outlined"
                      shape="rounded"
                      sx={{
                        "& button.Mui-selected": { backgroundColor: "#34D399" },
                      }} // Custom color for selected pagination button
                    />
                  </Stack>
                </div>
              )}
            </div>
          </div>
        </section>
      </main>
    </div>
  );
}
