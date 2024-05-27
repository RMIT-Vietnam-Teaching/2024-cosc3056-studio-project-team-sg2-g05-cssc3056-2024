const Personas = ({ persona }) => {
  return (
    <div
      className="h-[650px] w-[450px] bg-lightgrey 
    rounded-[16px] justify-start flex-col">
      <h1 className="w-full text-center mt-4 text-primary text-[34px] font-semibold">
        {persona.name}
      </h1>
      <div className="flex flex-col ml-3">
        <h1 className="text-primary font-bold">Gender: {persona.gender}</h1>
        <h1 className="text-primary font-bold">Age: {persona.age}</h1>
        <h1 className="text-primary font-bold">Location: {persona.location}</h1>
        <h1 className="text-primary font-bold">Background:</h1>
        {persona.background.map((background) => (
          <h1 className="text-primary text-[14px] font-semibold">
            {"+"}
            {background}
          </h1>
        ))}
        <h1 className="text-primary font-bold">Needs and Goals:</h1>
        {persona.needsAndGoals.map((needAndGoal) => (
          <h1 className="text-primary text-[14px] font-semibold">
            {"+"}
            {needAndGoal}
          </h1>
        ))}
        <h1 className="text-primary font-bold">Skill and Experiences:</h1>
        {persona.skillAndExperiences.map((skillAndExperience) => (
          <h1 className="text-primary text-[14px] font-semibold">
            {"+"}
            {skillAndExperience}
          </h1>
        ))}
        <h1 className="text-primary font-bold">Pain point:</h1>
        {persona.painPoints.map((painPoint) => (
          <h1 className="text-primary text-[14px] font-semibold">
            {"+"}
            {painPoint}
          </h1>
        ))}
      </div>
    </div>
  );
};
export default Personas;
