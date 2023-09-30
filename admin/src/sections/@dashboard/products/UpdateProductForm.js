import React, { useState, useEffect } from 'react';
import { Button, TextField, Grid, Select, MenuItem, FormControl, InputLabel } from '@mui/material';
import axios from 'axios';

const UpdateProductForm = ({ product, onUpdate }) => {
  const [updatedProduct, setUpdatedProduct] = useState(product);
  const [categories, setCategories] = useState([]);
  
  useEffect(() => {
    // Gọi API để lấy danh sách categories
    axios.get('http://localhost:1303/api/categories')
      .then(response => {
        setCategories(response.data);
      })
      .catch(error => {
        console.error(error);
      });
  }, []);

  const handleFieldChange = (fieldName, value) => {
    setUpdatedProduct(prevProduct => ({
      ...prevProduct,
      [fieldName]: value,
    }));
  };

  const handleSubmit = () => {
    // Gọi API hoặc hàm cập nhật sản phẩm ở đây
    onUpdate(updatedProduct);
  };

  return (
    <div style={{ backgroundColor: '#fff', padding: '20px' }}>
      <h2>Update Product</h2>
      <Grid container spacing={2}>
        <Grid item xs={12}>
          <TextField
            label="Title"
            value={updatedProduct.title}
            fullWidth
            onChange={e => handleFieldChange('title', e.target.value)}
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            label="Description"
            value={updatedProduct.description}
            fullWidth
            onChange={e => handleFieldChange('description', e.target.value)}
          />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField
            label="Price"
            value={updatedProduct.price}
            fullWidth
            onChange={e => handleFieldChange('price', e.target.value)}
          />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField
            label="Quantity"
            value={updatedProduct.quantity}
            fullWidth
            onChange={e => handleFieldChange('quantity', e.target.value)}
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            label="Image URL"
            value={updatedProduct.imageUrl}
            fullWidth
            onChange={e => handleFieldChange('imageUrl', e.target.value)}
          />
        </Grid>
        <Grid item xs={12} sm={6}>
          <FormControl fullWidth>
            <InputLabel>Category</InputLabel>
            <Select
              value={updatedProduct.category_id}
              onChange={e => handleFieldChange('category_id', e.target.value)}
            >
              {/* Hiển thị option giá trị hiện tại đầu tiên */}
              <MenuItem value={updatedProduct.category_id}>{updatedProduct.category_id}</MenuItem>
              {categories.map(category => (
                <MenuItem key={category.id} value={category.id}>
                  {category.name}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField
            label="Amount"
            value={updatedProduct.amount}
            fullWidth
            onChange={e => handleFieldChange('amount', e.target.value)}
          />
        </Grid>
        <Grid item xs={12}>
          <Button variant="contained" color="primary" onClick={handleSubmit}>
            Update
          </Button>
        </Grid>
      </Grid>
    </div>
  );
};

export default UpdateProductForm;
