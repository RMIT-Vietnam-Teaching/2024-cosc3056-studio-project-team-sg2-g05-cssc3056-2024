import nodata from "../../../assets/no-connection.png";
const PopulationPercentageChangeInfo = ({ value, color }) => {
  return (
    <div className="w-[64px] h-[24px] relative flex justify-center ml-3  items-center">
      <div
        className="rounded-[8px] opacity-25 w-[64px] h-[24px] absolute"
        style={{ backgroundColor: color }}></div>
      {value ? (
        <h1 className="text-[12px] font-bold  relative" style={{ color }}>
          {value}%
        </h1>
      ) : (
        <img
          src={nodata}
          className="w-[18px] h-[18px] relative"
          alt="No data"
        />
      )}
    </div>
  );
};
export default PopulationPercentageChangeInfo;
