import KeyboardDoubleArrowRightIcon from "@mui/icons-material/KeyboardDoubleArrowRight";

const DoubleArrowLeftButton = ({ disabled, handleClick }) => {
  return (
    <div onClick={handleClick}>
      <KeyboardDoubleArrowRightIcon
        sx={{ color: "#173C57" }}
        className={`cursor-pointer ${
          disabled ? "opacity-50 cursor-default" : ""
        }`}
      />
    </div>
  );
};

export default DoubleArrowLeftButton;
