import { styled } from "@mui/system";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import InputLabel from "@mui/material/InputLabel";

const SelectBox = ({ options, value, onChange, label, width = "200px" }) => (
  <FormControl sx={{ width: width }}>
    <InputLabel id="demo-controlled-open-select-label">{label}</InputLabel>
    <Select
      value={value}
      label={label}
      MenuProps={{ disablePortal: true }}
      onChange={(e) => onChange(e.target.value)}>
      {options.map((option) => (
        <MenuItem value={option.value}>{option.label}</MenuItem>
      ))}
    </Select>
  </FormControl>
);

export default SelectBox;
