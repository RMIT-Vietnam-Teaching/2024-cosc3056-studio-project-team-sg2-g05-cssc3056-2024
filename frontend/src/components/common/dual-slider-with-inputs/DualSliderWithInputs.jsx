import Slider from "@mui/material/Slider";
import { ReactComponent as Arrow } from "../../../assets/arrow.svg";
import InputField from "./InputField";
import { width } from "@mui/system";
const DualSliderWithInputs = ({
  firstFieldLabel,
  secondFiendLabel,
  firstValue,
  secondValue,
  onFirstFieldChange,
  onSecondFieldChange,
  min,
  max,
}) => {
  return (
    <div className="w-fit ">
      <div className="flex flex-row justify-evenly items-end w-[280px]">
        <InputField
          label={firstFieldLabel}
          value={firstValue}
          onInputChange={(value) => onFirstFieldChange(value)}
          min={min}
          max={secondValue}
        />
        <Arrow className="mb-2" />
        <InputField
          label={secondFiendLabel}
          value={secondValue}
          onInputChange={(value) => onSecondFieldChange(value)}
          min={firstValue}
          max={max}
        />
      </div>
      <Slider
        className="mt-2"
        sx={CustomSliderStyles}
        min={min}
        max={max}
        value={[firstValue, secondValue]}
        onChange={(event, newValue, activeThumb) => {
          const minDistance = 1;
          if (activeThumb === 0) {
            onFirstFieldChange(
              Math.min(newValue[0], secondValue - minDistance)
            );
          } else {
            onSecondFieldChange(
              Math.max(newValue[1], firstValue + minDistance)
            );
          }
        }}
        disableSwap
      />
    </div>
  );
};
export default DualSliderWithInputs;
const CustomSliderStyles = {
  "& .MuiSlider-thumb": {
    color: "#E4E7F1",
    width: "12px",
    height: "12px",
    boxShadow: "0 0 0 3.5px #205175",
    "&:hover, &.Mui-focusVisible, &.Mui-active, &:focus": {
      boxShadow: "0 0 0 3.5px #205175",
    },
  },
  "& .MuiSlider-track": {
    height: "12px",
    color: "#173C57",
  },
  "& .MuiSlider-rail": {
    height: "12px",
    color: "white",
  },
};
