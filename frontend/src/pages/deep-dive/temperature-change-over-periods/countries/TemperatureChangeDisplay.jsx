import React, { useEffect, useState } from "react";

import TemperatureInformation from "./TemperatureInformation";
import Header from "./Header";
import Pagination from "../../../../components/common/pagination/Pagination";

const TemperatureChangeDisplay = ({ data, type }) => {
  const [selectedPage, setSelectedPage] = useState(1);
  const [numberOfPage, setNumberOfPage] = useState(0);
  const [displayedData, setDisplayedData] = useState([]);
  useEffect(() => {
    setNumberOfPage(Math.ceil(data.length / 8));
    setSelectedPage(1);
  }, [data]);
  useEffect(() => {
    setDisplayedData(data.slice((selectedPage - 1) * 8, selectedPage * 8));
  }, [selectedPage, data]);
  return (
    <div className="h-[640px] relative">
      <Header isCountry={type === "Country"} />
      {displayedData.map((data) => (
        <TemperatureInformation
          id={data.id}
          num={data.number}
          averageValueOfTemperature={data.averageValueOfTemperature}
          name={data.name}
          startYear={data.startYear}
          endYear={data.endYear}
          availableYearCount={data.availableYearCount}
          countryName={data.countryName}
          type={type}
        />
      ))}
      <div className="absolute left-2 bottom-1 ">
        <Pagination
          selectedPage={selectedPage}
          totalPages={numberOfPage}
          onPageChange={setSelectedPage}
        />
      </div>
    </div>
  );
};
export default TemperatureChangeDisplay;
