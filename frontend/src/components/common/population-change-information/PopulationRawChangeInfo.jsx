import { ReactComponent as Line } from "../../../assets/long-change-line.svg";

const PopulationRawChangeInfo = ({ value, color }) => {
  return (
    <div className="flex flex-col items-center mb-[8px]">
      {value ? (
        <h2 style={{ color }} className="text-[12px] mb-[-4px] font-bold">
          {value > 0 && "+"}
          {value.toLocaleString()}
        </h2>
      ) : (
        <h2 className="text-[12px] mb-[-4px] font-bold text-[#E4E7F1]">
          {"x"}{" "}
          {/* dấu x trùng màu với background để flexbox không bị xáo trộn*/}
        </h2>
      )}
      <Line />
    </div>
  );
};
export default PopulationRawChangeInfo;
