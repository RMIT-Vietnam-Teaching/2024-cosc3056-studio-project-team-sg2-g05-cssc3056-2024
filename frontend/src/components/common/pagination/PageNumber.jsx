const PageNumber = ({ pageNumber, isSelected, handleClick }) => {
  return (
    <h1
      onClick={handleClick}
      style={{
        backgroundColor: isSelected ? "#173C57" : "#F0F1F8",
        color: isSelected ? "#F0F1F8" : "#173C57",
      }}
      className={`text-center caret-transparent w-[32px] pt-[6px] h-[32px] rounded-full
        text-[12px] font-bold ml-2 cursor-pointer`}>
      {pageNumber}
    </h1>
  );
};
export default PageNumber;
