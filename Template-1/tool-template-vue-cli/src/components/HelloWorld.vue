<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <h1>响应结果：{{ res }}</h1>
    <button @click="handleClick">点击</button>
  </div>
</template>

<script lang="ts">
import { getActToolUrl } from '@/base/constant';
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'HelloWorld',
  props: {
    msg: String,
  },
  data() {
    return {
      res: ""
    };
  },
  methods: {
    handleClick() {
      console.log((window as any).g_actToolUrl);
      const url = getActToolUrl() + "&action=hello";
      fetch(url).then((res) => res.json()).then((data) => { 
        this.res = data.data.msg; 
      });
    }
  },
});
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
h3 {
  margin: 40px 0 0;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
