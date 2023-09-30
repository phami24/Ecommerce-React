import React, { useState, useRef } from 'react';

function OTPForm() {
  const [otp, setOTP] = useState(['', '', '', '', '', '']);
  const inputRefs = useRef([]);
  const handleOTPChange = (index, value) => {
    const newOTP = [...otp];
    newOTP[index] = value;
  
    if (!value && index > 0) {
      // Nếu ô không có giá trị, xóa ô hiện tại và chuyển focus sang ô trước
      newOTP[index] = ''; // Xóa ô hiện tại
      inputRefs.current[index - 1].focus(); // Chuyển focus sang ô trước
    } else if (value) {
      if (index < 5) {
        inputRefs.current[index + 1].focus(); // Chuyển focus sang ô sau khi nhập xong
      }
    }
  
    setOTP(newOTP);
  };
  

  const handlePaste = (event, index) => {
    event.preventDefault();
    const clipboardData = event.clipboardData || window.clipboardData;
    const pastedText = clipboardData.getData('text');

    if (pastedText && pastedText.length <= 6 - index) {
      const newOTP = [...otp];
      for (let i = 0; i < pastedText.length; i++) {
        newOTP[index + i] = pastedText[i];
      }
      setOTP(newOTP);
    }
  };

  const handleResendClick = () => {
    // Xử lý logic gửi lại OTP
    console.log('Resend OTP');
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const enteredOTP = otp.join('');
    // Xử lý logic xác nhận OTP ở đây
    console.log('Entered OTP:', enteredOTP);
  };

  return (
    <div className="OTPForm flex justify-center items-center h-screen bg-gray-100">
      <div className="rounded-md bg-white p-8 shadow-lg">
        <h2 className="text-xl font-semibold mb-4">OTP Verification</h2>
        <form onSubmit={handleSubmit} className="space-y-4">
          <div className="flex justify-center space-x-2">
            {otp.map((digit, index) => (
              <input
                key={index}
                ref={(input) => (inputRefs.current[index] = input)}
                value={digit}
                onChange={(e) => handleOTPChange(index, e.target.value)}
                onPaste={(e) => handlePaste(e, index)} // Xử lý paste
                maxLength="1"
                className="OTPInput w-12 h-12 text-center border border-gray-300 rounded-md text-lg focus:outline-none focus:ring focus:border-blue-300 appearance-none"
              />
            ))}
          </div>
          <div className="flex justify-between">
            <button
              type="button"
              onClick={handleResendClick}
              className="text-blue-600 hover:underline"
            >
              Resend OTP
            </button>
            <button
              type="submit"
              className="bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600"
            >
              Submit
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default OTPForm;
