import { useEffect } from "react";
import { ReactComponent as SVGMap } from "../../../assets/world.svg";
import WorldInformation from "./WorldInformation";

const ChoroplethMap = ({
  year,
  populationAndTemperatureCountryData,
  status,
  setSelectedCountryCode,
  globalData,
}) => {
  const getWorldTemperature = () => {
    const world = globalData.find((element) => element.year === year);

    if (world?.averageTemperature === undefined) return "No Data";
    return world?.averageTemperature + "°C";
  };

  const getWorldPopulation = () => {
    const world = globalData.find((element) => element.year === year);
    if (!world?.population) return "No Data";
    return world?.population.toLocaleString();
  };
  useEffect(() => {
    const svg = document.getElementById("WLD");
    svg?.querySelectorAll("path:not(#WLD)").forEach((path) => {
      path.style.cursor = "pointer";
      path.style.strokeWidth = "0.6px";
      path.addEventListener("mouseover", () => {
        path.style.strokeWidth = "2px";
      });
      path.addEventListener("mouseout", () => {
        path.style.strokeWidth = "0.3px";
      });
    });
  }, []); //set cursor to pointer and hover for all countries except the world
  function getColorByData(data, status) {
    if (status === "Population") {
      return getColorByPopulation(data);
    } else {
      return getColorByTemperature(data);
    }
  }

  const getColorByTemperature = (temperature) => {
    return temperature > 25
      ? "#d6363b"
      : temperature > 20
      ? "#f56e53"
      : temperature > 15
      ? "#fcb16a"
      : temperature > 10
      ? "#ffe5ad"
      : temperature > 5
      ? "#34a5d9"
      : temperature > 0
      ? "#a1c8e3"
      : "#e4ecf5";
  };

  const getColorByPopulation = (population) => {
    return population > 800_000_000
      ? "#072134"
      : population > 500_000_000
      ? "#173c57"
      : population > 200_000_000
      ? "#21435B"
      : population > 100_000_000
      ? "#21435B"
      : population > 50_000_000
      ? "#205175"
      : population > 20_000_000
      ? "#457190"
      : population > 10_000_000
      ? "#8396a7"
      : "#8396a7";
  };
  const changeMapColor = () => {
    const svg = document.getElementById("WLD");

    if (populationAndTemperatureCountryData === undefined) return;
    if (svg) {
      const pathMap = new Map();
      //initialize the pathMap with false values for all countries except the world
      svg.querySelectorAll("path").forEach((path) => {
        const id = path.id;
        pathMap.set(id, false); //pathMap[id] = false will not work;
      });
      // set the color of the countries that have data
      populationAndTemperatureCountryData.forEach((element) => {
        if (element.year !== year) return;
        if (status === "Population" && element.population === 0) return;
        if (status === "Temperature" && element.averageTemperature === null)
          return;
        const countryElement = document.getElementById(element.code);
        if (element.population !== 0 || element.averageTemperature !== 0) {
          pathMap.set(element.code, true);
        }
        if (countryElement) {
          countryElement.setAttribute(
            "fill",
            status === "Population"
              ? getColorByData(element.population || 0, status)
              : status === "Temperature"
              ? getColorByData(element.averageTemperature || 0, status)
              : ""
          );
        }
      });
      //set the color of the countries that have no data to grey
      for (const [key, value] of pathMap.entries()) {
        if (!value) {
          const countryElement = document.getElementById(key);
          if (countryElement) {
            countryElement.setAttribute("fill", "grey");
          }
        }
      }
    }
  };
  useEffect(() => {
    changeMapColor(year);
  }, [populationAndTemperatureCountryData, status, year]);
  return (
    <div className="h-full relative">
      <SVGMap
        className="h-full stroke-[#F0F1F8] stroke-[0.3px]"
        onClick={(e) => {
          setSelectedCountryCode(e.target.id);
        }}
      />
      <div className="absolute left-1 bottom-1 w-full">
        <WorldInformation
          year={year}
          temperature={getWorldTemperature()}
          population={getWorldPopulation()}
        />
      </div>
    </div>
  );
};

export default ChoroplethMap;
