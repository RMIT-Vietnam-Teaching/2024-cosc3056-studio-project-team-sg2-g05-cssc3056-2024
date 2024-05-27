import { useEffect, useState } from "react";
import SettingsSideBar from "../../../components/settings-side-bar/SettingsSideBar";
import WorldDataChangeDisplay from "./WorldDataChangeDisplay";
import ProcessButton from "../../../components/common/process-button/ProcessButton";
import SelectBox from "../../../components/common/select-box/SelectBox";
import { fetchData } from "../../../api/api";
import DualSliderWithInputs from "../../../components/common/dual-slider-with-inputs/DualSliderWithInputs";
import TemperatureChangeInformation from "../../../components/common/temperature-change-information/TemperatureChangeInformation";
import PopulationChangeInformation from "../../../components/common/population-change-information/PopulationChangeInformation";
const ShallowGlanceWorld = () => {
  const [yearRange, setYearRange] = useState({ startYear: -1, endYear: -1 });
  const [limitedYearRange, setLimitedYearRange] = useState({
    minYear: -1,
    maxYear: -1,
  });
  const [worldData, setWorldData] = useState([]);
  const [sortBy, setSortBy] = useState("PercentageChange");
  const [sortOrder, setSortOrder] = useState("ASC");
  const [sortWith, setSortWith] = useState("Temperature");
  const [isClicked, setIsClicked] = useState(false);
  const [globalData, setGlobalData] = useState();
  const fetchWorldData = () => {
    const params = new URLSearchParams({
      startYear: yearRange.startYear,
      endYear: yearRange.endYear,
      sortBy: sortWith.toString() + sortBy.toString(),
      sortOrder: sortOrder,
    });
    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}/countries/records/raw-and-percentage-change?${params}`,
      setWorldData
    );
  };

  const fetchGlobalData = () => {
    const params = new URLSearchParams({
      startYear: yearRange.startYear,
      endYear: yearRange.endYear,
    });
    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}/global/records/raw-and-percentage-change?${params}`,
      setGlobalData
    );
  };

  const fetchYearRange = () => {
    // Fetch temperature year range from the API
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

  useEffect(() => {
    fetchYearRange();
  }, []);

  useEffect(() => {
    if (!isClicked) return;
    if (yearRange.startYear !== -1 && yearRange.endYear !== -1)
      fetchWorldData();
  }, [sortWith, sortOrder, sortBy]);
  useEffect(() => {
    if (globalData) setIsClicked(true);
  }, [globalData]);
  return (
    <div>
      <SettingsSideBar
        yearRange={yearRange}
        setYearRange={setYearRange}
        limitedYearRange={limitedYearRange}
      />
      <div className="w-[1200px] left-[310px] flex flex-col absolute">
        <div className="mb-5 ml-[60px]">
          <h1 className="text-[24px] font-bold text-[#173C57] mb-3  mt-7">
            Temperature and Population Change Analytics
            <span className="text-[#8497A8]"> (By World Region)</span>
          </h1>{" "}
          <div className="flex justify-start items-end mb-2">
            <div>
              <h1 className="font-bold text-primary">
                Global Temperature change
              </h1>
              <div className="h-[60px] w-[350px] rounded-[6px] bg-offWhite mr-10 flex flex-row justify-center items-center">
                {isClicked ? (
                  <TemperatureChangeInformation
                    startYear={globalData.startYear}
                    endYear={globalData.endYear}
                    startYearTemperature={globalData.startYearTemperature}
                    endYearTemperature={globalData.endYearTemperature}
                    rawValueChangeTemperature={
                      globalData.rawValueChangeTemperature
                    }
                    percentageChangeTemperature={
                      globalData.percentageChangeTemperature
                    }
                    type="World"
                    name="Global Record"
                  />
                ) : (
                  <h1 className="text-primary font-bold ">
                    Click button to fetch data
                  </h1>
                )}
              </div>
            </div>
            <div>
              <h1 className="font-bold text-primary">
                Global Population change
              </h1>
              <div className="h-[60px] w-[450px] rounded-[6px] bg-offWhite mr-10 flex flex-row justify-center items-center">
                {isClicked ? (
                  <PopulationChangeInformation
                    startYear={globalData.startYear}
                    endYear={globalData.endYear}
                    startYearPopulation={globalData.startYearPopulation}
                    endYearPopulation={globalData.endYearPopulation}
                    rawValueChangePopulation={
                      globalData.rawValueChangePopulation
                    }
                    percentageChangePopulation={
                      globalData.percentageChangePopulation
                    }
                    type="World"
                    name="Global Record"
                  />
                ) : (
                  <h1 className="text-primary font-bold">
                    Click button to fetch data
                  </h1>
                )}
              </div>
            </div>
            <ProcessButton
              onClick={() => {
                fetchGlobalData();
                fetchWorldData();
              }}
              text={"Process"}
            />
          </div>
          <SelectBox
            options={[
              {
                value: "PercentageChange",
                label: "Percentage Change",
              },
              {
                value: "RawValueChange",
                label: "Raw Value Change",
              },
            ]}
            value={sortBy}
            onChange={(e) => setSortBy(e)}
            label={"Sort By"}
          />
        </div>
        <WorldDataChangeDisplay
          worldData={worldData}
          sortWith={sortWith}
          sortOrder={sortOrder}
          setSortWith={setSortWith}
          setSortOrder={setSortOrder}
        />
      </div>
    </div>
  );
};
export default ShallowGlanceWorld;
