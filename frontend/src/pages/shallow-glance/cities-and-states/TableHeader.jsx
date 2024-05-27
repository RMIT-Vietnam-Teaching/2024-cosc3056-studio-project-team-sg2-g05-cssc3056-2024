const TableHeader = (status) => (
  <div className="flex w-full mb-1 items-center justify-start">
    <h1 className="text-[12px] font-bold text-[#8497A8] w-[5.4%] text-center">
      No
    </h1>
    <div className="w-full flex flex-row items-center justify-start">
      <h1 className="text-[12px] font-bold text-[#8497A8] w-[20%] mr-10 text-start ml-8">
        City
      </h1>
      <div className="w-[340px] flex justify-start ml-[170px]">
        <h1
          className="text-[12px] font-bold flex flex-row 
                    items-center text-[#8497A8] ">
          Temperature Change
        </h1>
      </div>
      <div className="w-[200px] flex justify-start mr-3">
        <h1
          className="text-[12px] font-bold flex flex-row 
                    items-center text-[#8497A8]">
          Average Percentage Change
        </h1>
      </div>
    </div>
  </div>
);
export default TableHeader;
