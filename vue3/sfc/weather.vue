<script>
export default {
  data() {
    return {
      city: "",
      weatherData: [],
    };
  },
  methods: {
    async fetchWeather() {
      if (this.city.trim() !== "") {
        const apiKey = "YOUR API KEY"; // Replace with your actual API key
        const apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${this.city}&units=metric&appid=${apiKey}`;

        try {
          const response = await fetch(apiUrl);
          const data = await response.json();

          const weatherEntry = {
            city: data.name,
            timestamp: new Date().toISOString(),
            temperature: data.main.temp,
            humidity: data.main.humidity,
            windSpeed: data.wind.speed,
            cloudiness: data.clouds.all,
          };

          this.weatherData.push(weatherEntry);
        } catch (error) {
          console.error("Error fetching weather data:", error);
        }
      }
      this.city = "";
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
  </div>
  <div class="weather-report">
    <h2>Weather Report</h2>
    <table>
      <thead>
        <tr>
          <th>City Name</th>
          <th>Timestamp<br />(yyyy:mm:dd:hh:mm:ss)</th>
          <th>Temperature<br />in &#8490;</th>
          <th>Humidity<br />in %</th>
          <th>Wind Speed<br />in miles per hour</th>
          <th>Cloudiness<br />in %</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="entry in weatherData" :key="entry.timestamp">
          <td>{{ entry.city }}</td>
          <td>{{ entry.timestamp }}</td>
          <td>{{ entry.temperature }}</td>
          <td>{{ entry.humidity }}</td>
          <td>{{ entry.windSpeed }}</td>
          <td>{{ entry.cloudiness }}</td>
        </tr>
      </tbody>
    </table>
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

.weather-report {
  max-width: 800px;
  margin: auto;
}

table {
  width: 100%;
  border-collapse: collapse;
  border: 1px solid #ccc;
}

th,
td {
  border: 1px solid #ccc;
  padding: 8px;
  text-align: center;
}
</style>
