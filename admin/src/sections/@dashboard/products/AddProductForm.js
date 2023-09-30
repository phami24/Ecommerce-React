import React, { useState, useEffect } from 'react';
import { Button, TextField, Grid, Select, MenuItem, FormControl, InputLabel } from '@mui/material';
import axios from 'axios';

const AddProductForm = ({ onAdd }) => {
  const [newProduct, setNewProduct] = useState({
    title: '',
    description: '',
    price: '',
    quantity: '',
    imageUrl: '',
    category_id: '',
    amount: '',
  });
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
    setNewProduct(prevProduct => ({
      ...prevProduct,
      [fieldName]: value,
    }));
  };

  const handleSubmit = () => {
    // Gọi API hoặc hàm thêm sản phẩm ở đây
    onAdd(newProduct);
  };

  return (
    <div style={{ backgroundColor: '#fff', padding: '20px' }}>
      <h2>Add Product</h2>
      <Grid container spacing={2}>
        <Grid item xs={12}>
          <TextField
            label="Title"
            value={newProduct.title}
            fullWidth
            onChange={e => handleFieldChange('title', e.target.value)}
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            label="Description"
            value={newProduct.description}
            fullWidth
            onChange={e => handleFieldChange('description', e.target.value)}
          />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField
            label="Price"
            value={newProduct.price}
            fullWidth
            onChange={e => handleFieldChange('price', e.target.value)}
          />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField
            label="Quantity"
            value={newProduct.quantity}
            fullWidth
            onChange={e => handleFieldChange('quantity', e.target.value)}
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            label="Image URL"
            value={newProduct.imageUrl}
            fullWidth
            onChange={e => handleFieldChange('imageUrl', e.target.value)}
          />
        </Grid>
        <Grid item xs={12} sm={6}>
          <FormControl fullWidth>
            <InputLabel>Category</InputLabel>
            <Select
              value={newProduct.category_id}
              onChange={e => handleFieldChange('category_id', e.target.value)}
            >
              <MenuItem value="">
                <em>None</em>
              </MenuItem>
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
            value={newProduct.amount}
            fullWidth
            onChange={e => handleFieldChange('amount', e.target.value)}
          />
        </Grid>
        <Grid item xs={12}>
          <Button variant="contained" color="primary" onClick={handleSubmit}>
            Add
          </Button>
        </Grid>
      </Grid>
    </div>
  );
};

export default AddProductForm;
