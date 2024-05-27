const TableHeader = ({}) => {
  return (
    <div className="flex w-full mb-1 items-center">
      <h1 className="text-[12px] font-bold text-[#8497A8] w-[5%] text-center">
        No
      </h1>
      <div className="w-full flex flex-row items-center justify-between">
        <h1 className="text-[12px] font-bold text-[#8497A8] w-[20%] text-start ml-8">
          Country
        </h1>
        <div className="w-[350px] flex justify-start mr-11">
          <h1
            className="text-[12px] font-bold flex flex-row 
                    items-center text-[#8497A8]">
            Average Temperature Change
          </h1>
        </div>
        <div className="w-[350px] flex justify-start mr-11">
          <h1
            className="text-[12px] font-bold flex flex-row 
                    items-center text-[#8497A8]">
            Population Change
          </h1>
        </div>
      </div>
    </div>
  );
};
export default TableHeader;
