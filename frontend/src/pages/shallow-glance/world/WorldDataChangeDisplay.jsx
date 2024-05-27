import React, { useEffect, useState } from "react";
import TemperatureAndPopulationChangeInformation from "../../../components/common/temperature-and-population-change-information/TemperatureAndPopulationChangeInformation";
import Pagination from "../../../components/common/pagination/Pagination";
import TableHeader from "./TableHeader";
const WorldDataChangeDisplay = ({
  worldData,
  sortWith,
  setSortWith,
  sortOrder,
  setSortOrder,
}) => {
  const [numberOfPage, setNumberOfPage] = useState(0);
  const [selectedPage, setSelectedPage] = useState(0);
  const [displayedData, setDisplayedData] = useState([]);

  useEffect(() => {
    setNumberOfPage(Math.ceil(worldData.length / 8));
    setSelectedPage(1);
  }, [worldData]);

  useEffect(() => {
    setDisplayedData(worldData.slice((selectedPage - 1) * 8, selectedPage * 8));
  }, [selectedPage, worldData]);

  return (
    <div className="w-90% h-[640px] relative">
      {worldData.length > 0 && (
        <TableHeader
          sortWith={sortWith}
          sortOrder={sortOrder}
          setSortWith={setSortWith}
          setSortOrder={setSortOrder}
        />
      )}
      {displayedData.map((worldData, index) => (
        <TemperatureAndPopulationChangeInformation
          key={index}
          name={worldData.name}
          num={worldData.number}
          startYearTemperature={worldData.startYearTemperature}
          endYearTemperature={worldData.endYearTemperature}
          rawValueChangeTemperature={worldData.rawValueChangeTemperature}
          percentageChangeTemperature={worldData.percentageChangeTemperature}
          startYearPopulation={worldData.startYearPopulation}
          endYearPopulation={worldData.endYearPopulation}
          rawValueChangePopulation={worldData.rawValueChangePopulation}
          percentageChangePopulation={worldData.percentageChangePopulation}
          startYear={worldData.startYear}
          endYear={worldData.endYear}
          id={worldData.id}
        />
      ))}
      <div className="absolute left-2 bottom-1 ">
        <Pagination
          selectedPage={selectedPage}
          totalPages={numberOfPage}
          onPageChange={(newValue) => setSelectedPage(newValue)}
        />
      </div>
    </div>
  );
};
export default WorldDataChangeDisplay;
