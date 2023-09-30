import React from 'react'
import IconButton from '@mui/material/IconButton'
import RemoveCircleOutlineIcon from '@mui/icons-material/RemoveCircleOutline';
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import Button from '@mui/material/Button'

const CartItem = () => {
    return (
        // box item checkout
        <div className='p-5 shadow border rounded-md'>

            {/* product card info */}
            <div className='flex items-center'>
                <div className='w-[5rem] h-[5rem] lg:w-[9rem] lg:h-[9rem]'>
                    <img src="https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/e777c881-5b62-4250-92a6-362967f54cca/air-force-1-07-shoe-NMmm1B.png"
                        className='w-full h-full object-cover object-top'
                        alt="" />
                </div>
                <div className='ml-5 space-y-1'>
                    <p className='font-semibold'>Nike Air Force One</p>
                    <p className='opacity-70'>Size L, White</p>
                    <p className='opacity-70 mt-2'>Seller: Oriz</p>
                    <div className='flex space-x-5 items-center text-gray-900 pt-6 '>
                        <p className='font-semibold'>199</p>
                        <p className='opacity-50 line-through'>200</p>
                        <p className='text-green-500 font-semibold'>50% OFF</p>
                    </div>
                </div>
            </div>

            {/* button add or remove product */}
            <div className='lg:flex items-center lg:space-x-10 pt-4'>
                <div className='flex items-center space-x-2 '>
                    <IconButton sx={{ color: "red" }}>
                        <RemoveCircleOutlineIcon />
                    </IconButton>
                    <span className='py-1 px-7 border rounded-sm'>3</span>
                    <IconButton sx={{ color: "purple" }}>
                        <AddCircleOutlineIcon />
                    </IconButton>

                </div>

                <div>
                    <Button sx={{ color: "purple" }}>Remove</Button>
                </div>
            </div>
        </div>
    )
}

export default CartItem