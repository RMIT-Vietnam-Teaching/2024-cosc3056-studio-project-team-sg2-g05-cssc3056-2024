import Title from "./Title";

const TableHeader = ({ sortWith, sortOrder, setSortWith, setSortOrder }) => {
  const getTitleColor = (title) => {
    if (sortWith === title) {
      return "#173c57";
    }
    return "#8497A8";
  };

  return (
    <div className="flex w-full mb-1 items-center">
      <h1 className="text-[12px] font-bold text-[#8497A8] w-[5.4%] text-center">
        No
      </h1>
      <div className="w-full flex flex-row items-center justify-between">
        <h1 className="text-[12px] font-bold text-[#8497A8] w-[20%] text-start ml-8">
          Country
        </h1>
        <Title
          title="Average Temperature"
          value="Temperature"
          color={getTitleColor("Temperature")}
          setSortWith={setSortWith}
          setSortOrder={setSortOrder}
          sortOrder={sortOrder}
          sortWith={sortWith}
        />
        <Title
          title="Population"
          value="Population"
          color={getTitleColor("Population")}
          setSortWith={setSortWith}
          setSortOrder={setSortOrder}
          sortOrder={sortOrder}
          sortWith={sortWith}
        />
      </div>
    </div>
  );
};
export default TableHeader;
