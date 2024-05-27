import ImageWithWarning from "../image-with-warning/ImageWithWarning";

const PopulationInfo = ({ population, color, year, id, name, type }) => (
  <h1 style={{ color }} className="font-bold text-[16px] w-fit">
    {population ? (
      population.toLocaleString()
    ) : (
      <h1 className="flex">
        N/A{" "}
        <ImageWithWarning
          id={id}
          type={type}
          typeOfData="Population"
          unavailableYear={year}
          name={name}
        />
      </h1>
    )}
  </h1>
);
export default PopulationInfo;
