import Modal from "@mui/material/Modal";
import warning from "../../../assets/warning.png";
import React, { useState } from "react";
const WarningModal = ({
  isOpen,
  setIsOpen,
  modalWarningMessage,
  setSelectedOptions,
}) => {
  const handleClose = () => {
    setIsOpen(false);
  };
  return (
    <Modal open={isOpen} onClose={handleClose}>
      <div
        style={style}
        className=" w-[350px] h-[300px] bg-[#F0F1F8] flex flex-col items-start justify-center rounded-[8px] ">
        <div className="flex flex-col justify-center-full items-center w-full">
          <img src={warning} alt="" className="w-[85px]" />
          <h1 className="text-[#173C57] text-[20px] font-bold mt-6 mb-3  ">
            Delete all {modalWarningMessage}?
          </h1>
          <h2 className="mb-4 font-semibold text-[#8497A8]">
            All {modalWarningMessage} will be deleted, think carefully!
          </h2>
          <div className="flex flex-row justify-center">
            <button
              onClick={() => {
                handleClose();
              }}
              className="bg-[#F0F1F8] text-red-500 rounded-[6px] h-[30px] w-[90px] font-bold border-red-500 border-valid-red border mr-3">
              Cancel
            </button>
            <button
              onClick={() => {
                setSelectedOptions([]);
                handleClose();
              }}
              className="bg-red-500 text-[#F0F1F8] rounded-[6px] h-[30px] w-[90px] font-bold border-red-500 border-valid-red border">
              Delete
            </button>
          </div>
        </div>
      </div>
    </Modal>
  );
};
export default WarningModal;
const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
};
