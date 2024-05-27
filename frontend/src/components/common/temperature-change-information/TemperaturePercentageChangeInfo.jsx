import nodata from "../../../assets/no-connection.png";
const TemperaturePercentageChangeInfo = ({
  percentageChangeTemperature,
  backgroundColor,
  color,
}) => (
  <div className="w-[64px] h-[24px] relative flex justify-center ml-3  items-center">
    <div
      className="rounded-[8px] opacity-25 w-[64px] h-[24px] absolute"
      style={{ backgroundColor }}></div>
    {percentageChangeTemperature ? (
      <h1 className="text-[12px] font-bold  relative" style={{ color }}>
        {percentageChangeTemperature}%
      </h1>
    ) : (
      <img src={nodata} className="w-[18px] h-[18px] relative" />
    )}
  </div>
);
export default TemperaturePercentageChangeInfo;
