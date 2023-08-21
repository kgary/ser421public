<template>
  <div>
    <h1>Authors</h1>
    <ul id="authors">
      <li v-for="author in authors" :key="author.id">
        <img :src="author.picture" alt="Author" />
        <span>{{ author.name }}</span>
      </li>
    </ul>

    <h1>Random Joke Generator</h1>
    <div>
      <span style="font-size: 25px">{{ joke }}</span>
    </div>

    <div>
      <button @click="generateNewJoke">New Joke</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";

const authors = ref([]);
const joke = ref("");
const jokes = [
  "Guess the number of programmers it takes to change a light bulb? Zero its a hardware problem",
  "There are only 10 kinds of people in this world: those who know binary and those who don’t.",
  "Real programmers count from 0.",
  "Why did the programmer quit his job? Because he didnt get arrays.",
  "A foo walks into a bar takes a look around and says Hello World",
  "0 is false 1 is true right? 1",
  "Things arent always #000000 and #FFFFFF.",
  "What is the most used language in programming? Profanity",
  "!False its funny because its True",
  "You had me at Hello World",
  "2b||!2b",
  "Yesterday I changed the name on my wifi to Hack if you can. Today I found it named Challenge Accepted",
  "A programmer is a person who fixed a problem that you didnt know you had in a way you dont understand",
  "How can you tell if a computer geek is an extrovert? They stare at your shoes when you talk instead of their own.",
  "I would love to change the world but they wont give me the source code.",
  "If at first you dont succedd call it version 1.0",
  "Computers make very fast very accurate mistakes",
  "I farted in the Apple store and everyone got mad at me. Not my fault they dont have Windows.",
  "Knock Knock... Whos there? Art... Art Who? R2D2",
  "Hilarious and amazingly true thing: if a pizza has a radius (z) and a depth (a) that pizzas volume can be defined Pi*z*z*a.",
];

function sleep(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}

async function sleepy() {
  console.log("Taking a break...");
  await sleep(5000);
  console.log("5 seconds later");
  getAuthors();
}

function getAuthors() {
  const url = "https://randomuser.me/api/?results=10";
  fetch(url)
    .then((resp) => resp.json())
    .then((data) => {
      authors.value = data.results.map((author) => ({
        id: author.login.uuid,
        picture: author.picture.medium,
        name: `${author.name.first} ${author.name.last}`,
      }));
    })
    .catch((error) => {
      console.log(JSON.stringify(error));
    });
}

function generateNewJoke() {
  const randomNumber = Math.floor(Math.random() * jokes.length);
  joke.value = jokes[randomNumber];
}

onMounted(() => {
  sleepy();
});
</script>

<style>
body {
  background: #f5f5f5;
}

h1 {
  text-align: center;
  font-family: arial;
  color: #5a5a5a;
}

ul {
  display: flex;
  list-style: none;
  flex-wrap: wrap;
  align-items: flex-start;
  justify-content: center;
  flex-basis: 80%;
}

li {
  flex-basis: 20%;
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
  align-items: center;
}

span {
  font-family: arial;
  font-size: 14px;
  color: #5a5a5a;
  text-align: center;
}

img {
  margin: 5px;
  border-radius: 3px;
  box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.2);
}
</style>
