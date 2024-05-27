import { useNavigate } from "react-router-dom";
import { ReactComponent as Logo } from "../../assets/logo.svg";
import DualSliderWithInputs from "../common/dual-slider-with-inputs/DualSliderWithInputs";
import DropdownMenu from "./DropdownMenu";

const SettingsSideBar = ({
  yearRange,
  setYearRange,
  limitedYearRange,
  noYearRange = false,
}) => {
  const navigate = useNavigate();
  return (
    <div
      className="fixed top-0 left-0 h-full w-[310px] bg-lightgrey 
    flex flex-col items-center justify-between">
      <div className="flex flex-col justify-start h-full mb-[60px] relative ml-[14px]">
        <Logo
          className="w-[80%] h-[100px] cursor-pointer"
          onClick={() => {
            navigate("/");
          }}
        />
        {!noYearRange && (
          <DualSliderWithInputs
            firstFieldLabel="Start year"
            secondFiendLabel="End Year"
            firstValue={yearRange.startYear}
            secondValue={yearRange.endYear}
            min={limitedYearRange.minYear}
            max={limitedYearRange.maxYear}
            onFirstFieldChange={(value) => {
              setYearRange({ ...yearRange, startYear: value });
            }}
            onSecondFieldChange={(value) => {
              setYearRange({ ...yearRange, endYear: value });
            }}
          />
        )}
      </div>
      <div className="ml-[14px] flex flex-col h-[800px] justify-evenly items-start">
        <DropdownMenu
          title="Analyze Temperature And Population Change"
          links={[
            {
              title: "By World Region",
              url: "/shallow-glance/world",
            },
            {
              title: "By Countries",
              url: "/shallow-glance/countries",
            },
            {
              title: "By Cities And States",
              url: "/shallow-glance/cities-and-states",
            },
          ]}
        />
        <DropdownMenu
          title="Calculate Average Temperature Over Periods"
          links={[
            {
              title: "By Countries",
              url: "/deep-dive/temperature-change-over-periods/countries",
            },
            {
              title: "By Cities",
              url: "/deep-dive/temperature-change-over-periods/cities",
            },
            {
              title: "By States",
              url: "/deep-dive/temperature-change-over-periods/states",
            },
          ]}
        />
        <DropdownMenu
          title="Find Similar Periods With Selected Region"
          links={[
            {
              title: "By Countries",
              url: "/deep-dive/similar-period/countries",
            },
            {
              title: "By States",
              url: "/deep-dive/similar-period/states",
            },
            {
              title: "By Cities",
              url: "/deep-dive/similar-period/cities",
            },
          ]}
        />
        
      </div>
    </div>
  );
};
export default SettingsSideBar;
