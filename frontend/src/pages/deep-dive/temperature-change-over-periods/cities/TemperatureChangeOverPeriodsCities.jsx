import React, { useEffect, useState } from "react";
import { fetchData } from "../../../../api/api";
import SettingsSideBar from "../../../../components/settings-side-bar/SettingsSideBar";
import ProcessButton from "../../../../components/common/process-button/ProcessButton";
import SearchAndChips from "../../../../components/common/search-and-chips/SearchAndChips";
import TemperatureChangeWithInPeriod from "../countries/TemperatureChangeWithInPeriod";
import { TextField } from "@mui/material";
import { toast } from "react-toastify";
const TemperatureChangeOverPeriodsCities = () => {
  const [cityOptions, setCityOptions] = useState([]);
  const [selectedCities, setSelectedCities] = useState([]);
  const [yearOptions, setYearOptions] = useState([]);
  const [selectedYears, setSelectedYears] = useState([]);
  const [yearLength, setYearLength] = useState("");
  const [selectedData, setSelectedData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const fetchCities = () => {
    fetchData(`${process.env.REACT_APP_API_BASE_URL}/cities`, setCityOptions);
  };

  const fetchYearOptions = () => {
    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}/cities/records/year-range`,
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
    if (selectedCities.length === 0) {
      toast.error("Please select at least one city");
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
      cityIds: selectedCities.map((city) => city.id).join(","),
      startYears: selectedYears.map((year) => year.id).join(","),
      yearLength: yearLength,
    });

    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}/cities/records/average-values-of-temperature?${params}`,
      setSelectedData,
      setIsLoading
    );
  };
  useEffect(() => {
    fetchYearOptions();
    fetchCities();
  }, []);

  return (
    <div>
      <SettingsSideBar noYearRange={true} />
      <div className="w-[1200px] left-[310px] flex flex-col absolute">
        <div className="w-[1100px] ml-[60px] flex flex-col items-start mb-5">
          <h1 className="text-[24px] font-bold text-[#173C57] mb-3 mt-7">
            Calculate temperature change over periods
            <span className="text-[#8497A8]"> (By Cities)</span>
          </h1>
          <SearchAndChips
            options={cityOptions}
            selectedOptions={selectedCities}
            setSelectedOptions={setSelectedCities}
            label="Selected Cities"
            placeholder="Search Cities"
            toastMessage={"City is already selected"}
            optionDisplay={(props, value) => (
              <li {...props}>
                {value.name} {" ("}
                {value.longitude}
                {", "}
                {value.latitude}
                {")"}
              </li>
            )}
            name="Cities"
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
          <TemperatureChangeWithInPeriod data={selectedData} type="Cities" />
        </div>
      </div>
    </div>
  );
};

export default TemperatureChangeOverPeriodsCities;
