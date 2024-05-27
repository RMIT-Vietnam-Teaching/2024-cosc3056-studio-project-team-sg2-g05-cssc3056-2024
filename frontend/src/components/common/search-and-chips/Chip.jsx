import React from "react";
import CloseIcon from "@mui/icons-material/Close";

const Chip = ({ name, setSelectedOptions, id }) => {
  return (
    <div
      className="flex flex-row h-[44px]
     rounded-[6px] bg-[#173C57] w-fit 
     justify-center items-center pr-3 pl-3 pt-4 pb-4 
     text-white 
     mr-3 mb-2
     ">
      <h1 className="text-center text-semiBold mr-1 text-[16px]">{name}</h1>
      <div className="">
        <CloseIcon
          className="cursor-pointer"
          fontSize="small"
          onClick={() => {
            setSelectedOptions((prevSelectedOptions) =>
              prevSelectedOptions.filter((option) => option.id !== id)
            );
          }}
        />
      </div>
    </div>
  );
};
export default Chip;
