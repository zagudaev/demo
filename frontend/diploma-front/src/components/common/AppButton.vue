<template>
  <button
    class="button"
    @click="$emit('click', $event)"
  >
    <slot>{{label}}</slot>
  </button>
</template>

<script>
export default {
  name: 'AppButton',
  props: {
    label: {
      type: String,
    },
  },
};
</script>

<style lang="scss" scoped>
.button {
  @include def-font;
  color: #fff;
  padding: 3px 15px;
  outline: none;
  border: 1px solid #afafaf;
  border-radius: 3px;
  background: linear-gradient(#2fe35d, #28a745);
  user-select: none;
  display: flex;
  align-items: center;
  &:active {
    background: linear-gradient(#2fe35d, #2bc04d);
  }
}
</style>
