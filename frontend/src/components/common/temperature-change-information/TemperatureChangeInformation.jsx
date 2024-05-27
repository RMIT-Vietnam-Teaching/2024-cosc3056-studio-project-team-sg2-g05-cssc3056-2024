import TemperatureInfo from "./TemperatureInfo";
import TemperaturePercentageChangeInfo from "./TemperaturePercentageChangeInfo";
import TemperatureRawChangeInfo from "./TemperatureRawChangeInfo";

const TemperatureChangeInformation = ({
  startYear,
  endYear,
  startYearTemperature,
  endYearTemperature,
  rawValueChangeTemperature,
  percentageChangeTemperature,
  id,
  type,
  name,
}) => {
  const getPercentageChangeBackgroundColor = () => {
    if (!startYearTemperature || !endYearTemperature) return "#232323";
    return percentageChangeTemperature < 0 ? "#457190" : "#CB5555";
  };

  const getStartYearTemperatureColor = () => {
    return startYearTemperature ? "#173C57" : "#747474";
  };

  const getEndYearTemperatureColor = () => {
    if (!endYearTemperature) return "#747474";
    if (!startYearTemperature) return "#747474";
    return rawValueChangeTemperature < 0 ? "#457190" : "#CB5555";
  };

  return (
    <div className="w-fit h-[60px] flex flex-row justify-start items-center rounded-[8px]">
      <div className="w-[100px] flex justify-end mr-3">
        <TemperatureInfo
          id={id}
          type={type}
          year={startYear}
          temperature={startYearTemperature}
          color={getStartYearTemperatureColor()}
          name={name}
        />
      </div>
      <TemperatureRawChangeInfo
        change={rawValueChangeTemperature}
        color={getEndYearTemperatureColor()}
      />
      <div className="w-[140px] flex justify-between items-center ml-3">
        <TemperatureInfo
          id={id}
          type={type}
          temperature={endYearTemperature}
          year={endYear}
          color={getEndYearTemperatureColor()}
          name={name}
        />
        <TemperaturePercentageChangeInfo
          percentageChangeTemperature={percentageChangeTemperature}
          backgroundColor={getPercentageChangeBackgroundColor()}
          color={getEndYearTemperatureColor()}
        />
      </div>
    </div>
  );
};

export default TemperatureChangeInformation;
