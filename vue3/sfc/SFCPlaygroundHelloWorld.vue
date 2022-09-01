// This is the example used in the SFC Playground, you can copy-paste to that tool and see the result
<script>
export default {
  data() {
    return {
      msg: 'Hello World!'
    }
  }
}
</script>

<template>
  <p class="greeting">{{ msg }}</p>
</template>

<style>
.greeting {
  color: red;
  font-weight: bold;
}
</style>
