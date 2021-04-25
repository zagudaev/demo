import request from '../defRequest';

export default {
  namespaced: true,
  state: {
    list: [],
    lastCategoryId: null,

    product: {},
  },
  mutations: {
    setList(state, data) {
      state.list = data;
    },
    setLastCategoryId(state, data) {
      state.lastCategoryId = data;
    },
    setProduct(state, data) {
      state.product = data;
    },
  },
  actions: {
    create({ state, dispatch }, params) {
      params.pictures.forEach((item) => {
        item.content = item.content.file;
      });
      params.pictures = params.pictures.filter((item) => item.content);
      return request().post('product', params)
        .then(({ data }) => data)
        .finally(() => dispatch('getByCategoryId', state.lastCategoryId));
    },

    change({ state, dispatch }, params) {
      params.pictures.forEach((item) => {
        item.content = item.content.file;
      });
      params.pictures = params.pictures.filter((item) => item.content);
      return request().put('product', params)
        .then(({ data }) => data)
        .finally(() => dispatch('getByCategoryId', state.lastCategoryId));
    },

    getByCategoryId({ commit }, categoryId) {
      return request().get('product', { params: { categoryId } })
        .then(({ data }) => {
          // if (data.pictures) data.pictures = [];
          commit('setLastCategoryId', categoryId);
          commit('setList', data);
          return data;
        });
    },

    getById({ commit }, id) {
      commit('setProduct', {});
      return request().get(`product/${id}`)
        .then(({ data }) => {
          commit('setProduct', data);
        });
    },

    delete({ state, dispatch }, id) {
      return request().delete(`product/${id}`).finally(() => {
        dispatch('getByCategoryId', state.lastCategoryId);
      });
    },

  },
};
