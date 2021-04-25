<template>
  <div class="tree-viewer">

    <div class="tree-viewer__body">
      <div class="tree-viewer__tree">
        <p class="title">
          <slot name="title" />
        </p>

        <tree-node v-for="(item, index) in items" :key="index"
                   :item="item"
                   :selected-node="selectedNode"
                   @select="$emit('select', $event)"
        >

          <template #item="{item}">
            <slot name="item" :item="item"/>
          </template>

          <template #end="{item}">
            <slot name="end" :item="item"/>
          </template>

        </tree-node>

        <div class="tree-viewer__end">
          <slot name="end" />
        </div>
      </div>

      <div class="tree-viewer__selected-node">
        <slot name="editor" />
      </div>
    </div>

  </div>
</template>

<script>
import TreeNode from './TreeNode.vue';

export default {
  name: 'AppTreeViewer',
  components: { TreeNode },
  model: {
    prop: 'selectedNode',
    event: 'select',
  },
  props: {
    items: {
      type: Array,
      default: () => [],
    },
    selectedNode: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style lang="scss" scoped>
  .tree-viewer {
    @include box-shadow-bold();
    margin-bottom: 20px;
    border-radius: 5px;
    background: $bg-white;
    user-select: none;

    &__tree {
      padding: 10px;
      flex: 1;
    }

    .title {
      padding: 0 0 10px 0;
      font-size: 20px;
    }

    &__body {
      display: flex;
      padding: 5px;
      min-height: 250px;
    }

    &__selected-node, &__under {
      padding: 10px 12px;
      display: flex;
      flex-direction: column;
      background: $bg-light-gray;
      border-radius: 3px;
      .title {
        padding: 5px 0 10px;
      }
    }

    &__under {
      margin: 0 5px 5px 5px;
      flex: 1;
      /*height: 500px;*/
    }

    &__selected-node {
      width: 60%;
    }

    &__end {
      margin-left: 20px;
      padding: 4px 0;
      display: flex;
      align-items: center;
    }
  }

  @media screen and (max-width: #{(66 + 30) * 6 + 20}px) {
    .tree-viewer__body {
      flex-direction: column;
    }
    .tree-viewer__selected-node {
      width: 100%;

      .button {
        margin: 5px 0 0 !important;
        width: 100%;
      }
    }
  }
</style>
