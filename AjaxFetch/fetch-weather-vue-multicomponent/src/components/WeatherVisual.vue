<script setup>
import { defineProps } from "vue";

const props = defineProps({
  selectedEntry: Object,
});

const getWeatherIconURL = (iconCode) => {
  console.log(iconCode);
  return `https://openweathermap.org/img/wn/${iconCode}@4x.png`;
};
</script>

<template>
  <div class="weather-visual">
    <h2 class="weather-title">Weather Visuals : {{ selectedEntry.city }}</h2>
    <div class="weather-icon">
      <img
        :src="getWeatherIconURL(selectedEntry.weather[0].icon)"
        alt="Weather Icon"
      />
      <p>{{ selectedEntry.weather[0].description }}</p>
    </div>
    <div class="weather-details">
      <p>{{ selectedEntry.temperature }}°F</p>
      <p>Wind: {{ selectedEntry.windSpeed }} mph</p>
      <p>Cloudiness: {{ selectedEntry.cloudiness }}%</p>
    </div>
  </div>
</template>

<style scoped>
.weather-visual {
  max-width: 400px;
  margin: auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-top: 20px;
}

.weather-title {
  text-align: center;
  margin-bottom: 20px;
}

.weather-icon {
  display: flex;
  align-items: left;
  gap: 10px;
}

.weather-icon img {
  width: 100px;
  height: 100px;
}

.weather-details {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-top: 10px;
}
</style>
