import React, { useState } from "react";
import DeleteForeverSharpIcon from "@mui/icons-material/DeleteForeverSharp";
import Search from "./Search";
import Chip from "./Chip";
import WarningModal from "./WarningModal";
const SearchAndChips = ({
  options,
  selectedOptions,
  setSelectedOptions,
  placeholder,
  label,
  optionDisplay,
  toastMessage,
  name,
}) => {
  const [isOpen, setIsOpen] = useState(false);
  const handleOpen = () => {
    if (selectedOptions.length === 0) return;
    if (!isOpen) setIsOpen(true);
  };
  return (
    <div className="w-full">
      <Search
        placeholder={placeholder}
        onSearchChange={(newValue) =>
          setSelectedOptions([...selectedOptions, newValue])
        }
        options={options}
        optionDisplay={(props, value) => optionDisplay(props, value)}
        toastMessage={toastMessage}
        selectedOptions={selectedOptions}
      />

      <div className="flex flex-row justify-between items-end mt-2">
        <h1 className="text-[16px] font-bold text-[#173C57] mb-3">
          {label}
          <span className="text-[#8497A8]">
            {" "}
            ({selectedOptions.length} {name})
          </span>
        </h1>
        <h1
          className="mr-[30px] font-semiBold mb-3 underline text-[#8497A8]
           cursor-pointer caret-transparent decoration-1 "
          onClick={() => {
            handleOpen();
          }}>
          {" "}
          Clear all
          <DeleteForeverSharpIcon className="pb-1 " />
          <WarningModal
            isOpen={isOpen}
            setIsOpen={setIsOpen}
            modalWarningMessage={name}
            setSelectedOptions={setSelectedOptions}
          />
        </h1>
      </div>
      <div className="flex-wrap flex flex-row overflow-auto max-h-[121px] min-h-[60px] ">
        {selectedOptions.map((selectedOption) => (
          <Chip
            name={getName(selectedOption)}
            setSelectedOptions={setSelectedOptions}
            id={selectedOption.id}
          />
        ))}
      </div>
    </div>
  );
};
const getName = (selectedOption) => {
  var name = selectedOption.name;
  if (selectedOption.longitude) {
    name =
      name +
      " (" +
      selectedOption.longitude +
      ", " +
      selectedOption.latitude +
      ")";
  }
  if (selectedOption.countryName) {
    name = name + "-" + selectedOption.countryName;
  }
  return name;
};
export default SearchAndChips;
