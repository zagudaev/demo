<template>
  <app-content-block :width="1152" class="page page-catalog">
    <transition name="tr" >
      <div v-if="anim" class="blocks">
        <p v-if="$store.state.category.catalog.length" class="title">Каталог</p>
        <app-product-card
          v-for="(item, index) in $store.state.category.catalog" :key="`CATEGORY_${index}`"
          :title="item.name"
          :image="getCategoryImageURL(item)"
          @click="onCatalogSelect(item)"
        />
        <p v-if="$store.state.product.list.length" class="title">Товары</p>
        <app-product-card
          v-for="(item, index) in $store.state.product.list" :key="`PRODUCT_${index}`"
          :title="item.name"
          :image="getProductImageURL(item)"
          @click="onProductSelect(item)"
        />
      </div>
    </transition>
  </app-content-block>
</template>

<script>
import AppContentBlock from '../components/AppContentBlock.vue';
import AppProductCard from '../components/AppProductCard.vue';

export default {
  name: 'Home',
  components: {
    AppContentBlock,
    AppProductCard,
  },
  props: {
    id: {},
  },
  data() {
    return {
      anim: true,
    };
  },
  created() {
    this.$store.commit('product/setList', []);
    this.$store.dispatch('category/getWithChildren', this.id ? this.id : '');
    if (this.id) this.$store.dispatch('product/getByCategoryId', this.id);
  },
  methods: {
    getCategoryImageURL(item) {
      if (!(item.picture && item.picture.id)) return '';
      return `${process.env.VUE_APP_BASE_URL}picture/${item.picture.id}`;
    },
    getProductImageURL(item) {
      if (!(item.pictures && item.pictures.length > 0 && item.pictures[0].id)) return '';
      return `${process.env.VUE_APP_BASE_URL}picture/${item.pictures[0].id}`;
    },
    onCatalogSelect(item) {
      this.$router.push({ name: 'Catalog', query: { id: item.id } });
    },
    onProductSelect(item) {
      this.$router.push({ name: 'Product', query: { id: item.id } });
    },
  },
  watch: {
    id() {
      this.$store.commit('product/setList', []);
      this.$store.dispatch('category/getWithChildren', this.id ? this.id : '');
      if (this.id) this.$store.dispatch('product/getByCategoryId', this.id);
      this.anim = false;
      setTimeout(() => {
        this.anim = true;
      }, 300);
    },
  },
};
</script>

<style lang="scss" scoped>
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
  .page-catalog {
    flex-grow: 1;
    background: $bg-light-gray;

    ::v-deep .content-block {
      display: flex;
      justify-content: center;
      align-items: center;
    }

    .title {
      padding: 15px;
      width: 100%;
    }

    .blocks {
      width: 1152px;
      display: flex;
      flex-wrap: wrap;
      flex-direction: row;

      .product-card {
        margin-bottom: 30px;
      }
    }
  }

  //1172
  @media screen and (max-width: #{(66 + 30) * 12 + 20}px) {
    .page-catalog .blocks {
      width: #{(66 + 30) * 9}px;
    }
  }

  @media screen and (max-width: #{(66 + 30) * 9 + 20}px) {
    .page-catalog .blocks {
      width: #{(66 + 30) * 6}px;
    }
  }

  @media screen and (max-width: #{(66 + 30) * 6 + 20}px) {
    .page-catalog .blocks {
      width: #{(66 + 30) * 3}px;
    }
  }
</style>
