import { useEffect, useState } from "react";
import Box from "@mui/material/Box";
import Tab from "@mui/material/Tab";
import TabContext from "@mui/lab/TabContext";
import TabList from "@mui/lab/TabList";

import TabPanel from "@mui/lab/TabPanel";
import TemperatureChangeDisplay from "./TemperatureChangeDisplay";

const TemperatureChangeWithInPeriod = ({ data, type }) => {
  const [value, setValue] = useState("1");

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  useEffect(() => {
    setValue("0");
  }, [data]);
  return (
    <Box sx={{ width: "100%", typography: "body1" }}>
      <TabContext value={value}>
        <Box sx={{ borderBottom: 1, borderColor: "divider" }}>
          <TabList
            onChange={handleChange}
            variant="scrollable"
            scrollButtons="auto">
            {data.map((period, index) => (
              <Tab
                key={index}
                label={`${period.startYear} - ${
                  period.startYear + period.yearLength - 1
                }`}
                value={index.toString()}
              />
            ))}
          </TabList>
        </Box>
        {data.map((period, index) => (
          <TabPanel key={index} value={index.toString()}>
            <TemperatureChangeDisplay
              type={type}
              data={
                period.averageValueOfTemperatureCountryRecordDTOs ||
                period.averageValueOfTemperatureCityRecordDTOs ||
                period.averageValueOfTemperatureStateRecordDTOs
              }
            />
          </TabPanel>
        ))}
      </TabContext>
    </Box>
  );
};
export default TemperatureChangeWithInPeriod;
