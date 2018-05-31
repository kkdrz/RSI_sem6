<template>
<div class="fruit">
   {{fruit.name}}
<div>
  Price: {{fruit.price}}</div>
  <div>Sweetness: {{fruit.sweetness}}
</div>
</div>

</template>

<script>
export default {
  name: 'FruitViewer',
  props: ["fruit"]
}
</script>


<style scoped>
.fruit {
  background-color: cadetblue;
  padding: 3px;
  margin-top: 3px;
  color: white;
}
</style>
