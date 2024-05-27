import React from "react";
import { Routes, Route } from "react-router-dom";
import "./App.css";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import Home from "./pages/home/Home.jsx";
import ShallowGlanceWorld from "./pages/shallow-glance/world/ShallowGlanceWorld.jsx";
import ShallowGlanceCountries from "./pages/shallow-glance/countries/ShallowGlanceCountries.jsx";
import ShallowGlanceCitesAndStates from "./pages/shallow-glance/cities-and-states/ShallowGlanceCitiesAndStates.jsx";
import SimilarPeriodCities from "./pages/deep-dive/find-similar-period/cities/SimilarPeriodCities.jsx";
import TemperatureChangeOverPeriodsCountries from "./pages/deep-dive/temperature-change-over-periods/countries/TemperatureChangeOverPeriodsCountries.jsx";
import SimilarPeriodStates from "./pages/deep-dive/find-similar-period/states/SimilarPeriodStates.jsx";
import SimilarPeriodCountries from "./pages/deep-dive/find-similar-period/countries/SimilarPeriodCountries.jsx";
import TemperatureChangeOverPeriodsCities from "./pages/deep-dive/temperature-change-over-periods/cities/TemperatureChangeOverPeriodsCities.jsx";
import TemperatureChangeOverPeriodsStates from "./pages/deep-dive/temperature-change-over-periods/states/TemperatureChangeOverPeriodsStates.jsx";
function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/shallow-glance/world" element={<ShallowGlanceWorld />} />
        <Route
          path="/shallow-glance/countries"
          element={<ShallowGlanceCountries />}
        />
        <Route
          path="/shallow-glance/cities-and-states"
          element={<ShallowGlanceCitesAndStates />}
        />
        <Route
          path="/deep-dive/similar-period/cities"
          element={<SimilarPeriodCities />}
        />
        <Route
          path="/deep-dive/temperature-change-over-periods/countries"
          element={<TemperatureChangeOverPeriodsCountries />}
        />
        <Route
          path="/deep-dive/temperature-change-over-periods/cities"
          element={<TemperatureChangeOverPeriodsCities />}
        />
        <Route
          path="/deep-dive/temperature-change-over-periods/states"
          element={<TemperatureChangeOverPeriodsStates />}
        />
        <Route
          path="/deep-dive/similar-period/states"
          element={<SimilarPeriodStates />}
        />
        <Route
          path="/deep-dive/similar-period/countries"
          element={<SimilarPeriodCountries />}
        />
      </Routes>
      <ToastContainer />
    </>
  );
}

export default App;
