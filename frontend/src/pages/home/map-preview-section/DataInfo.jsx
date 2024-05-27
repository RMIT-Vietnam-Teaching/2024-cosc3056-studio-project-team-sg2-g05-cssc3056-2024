const DataInfo = ({ label, content }) => (
  <div className="flex flex-row justify-between w-full items-center">
    <h1 className="font-bold text-[#173C57] ml-[16px] ">{label}</h1>
    <p className="font-semibold text-[#173C57] mr-[40px] bg-[#F0F1F8] rounded-[6px] w-[25%] text-center pt-[4px] pb-[4px] text-[16px]">
      {content ? content : "N/A"}
    </p>
  </div>
);
export default DataInfo;
