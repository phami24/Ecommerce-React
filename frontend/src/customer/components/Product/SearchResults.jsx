import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import ProductCard from "./ProductCard";

export default function SearchResults() {
  const [products, setProducts] = useState([]);
  const { searchQuery } = useParams();
  const navigate = useNavigate();
  useEffect(() => {
    axios
      .get(
        `http://localhost:1303/api/products/search-by-name?name=${searchQuery}`
      )
      .then((res) => {
        setProducts(res.data);
      })
      .catch((err) => console.log(err));
  }, [searchQuery]);

  return (
    <div className="bg-white ">
      <main className="mx-auto px-4 sm:px-6 lg:px-24">
        <section aria-labelledby="products-heading" className="pb-24 pt-6">
          <h2
            id="products-heading"
            className="text-center text-3xl font-bold mb-6 text-blue-600"
          >
            Search Results For : {searchQuery}
          </h2>
          <div className="text-center">
            {products.length === 0 ? (
              <div className="flex justify-center items-center h-full">
                <p
                  className="text-gray-500 text-xl font-mar"
                  style={{ fontSize: "30px" }}
                >
                  No products available.
                </p>
              </div>
            ) : (
              <div className="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-3 gap-10">
                {products.map((itemSearch, index) => (
                  <div key={index} className="flex justify-center">
                    <ProductCard product={itemSearch} />
                  </div>
                ))}
              </div>
            )}
          </div>

          <div className="flex justify-center mt-8">
            <button
              className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded shadow"
              onClick={() => navigate("/products")}
            >
              Back
            </button>
          </div>
        </section>
      </main>
    </div>
  );
}
