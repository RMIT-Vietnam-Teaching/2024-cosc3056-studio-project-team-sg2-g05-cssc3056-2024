import DualSwitchButton from "../../../components/common/dual-switch-button/DualSwitchButton";
import DualSliderWithInputs from "../../../components/common/dual-slider-with-inputs/DualSliderWithInputs";
const YearSlider = ({
  yearRange,
  setYearRange,
  setStatus,
  limitYearRange,
  status,
}) => {
  return (
    <>
      <DualSwitchButton
        label="View by"
        leftLabel="Start Year"
        rightLabel="End Year"
        leftValue="Start Year"
        rightValue="End Year"
        value={status}
        onButtonClick={(value) => {
          setStatus(value);
        }}
      />
      <div className="h-2"></div>
      <DualSliderWithInputs
        firstFieldLabel="Start Year"
        secondFiendLabel="End Year"
        firstValue={yearRange.startYear}
        secondValue={yearRange.endYear}
        onFirstFieldChange={(newValue) => {
          setYearRange({
            ...yearRange,
            startYear: newValue,
          });
        }}
        onSecondFieldChange={(newValue) => {
          setYearRange({
            ...yearRange,
            endYear: newValue,
          });
        }}
        min={limitYearRange.minYear}
        max={limitYearRange.maxYear}
      />
    </>
  );
};
export default YearSlider;
