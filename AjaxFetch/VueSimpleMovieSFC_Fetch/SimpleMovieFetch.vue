<script>
    export default {
      name: "App",
      data() {
        return {
            movieId: 'tt3896198',
            src: null
          }
      },
      beforeMount() {
        this.getMovie();
      },
      methods: {
        async getMovie() {
          const res = await fetch("https://www.omdbapi.com/?i="+this.movieId+"&apikey=591413f8");
          const data = await res.json(); 
          this.src = data;
        }
      }
    }
</script>
<template>
    <p>In this version of the app, we initialize source data via a fetch call using async/await on the beforeMount lifecycle method</p>
    <div id="app" v-if="src">
        <ul>
          <li v-for="(value,key) in src"><b>{{key}}</b>: {{value}}</li>
        </ul>
        <br/>
        Choose a different movie: <input type=text v-model='movieId' /><button @click="getMovie()">Go</button><br/>
        (you can find movie IDs in the URLs from <a href="www.imdb.com">imdb.com</a>)
    </div>
</template>