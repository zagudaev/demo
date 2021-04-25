<template>
  <div id="app">
    <the-header/>
    <router-view class="view-content"/>
    <the-footer/>
  </div>
</template>

<script>
import TheHeader from './components/TheHeader.vue';
import TheFooter from './components/TheFooter.vue';

export default {
  name: 'App',
  components: {
    TheHeader,
    TheFooter,
  },
};
</script>

<style lang="scss">
@import '~@/assets/scss/classes';
#app {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  overflow-y: visible;
}
html, body {
  background: $bg-dark-gray;
}
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Roboto', sans-serif;
  &::selection {
    background: #555;
    color: #fff;
  }
}
.view-content {
  @include box-shadow-bold();
  margin-top: $header-height;
}
</style>
