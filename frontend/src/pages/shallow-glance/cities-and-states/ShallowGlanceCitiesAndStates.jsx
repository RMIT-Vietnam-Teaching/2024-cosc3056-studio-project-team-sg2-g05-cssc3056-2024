import React, { useState, useEffect } from "react";

import ProcessButton from "../../../components/common/process-button/ProcessButton";
import SettingsSideBar from "../../../components/settings-side-bar/SettingsSideBar";
import SelectSearchBox from "../../../components/common/select-search-box/SelectSearchBox";
import SelectBox from "../../../components/common/select-box/SelectBox";
import CitiesAndStatesDataDisplay from "./CitiesAndStatesDataDisplay";
import DualSwitchButton from "../../../components/common/dual-switch-button/DualSwitchButton";
import { fetchData } from "../../../api/api";

const ShallowGlanceCitesAndStates = () => {
  const [yearRange, setYearRange] = useState({ startYear: -1, endYear: -1 });
  const [limitedYearRange, setLimitedYearRange] = useState({
    minYear: -1,
    maxYear: -1,
  });
  const [options, setOptions] = useState([]);
  const [typeOfTemperature, setTypeOfTemperature] =
    useState("AverageTemperature");
  const [selectedCountry, setSelectedCountry] = useState(null);
  const [selectedCitiesData, setSelectedCitiesData] = useState([]);
  const [selectedStatesData, setSelectedStatesData] = useState([]);
  const [status, setStatus] = useState("Cities");
  const [isClicked, setIsClicked] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const fetchYearRange = () => {
    // Fetch temperature year range of city record from the API
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

    // Fetch temperature year range of state record from the API
    // if minYear of state record is less than minYear of city record, set minYear is minYear of state
    // if maxYear of state record is greater than maxYear of city record, set maxYear is maxYear of state
    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}/states/records/year-range`,
      (data) => {
        setYearRange({
          startYear: Math.min(data.minYear, yearRange.startYear),
          endYear: Math.max(data.maxYear, yearRange.endYear),
        });
        setLimitedYearRange({
          minYear: Math.min(data.minYear, yearRange.startYear),
          maxYear: Math.max(data.maxYear, yearRange.endYear),
        });
      }
    );
  };

  const fetchCountries = () => {
    fetchData(`${process.env.REACT_APP_API_BASE_URL}/countries`, setOptions);
  };

  const fetchCitiesAndStatesData = () => {
    if (!selectedCountry) return;
    setIsClicked(true);
    const params = new URLSearchParams({
      startYear: yearRange.startYear,
      endYear: yearRange.endYear,
      countryId: selectedCountry.id,
      typeOfTemperature: typeOfTemperature,
    });
    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}/cities/records/temperature-change-by-country-id?${params}`,
      setSelectedCitiesData,
      setIsLoading
    );
    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}/states/records/temperature-change-by-country-id?${params}`,
      setSelectedStatesData,
      setIsLoading
    );
  };

  useEffect(() => {
    fetchYearRange();
    fetchCountries();
  }, []);

  return (
    <div>
      <SettingsSideBar
        yearRange={yearRange}
        setYearRange={setYearRange}
        limitedYearRange={limitedYearRange}
      />
      <div className="w-[1200px] left-[310px] flex flex-col absolute">
        <div className="w-[1100px] ml-[60px] flex flex-col items-start mb-5">
          <h1 className="text-[24px] font-bold text-[#173C57] mb-3 mt-7">
            Temperature Change Analytics
            <span className="text-[#8497A8]"> (By Cities/States)</span>
          </h1>
          <div className="flex flex-row">
            <SelectSearchBox
              options={options}
              placeholder="Search for country"
              value={selectedCountry}
              onChange={(e) => setSelectedCountry(e)}
            />
            <div className="w-[10px]"></div>
            <SelectBox
              options={[
                { value: "AverageTemperature", label: "Average Temperature" },
                { value: "MaxTemperature", label: "Max Temperature" },
                { value: "MinTemperature", label: "Min Temperature" },
              ]}
              value={typeOfTemperature}
              onChange={(e) => setTypeOfTemperature(e)}
              label="Type of Temperature"
              width="220px"
            />
          </div>
          <div className="flex flex-row justify-between w-full">
            <ProcessButton
              onClick={fetchCitiesAndStatesData}
              text="Process"
              isDisable={isLoading}
            />
            <DualSwitchButton
              leftLabel={"Cities"}
              rightLabel={"States"}
              leftValue={"Cities"}
              rightValue={"States"}
              value={status}
              onButtonClick={(value) => setStatus(value)}
            />
          </div>
        </div>
        <CitiesAndStatesDataDisplay
          data={status === "Cities" ? selectedCitiesData : selectedStatesData}
          status={status}
          isClicked={isClicked}
        />
      </div>
    </div>
  );
};
export default ShallowGlanceCitesAndStates;
