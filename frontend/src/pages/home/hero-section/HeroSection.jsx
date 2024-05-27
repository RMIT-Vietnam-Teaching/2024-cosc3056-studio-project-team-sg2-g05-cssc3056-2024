import NavigationBar from "./NavigationBar";
import HeroContent from "./HeroContent";

const HeroSection = ({ mapPreviewRef, aboutMeRef, targetRef }) => {
  return (
    <section className="w-full max-w-[2000px] bg-offWhite">
      <NavigationBar
        mapPreviewRef={mapPreviewRef}
        targetRef={targetRef}
        aboutMeRef={aboutMeRef}
      />
      <HeroContent />
    </section>
  );
};

export default HeroSection;
