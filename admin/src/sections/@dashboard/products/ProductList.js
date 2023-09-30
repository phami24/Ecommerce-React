import PropTypes from 'prop-types';
import { Grid } from '@mui/material';
import ProductCard from './ProductCard';


ProductList.propTypes = {
  products: PropTypes.array.isRequired,
};

const productCardStyle = {
  display: 'flex',
  flexDirection: 'column',
  justifyContent: 'space-between',
  transition: 'transform 0.3s ease',
  '&:hover': {
    transform: 'translateY(-4px)',
  },
};

export default function ProductList({ products,onDeleteSuccess,setProducts }) {
  return (
    <Grid container spacing={3}>
      {products.map((product) => (
        <Grid item xs={12} sm={6} md={4} lg={3} key={product.id}>
          <div style={productCardStyle}>
            <ProductCard product={product}  onDeleteSuccess={onDeleteSuccess} setProducts={setProducts}/>
          </div>
        </Grid>
      ))}
    </Grid>
  );
}
