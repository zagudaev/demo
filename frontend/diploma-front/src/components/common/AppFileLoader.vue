<template>
  <div class="file-loader">
    <input
      type="file"
      ref="inputFile"
      @change="onSelect"
      hidden
    >
    <app-button
      class="file-loader__button"
      @click="onClick"
    >
      {{btnLabel}}
      <svg
        v-if="file && file.file && file.filename"
        class="file-loader__clear-btn"
        width="14"
        height="14"
        viewBox="0 0 24 24"
        fill="#fff"
        xmlns="http://www.w3.org/2000/svg"
        @click="onClear"
      >
        <path d="M14.6433 12.0007L23.6166 3.027C23.8634 2.77997 23.9996 2.45041 24 2.099C24 1.74739
        23.8638 1.41744 23.6166 1.1708L22.8302 0.384658C22.583 0.137048 22.2535 0.00143814 21.9017
        0.00143814C21.5504 0.00143814 21.2209 0.137048 20.9737 0.384658L12.0004 9.35773L3.02673
        0.384658C2.7799 0.137048 2.45015 0.00143814 2.09854 0.00143814C1.74732 0.00143814 1.41756
        0.137048 1.17073 0.384658L0.384 1.1708C-0.128 1.6828 -0.128 2.51558 0.384 3.027L9.35746
        12.0007L0.384 20.9739C0.136976 21.2213 0.00097561 21.5509 0.00097561 21.9023C0.00097561
        22.2537 0.136976 22.5833 0.384 22.8305L1.17054 23.6167C1.41737 23.8641 1.74732 23.9999
        2.09834 23.9999C2.44995 23.9999 2.77971 23.8641 3.02654 23.6167L12.0002 14.6434L20.9735
        23.6167C21.2207 23.8641 21.5502 23.9999 21.9015 23.9999H21.9019C22.2533 23.9999 22.5828
        23.8641 22.83 23.6167L23.6164 22.8305C23.8632 22.5835 23.9994 22.2537 23.9994 21.9023C23.999
        21.5509 23.8632 21.2213 23.6164 20.9741L14.6433 12.0007Z"/>
      </svg>
    </app-button>
  </div>
</template>

<script>
import AppButton from './AppButton.vue';

export default {
  name: 'AppFileLoader',
  components: { AppButton },
  model: {
    prop: 'file',
    event: 'change',
  },
  props: {
    file: {},
    type: {
      type: String,
      default: 'b64',
      validator: (prop) => ['blob', 'b64'].includes(prop),
    },
    label: {
      type: String,
      default: 'Загрузить файл',
    },
  },
  methods: {
    onClick() {
      if (
        (!this.file.file || !this.file.filename)
        && !this.$refs.inputFile.value
      ) this.$refs.inputFile.click();
      else this.onClear();
    },
    onSelect(ev) {
      const vm = this;
      if (!ev.target.value) {
        this.onClear();
        return;
      }
      if (this.type === 'blob') {
        // blob
        vm.$emit('change', {
          file: ev.target.files[0],
          filename: ev.target.value,
        });
      } else if (this.type === 'b64') {
        // base64
        const reader = new FileReader();
        reader.readAsDataURL(ev.target.files[0]);
        reader.onload = () => {
          vm.$emit('change', {
            file: reader.result,
            filename: ev.target.value,
          });
        };
      }
    },
    onClear() {
      this.$refs.inputFile.value = '';
      this.$emit('change', { file: '', filename: '' });
    },
  },
  computed: {
    btnLabel() {
      if (this.file && this.file.file && this.file.filename) {
        const arr = this.file.filename.split('\\');
        return arr.pop();
      }
      return this.label;
    },
  },
};
</script>

<style lang="scss" scoped>
.file-loader {
  position: relative;
  display: flex;
  align-items: center;
  .button {
    /*flex: 1;*/
  }
  &__clear-btn {
    pointer-events: none;
    margin-left: 15px;
  }
}
</style>
