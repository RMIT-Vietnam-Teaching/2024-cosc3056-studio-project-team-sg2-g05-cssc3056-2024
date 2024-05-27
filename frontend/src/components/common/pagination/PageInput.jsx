import React, { useEffect, useRef } from "react";
const PageInput = ({ selectedPage, setSelectedPage, totalPages }) => {
  const inputRef = useRef(null);
  useEffect(() => {
    if (inputRef.current) inputRef.current.value = selectedPage;
  }, [selectedPage, totalPages]);

  const handleInputChange = (e) => {
    e.target.value = e.target.value.replace(/\D/g, "");
  };

  const handleInputBlur = (e) => {
    var newValue = e.target.value;
    e.target.value = e.target.value.replace(/\D/g, "");
    if (newValue.trim() === "") {
      newValue = selectedPage;
    } else if (Number(newValue) > totalPages) {
      newValue = totalPages;
    } else if (Number(newValue) < 1) {
      newValue = 1;
    }
    if (newValue.length >= 2 && newValue[0] === "0") {
      newValue = newValue.slice(1);
    }
    setSelectedPage(Number(newValue));
    inputRef.current.value = newValue;
  };

  const handleInputKeyDown = (e) => {
    if (e.key === "Enter") {
      inputRef.current.blur();
    }
  };
  return (
    <h1 className="text-[14px] text-[#173C57] font-bold ml-2 caret-transparent flex items-center w-[150px]">
      Page
      <input
        ref={inputRef}
        defaultValue={selectedPage}
        maxLength={5}
        className="bg-[#F0F1F8] ml-1 mr-1 rounded-[6px] h-[30px] w-[40px] text-center font-bold 
            text-[16px] text-[#173C57] focus:border-none focus:outline-none caret-black mb-[2px]"
        onChange={handleInputChange}
        onKeyDown={handleInputKeyDown}
        onBlur={handleInputBlur}
      />
      of {totalPages}
    </h1>
  );
};

export default PageInput;
