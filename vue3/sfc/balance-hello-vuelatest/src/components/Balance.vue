<script>
export default {
    name: 'Balance',
    data() {
        return {
            balance: 100,
            amount: 10
        }
    },
    computed: {
        balanceString() {
            return `Account Balance: ${this.balance}`;
        }
    },
    methods: {
        addBalance() {
            this.balance += this.amount
            //this.amount = 0;
        },
        subtractBalance() {
            this.balance -= this.amount
            //this.amount = 0;
        }
    },
    mounted() {
        console.log('Application mounted');
    },
}
</script>

<template>
    <div class="greetings">
      <h3>{{balanceString}}</h3>
      <h3>Amount: {{ amount }}</h3>
      <button @click="addBalance">Add</button><button @click="subtractBalance">Subtract</button>
    </div>
  </template>
  
  <style scoped>
  h1 {
    font-weight: 500;
    font-size: 2.6rem;
    top: -10px;
  }
  
  h3 {
    font-size: 1.2rem;
  }
  
  .greetings h1,
  .greetings h3 {
    text-align: center;
  }
  
  @media (min-width: 1024px) {
    .greetings h1,
    .greetings h3 {
      text-align: left;
    }
  }
  </style>