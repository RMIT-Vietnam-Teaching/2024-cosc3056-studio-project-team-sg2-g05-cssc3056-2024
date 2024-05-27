// api.js
const fetchData = async (url, setState, setIsLoading = null) => {
  if (setIsLoading) setIsLoading(true);
  try {
    const response = await fetch(url);
    const data = await response.json();
    setState(data);
    console.log(data);
  } catch (error) {
    console.error("Error fetching data:", error);
  } finally {
    if (setIsLoading) setIsLoading(false);
  }
};

export { fetchData };
