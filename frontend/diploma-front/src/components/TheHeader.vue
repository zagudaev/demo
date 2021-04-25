<template>
  <div class="header">
    <div class="header__blocks">

      <div class="header__block header__to-main link"
           @click="onClickToMain"
      >
        <p class="header__text">На главную</p>
      </div>

      <div class="spacer"></div>

      <!-- Shop-cart -->
      <div
        class="header__block header__shop-cart link"
        @click="onClickShopCart"
      >
        <icon-shop-cart />
        <p class="header__text">Корзина</p>
      </div>

      <!-- Login -->
      <div
        class="header__block header__shop-cart link"
        @click="onClickLogIn"
      >
        <icon-log-in state="not-logged-in" />
        <p class="header__text">{{logInText}}</p>
      </div>

    </div>
  </div>
</template>

<script>
import IconShopCart from '../icons/IconShopCart.vue';
import IconLogIn from '../icons/IconLogIn.vue';
import UserAdmin from '../scripts/UserAdmin';

export default {
  name: 'TheHeader',
  components: {
    IconShopCart,
    IconLogIn,
  },
  methods: {
    onClickToMain() {
      if (this.$route.name !== 'Catalog'
        || (this.$route.name === 'Catalog' && this.$route.query !== {}))
        this.$router.push({ name: 'Catalog' });
    },
    onClickShopCart() {
      this.$router.push({ name: 'Basket' });
    },
    onClickLogIn() {
      // console.log('click on log-in');
      // eslint-disable-next-line new-cap
      const regUser = new UserAdmin('admin');
      console.log(regUser);
    },
  },
  computed: {
    logInText() {
      if (this.$store.state.user.status === 'not-logged-in') return 'Войти';
      if (this.$store.state.user.status === 'logged-in') return 'Выйти';
      return this.$store.state.user.status;
    },
  },
};
</script>

<style lang="scss" scoped>
.header {
  @include box-shadow-bold;
  position: fixed;
  top: 0;
  width: 100%;
  height: $header-height;
  display: flex;
  background: $bg-white;
  &__blocks {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: row;
  }
  &__block {
    transition: background 0.4s;
    svg {
      margin-right: 10px;
      width: 24px;
      height: 24px;
      fill: $header-font-color-gray;
    }
    &:hover {
      background: $bg-dark-gray;
    }
  }
  &__title {}
  &__text {
    @include header-font-gray;
  }
  &__shop-cart {
    padding: 10px 38px;
    /*width: min-content;*/
    height: 100%;
    display: flex;
    align-items: center;
    user-select: none;
  }
  &__to-main {
    padding: 0 50px;
    display: flex;
    justify-content: center;
    align-items: center;
    p {
      font-size: 20px;
    }
  }
}
</style>
