import ImageWithCalculationWarning from "../../../../components/common/image-with-calculation-warning/ImageWithCalculationWarning";
const TemperatureInformation = ({
  num,
  name,
  averageValueOfTemperature,
  id,
  startYear,
  endYear,
  availableYearCount,
  countryName,
  type,
}) => (
  <div className="flex flex-row items-center h-[64px] mb-2">
    <h1 className="text-[16px] font-bold text-[#173C57] w-[5%] text-center">
      {num}
    </h1>
    <div
      className="flex flex-row items-center h-full w-[90%] 
      bg-[#E4E7F1] rounded-[8px] relative justify-start">
      <h1 className="text-[16px] font-bold text-[#173C57] w-[35%] pl-8  mr-[70px] ">
        {name}
      </h1>
      <div className="flex justify-start mr-[175px] w-[120px]">
        <h1
          style={{ color: getColor(averageValueOfTemperature) }}
          className="text-[16px] font-bold  pl-3 text-center">
          {averageValueOfTemperature ? averageValueOfTemperature + "°C" : "N/A"}
        </h1>
        {availableYearCount !== endYear - startYear + 1 && (
          <ImageWithCalculationWarning
            id={id}
            type={type}
            name={name}
            startYear={startYear}
            endYear={endYear}
            typeOfData={"Temperature"}
          />
        )}
      </div>
      <h1 className="text-[16px] font-bold  pl-3  text-primary w-[25%]">
        {countryName && countryName}
      </h1>
    </div>
  </div>
);
const getColor = (temperature) => {
  if (temperature === null) return "#7c7c7d";
  return temperature < 0 ? "#457190" : "#CB5555";
};
export default TemperatureInformation;
