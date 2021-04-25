<template>
  <transition name="tr">
    <div v-if="show" class="modal-window" @mousedown.self="onClose">
      <div class="modal-window__frame">
        <div class="modal-window__header">
          <slot name="header" />
        </div>

        <div class="modal-window__content">
          <slot name="content" />
        </div>

        <div class="modal-window__buttons">
          <slot name="buttons" />
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
  name: 'AppModalWindow',
  props: {
    show: {
      type: Boolean,
      required: true,
    },
  },
  methods: {
    onClose() {
      this.$emit('close');
    },
  },
};
</script>

<style lang="scss" scoped>
  .modal-window {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background: rgba($color-dark-gray, 0.5);

    &__frame {
      @include box-shadow-bold();
      border-radius: 3px;
      padding: 15px;
      min-width: 600px;
      background: $color-white;
      display: flex;
      flex-direction: column;

      .input {
        margin: 10px 0;
      }
    }

    &__header, &__content, &__buttons {
      display: flex;
      flex-direction: column;
    }

    ::v-deep &__header {
      .title {
        padding: 0 0 20px;
      }
    }

    ::v-deep &__content {
      padding: 0 0 20px;
    }

    ::v-deep &__buttons {
      flex-direction: row;
      flex-wrap: wrap;

      .button {
        margin: 5px 10px 0 0;

        &:first-child {
          margin-left: 0;
        }

        &:last-child {
          margin-right: 0;
        }
      }
    }
  }

  .tr {
    &-enter-active, &-leave-active {
      transition: opacity 0.3s;
    }
    &-enter, &-leave-to {
      opacity: 0;
    }
    &-enter-to, &-leave {
      opacity: 1;
    }
  }
</style>
