<template>
  <div class="text-area">
    <p v-if="label" class="text-area__label">{{label}}</p>
    <textarea :value="value"
              @input="$emit('input', $event.target.value)"
              :readonly="readonly"
              :rows="lineCount"
              :class="{'text-area_disabled': readonly}"
              class="text-area__input"
    />
  </div>
</template>

<script>
export default {
  name: 'AppTextArea',
  props: {
    value: {
      type: String,
      required: true,
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
  computed: {
    lineCount() {
      return this.value.split('\n').length;
    },
  },
};
</script>

<style lang="scss" scoped>
  .text-area {
    margin: 5px 0;

    &__label {
      font-size: 14px;
      user-select: none;
      cursor: default;
    }

    &__input {
      @include def-font;
      width: 100%;
      min-width: 100%;
      max-width: 100%;
      padding: 3px;
      outline: none;
      background: #ffffff;
      border: 1px solid $color-border-gray;
      border-radius: 3px;
      resize: none;
    }

    &_disabled {
      background: #dddddd;
      cursor: default;
    }
  }
</style>
