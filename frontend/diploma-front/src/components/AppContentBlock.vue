<template>
  <div class="content-block__holder">
    <div
      class="content-block"
      :style="{ 'width': width !== -1 ? `${width}px` : '' }"
    >
      <slot></slot>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AppContentBlock',
  props: {
    width: {
      type: Number,
      default: -1,
    },
  },
};
</script>

<style lang="scss" scoped>
.content-block__holder {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.content-block {
  max-width: 100%;
  display: flex;
  flex-direction: column;
}
</style>
