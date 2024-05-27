import { useNavigate } from "react-router-dom";
import { ReactComponent as Logo } from "../../../assets/logo.svg";

const NavigationBar = ({ mapPreviewRef, aboutMeRef, targetRef }) => {
  const navigate = useNavigate();

  return (
    <nav className="w-full flex flex-row justify-between items-center cursor-pointer">
      <Logo
        className="w-[300px] h-[80px] ml-[40px]"
        onClick={() => navigate("/")}
      />
      <div className="flex flex-row justify-around w-[40%] mr-[30px]">
        <h1
          className="text-[16px] font-bold text-primary 
            hover:cursor-pointer transition-colors duration-300 caret-transparent"
          onClick={() => navigate(`/shallow-glance/world`)}>
          Feature
        </h1>
        <h1
          className="text-[16px] font-bold text-primary 
            hover:cursor-pointer transition-colors duration-300 caret-transparent"
          onClick={() => {
            if (mapPreviewRef.current) {
              mapPreviewRef.current.scrollIntoView({ behavior: "smooth" });
            }
          }}>
          Map Preview
        </h1>
        <h1
          className="text-[16px] font-bold text-primary 
            hover:cursor-pointer transition-colors duration-300 caret-transparent"
          onClick={() => {
            if (targetRef.current) {
              targetRef.current.scrollIntoView({ behavior: "smooth" });
            }
          }}>
          Our Target
        </h1>
        <h1
          className="text-[16px] font-bold text-primary 
            hover:cursor-pointer transition-colors duration-300 caret-transparent"
          onClick={() => {
            if (aboutMeRef.current) {
              aboutMeRef.current.scrollIntoView({ behavior: "smooth" });
            }
          }}>
          About me
        </h1>
      </div>
    </nav>
  );
};

export default NavigationBar;
