import React, { useEffect, useState } from "react";
import { fetchData } from "../../../api/api";
const AboutMeSection = ({ aboutMeRef }) => {
  const [teamMembers, setTeamMembers] = useState([]);
  useEffect(() => {
    fetchData(
      `${process.env.REACT_APP_API_BASE_URL}/team-members`,
      setTeamMembers
    );
  }, []);
  return (
    <section
      ref={aboutMeRef}
      className="flex flex-col mt-10 justify-center max-w-[2000px] w-full items-center">
      <h1 className="text-[40px] font-bold text-primary">About me</h1>
      <div
        className="h-fit pb-4 mb-3 w-[450px] bg-lightgrey 
    rounded-[16px] justify-start flex-col">
        <h1 className="w-full text-center mt-4 text-primary text-[34px] font-semibold">
          {teamMembers[0]?.name}
        </h1>
        <div className="ml-4 mt-4">
          <h1 className="text-primary font-bold">Age: {teamMembers[0]?.age}</h1>
        </div>
        <div className="ml-4 mt-4">
          <h1 className="text-primary font-bold">
            Contact: {teamMembers[0]?.gmail}
          </h1>
        </div>
        <h1 className="w-full text-center mt-4 text-primary text-[14px] font-semibold">
          {"` "}
          {teamMembers[0]?.quote} {"`"}
        </h1>
      </div>
      <div className="mt-6 flex flex-col underline items-center text-primary">
        <a href="https://www.flaticon.com/free-icons/warning" title="warning icons" className="">Warning icons created by Good Ware - Flaticon</a>
        <a href="https://www.flaticon.com/free-icons/no-data" title="no data icons">No data icons created by Freepik - Flaticon</a>
        <a href="https://thenounproject.com/browse/icons/term/sort/" target="_blank" title="Sort Icons">Sort by Riyan Resdian from Noun Project(CC BY 3.0)</a> 
        <a href="https://www.flaticon.com/free-icons/miscellaneous" title="miscellaneous icons">Miscellaneous icons created by Vectorsclub - Flaticon</a>
        <a href="https://creativecommons.org/licenses/by/4.0/">World map by mapsvg.com (Licensed under CC BY 4.0)</a>
        </div>
    </section>
  );
};
export default AboutMeSection;
