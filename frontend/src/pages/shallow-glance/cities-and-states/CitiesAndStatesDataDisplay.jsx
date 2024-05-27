import { useState, useEffect } from "react";
import CitiesAndStatesDataInformation from "./CitiesAndStatesDataInformation";
import Pagination from "../../../components/common/pagination/Pagination";
import TableHeader from "./TableHeader";
const CitiesAndStatesDataDisplay = ({ data, status, isClicked, isLoading }) => {
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
    <div>
      {isClicked && data.length > 0 && (
        <div className="h-[640px] relative">
          <TableHeader />
          {displayedData.map((data, index) => (
            <CitiesAndStatesDataInformation
              num={data.number}
              name={
                status === "Cities"
                  ? data.name +
                    " (" +
                    data.longitude +
                    ", " +
                    data.latitude +
                    ")"
                  : data.name
              }
              percentageChangeTemperature={data.percentageChangeTemperature}
              rawValueChangeTemperature={data.rawValueChangeTemperature}
              startYearTemperature={data.startYearTemperature}
              endYearTemperature={data.endYearTemperature}
              startYear={data.startYear}
              endYear={data.endYear}
              averagePercentageChangeTemperature={
                data.averagePercentageChangeTemperature
              }
              availableYearCount={data.availableYearCount}
              id={data.id}
              type={status}
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
      )}
      {isClicked && data.length === 0 && !isLoading && (
        <h1 className="text-[40px] justify-center flex items-start text-gray-700 h-[640px]">
          No {status} Found in the selected year range for this country
        </h1>
      )}
    </div>
  );
};
export default CitiesAndStatesDataDisplay;
