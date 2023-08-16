<script>
export default {
  data() {
    return {
      city: "",
      weatherData: null,
    };
  },
  methods: {
    async fetchWeather() {
      if (this.city.trim() !== "") {
        // You will need to go get your own free API key to get this to work
        const apiKey = "Your API Key";
        const apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${this.city}&units=metric&appid=${apiKey}`;

        try {
          const response = await fetch(apiUrl);
          const data = await response.json();
          this.weatherData = data;
        } catch (error) {
          console.error("Error fetching weather data:", error);
        }
      }
    },
  },
};
</script>

<template>
  <p>This example can be run directly in Vue SFC Playground</p>
  <div class="weather-app">
    <h1>Weather App</h1>
    <input
      v-model="city"
      @keyup.enter="fetchWeather"
      placeholder="Enter city name"
    />
    <button @click="fetchWeather">Fetch Weather</button>

    <div v-if="weatherData">
      <h2>{{ weatherData.name }}</h2>
      <p>Temperature: {{ weatherData.main.temp }}°C</p>
      <p>Weather: {{ weatherData.weather[0].description }}</p>
    </div>
  </div>
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

h2 {
  margin-top: 20px;
}

p {
  margin: 5px 0;
}
</style>
