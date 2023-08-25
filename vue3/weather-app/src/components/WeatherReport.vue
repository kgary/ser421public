<template>
  <div class="weather-report">
    <h2>Weather Report</h2>
    <p v-if="weatherEntries.length">Select row to see the weather visuals</p>
    <table>
      <thead>
        <tr>
          <th>City Name</th>
          <th>Timestamp<br />(yyyy:mm:dd:hh:mm:ss)</th>
          <th>Temperature<br />in F</th>
          <th>Humidity<br />in %</th>
          <th>Wind Speed<br />in miles per hour</th>
          <th>Cloudiness<br />in %</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(entry, index) in weatherEntries"
          :key="entry.timestamp"
          :class="{ 'selected-row': index === selectedRowIndex }"
          @click="
            selectedRowIndex = index;
            selectedEntry(entry);
          "
        >
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
import { defineProps, defineEmits, ref } from "vue";

const props = defineProps({
  weatherEntries: Array,
});

const emit = defineEmits();

const selectedRowIndex = ref(-1);
const selectedEntry = (entry) => {
  emit("entry-selected", entry);
};
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

th,
td {
  border: 1px solid #ccc;
  padding: 8px;
  text-align: center;
}
.selected-row {
  background-color: #cccccc22;
}
td:hover {
  cursor: pointer;
}
</style>
