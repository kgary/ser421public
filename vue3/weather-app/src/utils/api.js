import { API_KEY } from "./config";

const host = "api.openweathermap.org";
const path = '/data/2.5/weather?q='

export async function fetchWeatherAPI(city) {
  try {
    const response = await fetch(
      `https://${host}${path}${city}&appid=${API_KEY}&units=metric`
    );

    if (!response.ok) {
      throw new Error("City not found");
    }

    const data = await response.json();
    return data;
  } catch (error) {
    throw new Error("Error fetching weather data");
  }
}


