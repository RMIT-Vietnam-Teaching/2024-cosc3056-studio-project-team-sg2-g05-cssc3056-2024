import { useEffect, useState } from "react";
import SettingsSideBar from "../../../components/settings-side-bar/SettingsSideBar";
import SearchAndChips from "../../../components/common/search-and-chips/SearchAndChips";
import ProcessButton from "../../../components/common/process-button/ProcessButton";
import CountriesChangeDisplay from "./CountriesChangeDisplay";
import { fetchData } from "../../../api/api";

const ShallowGlanceCountries = () => {
  const [yearRange, setYearRange] = useState({ startYear: -1, endYear: -1 });
  const [limitedYearRange, setLimitedYearRange] = useState({
    minYear: -1,
    maxYear: -1,
  });
  const [options, setOptions] = useState([]);
  const [selectedOptions, setSelectedOptions] = useState([]);
  const [selectedCountryData, setSelectedCountryData] = useState([]);

  const fetchYearRange = () => {
    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}/countries/records/year-range`,
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

  const fetchCountries = () => {
    fetchData(`${process.env.REACT_APP_API_BASE_URL}/countries`, setOptions);
  };

  const fetchSelectedCountryData = () => {
    const params = new URLSearchParams({
      startYear: yearRange.startYear,
      endYear: yearRange.endYear,
      countryIds: selectedOptions.map((option) => option.id).join(","),
    });
    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}/countries/records/raw-and-percentage-change-by-country-ids?${params}`,
      setSelectedCountryData
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
            Temperature and Population Change Analytics
            <span className="text-[#8497A8]"> (By Countries)</span>
          </h1>
          <SearchAndChips
            selectedOptions={selectedOptions}
            options={options}
            toastMessage="Country already selected"
            setSelectedOptions={setSelectedOptions}
            label="Selected Countries"
            placeholder="Search for countries"
            optionDisplay={(props, value) => <li {...props}>{value.name}</li>}
            name="countries"
          />
          <ProcessButton onClick={fetchSelectedCountryData} text="Process" />
        </div>
        <CountriesChangeDisplay selectedCountryData={selectedCountryData} />
      </div>
    </div>
  );
};
export default ShallowGlanceCountries;
