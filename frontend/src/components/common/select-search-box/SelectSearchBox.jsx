import TextField from "@mui/material/TextField";
import Autocomplete from "@mui/material/Autocomplete";
import { styled } from "@mui/system";
import { useState } from "react";
const SelectSearchBox = ({
  options,
  value,
  onChange,
  placeholder,
  width = "350px",
}) => {
  const [inputValue, setInputValue] = useState("");
  return (
    <Autocomplete
      onRemove={(e) => {}}
      sx={{ width: width }}
      value={value}
      onChange={(event, newValue) => {
        onChange(newValue);
      }}
      groupBy={(option) => option.countryName}
      inputValue={inputValue}
      onInputChange={(event, newInputValue) => {
        setInputValue(newInputValue);
      }}
      getOptionLabel={(option) => option.name}
      options={options}
      renderInput={(params) => (
        <StyledTextField {...params} label={placeholder} />
      )}
    />
  );
};
const StyledTextField = styled(TextField)(({ theme }) => ({
  "& .MuiOutlinedInput-root": {
    "& fieldset": {
      borderColor: "gray", // Default border color
    },
    "&:hover fieldset": {
      borderColor: "darkgray", // Border color on hover
    },
    "&.Mui-focused fieldset": {
      borderColor: "gray", // Border color when focused
    },
    "&.Mui-focused .MuiOutlinedInput-input": {
      color: "black", // Text color when focused
    },
  },
  "& .MuiInputLabel-root": {
    color: "gray", // Default label color
  },
  "& .MuiInputLabel-root.Mui-focused": {
    color: "gray", // Label color when focused
  },
}));
export default SelectSearchBox;
