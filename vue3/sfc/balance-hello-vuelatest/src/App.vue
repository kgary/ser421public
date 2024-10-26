<script>
import Balance from './components/Balance.vue'
import HelloWorld from './components/HelloWorld.vue'

export default {
  data() {
    return {
      fname: "name",
      bal: 0
    }
  },
  components: {
    Balance,
    HelloWorld
  }
}
</script>

<template>
  <header>
    <div class="wrapper">
      <input v-model="fname">
      <HelloWorld v-bind:name="this.fname" /> you have {{bal}} dollars 
      <Balance @new-bal="(v) => this.bal=v" />
    </div>
  </header>
  <p>This example shows how to use Options with the npm init vue@latest scaffolding with one parent (App.vue) and 2 child (Balance, HelloWorld) components</p>
</template>

<style scoped>
header {
  line-height: 1.5;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 4);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }
}
</style>
