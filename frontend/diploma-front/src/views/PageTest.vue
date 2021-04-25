<template>
  <app-content-block :width="1152" class="page page-test">
    <p class="understudy-text">inputValue: {{inputValue}}</p>
    <app-input
      v-model="inputValue"
    ></app-input>
    <div
      v-if="image"
      class="understudy-image"
      :style="{'background-image': `url(${image.file})`}"
    ></div>
    <app-file-loader
      v-model="image"
    ></app-file-loader>
    <app-button label="Button"></app-button>
  </app-content-block>
</template>

<script>
import AppButton from '../components/common/AppButton.vue';
import AppInput from '../components/common/AppInput.vue';
import AppFileLoader from '../components/common/AppFileLoader.vue';
import AppContentBlock from '../components/AppContentBlock.vue';

export default {
  name: 'PageTest',
  components: {
    AppContentBlock, AppButton, AppInput, AppFileLoader,
  },
  data() {
    return {
      inputValue: '',
      image: null,
    };
  },
};
</script>

<style scoped>
.understudy-text {
  margin: 10px;
  color: #fff;
}
.understudy-image {
  margin: 10px;
  height: 260px;
  background-size: contain;
  background-repeat: no-repeat;
}
</style>
