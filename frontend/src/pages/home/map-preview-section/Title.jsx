const Title = ({ temperatureYearRange, populationYearRange }) => {
  return (
    <div className="flex flex-col justify-center items-center mb-4">
      <h1 className="text-[36px] font-bold text-primary">Map Preview</h1>
      <h2 className="text-[20px] text-primary font-semibold">
        This map shows population data spanning{" "}
        {populationYearRange?.numberOfYears} years, from{" "}
        {populationYearRange?.minYear} to {populationYearRange?.maxYear}, and
        temperature data spanning {temperatureYearRange?.numberOfYears} years,
        from {temperatureYearRange?.minYear + " "} to{" "}
        {temperatureYearRange?.maxYear}
      </h2>
    </div>
  );
};
export default Title;
