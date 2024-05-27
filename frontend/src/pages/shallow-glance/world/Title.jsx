import React from "react";

import SortArrowIcon from "./SortArrowIcon.jsx";

const Title = ({
  title,
  color,
  sortOrder,
  sortWith,
  setSortWith,
  setSortOrder,
  value,
}) => {
  return (
    <div className="w-[350px] flex justify-start mr-11">
      <h1
        style={{ color: color }}
        className="text-[12px] font-bold flex flex-row 
        items-center cursor-pointer select-none"
        onClick={() => {
          if (sortWith === value) {
            setSortOrder((prev) => (prev === "ASC" ? "DESC" : "ASC"));
          } else {
            setSortWith(value);
            setSortOrder("ASC");
          }
        }}>
        {title} Change
        <SortArrowIcon
          sortWith={sortWith}
          sortOrder={sortOrder}
          value={value}
        />
      </h1>
    </div>
  );
};

export default Title;
