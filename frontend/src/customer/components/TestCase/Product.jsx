import React from "react";
import axios from 'axios'
import { useCart } from "./CartContext";

const Product = ({ product }) => {
  const { fetchCartItems } = useCart();

  const addToCart = async () => {
    try {
      await axios.post("/api/cart/add", { productId: product.id, quantity: 1 }); // Replace with your API endpoint
      fetchCartItems();
    } catch (error) {
      console.error("Error adding to cart:", error);
    }
  };

  return (
    <div>
      <div>
        <h2>Product: {product.name}</h2>
        <p>{product.price}</p>
      </div>
    </div>
  );
};

export default Product;
