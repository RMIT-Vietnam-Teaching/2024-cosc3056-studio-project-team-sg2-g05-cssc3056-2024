import { fetchData } from "../../../api/api";
import { useEffect, useState } from "react";
import Personas from "./Personas";
export default function TargetSection({ targetRef }) {
  const [personas, setPersonas] = useState([]);
  useEffect(() => {
    fetchData(`${process.env.REACT_APP_API_BASE_URL}/personas`, setPersonas);
  }, []);
  return (
    <section
      ref={targetRef}
      className="flex flex-col mt-10 justify-center max-w-[2000px] w-full items-center">
      <h1 className="text-[40px] font-bold text-primary">Our Target</h1>
      <div className="flex flex-row justify-evenly w-full mt-2">
        {personas.map((persona) => (
          <Personas key={persona.id} persona={persona} />
        ))}
      </div>
    </section>
  );
}
