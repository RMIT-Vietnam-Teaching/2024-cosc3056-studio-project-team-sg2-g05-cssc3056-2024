import React from "react";
import KeyboardDoubleArrowLeftIcon from "@mui/icons-material/KeyboardDoubleArrowLeft";

const DoubleArrowLeftButton = ({ disabled, handleClick }) => {
  return (
    <div onClick={handleClick}>
      <KeyboardDoubleArrowLeftIcon
        sx={{ color: "#173C57" }}
        className={`ml-2 cursor-pointer ${
          disabled ? "opacity-50 cursor-default" : ""
        }`}
      />
    </div>
  );
};

export default DoubleArrowLeftButton;
