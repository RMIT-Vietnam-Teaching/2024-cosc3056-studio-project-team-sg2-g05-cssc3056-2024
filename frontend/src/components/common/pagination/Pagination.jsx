import { useState, useRef, useEffect } from "react";

import ArrowLeftButton from "./ArrowLeftButton";
import ArrowRightButton from "./ArrowRightButton";
import DoubleArrowLeftButton from "./DoubleArrowLeftButton";
import DoubleArrowRightButton from "./DoubleArrowRightButton";
import PageNumber from "./PageNumber";
import PageInput from "./PageInput";
const Pagination = ({ selectedPage, totalPages, onPageChange }) => {
  const [listOfPage, setListOfPage] = useState([]);
  useEffect(() => {
    const displayedListOfPage = [];
    if (!totalPages) return setListOfPage([]);
    if (totalPages <= 8)
      for (let i = 1; i <= totalPages; i++) displayedListOfPage.push(i);
    else if (selectedPage <= 4) {
      for (let i = 1; i <= 8; i++) {
        displayedListOfPage.push(i);
      }
    } else if (selectedPage >= totalPages - 3) {
      for (let i = totalPages - 7; i <= totalPages; i++) {
        displayedListOfPage.push(i);
      }
    } else {
      for (let i = selectedPage - 3; i <= selectedPage + 4; i++) {
        displayedListOfPage.push(i);
      }
    }

    setListOfPage(displayedListOfPage);
  }, [selectedPage, totalPages]);

  return (
    totalPages > 0 && (
      <div className="flex flex-row items-center h-full justify-start">
        <DoubleArrowLeftButton
          disabled={selectedPage === 1}
          handleClick={() => onPageChange(1)}
        />
        <ArrowLeftButton
          disabled={selectedPage === 1}
          handleClick={() => onPageChange(selectedPage - 1)}
        />
        {listOfPage.map((pageNumber) => (
          <PageNumber
            key={pageNumber}
            pageNumber={pageNumber}
            isSelected={pageNumber === selectedPage}
            handleClick={() => onPageChange(pageNumber)}
          />
        ))}
        <ArrowRightButton
          disabled={selectedPage === totalPages}
          handleClick={() => onPageChange(selectedPage + 1)}
        />
        <DoubleArrowRightButton
          disabled={selectedPage === totalPages}
          handleClick={() => onPageChange(totalPages)}
        />
        <PageInput
          selectedPage={selectedPage}
          setSelectedPage={(newValue) => onPageChange(newValue)}
          totalPages={totalPages}
        />
      </div>
    )
  );
};

export default Pagination;
