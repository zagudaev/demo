<template>
  <div class="icon-arrow">
    <svg xmlns="http://www.w3.org/2000/svg"
         width="14" height="14"
         viewBox="0 0 512.011 512.011" xml:space="preserve"
    >
      <path d="M505.755,123.592c-8.341-8.341-21.824-8.341-30.165,0L256.005,343.176L36.421,123.592c-8.341-8.341-21.824-8.341-30.165,0
        s-8.341,21.824,0,30.165l234.667,234.667c4.16,4.16,9.621,6.251,15.083,6.251c5.462,0,10.923-2.091,15.083-6.251l234.667-234.667
        C514.096,145.416,514.096,131.933,505.755,123.592z"
      />
    </svg>
  </div>
</template>

<script>
export default {
  name: 'IconArrow',
};
</script>

<style lang="scss" scoped>
  .icon-arrow {
    display: flex;
    justify-content: center;
    align-items: center;
    &.left {
      transform: rotate(90deg);
    }
    &.right {
      transform: rotate(-90deg);
    }
    &.top {
      transform: rotate(180deg);
    }
    svg {
      padding: 1px;
    }
  }
</style>
