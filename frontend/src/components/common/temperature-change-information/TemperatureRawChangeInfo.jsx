import { ReactComponent as Line } from "../../../assets/change-line.svg";

const TemperatureRawChangeInfo = ({ change, color }) => (
  <div className="flex flex-col items-center mb-[8px]">
    {change ? (
      <h2 className="text-[12px] mb-[-4px] font-bold " style={{ color }}>
        {change > 0 && "+"}
        {change}°C
      </h2>
    ) : (
      <h2 className="text-[12px] mb-[-4px] font-bold text-[#E4E7F1] select-none">
        {"x"} {/* dấu x trùng màu với background để flexbox không bị xáo trộn*/}
      </h2>
    )}
    <Line />
  </div>
);

export default TemperatureRawChangeInfo;
