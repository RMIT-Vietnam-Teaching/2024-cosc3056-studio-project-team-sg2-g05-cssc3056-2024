import { useEffect, useState } from "react";
import Pagination from "../../../../components/common/pagination/Pagination";
import DataInformation from "./DataInformation";
import Header from "./Header";
const DataDisplay = ({ data, type }) => {
  const [selectedPage, setSelectedPage] = useState(1);
  const [numberOfPage, setNumberOfPage] = useState(0);
  const [displayedData, setDisplayedData] = useState([]);
  useEffect(() => {
    setNumberOfPage(Math.ceil(data.length / 8));
    setSelectedPage(1);
  }, [data]);
  useEffect(() => {
    setDisplayedData(data.slice((selectedPage - 1) * 8, selectedPage * 8));
    console.log("LENGTH " + displayedData.length);
  }, [data, selectedPage]);

  return (
    <div className="h-[640px] relative w-full mt-10">
      <Header isCountry={type != "Country" ? true : false} />
      {displayedData.map((data) => (
        <DataInformation
          type={type}
          id={data.id}
          num={data.number}
          name={
            data.longitude
              ? data.name + " (" + data.longitude + ", " + data.latitude + ")"
              : data.name
          }
          startYear={data.startYear}
          endYear={data.endYear}
          averageValueOfTemperature={data.averageValueOfTemperature}
          temperatureDifference={data.temperatureDifference}
          availableYearCount={data.availableYearCount}
          countryName={data.countryName}
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
export default DataDisplay;
