<template>
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
        <tr v-for="entry in weatherEntries" :key="entry.timestamp">
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

<script setup>
import { defineProps } from "vue";

const props = defineProps({
  weatherEntries: Array,
});
</script>

<style>
.weather-report {
  max-width: 800px;
  margin: auto;
}

table {
  width: 100%;
  border-collapse: collapse;
  border: 1px solid #ccc;
}

th, td {
  border: 1px solid #ccc;
  padding: 8px;
  text-align: center;
}
</style>
