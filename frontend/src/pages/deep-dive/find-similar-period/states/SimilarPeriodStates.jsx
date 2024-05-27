import React, { useState, useEffect } from "react";
import { fetchData } from "../../../../api/api";
import SettingsSideBar from "../../../../components/settings-side-bar/SettingsSideBar";
import SelectSearchBox from "../../../../components/common/select-search-box/SelectSearchBox";
import { TextField } from "@mui/material";
import ProcessButton from "../../../../components/common/process-button/ProcessButton";
import { toast } from "react-toastify";
import DataDisplay from "../cities/DataDisplay";

const SimilarPeriodStates = () => {
  const [stateOptions, setStateOptions] = useState([]);
  const [selectedState, setSelectedState] = useState(null);
  const [yearOptions, setYearOptions] = useState([]);
  const [selectedYear, setSelectedYear] = useState(null);
  const [yearLength, setYearLength] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const [selectedData, setSelectedData] = useState([]);
  const [numberOfResult, setNumberOfResult] = useState(10);

  const fetchStates = () => {
    fetchData(`${process.env.REACT_APP_API_BASE_URL}/states`, setStateOptions);
  };
  const fetchYearOptions = () => {
    setYearOptions([]);
    setSelectedYear(null);
    if (!selectedState) return;

    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}/states/records/year-range-by-state-id?stateId=${selectedState.id}`,
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
    fetchStates();
  }, []);
  useEffect(() => {
    fetchYearOptions();
  }, [selectedState]);

  const fetchSelectedData = () => {
    if (!selectedState) {
      toast.error("Please select a state");
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
      stateId: selectedState.id,
      limit: numberOfResult,
    });
    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}/states/records/similar-periods-by-state-id?${params}`,
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
            <span className="text-[#8497A8]"> (By States)</span>
          </h1>
          <div className="flex flex-row items-end">
            <SelectSearchBox
              options={stateOptions.map((state) => ({
                id: state.id,
                name: state.name,
                countryName: state.countryName,
              }))}
              placeholder="Search for state"
              value={selectedState}
              onChange={(e) => {
                setSelectedState(e);
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
          <DataDisplay data={selectedData} type="States" />
        </div>
      </div>
    </div>
  );
};
export default SimilarPeriodStates;
