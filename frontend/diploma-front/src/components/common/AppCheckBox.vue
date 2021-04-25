<template>
  <label class="check-box">
    <input
      class="check-box__check-box"
      type="checkbox"
      :checked="checked"
      @change="$emit('change', $event.target.checked)"
    >
    <p class="check-box__label">{{label}}</p>
  </label>
</template>

<script>
export default {
  name: 'AppCheckBox',
  model: {
    prop: 'checked',
    event: 'change',
  },
  props: {
    checked: {},
    label: {
      type: String,
    },
  },
};
</script>

<style lang="scss" scoped>
.check-box {
  margin: 5px;
  display: flex;
  align-items: center;
  user-select: none;
  &__label {
    margin-left: 5px;
  }
}
</style>
