import PopulationInfo from "./PopulationInfo";
import PopulationPercentageChangeInfo from "./PopulationPercentageChangeInfo";
import PopulationRawChangeInfo from "./PopulationRawChangeInfo";

const PopulationChangeInformation = ({
  startYearPopulation,
  endYearPopulation,
  rawValueChangePopulation,
  percentageChangePopulation,
  startYear,
  endYear,
  id,
  type,
  name,
}) => {
  const getPercentageChangeBackgroundColor = () => {
    if (!startYearPopulation || !endYearPopulation) return "#232323";
    return percentageChangePopulation < 0 ? "#c37d30" : "#007060";
  };

  const getStartYearPopulationColor = () =>
    startYearPopulation ? "#173C57" : "#747474";

  const getEndYearPopulationColor = () => {
    if (!endYearPopulation) return "#747474";
    if (!startYearPopulation) return "#747474";
    return rawValueChangePopulation < 0 ? "#c37d30" : "#007060";
  };

  return (
    <div className="w-fit h-[60px] flex flex-row justify-start items-center  rounded-[8px]">
      <div className="w-[120px] flex flex-row justify-end mr-3">
        <PopulationInfo
          id={id}
          type={type}
          population={startYearPopulation}
          color={getStartYearPopulationColor()}
          name={name}
        />
      </div>
      <PopulationRawChangeInfo
        value={rawValueChangePopulation}
        color={getEndYearPopulationColor()}
      />
      <div className="w-[180px] flex flex-row justify-between items-between ml-3">
        <PopulationInfo
          id={id}
          type={type}
          population={endYearPopulation}
          color={getEndYearPopulationColor()}
          name={name}
        />
        <PopulationPercentageChangeInfo
          value={percentageChangePopulation}
          color={getPercentageChangeBackgroundColor()}
        />
      </div>
    </div>
  );
};

export default PopulationChangeInformation;
