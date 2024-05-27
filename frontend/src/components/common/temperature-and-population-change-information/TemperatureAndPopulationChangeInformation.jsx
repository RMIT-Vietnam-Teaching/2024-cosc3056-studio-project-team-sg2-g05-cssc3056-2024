import TemperatureChangeInformation from "../temperature-change-information/TemperatureChangeInformation";
import PopulationChangeInformation from "../population-change-information/PopulationChangeInformation";

const TemperatureAndPopulationChangeInformation = ({
  num,
  name,
  percentageChangeTemperature,
  rawValueChangeTemperature,
  startYearTemperature,
  endYearTemperature,
  percentageChangePopulation,
  rawValueChangePopulation,
  startYearPopulation,
  endYearPopulation,
  startYear,
  endYear,
  id,
}) => {
  return (
    <div className="flex flex-row items-center h-[64px] mb-2">
      <h1 className="text-[16px] font-bold text-[#173C57] w-[5%] text-center">
        {num}
      </h1>
      <div
        className="flex flex-row items-center h-full w-[90%] 
      bg-[#E4E7F1] rounded-[8px] relative justify-evenly">
        <h1 className="text-[16px] font-bold text-[#173C57] w-[20%] pl-3">
          {name}
        </h1>
        <TemperatureChangeInformation
          type="Country"
          id={id}
          percentageChangeTemperature={percentageChangeTemperature}
          rawValueChangeTemperature={rawValueChangeTemperature}
          startYearTemperature={startYearTemperature}
          endYearTemperature={endYearTemperature}
          startYear={startYear}
          endYear={endYear}
          name={name}
        />

        <div className=" w-[40px]"></div>
        <PopulationChangeInformation
          type="Country"
          percentageChangePopulation={percentageChangePopulation}
          rawValueChangePopulation={rawValueChangePopulation}
          startYearPopulation={startYearPopulation}
          endYearPopulation={endYearPopulation}
          startYear={startYear}
          endYear={endYear}
          id={id}
          name={name}
        />
      </div>
    </div>
  );
};
export default TemperatureAndPopulationChangeInformation;
