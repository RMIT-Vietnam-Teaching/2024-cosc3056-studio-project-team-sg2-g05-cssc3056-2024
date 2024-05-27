import ImageWithCalculationWarning from "../../../components/common/image-with-calculation-warning/ImageWithCalculationWarning";
import TemperatureChangeInformation from "../../../components/common/temperature-change-information/TemperatureChangeInformation";
const CitiesAndStatesDataInformation = ({
  num,
  name,
  percentageChangeTemperature,
  rawValueChangeTemperature,
  startYearTemperature,
  endYearTemperature,
  startYear,
  endYear,
  averagePercentageChangeTemperature,
  availableYearCount,
  id,
  type,
}) => {
  const getColor = () => {
    return averagePercentageChangeTemperature < 0 ? "#457190" : "#CB5555";
  };
  return (
    <div className="flex flex-row items-center h-[64px] mb-2">
      <h1 className="text-[16px] font-bold text-[#173C57] w-[5%] text-center">
        {num}
      </h1>
      <div
        className="flex flex-row items-center h-full w-[90%] 
      bg-[#E4E7F1] rounded-[8px] relative justify-start">
        <h1 className="text-[16px] font-bold text-[#173C57] w-[35%] pl-8 ">
          {name}
        </h1>
        <div className="w-fit pr-[140px]">
          <TemperatureChangeInformation
            percentageChangeTemperature={percentageChangeTemperature}
            rawValueChangeTemperature={rawValueChangeTemperature}
            startYearTemperature={startYearTemperature}
            endYearTemperature={endYearTemperature}
            startYear={startYear}
            endYear={endYear}
            id={id}
            type={type}
            name={name}
          />
        </div>
        <div className="flex justify-start">
          <h1
            style={{ color: getColor() }}
            className="text-[16px] font-bold  pl-3 ">
            {averagePercentageChangeTemperature}%
          </h1>
          {availableYearCount !== endYear - startYear && (
            <ImageWithCalculationWarning
              id={id}
              type={type}
              name={name}
              startYear={startYear}
              endYear={endYear}
            />
          )}
        </div>
      </div>
    </div>
  );
};
export default CitiesAndStatesDataInformation;
