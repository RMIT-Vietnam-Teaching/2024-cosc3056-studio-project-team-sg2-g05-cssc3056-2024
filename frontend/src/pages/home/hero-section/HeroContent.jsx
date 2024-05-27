import { useNavigate } from "react-router-dom";
import { ReactComponent as Ellipse } from "../../../assets/ellipse.svg";
import { ReactComponent as Leaf } from "../../../assets/leaf.svg";
import { ReactComponent as Rectangle } from "../../../assets/rectangle.svg";
import { ReactComponent as Dots } from "../../../assets/dots.svg";

const HeroContent = () => {
  const navigate = useNavigate();

  return (
    <div className="relative">
      <div className="flex flex-col justify-center h-full w-[30%] mt-[7%] ml-[80px] caret-transparent">
        <h1 className="text-[56px] font-bold text-primary z-10">
          Green Today, Better Tomorrow
        </h1>
        <p className="text-[20px] font-semibold text-black mt-[16px]  caret-transparent z-10">
          We tackle climate change by making temperature and population data
          accessible. Scientists and governments can analyze trends, while the
          public explores clear visualizations.
        </p>
        <button
          className="bg-primary font-bold text-offWhite rounded-full text-center h-[50px] w-[40%] mt-[30px] hover:bg-[#E4E7F1] hover:text-primary hover:shadow-md focus:outline-none transition duration-300 ease-in-out text-[20px] caret-transparent z-10"
          onClick={() => navigate("/shallow-glance/world")}>
          Explore
        </button>
      </div>
      <Ellipse className="absolute top-[-100px] right-0" />
      <Leaf className="absolute bottom-[30%] left-[55%]" />
      <Rectangle className="absolute bottom-[-30px] left-0 " />
      <Dots className="absolute bottom-[-30px] right-0" />
    </div>
  );
};

export default HeroContent;
