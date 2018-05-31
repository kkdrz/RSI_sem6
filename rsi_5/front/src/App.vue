<template>
  <div id="app">
    <fruit v-for="fruit in fruits" :key="fruit.name" :fruit="fruit"></fruit>
  </div>
</template>

<script>
import axios from 'axios';
import Fruit from './components/Fruit'

export default {
  name: 'App',
  components: {
    Fruit
  },
  data() {
    return {
      fruits: []
    }
  }, created() {
    axios.get("http://localhost:8080/fruits").then((result) => {
      this.fruits = result.data
    }).catch((err) => {
      console.log(err)
    });
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
