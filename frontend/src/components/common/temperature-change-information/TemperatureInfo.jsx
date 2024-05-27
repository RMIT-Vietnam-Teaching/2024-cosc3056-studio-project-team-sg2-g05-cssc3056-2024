import ImageWithWarning from "../image-with-warning/ImageWithWarning";

const TemperatureInfo = ({ temperature, color, id, type, year, name }) => (
  <h1 className="flex flex-row font-bold text-[16px] w-fit" style={{ color }}>
    {temperature ? temperature + "°C" : "N/A"}
    {!temperature && (
      <ImageWithWarning
        id={id}
        type={type}
        typeOfData={"Temperature"}
        unavailableYear={year}
        name={name}
      />
    )}
  </h1>
);
export default TemperatureInfo;
