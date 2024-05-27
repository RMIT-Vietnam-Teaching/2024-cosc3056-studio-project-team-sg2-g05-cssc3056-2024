import { ToastContainer, toast } from "react-toastify";
import { useState } from "react";
import TextField from "@mui/material/TextField";
import Autocomplete from "@mui/material/Autocomplete";
import { styled } from "@mui/system";
import "react-toastify/dist/ReactToastify.css";

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
const Search = ({
  options,
  onSearchChange,
  placeholder,
  toastMessage,
  optionDisplay,
  selectedOptions,
}) => {
  const [inputValue, setInputValue] = useState("");
  const showToastMessage = (message) => {
    toast.error(message, {
      position: "top-right",
      autoClose: 1500,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
      theme: "light",
    });
  };
  return (
    <Autocomplete
      className="w-[350px]"
      options={options}
      getOptionLabel={(option) => option.name}
      groupBy={(option) => option.countryName}
      getOptionSelected={(option, value) => option.id === value.id}
      renderOption={(props, option) => optionDisplay(props, option)}
      renderInput={(params) => (
        <StyledTextField
          {...params}
          label={placeholder}
          variant="outlined"
          value={inputValue}
          onChange={(event) => setInputValue(event.target.value)}
        />
      )}
      onChange={(event, newValue) => {
        if (newValue && !selectedOptions.includes(newValue)) {
          onSearchChange(newValue);
        } else {
          showToastMessage(toastMessage);
        }
        setInputValue("");
      }}
      inputValue={inputValue}
      onInputChange={(event, newInputValue) => {
        if (event && event.type === "change") {
          setInputValue(newInputValue);
        }
      }}
      value={null}
      disableClearable
    />
  );
};

export default Search;
