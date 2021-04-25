import Vue from 'vue';
import Vuex from 'vuex';

import user from './modules/user';
import basket from './modules/basket';
import category from './modules/category';
import product from './modules/product';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    user,
    category,
    product,
    basket,
  },
});
