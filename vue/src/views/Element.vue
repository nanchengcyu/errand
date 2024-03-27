<script xmlns:v-bind="http://www.w3.org/1999/xhtml" xmlns:v-bind="http://www.w3.org/1999/xhtml">
export default {
  name: "Element.vue",
  data() {
    return {

      radio:''
    }
  },
  methods: {
    showContent(){
      alert(this.radio)
    }
  },

}
</script>

<template>
  <div>

    <el-radio v-model="radio" label="男" @change="showContent">男</el-radio>
    <el-radio v-model="radio" label="女" @change="showContent">女</el-radio>
  </div>
</template>

<style scoped>

</style>