import React, { useEffect, useRef } from "react";
import { toast } from "react-toastify";
function InputField({ label, value, onInputChange, min, max }) {
  const inputRef = useRef(null);

  useEffect(() => {
    inputRef.current.value = value;
  }, [value]);

  return (
    <div className="flex flex-col items-start w-[35%]">
      <h1 className="font-bold text-[16px] text-primary mb-1 caret-transparent">
        {label}
      </h1>
      <input
        ref={inputRef}
        maxLength={4}
        className="bg-offWhite rounded-[6px] h-[40px] w-full  text-center font-semibold 
          text-[16px] text-primary focus:border-none focus:outline-none caret-black"
        onChange={(e) => {
          const newValue = e.target.value.replace(/\D/g, "");
          e.target.value = e.target.value.replace(/\D/g, "");
          if (Number(newValue) <= min || Number(newValue) >= max) return;
          onInputChange(Number(newValue));
        }}
        onBlur={() => {
          if (inputRef.current.value === "") {
            toast.error("This field cannot be empty");
          } else if (Number(inputRef.current.value) <= min) {
            toast.error("Value cannot be less than " + min);
          } else if (Number(inputRef.current.value) >= max) {
            toast.error("Value cannot be more than " + max);
          }
          inputRef.current.value = value;
        }}
        defaultValue={value}
      />
    </div>
  );
}
export default InputField;
