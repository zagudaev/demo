<template>
  <div class="images">
    <div class="images__preview-holder">
      <div class="images__preview">
        <div v-for="(item, index) in images" :key="index"
             @click="onSelect(item)"
             class="image"
        >
          <img :src="item" class="img" :alt="index">
        </div>
      </div>
    </div>

    <div class="images__selected">
      <img :src="selectedImage">
    </div>
  </div>
</template>

<script>
export default {
  name: 'AppImages',
  model: {
    prop: 'selectedImage',
    event: 'select',
  },
  props: {
    selectedImage: {
      type: String,
      required: true,
    },
    images: {
      type: Array,
      required: true,
    },
  },
  methods: {
    onSelect(item) {
      this.$emit('select', item);
    },
  },
};
</script>

<style lang="scss" scoped>
  .images {
    display: flex;
    user-select: none;
    .images__preview-holder {
      max-height: #{70px * 7};
      /*background: red;*/
      overflow: hidden;
    }
    .images__preview {
      display: flex;
      flex-direction: column;
      .image {
        margin: 5px;
        padding: 5px;
        width: 60px;
        height: 60px;
        border: 1px solid $color-dark-gray;
        background: $color-white;
        display: flex;
        justify-content: center;

        &.selected {
          margin: 4px;
          width: 62px;
          height: 62px;
          border-width: 2px;
        }

        .img {
          max-height: 100%;
          max-width: 100%;
        }
      }
    }

    .images__selected {
      margin: 5px;
      padding: 5px;
      border: 1px solid $color-dark-gray;
      width: #{70px * 7 - 10px};
      height: #{70px * 7 - 10px};
      background: $color-white;
      display: flex;
      justify-content: center;
      align-items: center;

      img {
        max-height: 100%;
        max-width: 100%;
      }
    }
  }
</style>
