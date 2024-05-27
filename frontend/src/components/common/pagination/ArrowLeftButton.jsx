import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";

const ArrowLeftButton = ({ disabled, handleClick }) => {
  return (
    <div
      onClick={() => {
        if (disabled) return;
        handleClick();
      }}>
      <ArrowBackIosNewIcon
        sx={{ color: "#173C57" }}
        className={`pb-[2px] cursor-pointer ${
          disabled ? "opacity-50 cursor-default" : ""
        }`}
        fontSize="tiny"
      />
    </div>
  );
};

export default ArrowLeftButton;
