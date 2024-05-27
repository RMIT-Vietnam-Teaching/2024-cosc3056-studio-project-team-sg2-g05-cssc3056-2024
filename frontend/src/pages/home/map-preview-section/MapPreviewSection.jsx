import { useState, useEffect } from "react";
import DualSliderWithInputs from "../../../components/common/dual-slider-with-inputs/DualSliderWithInputs";
import ChoroplethMap from "./ChoroplethMap";
import { fetchData } from "../../../api/api";

import YearSlider from "./YearSlider";
import SelectedInformation from "./SelectedInformation";
import Title from "./Title";
const MapPreviewSection = ({ mapPreviewRef }) => {
  //Start Year and End Year for Population and Temperature
  const [populationYearRange, setPopulationYearRange] = useState({
    startYear: -1,
    endYear: -1,
  });
  const [temperatureYearRange, setTemperatureYearRange] = useState({
    startYear: -1,
    endYear: -1,
  });

  //Min and Max Year for Population and Temperature
  const [populationLimitYearRange, setPopulationLimitYearRange] = useState({
    minYear: -1,
    maxYear: -1,
  });
  const [temperatureLimitYearRange, setTemperatureLimitYearRange] = useState({
    minYear: -1,
    maxYear: -1,
  });

  const [
    populationAndTemperatureCountryData,
    setPopulationAndTemperatureCountryData,
  ] = useState([]);
  const [status, setStatus] = useState("Population");
  const [populationYearStatus, setPopulationYearStatus] =
    useState("Start Year");
  const [temperatureYearStatus, setTemperatureYearStatus] =
    useState("Start Year");
  const [
    populationAndAverageTemperatureInformation,
    setPopulationAndAverageTemperatureInformation,
  ] = useState(null);
  const [selectedCountryCode, setSelectedCountryCode] = useState("WLD");
  const [globalData, setGlobalData] = useState([]);
  const fetchCountryData = () => {
    // Fetch country data from the API
    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}/countries/records`,
      setPopulationAndTemperatureCountryData
    );
  };
  const fetchGlobalData = () => {
    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}/global/records`,
      setGlobalData
    );
  };
  const fetchTemperatureYearRange = () => {
    // Fetch temperature year range from the API
    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}/global/records/temperature-year-range`,
      (data) => {
        setTemperatureYearRange({
          startYear: data.minYear,
          endYear: data.maxYear,
        });
        setTemperatureLimitYearRange({
          minYear: data.minYear,
          maxYear: data.maxYear,
          numberOfYears: data.numberOfYears,
        });
      }
    );
  };

  const fetchPopulationYearRange = () => {
    // Fetch population year range from the API
    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}/global/records/population-year-range`,
      (data) => {
        setPopulationYearRange({
          startYear: data.minYear,
          endYear: data.maxYear,
        });
        setPopulationLimitYearRange({
          minYear: data.minYear,
          maxYear: data.maxYear,
          numberOfYears: data.numberOfYears,
        });
      }
    );
  };

  useEffect(() => {
    fetchGlobalData();
    fetchCountryData();
    fetchTemperatureYearRange();
    fetchPopulationYearRange();
  }, []);
  useEffect(() => {}, [populationAndTemperatureCountryData]);
  const changePopulationAndAverageTemperatureInformation = () => {
    var startYear = -1;
    var endYear = -1;
    if (status === "Population") {
      startYear = populationYearRange.startYear;
      endYear = populationYearRange.endYear;
    } else if (status === "Temperature") {
      startYear = temperatureYearRange.startYear;
      endYear = temperatureYearRange.endYear;
    }

    if (selectedCountryCode === "WLD") {
      const startYearGlobal = globalData.find(
        (element) => element.year === startYear
      );
      const endYearGlobal = globalData.find(
        (element) => element.year === endYear
      );
      const startYearPopulation = startYearGlobal?.population || null;
      const endYearPopulation = endYearGlobal?.population || null;
      const startYearAverageTemperature =
        startYearGlobal?.averageTemperature || null;
      const endYearAverageTemperature =
        endYearGlobal?.averageTemperature || null;
      setPopulationAndAverageTemperatureInformation({
        name: "World",
        code: "WLD",
        startYearPopulation,
        endYearPopulation,
        startYearAverageTemperature,
        endYearAverageTemperature,
        status,
      });
      return;
    }
    const startYearChosenCountry = populationAndTemperatureCountryData.find(
      (element) =>
        element.code === selectedCountryCode && element.year === startYear
    );
    const endYearChosenCountry = populationAndTemperatureCountryData.find(
      (element) =>
        element.code === selectedCountryCode && element.year === endYear
    );

    const startYearPopulation = startYearChosenCountry?.population || null;
    const endYearPopulation = endYearChosenCountry?.population || null;
    const startYearAverageTemperature =
      startYearChosenCountry?.averageTemperature || null;
    const endYearAverageTemperature =
      endYearChosenCountry?.averageTemperature || null;
    const ChosenCountryName = document
      .getElementById(selectedCountryCode)
      ?.getAttribute("title");
    setPopulationAndAverageTemperatureInformation({
      name: ChosenCountryName,
      code: selectedCountryCode,
      startYearPopulation: startYearPopulation,
      endYearPopulation: endYearPopulation,
      startYearAverageTemperature: startYearAverageTemperature,
      endYearAverageTemperature: endYearAverageTemperature,
    });
  };
  useEffect(() => {
    if (populationAndTemperatureCountryData.length === 0) return;
    changePopulationAndAverageTemperatureInformation();
  }, [
    populationAndTemperatureCountryData,
    selectedCountryCode,
    temperatureYearRange,
    populationYearRange,
    status,
  ]);

  const getYear = (status) => {
    const yearRange =
      status === "Population" ? populationYearRange : temperatureYearRange;
    const yearStatus =
      status === "Population" ? populationYearStatus : temperatureYearStatus;
    if (status === "Population") {
      return yearStatus === "Start Year"
        ? yearRange.startYear
        : yearRange.endYear;
    } else if (status === "Temperature") {
      return yearStatus === "Start Year"
        ? yearRange.startYear
        : yearRange.endYear;
    }
    return null;
  };
  return (
    <section
      ref={mapPreviewRef}
      className="flex flex-col justify-around max-w-[2000px] w-full">
      <Title
        populationYearRange={populationLimitYearRange}
        temperatureYearRange={temperatureLimitYearRange}
      />
      <div className="flex flex-row justify-around w-full">
        <ChoroplethMap
          populationAndAverageTemperatureInformation={
            populationAndAverageTemperatureInformation
          }
          changePopulationAndAverageTemperatureInformation={
            changePopulationAndAverageTemperatureInformation
          }
          populationAndTemperatureCountryData={
            populationAndTemperatureCountryData
          }
          status={status}
          year={getYear(status)}
          globalData={globalData}
          setSelectedCountryCode={setSelectedCountryCode}
        />
        <div className="flex flex-col justify-between h-[620px]">
          <SelectedInformation
            status={status}
            setStatus={setStatus}
            yearRange={
              status === "Population"
                ? populationYearRange
                : temperatureYearRange
            }
            countryName={populationAndAverageTemperatureInformation?.name}
            startYearTemperature={
              populationAndAverageTemperatureInformation?.startYearAverageTemperature
            }
            endYearTemperature={
              populationAndAverageTemperatureInformation?.endYearAverageTemperature
            }
            startYearPopulation={
              populationAndAverageTemperatureInformation?.startYearPopulation
            }
            endYearPopulation={
              populationAndAverageTemperatureInformation?.endYearPopulation
            }
          />
          <div className="h-fit flex flex-col bg-lightgrey p-5 rounded-[12px] pl-9 pr-9">
            {status === "Temperature" && (
              <YearSlider
                yearRange={temperatureYearRange}
                setYearRange={setTemperatureYearRange}
                setStatus={setTemperatureYearStatus}
                limitYearRange={temperatureLimitYearRange}
                status={temperatureYearStatus}
              />
            )}
            {status === "Population" && (
              <YearSlider
                yearRange={populationYearRange}
                setYearRange={setPopulationYearRange}
                setStatus={setPopulationYearStatus}
                limitYearRange={populationLimitYearRange}
                status={populationYearStatus}
              />
            )}
          </div>
        </div>
      </div>
    </section>
  );
};

export default MapPreviewSection;
