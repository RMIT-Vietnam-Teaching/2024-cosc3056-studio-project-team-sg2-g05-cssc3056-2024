import { ReactComponent as SortUp } from "../../../assets/arrow-up.svg";
import { ReactComponent as SortDown } from "../../../assets/arrow-down.svg";

const SortArrowIcon = ({ sortWith, sortOrder, value }) => {
  if (sortWith === value) {
    if (sortOrder === "ASC") {
      return <SortUp className="w-[20px] fill-primary" />;
    } else {
      return <SortDown className="w-[20px] fill-primary" />;
    }
  }
  return null;
};
export default SortArrowIcon;
