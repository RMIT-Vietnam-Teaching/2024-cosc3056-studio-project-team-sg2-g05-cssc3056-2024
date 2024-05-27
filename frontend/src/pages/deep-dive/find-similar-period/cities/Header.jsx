const Header = ({ isCountry }) => (
  <div className="flex w-full mb-1 items-center justify-start">
    <h1 className="text-[12px] font-bold text-[#8497A8] w-[5.4%] text-center">
      Rank
    </h1>
    <div className="w-full flex flex-row items-center justify-start">
      <h1 className="text-[12px] font-bold text-[#8497A8] w-[20%] mr-[25px] text-start ml-8">
        Name
      </h1>
      <h1 className="text-[12px] font-bold text-[#8497A8] w-fit ml-8 mr-[8px]">
        Start Year
      </h1>
      <h1 className="text-[12px] font-bold text-[#8497A8] w-fit ml-8 mr-4">
        End Year
      </h1>
      <h1 className="text-[12px] font-bold text-[#8497A8] w-fit ml-8 ">
        Average Temperature
      </h1>
      <h1 className="text-[12px] font-bold text-[#8497A8] w-fit ml-7 mr-[110px]">
        Difference
      </h1>
      {isCountry && (
        <h1 className="text-[12px] font-bold text-[#8497A8] w-[20%]">
          Country Name
        </h1>
      )}
    </div>
  </div>
);
export default Header;
