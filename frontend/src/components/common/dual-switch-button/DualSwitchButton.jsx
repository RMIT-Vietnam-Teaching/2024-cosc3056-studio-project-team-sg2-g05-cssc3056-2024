import { useState, useRef, useEffect } from "react";
import { gsap } from "gsap";
const DualSwitchButton = ({
  label = "View by",
  leftLabel,
  rightLabel,
  leftValue,
  rightValue,
  value,
  onButtonClick,
}) => {
  const buttonRef = useRef(null);
  const leftButtonRef = useRef(null);
  const rightButtonRef = useRef(null);

  const setButtonPosition = (position) => {
    gsap.to(buttonRef.current, {
      duration: 0.3,
      x: position,
      ease: "power3.inOut",
    });
  };
  const setButtonColor = (buttonRef, color) => {
    gsap.to(buttonRef.current, {
      duration: 0.3,
      color: color,
      ease: "power3.inOut",
    });
  };
  useEffect(() => {
    if (value === leftValue) {
      setButtonPosition(0);
      setButtonColor(leftButtonRef, "white");
      setButtonColor(rightButtonRef, "#205175");
    } else if (value === rightValue) {
      setButtonPosition(buttonRef.current.offsetWidth);
      setButtonColor(leftButtonRef, "#205175");
      setButtonColor(rightButtonRef, "white");
    }
  }, [value]);

  return (
    <div>
      <h1 className="font-bold text-[14px] text-primary mb-1 caret-transparent">
        {label}
      </h1>
      <div
        className="bg-offWhite rounded-full w-fit h-[50px]
          flex flex-row justify-around caret-transparent relative">
        <div
          className="bg-primary h-full w-[50%] absolute left-0 rounded-full"
          ref={buttonRef}></div>
        <button
          ref={leftButtonRef}
          value="oke"
          className="text-offWhite h-full text-center 
            outline-none border-none 
            bg-transparent relative text-[12px] font-bold
            p-6 flex items-center justify-center
            "
          onClick={() => {
            onButtonClick(leftValue);
          }}>
          {leftLabel}
        </button>
        <button
          ref={rightButtonRef}
          className="text-primary h-full text-center outline-none border-none
         bg-transparent relative text-[12px] font-bold p-6 flex items-center justify-center"
          onClick={() => {
            onButtonClick(rightValue);
          }}>
          {rightLabel}
        </button>
      </div>
    </div>
  );
};
export default DualSwitchButton;
