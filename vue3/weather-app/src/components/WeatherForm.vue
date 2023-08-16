<script setup>
import { ref } from "vue";
import { fetchWeatherAPI } from "../utils/api";
import WeatherReport from "./WeatherReport.vue";

const city = ref("");
const weatherData = ref([]);
const error = ref(null);

const fetchWeather = async () => {
  error.value = null;
  try {
    const data = await fetchWeatherAPI(city.value);
    weatherData.value.push({
      city: city.value,
      timestamp: new Date().toISOString(),
      temperature: data.main.temp,
      humidity: data.main.humidity,
      windSpeed: data.wind.speed,
      cloudiness: data.clouds.all,
    });
  } catch (e) {
    error.value = e.message;
  }
  city.value = "";
};
</script>

<template>
  <div class="weather-app">
    <h1>Weather App</h1>
    <input
      type="text"
      v-model="city"
      placeholder="Enter city name"
      @keyup.enter="fetchWeather"
    />
    <button @click="fetchWeather">Fetch Weather</button>
  </div>
  <WeatherReport v-if="weatherData" :weatherEntries="weatherData" />
</template>

<style>
.weather-app {
  max-width: 400px;
  margin: auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

input {
  padding: 5px;
  width: 100%;
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-bottom: 10px;
}

button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 5px 10px;
  border-radius: 5px;
  cursor: pointer;
}

h1 {
  margin-top: 20px;
}
</style>
