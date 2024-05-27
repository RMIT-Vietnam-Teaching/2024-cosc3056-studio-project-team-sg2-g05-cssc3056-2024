import DualSwitchButton from "../../../components/common/dual-switch-button/DualSwitchButton";
import DataInfo from "./DataInfo";
const convertPopulationToShortScale = (population) => {
  if (isNaN(population)) return "No data";
  if (population === 0) return "No data";
  if (population >= 1000000000) {
    return (population / 1000000000).toFixed(4) + " B";
  } else if (population >= 1000000) {
    return (population / 1000000).toFixed(3) + " M";
  } else {
    return population.toLocaleString();
  }
};
const SelectedInformation = ({
  status,
  setStatus,
  yearRange,
  countryName,
  startYearTemperature,
  endYearTemperature,
  startYearPopulation,
  endYearPopulation,
}) => {
  const getContent = (value) => {
    if (status === "Population") {
      if (value === null) return "N/A";
      return convertPopulationToShortScale(value);
    }
    if (status === "Temperature") {
      if (value === null || value === undefined) return "N/A";
      return value + "°C";
    }
    return null;
  };

  return (
    <div className="h-[300px] bg-lightgrey rounded-[12px] flex flex-col justify-start ">
      <div className="ml-8 mt-8">
        <DualSwitchButton
          label="View by"
          leftLabel="Population"
          rightLabel="Temperature"
          leftValue="Population"
          rightValue="Temperature"
          value={status}
          onButtonClick={(value) => {
            setStatus(value);
          }}
        />
      </div>
      <div className="flex flex-col justify-center items-center">
        <h1 className="text-[40px] font-bold text-[#173C57] mb-3  mt-1 ">
          {countryName}
        </h1>
        {status === "Population" && (
          <>
            <DataInfo
              label={"Population - " + yearRange.startYear}
              content={getContent(startYearPopulation)}
            />
            <div className="h-5"></div>
            <DataInfo
              label={"Population - " + yearRange.endYear}
              content={getContent(endYearPopulation)}
            />
          </>
        )}
        {status === "Temperature" && (
          <>
            <DataInfo
              label={"Temperature - " + yearRange.startYear}
              content={getContent(startYearTemperature)}
            />
            <div className="h-5"></div>
            <DataInfo
              label={"Temperature - " + yearRange.endYear}
              content={getContent(endYearTemperature)}
            />
          </>
        )}
      </div>
    </div>
  );
};
export default SelectedInformation;
