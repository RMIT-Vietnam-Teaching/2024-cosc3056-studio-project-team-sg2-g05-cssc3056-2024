import React from "react";
import { useState, useEffect } from "react";
import warning from "../../../assets/yellow-warning.png";
import { fetchData } from "../../../api/api";
const ImageWithWarning = ({ id, unavailableYear, type, typeOfData, name }) => {
  const [isHovered, setIsHovered] = useState(false);
  const [message, setMessage] = useState("");
  const [limitedYearRange, setLimitedYearRange] = useState({
    minYear: -1,
    maxYear: -1,
  });

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
    } else if (type === "World") {
      if (typeOfData === "Population")
        url = `/global/records/population-year-range`;
      else if (typeOfData === "Temperature")
        url = `/global/records/temperature-year-range`;
    }

    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}${url}`,
      setLimitedYearRange
    );
  };

  useEffect(() => {
    if (
      limitedYearRange.minYear < unavailableYear &&
      limitedYearRange.maxYear > unavailableYear
    )
      setMessage(`Data for ${unavailableYear} is   missing`);
    else
      setMessage(
        `${typeOfData} data for ${name} is available from ${limitedYearRange.minYear} to ${limitedYearRange.maxYear}`
      );
  }, [limitedYearRange]);

  return (
    <div className="relative">
      <img
        src={warning}
        alt="Your Image"
        className="w-[18px] h-[18px] mt-1 ml-1"
        onMouseEnter={() => {
          fetchYearRange();
          setIsHovered(true);
        }}
        onMouseLeave={() => setIsHovered(false)}
      />
      {isHovered && (
        <div
          onMouseLeave={() => setIsHovered(false)}
          onMouseEnter={() => setIsHovered(true)}
          className="absolute bottom-[20px] left-[40px]  transform -translate-x-1/2 
          bg-lightgrey
          px-4 py-2 rounded-lg shadow-lg flex  items-center w-[300px] max-h-[100px] h-fit text-primary z-10 overflow-auto">
          {message}
        </div>
      )}
    </div>
  );
};

export default ImageWithWarning;
