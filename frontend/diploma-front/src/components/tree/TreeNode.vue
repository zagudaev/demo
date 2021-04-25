<template>
  <div class="tree-node">
    <div class="node-item__body" :class="{'empty': !(item.children && item.children.length) }">
      <icon-arrow v-if="item.children && item.children.length"
                  :class="{ 'right': !isOpen }"
                  @click.native="isOpen = !isOpen"
      />
      <div :class="{'selected': selectedNode.id === item.id}" @click="$emit('select', item)">
        <slot name="item" :item="item">{{item.name}}</slot>
      </div>
    </div>

    <div v-if="item.children && item.children.length && isOpen"
         class="node-item__child"
    >
      <div v-for="(child, index) in item.children"
           :key="`TREE-NODE-${index}`"
           class="node-item"
      >
        <tree-node :item="child"
                   :parent="item"
                   :selected-node="selectedNode"
                   @select="$emit('select', $event)"
        >
          <template #item="{item}">
            <slot name="item" :item="item">{{item.name}}</slot>
          </template>
          <template #end="{item}">
            <slot name="end" :item="item" />
          </template>
        </tree-node>
      </div>
    </div>
  </div>
</template>

<script>
import IconArrow from '../../icons/IconArrow.vue';

export default {
  name: 'TreeNode',
  components: { IconArrow },
  props: {
    item: {
      type: Object,
      required: true,
    },
    parent: {
      type: Object,
      default: () => {},
    },
    selectedNode: {
      type: Object,
      default: () => {},
    },
  },
  data() {
    return {
      isOpen: false,
    };
  },
};
</script>

<style lang="scss" scoped>
  .tree-node {
    .node-item {
      margin: 0 0 0 15px;
      &__body {
        padding: 4px 0;
        display: flex;
        align-items: center;

        &.empty {
          margin-left: 20px;
        }

        .icon-arrow {
          margin-right: 6px;
          cursor: pointer;
        }
        .selected {
          text-decoration: underline;
          color: red;
        }
      }
      &__child {
        padding: 0 0 10px;
      }
    }
  }
</style>
