import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Helmet } from 'react-helmet-async';
import { Container, Stack, Typography, Link, Modal } from '@mui/material';
import { Pagination, ProductList, AddProductForm } from '../sections/@dashboard/products';

export default function ProductsPage() {
  const [openFilter, setOpenFilter] = useState(false);
  const [products, setProducts] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [productsPerPage] = useState(8); // Số sản phẩm hiển thị trên mỗi trang
  const [showAddForm, setShowAddForm] = useState(false); // Trạng thái để hiển thị modal thêm sản phẩm
  const [addSuccess, setAddSuccess] = useState(false); // Trạng thái để hiển thị thông báo thêm sản phẩm thành công

  useEffect(() => {
    axios
      .get('http://localhost:1303/api/products/all')
      .then((res) => {
        setProducts(res.data);
      })
      .catch((err) => {
        console.error(err);
      });
  }, []);

  const handleOpenFilter = () => {
    setOpenFilter(true);
  };

  const handleCloseFilter = () => {
    setOpenFilter(false);
  };

  // Tính index bắt đầu và index kết thúc của danh sách sản phẩm hiển thị trên trang hiện tại
  const indexOfLastProduct = currentPage * productsPerPage;
  const indexOfFirstProduct = indexOfLastProduct - productsPerPage;
  const currentProducts = products.slice(indexOfFirstProduct, indexOfLastProduct);

  const paginate = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  // Mở modal thêm sản phẩm
  const handleOpenAddForm = () => {
    setShowAddForm(true);
    setAddSuccess(false); // Reset trạng thái thông báo khi mở modal thêm sản phẩm
  };

  // Đóng modal thêm sản phẩm
  const handleCloseAddForm = () => {
    setShowAddForm(false);
  };

  // Xử lý sau khi thêm sản phẩm thành công
  const handleAddSuccess = () => {
    setAddSuccess(true);
    setShowAddForm(false); // Đóng modal thêm sản phẩm
    // Gọi lại API hoặc cập nhật state để cập nhật danh sách sản phẩm mới
    axios
      .get('http://localhost:1303/api/products/all')
      .then((res) => {
        setProducts(res.data);
      })
      .catch((err) => {
        console.error(err);
      });
  };
  const handleDeleteSuccess = (deletedProductName) => {
    // Xử lý sau khi xóa thành công (ví dụ: thông báo, cập nhật danh sách sản phẩm)
    console.log(`Product ${deletedProductName} has been deleted.`);

  };

  return (
    <>
      <Helmet>
        <title>Dashboard: Products</title>
      </Helmet>

      <Container>
        <Typography variant="h4" sx={{ mb: 5 }}>
          Products
        </Typography>

        {/* Nút Add */}
        <Stack direction="row" alignItems="center" justifyContent="flex-end" sx={{ mb: 2 }}>
          <button
            onClick={handleOpenAddForm}
            style={{
              padding: '8px 16px',
              backgroundColor: '#4caf50',
              color: 'white',
              border: 'none',
              borderRadius: '4px',
              cursor: 'pointer',
            }}
          >
            Add
          </button>
        </Stack>

        {/* Hiển thị thông báo thêm sản phẩm thành công */}
        {addSuccess && <div style={{ marginBottom: '10px', color: 'green' }}>Product added successfully!</div>}

        <ProductList products={currentProducts} onDeleteSuccess={handleDeleteSuccess} setProduct={setProducts} />

        {/* Hiển thị các nút phân trang */}
        <Pagination
          productsPerPage={productsPerPage}
          totalProducts={products.length}
          paginate={paginate}
          currentPage={currentPage}
        />

        {/* Modal thêm sản phẩm */}
        <Modal open={showAddForm} onClose={handleCloseAddForm}>
          <div>
            <AddProductForm onClose={handleCloseAddForm} onAddSuccess={handleAddSuccess} />
          </div>
        </Modal>
      </Container>
    </>
  );
}
