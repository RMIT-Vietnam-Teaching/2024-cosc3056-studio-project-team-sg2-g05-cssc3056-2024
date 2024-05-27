import CircularProgress from "@mui/material/CircularProgress";
const ProcessButton = ({ onClick, text, isDisable = false }) => (
  <button
    disabled={isDisable}
    style={{ backgroundColor: isDisable ? "gray" : "#173C57" }}
    className="text-offWhite flex justify-center items-center rounded-[6px] h-[60px] w-[180px] mt-4 font-bold text-[16px] focus:outline-none focus:border-none caret-transparent mr-3"
    onClick={onClick}>
    {text}
    {isDisable && (
      <CircularProgress size={20} color="inherit" className="ml-2" />
    )}
  </button>
);

export default ProcessButton;
