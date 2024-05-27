import { useState, useEffect } from "react";
import warning from "../../../assets/yellow-warning.png";
import { fetchData } from "../../../api/api";
const ImageWithCalculationWarning = ({
  id,
  type,
  typeOfData,
  name,
  startYear,
  endYear,
}) => {
  const [isHovered, setIsHovered] = useState(false);
  const [firstMessage, setFirstMessage] = useState("");
  const [secondMessage, setSecondMessage] = useState("");
  const [thirdMessage, setThirdMessage] = useState("");
  const [limitedYearRange, setLimitedYearRange] = useState({
    minYear: -1,
    maxYear: 100000000,
  });
  const [unavailableYears, setUnavailableYears] = useState([]);
  const fetchYearRange = () => {
    var url = "";
    if (type === "Country") {
      if (typeOfData === "Population")
        url = `/countries/records/population-year-range-by-country-id?countryId=${id}`;
      else if (typeOfData === "Temperature")
        url = `/countries/records/temperature-year-range-by-country-id?countryId=${id}`;
    } else if (type === "Cities") {
      url = `/cities/records/year-range-by-city-id?cityId=${id}`;
    } else if (type === "States") {
      url = `/states/records/year-range-by-state-id?stateId=${id}`;
    }
    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}${url}`,
      setLimitedYearRange
    );
  };

  const fetchUnavailableYears = () => {
    var url = "";
    const param = new URLSearchParams({
      startYear: startYear,
      endYear: endYear,
    });

    if (type === "Country") {
      if (typeOfData === "Population")
        url = `/countries/records/population-unavailable-years-by-country-id?countryId=${id}&${param}`;
      else if (typeOfData === "Temperature")
        url = `/countries/records/temperature-unavailable-years-by-country-id?countryId=${id}&${param}`;
    } else if (type === "Cities") {
      url = `/cities/records/unavailable-years-by-city-id?cityId=${id}&${param}`;
    } else if (type === "States") {
      url = `/states/records/unavailable-years-by-state-id?stateId=${id}&${param}`;
    }
    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}${url}`,
      setUnavailableYears
    );
  };

  useEffect(() => {
    setFirstMessage(
      `Missing data for ${name} in the following years: ` +
        unavailableYears.join(", ") +
        "."
    );
  }, [unavailableYears]);

  useEffect(() => {
    if (
      startYear < limitedYearRange.minYear ||
      endYear < limitedYearRange.minYear
    ) {
      setSecondMessage(
        "The calculation does not include years before " +
          limitedYearRange.minYear +
          "."
      );
    }

    if (
      startYear > limitedYearRange.maxYear ||
      endYear > limitedYearRange.maxYear
    ) {
      setThirdMessage(
        "The calculation does not include years after " +
          limitedYearRange.maxYear +
          "."
      );
    }
  }, [limitedYearRange]);

  return (
    <div className="relative">
      <img
        src={warning}
        alt="Your Image"
        className="w-[18px] h-[18px] mt-1 ml-1"
        onMouseEnter={() => {
          fetchYearRange();
          fetchUnavailableYears();
          setIsHovered(true);
        }}
        onMouseLeave={() => setIsHovered(false)}
      />

      {isHovered && (
        <div
          onMouseLeave={() => setIsHovered(false)}
          onMouseEnter={() => setIsHovered(true)}
          className="absolute bottom-[20px] left-[0px]  transform -translate-x-1/2 
          bg-lightgrey
          px-4 py-2 rounded-lg shadow-lg  item  s-center w-[320px] h-fit max-h-[140px] text-primary z-10, overflow-auto ">
          {secondMessage && (
            <h1 className="text-primary font-bold">{secondMessage}</h1>
          )}
          {thirdMessage && (
            <h1 className="text-primary font-bold">{thirdMessage}</h1>
          )}
          {unavailableYears.length > 0 && (
            <h1 className="text-primary font-bold">{firstMessage}</h1>
          )}
        </div>
      )}
    </div>
  );
};
export default ImageWithCalculationWarning;
