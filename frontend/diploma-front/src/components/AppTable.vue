<template>
  <div class="table">
    <slot name="table-header" />
    <table cellpadding="0" cellspacing="0">
      <tr>
        <td class="cell-header" v-for="(item, index) in headers" :key="index">
          <slot name="header" :item="item">{{item}}</slot>
        </td>
      </tr>
      <tr v-for="(item, index) in items" :key="index"
          :class="{ 'selected': item === selectedItem }"
      >
        <slot name="item" :item="item" />
      </tr>
    </table>
    <div class="spacer"></div>
    <div class="buttons">
      <slot name="buttons" />
    </div>
  </div>
</template>

<script>
export default {
  name: 'AppTable',
  model: {
    prop: 'selectedItem',
    event: 'select',
  },
  props: {
    selectedItem: {
      type: Object,
      required: true,
    },
    items: {
      type: Array,
      required: true,
    },
    headers: {
      type: Array,
      required: true,
    },
  },
};
</script>

<style lang="scss" scoped>
  .table {
    @include box-shadow-bold();
    padding: 15px;
    width: 100%;
    min-height: 300px;
    /*overflow-x: scroll;*/
    border-radius: 5px;
    background: $color-white;
    display: flex;
    flex-direction: column;

    .title {
      padding: 0 0 20px;
      font-size: 20px;
    }

    table {
      max-height: 600px;
      width: 100%;

      tr {
        .selected {
          td {
            border: none;
            background: $color-light-gray;
            &:first-child {
              padding-left: 15px;
              border-top-left-radius: 3px;
              border-bottom-left-radius: 3px;
            }
            &:last-child {
              padding-right: 15px;
              border-top-right-radius: 3px;
              border-bottom-right-radius: 3px;
            }
          }
        }
      }

      td {
        padding: 7px 15px;
        border-bottom: 1px solid $color-light-gray;

        &:first-child {
          padding-left: 15px;
        }
        &:last-child {
          padding-right: 15px;
        }

        &.cell-header {
          padding: 7px 15px;
          background: $color-dark-gray;
          color: $color-white;
          &:first-child {
            padding-left: 15px;
            border-top-left-radius: 3px;
            border-bottom-left-radius: 3px;
          }
          &:last-child {
            padding-right: 15px;
            border-top-right-radius: 3px;
            border-bottom-right-radius: 3px;
          }
        }
      }
    }

    ::v-deep .buttons {
      .button {
        margin-right: 5px;
      }
    }
  }
</style>
