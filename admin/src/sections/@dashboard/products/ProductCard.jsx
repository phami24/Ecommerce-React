import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Rating, Menu, MenuItem, Button, Modal } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import UpdateProductForm from './UpdateProductForm';

const ProductCard = ({ product, onDeleteSuccess, setProducts }) => {
  const navigate = useNavigate();
  const [anchorEl, setAnchorEl] = useState(null);
  const [averageRating, setAverageRating] = useState(0);
  const [showUpdateForm, setShowUpdateForm] = useState(false);

  useEffect(() => {
    axios
      .get(`http://localhost:1303/api/ratings/average-rating/${product.id}`)
      .then((res) => {
        setAverageRating(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [product.id]);

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const handleUpdate = () => {
    setShowUpdateForm(true);
    handleClose();
  };
  const closeModal = () => {
    setShowUpdateForm(false);
  };

  const handleDelete = () => {
    onDeleteSuccess(product.title);
    // Gọi API hoặc hàm xóa sản phẩm ở đây
    axios
      .delete(`http://localhost:1303/api/products/${product.id}`)
      .then((res) => {
        // Gọi lại API hoặc cập nhật state để cập nhật danh sách sản phẩm mới
        axios
          .get('http://localhost:1303/api/products/all')
          .then((res) => {
            setProducts(res.data);
          })
          .catch((err) => {
            console.error(err);
          });
      })
      .catch((err) => {
        console.error(err);
      });

    handleClose();
  };

  return (
    <div
      className="product-card group relative cursor-pointer transition-shadow"
      style={{
        border: '2px solid #ccc',
        borderRadius: '5px',
        padding: '10px',
        margin: '10px',
        boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
        transition: 'box-shadow 0.3s',
        position: 'relative',
        marginBottom: '50px',
      }}
    >
      <div
        style={{
          position: 'relative',
        }}
      >
        <button
          className="ellipsis-button"
          onClick={handleClick}
          style={{
            position: 'absolute',
            top: '10px',
            right: '10px',
            backgroundColor: 'transparent',
            border: 'none',
            cursor: 'pointer',
            fontSize: '1rem',
          }}
        >
          ...
        </button>
        <img
          src={product.imageUrl}
          alt=""
          style={{
            width: '100%',
            height: '300px',
            objectFit: 'cover',
          }}
        />
      </div>

      <div
        style={{
          flexGrow: 1,
          paddingTop: '10px',
        }}
      >
        <h1
          className="title font-mar text-xl text-black relative"
          style={{
            fontSize: '1.25rem',
            lineHeight: '1.5',
          }}
        >
          <a
            href="#"
            className="group text-black"
            style={{
              textDecoration: 'none',
              color: 'inherit',
            }}
          >
            {product.title}
          </a>
        </h1>
        <Rating
          name="read-only"
          value={averageRating}
          precision={0.5}
          readOnly
          style={{
            marginTop: '5px',
          }}
        />
        <p
          className="text-lg font-medium font-san text-black"
          style={{
            marginTop: '5px',
            fontSize: '1.125rem',
          }}
        >
          ${product.price}
        </p>
      </div>

      <Menu
        anchorEl={anchorEl}
        open={Boolean(anchorEl)}
        onClose={handleClose}
        anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
        transformOrigin={{ vertical: 'top', horizontal: 'right' }}
      >
        <MenuItem onClick={handleUpdate}>Update</MenuItem>
        <MenuItem onClick={handleDelete}>Delete</MenuItem>
      </Menu>

      <Modal open={showUpdateForm} onClose={closeModal}>
        <div>
          <UpdateProductForm product={product} onUpdate={closeModal} />
        </div>
      </Modal>
    </div>
  );
};

export default ProductCard;
