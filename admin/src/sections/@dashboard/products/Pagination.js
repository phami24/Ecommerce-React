import React from 'react';

const Pagination = ({ productsPerPage, totalProducts, paginate, currentPage }) => {
  const pageNumbers = [];

  for (let i = 1; i <= Math.ceil(totalProducts / productsPerPage); i++) {
    pageNumbers.push(i);
  }

  const paginationStyle = {
    display: 'flex',
    justifyContent: 'center',
    listStyle: 'none',
    padding: 0,
  };

  const pageItemStyle = {
    margin: '0 5px',
  };

  const visiblePageNumbers = pageNumbers.slice(currentPage - 1, currentPage + 2);

  return (
    <ul style={paginationStyle} className="pagination">
      {currentPage > 1 && (
        <li style={pageItemStyle}>
          <button
            onClick={() => paginate(currentPage - 1)}
            style={{
              display: 'inline-block',
              padding: '8px 12px',
              textDecoration: 'none',
              color: '#fff',
              backgroundColor: '#4caf50',
              border: 'none',
              borderRadius: '4px',
              transition: 'background-color 0.3s ease',
              cursor: 'pointer',
            }}
          >
            Prev
          </button>
        </li>
      )}

      {visiblePageNumbers.map(number => (
        <li key={number} style={pageItemStyle} className="page-item">
          <button
            onClick={() => paginate(number)}
            style={{
              display: 'inline-block',
              padding: '8px 12px',
              textDecoration: 'none',
              color: '#fff',
              backgroundColor: currentPage === number ? '#45a049' : '#4caf50',
              border: currentPage === number ? '1px solid #4caf50' : 'none',
              borderRadius: '4px',
              transition: 'background-color 0.3s ease',
              cursor: 'pointer',
            }}
            className={currentPage === number ? 'active' : ''}
          >
            {number}
          </button>
        </li>
      ))}

      {currentPage < pageNumbers.length && (
        <li style={pageItemStyle}>
          <button
            onClick={() => paginate(currentPage + 1)}
            style={{
              display: 'inline-block',
              padding: '8px 12px',
              textDecoration: 'none',
              color: '#fff',
              backgroundColor: '#4caf50',
              border: 'none',
              borderRadius: '4px',
              transition: 'background-color 0.3s ease',
              cursor: 'pointer',
            }}
          >
            Next
          </button>
        </li>
      )}
    </ul>
  );
};


export default Pagination; 