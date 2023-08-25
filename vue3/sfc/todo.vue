<script>
export default {
  data() {
    return {
      newTask: "",
      tasks: ["Sample Task"],
    };
  },
  methods: {
    addTask() {
      if (this.newTask.trim() !== "") {
        this.tasks.push(this.newTask);
        this.newTask = "";
      }
    },
    editTask(index) {
      const updatedTask = prompt("Edit task:", this.tasks[index]);
      if (updatedTask !== null) {
        this.tasks[index] = updatedTask;
      }
    },
    deleteTask(index) {
      if (confirm("Are you sure you want to delete this task?")) {
        this.tasks.splice(index, 1);
      }
    },
  },
};
</script>

<template>
  <p>This example can be run directly in Vue SFC Playground</p>
  <div class="todo-app">
    <h1>Todo List</h1>
    <input
      v-model="newTask"
      @keyup.enter="addTask"
      placeholder="Add a new task"
    />
    <ul>
      <li v-for="(task, index) in tasks" :key="index">
        <span>{{ task }}</span>
        <button @click="editTask(index)">Edit</button>
        <button @click="deleteTask(index)">Delete</button>
      </li>
    </ul>
  </div>
</template>

<style>
.todo-app {
  max-width: 400px;
  margin: auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

ul {
  list-style: none;
  padding: 0;
}

li {
  display: flex;
  justify-content: space-between;
  align-items: center;
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

input {
  padding: 5px;
  width: 100%;
  border: 1px solid #ccc;
  border-radius: 5px;
}
</style>
