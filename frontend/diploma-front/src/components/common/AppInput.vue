<template>
  <div class="input">
    <p v-if="label" class="input__label">{{label}}</p>
    <input
      class="input__input"
      :type="type"
      :value="value"
      :readonly="readonly"
      :class="{'input_disabled': readonly}"
      @input="$emit('input', $event.target.value)"
    >
  </div>
</template>

<script>
export default {
  name: 'AppInput',
  props: {
    value: {},
    type: {
      type: String,
      default: 'text',
    },
    label: {
      type: String,
      required: false,
    },
    readonly: {
      type: Boolean,
      default: false,
    },
  },
};
</script>

<style lang="scss" scoped>
  .input {
    margin: 5px 0;
    &__label {
      font-size: 14px;
      user-select: none;
      cursor: default;
    }
    &__input {
      @include def-font;
      width: 100%;
      padding: 3px;
      outline: none;
      background: #ffffff;
      border: 1px solid $color-border-gray;
      border-radius: 3px;
    }
    &_disabled {
      background: #dddddd;
      cursor: default;
    }
  }
</style>
