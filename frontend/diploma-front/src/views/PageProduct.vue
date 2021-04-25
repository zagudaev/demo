<template>
  <app-content-block :width="1152" class="page page-product">
    <p class="title">{{$store.state.product.product.name}}</p>
    <div class="page-product__content">
      <app-images v-model="selectedImage" :images="images" />
      <div class="page-product__info">
        <p class="info">Наимерование товара: {{$store.state.product.product.name}}</p>
        <p class="info">Описание товара: {{$store.state.product.product.description}}</p>
        <p class="info">Цена: {{$store.state.product.product.price}}</p>
        <p class="info">Количество: {{$store.state.product.product.quantity}}</p>
        <app-button v-if="!basketContain" label="Добавить в корзину" @click="addToBasket" />
        <app-button v-else label="Удалить из корзины" @click="removeFromBasket" />
      </div>
    </div>
  </app-content-block>
</template>

<script>
import AppContentBlock from '../components/AppContentBlock.vue';
import AppImages from '../components/AppImages.vue';
import AppButton from '../components/common/AppButton.vue';

export default {
  name: 'PageProduct',
  components: { AppButton, AppContentBlock, AppImages },
  props: {
    id: {},
  },
  data() {
    return {
      selectedImage: '',
      images: [],
    };
  },
  created() {
    this.$store.dispatch('product/getById', this.id).then(() => this.setImages());
  },
  computed: {
    basketContain() {
      return this.$store.state.basket.basketArray.find(
        (val) => val.id === this.$store.state.product.product.id
      );
    },
  },
  methods: {
    setImages() {
      if (this.$store.state.product.product.pictures) {
        this.images = [];
        this.$store.state.product.product.pictures.forEach((item) => {
          this.images.push(`${process.env.VUE_APP_BASE_URL}picture/${item.id}`);
        });
        if (this.images.length) this.selectedImage = this.images[0];
      }
    },
    addToBasket() {
      this.$store.commit('basket/addTo', { product: this.$store.state.product.product, quantity: 1 });
    },
    removeFromBasket() {
      this.$store.commit('basket/removeFrom', this.$store.state.product.product);
    },
  },
};
</script>

<style lang="scss" scoped>
  .page-product {
    &__content {
      display: flex;
    }

    &__info {
      margin: 5px;
    }
  }
</style>
