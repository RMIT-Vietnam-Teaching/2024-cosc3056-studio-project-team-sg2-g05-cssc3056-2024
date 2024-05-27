import TableHeader from "./TableHeader";
import TemperatureAndPopulationChangeInformation from "../../../components/common/temperature-and-population-change-information/TemperatureAndPopulationChangeInformation";
import Pagination from "../../../components/common/pagination/Pagination";
import { useEffect, useState } from "react";
const CountriesChangeDisplay = ({ selectedCountryData }) => {
  const [selectedPage, setSelectedPage] = useState(1);
  const [numberOfPage, setNumberOfPage] = useState(0);
  const [displayedData, setDisplayedData] = useState([]);
  useEffect(() => {
    setNumberOfPage(Math.ceil(selectedCountryData.length / 8));
    setSelectedPage(1);
  }, [selectedCountryData]);

  useEffect(() => {
    setDisplayedData(
      selectedCountryData.slice((selectedPage - 1) * 8, selectedPage * 8)
    );
  }, [selectedPage, selectedCountryData]);

  return (
    selectedCountryData.length > 0 && (
      <div className="h-[640px] relative">
        <TableHeader />
        {displayedData.map((countryData, index) => (
          <TemperatureAndPopulationChangeInformation
            name={countryData.name}
            num={countryData.number}
            startYearTemperature={countryData.startYearTemperature}
            endYearTemperature={countryData.endYearTemperature}
            rawValueChangeTemperature={countryData.rawValueChangeTemperature}
            percentageChangeTemperature={
              countryData.percentageChangeTemperature
            }
            startYearPopulation={countryData.startYearPopulation}
            endYearPopulation={countryData.endYearPopulation}
            rawValueChangePopulation={countryData.rawValueChangePopulation}
            percentageChangePopulation={countryData.percentageChangePopulation}
            startYear={countryData.startYear}
            endYear={countryData.endYear}
            id={countryData.id}
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
    )
  );
};

export default CountriesChangeDisplay;
