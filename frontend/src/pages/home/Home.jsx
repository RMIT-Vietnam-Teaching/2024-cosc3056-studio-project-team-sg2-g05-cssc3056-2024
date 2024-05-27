import React from "react";
import HeroSection from "./hero-section/HeroSection";
import MapPreviewSection from "./map-preview-section/MapPreviewSection";
import TargetSection from "./target-section/TargetSection";
import AboutMeSection from "./about-me-section/AboutMeSection";
import { useRef } from "react";
function Home() {
  const mapPreviewRef = useRef(null);
  const targetRef = useRef(null);
  const aboutMeRef = useRef(null);
  return (
    <div className="flex flex-col items-center p-0 m-0 h-full w-full bg-offWhite">
      <HeroSection
        mapPreviewRef={mapPreviewRef}
        targetRef={targetRef}
        aboutMeRef={aboutMeRef}
      />
      <div className="h-10"></div>
      <MapPreviewSection mapPreviewRef={mapPreviewRef} />
      <TargetSection targetRef={targetRef} />
      <AboutMeSection aboutMeRef={aboutMeRef} />
    </div>
  );
}

export default Home;
