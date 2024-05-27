import ArrowForwardIosIcon from "@mui/icons-material/ArrowForwardIos";

const ArrowRightButton = ({ disabled, handleClick }) => {
  return (
    <div
      onClick={() => {
        if (disabled) return;
        handleClick();
      }}>
      <ArrowForwardIosIcon
        sx={{ color: "#173C57" }}
        className={`pb-[2px] ml-2 cursor-pointer ${
          disabled ? "opacity-50 cursor-default" : ""
        }`}
        fontSize="tiny"
      />
    </div>
  );
};

export default ArrowRightButton;
