import { Box, Modal, Typography } from "@mui/material";
import React from "react";
import Register from "./Register";
import { useLocation } from "react-router-dom";
import LoginForm from "./LoginForm";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 500,
  p: 4,
};

const AuthModal = ({ handleClose, open }) => {
  const location = useLocation();

  return (
    <div>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box
          sx={{
            ...style,
            backgroundColor: "white", // Change background color if needed
            boxShadow: "lg", // Apply shadow
            border: "2px solid",
            borderColor: "purple.600",
            rounded: "md",
            padding:"40px"
          }}
          className="p-4 border-2 border-purple-600 rounded-md bg-white shadow-lg"
        >
          {location.pathname === "/login" ? <LoginForm/> : <Register/>}
        </Box>
      </Modal>
    </div>
  );
};

export default AuthModal;
