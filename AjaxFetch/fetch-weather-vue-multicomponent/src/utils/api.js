import { API_KEY } from "./config";

const host = "api.openweathermap.org";
const path = '/data/2.5/weather?q=';

export async function fetchWeatherAPI(city) {
  try {
    const response = await fetch(
      `https://${host}${path}${city}&appid=${API_KEY}&units=metric`
    );

    const responseCode = response.status;

    if (responseCode === 401) {
      const errorData = await response.json();
      return { error: true, code: 401, message: errorData.message };
    } else if (responseCode === 404) {
      return { error: true, code: 404, message: "City not found" };
    } else if (!response.ok) {
      throw new Error("An unknown error occurred");
    }

    const data = await response.json();
    return { error: false, code: 200, data };
  } catch (error) {
    return { error: true, code: 500, message: "Error fetching weather data" };
  }
}
