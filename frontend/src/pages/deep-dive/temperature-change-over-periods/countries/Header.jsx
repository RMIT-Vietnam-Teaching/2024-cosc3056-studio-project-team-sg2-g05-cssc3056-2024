const Header = ({ isCountry }) => (
  <div className="flex w-full mb-1 items-center justify-start">
    <h1 className="text-[12px] font-bold text-[#8497A8] w-[5.4%] text-center">
      Rank
    </h1>
    <div className="w-full flex flex-row items-center justify-start">
      <h1 className="text-[12px] font-bold text-[#8497A8] w-[20%] mr-10 text-start ml-8">
        Name
      </h1>
      <div className="w-[340px] flex justify-start ml-[90px]">
        <h1
          className="text-[12px] font-bold flex flex-row 
                    items-center text-[#8497A8] ">
          Average Value Of Temperature
        </h1>
      </div>
      <h1
        className="text-[12px] font-bold flex flex-row 
                    items-center text-[#8497A8] ">
        {!isCountry && "Country name"}
      </h1>
    </div>
  </div>
);
export default Header;
