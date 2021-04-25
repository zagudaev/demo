<template>
  <div class="product-card" @click="onClick">
    <div class="product-card__img">
      <img v-if="image" :src="image">
    </div>
    <div class="product-card__title">{{title}}</div>
  </div>
</template>

<script>
export default {
  name: 'AppProductCard',
  props: {
    image: {
      type: String,
      default: '',
    },
    title: {
      type: String,
      default: 'title',
    },
  },
  methods: {
    onClick() {
      this.$emit('click');
    },
  },
};
</script>

<style lang="scss" scoped>
.product-card {
  @include box-shadow-light();
  margin: 15px;
  padding: 15px;
  width: 258px;
  height: 250px;
  border-radius: 7px;
  background: $bg-white;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  user-select: none;
  &__img {
    margin-bottom: 10px;
    pointer-events: none;

    img {
      max-width: 228px;
      max-height: 160px;
    }
  }
  &__title {
    text-align: center;
  }
}
</style>
