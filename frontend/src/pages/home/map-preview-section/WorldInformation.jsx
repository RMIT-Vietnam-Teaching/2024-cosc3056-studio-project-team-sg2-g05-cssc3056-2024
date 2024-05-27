const WorldInformation = ({ year, population, temperature }) => {
  return (
    <div className="absolute bottom-[12px] left-[0px] flex flex-col items-start mb-12">
      <h1 className="text-[32px] font-bold text-[#173C57] caret-transparent">
        World - {year}
      </h1>
      <div className="flex flex-row justify-center">
        <h2 className="text-[16px] font-bold text-[#8396a7] caret-transparent">
          Population:
        </h2>
        <h2 className="text-[16px] font-bold text-[#8396a7] caret-transparent ml-2">
          {population}
        </h2>
      </div>
      <div className="flex flex-row justify-center">
        <h2 className="text-[16px] font-bold text-[#8396a7] caret-transparent">
          Average Temperature:
        </h2>
        <h2 className="text-[16px] font-bold text-[#8396a7] caret-transparent ml-2">
          {temperature}
        </h2>
      </div>
    </div>
  );
};
export default WorldInformation;
