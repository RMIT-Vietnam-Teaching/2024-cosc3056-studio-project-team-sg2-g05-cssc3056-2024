import React, { useState, useEffect } from "react";
import { fetchData } from "../../../../api/api";
import SettingsSideBar from "../../../../components/settings-side-bar/SettingsSideBar";
import SelectSearchBox from "../../../../components/common/select-search-box/SelectSearchBox";
import { TextField } from "@mui/material";
import ProcessButton from "../../../../components/common/process-button/ProcessButton";
import { toast } from "react-toastify";
import DataDisplay from "./DataDisplay";
const SimilarPeriodCities = () => {
  const [yearRange, setYearRange] = useState({ startYear: -1, endYear: -1 });
  const [limitedYearRange, setLimitedYearRange] = useState({
    minYear: -1,
    maxYear: -1,
  });
  const [cityOptions, setCityOptions] = useState([]);
  const [selectedCity, setSelectedCity] = useState(null);
  const [yearOptions, setYearOptions] = useState([]);
  const [selectedYear, setSelectedYear] = useState(null);
  const [yearLength, setYearLength] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const [selectedData, setSelectedData] = useState([]);
  const [numberOfResult, setNumberOfResult] = useState(10);

  const fetchYearRange = () => {
    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}/cities/records/year-range`,
      (data) => {
        setYearRange({
          startYear: data.minYear,
          endYear: data.maxYear,
        });
        setLimitedYearRange({
          minYear: data.minYear,
          maxYear: data.maxYear,
        });
      }
    );
  };
  const fetchCities = () => {
    fetchData(`${process.env.REACT_APP_API_BASE_URL}/cities`, setCityOptions);
  };
  const fetchYearOptions = () => {
    setYearOptions([]);
    setSelectedYear(null);
    if (!selectedCity) return;

    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}/cities/records/year-range-by-city-id?cityId=${selectedCity.id}`,
      (data) => {
        setYearOptions(
          Array.from({ length: data.maxYear - data.minYear + 1 }, (_, i) => ({
            id: i + data.minYear,
            name: i + data.minYear,
          }))
        );
      }
    );
  };
  useEffect(() => {
    fetchYearRange();
    fetchCities();
  }, []);
  useEffect(() => {
    fetchYearOptions();
  }, [selectedCity]);

  const fetchSelectedData = () => {
    if (!selectedCity) {
      toast.error("Please select a city");
      return;
    }
    if (!selectedYear) {
      toast.error("Please select a year");
      return;
    }
    if (!yearLength) {
      toast.error("Please enter year length");
      return;
    }
    if (
      selectedYear + Number(yearLength) >
      Number(yearOptions[yearOptions.length - 1].name)
    ) {
      toast.error(
        "The sum of the start year and year length should be less than or equal to the maximum year available"
      );
      return;
    }
    if (numberOfResult === 0) {
      toast.error("Number of results should be greater than 0");
      return;
    }
    const params = new URLSearchParams({
      startYear: selectedYear.name,
      yearLength: yearLength,
      cityId: selectedCity.id,
      limit: numberOfResult,
    });
    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}/cities/records/similar-periods-by-city-id?${params}`,
      setSelectedData,
      setIsLoading
    );
  };

  return (
    <div>
      <SettingsSideBar noYearRange={true} />
      <div className="w-[1200px] left-[310px] flex flex-col absolute">
        <div className="w-[1100px] ml-[60px] flex flex-col items-start mb-5">
          <h1 className="text-[24px] font-bold text-[#173C57] mb-3 mt-7">
            Find Similar Period
            <span className="text-[#8497A8]"> (By Cities)</span>
          </h1>
          <div className="flex flex-row items-end">
            <SelectSearchBox
              options={cityOptions.map((city) => ({
                id: city.id,
                name:
                  city.name +
                  " (" +
                  city.longitude +
                  ", " +
                  city.latitude +
                  ")",
                countryName: city.countryName,
              }))}
              placeholder="Search for city"
              value={selectedCity}
              onChange={(e) => {
                setSelectedCity(e);
              }}
            />
            <div className="w-[10px]"></div>
            <SelectSearchBox
              options={yearOptions}
              placeholder="Choose Start Year"
              value={selectedYear}
              onChange={(e) => setSelectedYear(e)}
              width="200px"
            />
            <div className="w-[10px]"></div>
            <TextField
              sx={{ width: "160px" }}
              label="Year Length"
              value={yearLength}
              onChange={(e) => {
                const newValue = e.target.value.replace(/\D/g, "");
                if (newValue.length > 5) return;
                setYearLength(newValue);
              }}
            />
            <div className="w-[10px]"></div>
            <TextField
              sx={{ width: "160px" }}
              label="Number Of Results"
              value={numberOfResult}
              onChange={(e) => {
                const newValue = e.target.value.replace(/\D/g, "");

                setNumberOfResult(newValue);
              }}
            />
            <div className="w-[10px]"></div>
            <ProcessButton
              onClick={fetchSelectedData}
              text="Process"
              isDisable={isLoading}
            />
          </div>
          <DataDisplay data={selectedData} type="Cities" />
        </div>
      </div>
    </div>
  );
};
export default SimilarPeriodCities;
