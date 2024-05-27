import ImageWithCalculationWarning from "../../../../components/common/image-with-calculation-warning/ImageWithCalculationWarning";
const DataInformation = ({
  id,
  num,
  type,
  typeOfData,
  name,
  startYear,
  endYear,
  countryName,
  averageValueOfTemperature,
  temperatureDifference,
  availableYearCount,
}) => {
  return (
    <div className="flex flex-row items-center h-[64px] mb-2">
      <h1 className="text-[16px] font-bold text-[#173C57] w-[5%] text-center">
        {num != 0 && num}
      </h1>
      <div
        className="flex flex-row items-center h-full w-[90%] 
      bg-[#E4E7F1] rounded-[8px] relative justify-start">
        <h1 className="text-[16px] font-bold text-[#173C57] w-[28%] ml-8 ">
          {name}
        </h1>
        <h1 className="text-[16px] font-bold text-[#173C57] w-[5%] ml-8  mr-4  ">
          {startYear}
        </h1>
        <h1 className="text-[16px] font-bold text-[#173C57] w-[5%] ml-8 mr-4 ">
          {endYear}
        </h1>
        <div className="flex justify-end w-[15%] mr-[60px] pr-2">
          <h1 className="text-[16px] text-primary font-bold  pl-3 ">
            {averageValueOfTemperature + "°C" || "N/A"}
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
        <div className="w-[19%]">
          {num !== 0 && (
            <h1 className="text-[16px] font-bold text-[#173c57] mr-12 ">
              {temperatureDifference !== null
                ? temperatureDifference + "°C"
                : "N/A"}
            </h1>
          )}
        </div>

        <h1 className="text-[16px] font-bold text-[#173C57] w-[20%] ">
          {countryName}
        </h1>
      </div>
    </div>
  );
};
export default DataInformation;
