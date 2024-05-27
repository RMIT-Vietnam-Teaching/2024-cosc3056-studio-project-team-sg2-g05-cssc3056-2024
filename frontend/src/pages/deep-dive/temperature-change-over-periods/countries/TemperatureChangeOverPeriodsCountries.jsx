import React, { useEffect, useState } from "react";
import { fetchData } from "../../../../api/api";
import SettingsSideBar from "../../../../components/settings-side-bar/SettingsSideBar";
import ProcessButton from "../../../../components/common/process-button/ProcessButton";
import SearchAndChips from "../../../../components/common/search-and-chips/SearchAndChips";
import TemperatureChangeWithInPeriod from "./TemperatureChangeWithInPeriod";
import { TextField } from "@mui/material";
import { toast } from "react-toastify";
const TemperatureChangeOverPeriodsCountries = () => {
  const [countryOptions, setCountryOptions] = useState([]);
  const [selectedCountries, setSelectedCountries] = useState([]);
  const [yearOptions, setYearOptions] = useState([]);
  const [selectedYears, setSelectedYears] = useState([]);
  const [yearLength, setYearLength] = useState("");
  const [selectedData, setSelectedData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const fetchCountries = () => {
    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}/countries`,
      setCountryOptions
    );
  };
  useEffect(() => {}, [countryOptions]);
  const fetchYearOptions = () => {
    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}/countries/records/year-range`,
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
  const fetchSelectedData = () => {
    if (yearLength > yearOptions.length) {
      toast.error(
        "Year length should be less than or equal to the number of years available"
      );
      return;
    }
    if (selectedCountries.length === 0) {
      toast.error("Please select at least one country");
      return;
    }
    if (selectedYears.length === 0) {
      toast.error("Please select at least one year");
      return;
    }
    if (!yearLength) {
      toast.error("Please enter year length");
      return;
    }

    const params = new URLSearchParams({
      countryIds: selectedCountries.map((country) => country.id).join(","),
      startYears: selectedYears.map((year) => year.id).join(","),
      yearLength: yearLength,
    });

    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}/countries/records/average-values-of-temperature?${params}`,
      setSelectedData,
      setIsLoading
    );
  };
  useEffect(() => {
    fetchYearOptions();
    fetchCountries();
  }, []);

  return (
    <div>
      <SettingsSideBar noYearRange={true} />
      <div className="w-[1200px] left-[310px] flex flex-col absolute">
        <div className="w-[1100px] ml-[60px] flex flex-col items-start mb-5">
          <h1 className="text-[24px] font-bold text-[#173C57] mb-3 mt-7">
            Calculate temperature change over periods
            <span className="text-[#8497A8]"> (By Countries)</span>
          </h1>
          <SearchAndChips
            options={countryOptions}
            selectedOptions={selectedCountries}
            setSelectedOptions={setSelectedCountries}
            label="Selected Countries"
            placeholder="Search Countries"
            toastMessage={"Country is already selected"}
            optionDisplay={(props, value) => <li {...props}>{value.name}</li>}
            name="Countries"
          />
          <div className="h-4"></div>
          <SearchAndChips
            options={yearOptions}
            selectedOptions={selectedYears}
            setSelectedOptions={setSelectedYears}
            label="Selected Years"
            placeholder="Search Years"
            toastMessage="Year is already selected"
            optionDisplay={(props, value) => <li {...props}>{value.name}</li>}
            name="Years"
          />
          <div className="flex flex-row justify-start items-end mb-5">
            <TextField
              label="Year Length"
              value={yearLength}
              onChange={(e) => {
                const newValue = e.target.value.replace(/\D/g, "");
                if (newValue.length > 5) return;
                setYearLength(newValue);
              }}
            />
            <div className="w-4"></div>
            <ProcessButton
              onClick={fetchSelectedData}
              text="Process"
              isDisable={isLoading}
            />
          </div>
          <TemperatureChangeWithInPeriod data={selectedData} type="Country" />
        </div>
      </div>
    </div>
  );
};

export default TemperatureChangeOverPeriodsCountries;
