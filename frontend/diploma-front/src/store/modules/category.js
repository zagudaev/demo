import request from '../defRequest';

export default {
  namespaced: true,
  state: {
    tree: [],
    catalog: [],
  },
  mutations: {
    setTree(state, data) {
      state.tree = [];
      state.tree = data;
    },
    setCatalog(state, data) {
      state.catalog = data;
    },
  },
  actions: {
    create({ dispatch }, params) {
      params.picture.content = params.picture.content.file;
      return request().post('category', params)
        .then(({ data }) => data)
        .finally(() => dispatch('getFullTree'));
    },

    change({ dispatch }, params) {
      params.picture.content = params.picture.content.file;
      return request().put('category', params)
        .then(({ data }) => data)
        .finally(() => dispatch('getFullTree'));
    },

    deprecate({ dispatch }, params) {
      return request().put(`category/deprecated/?id=${params.id}`)
        .then(({ data }) => data)
        .finally(() => dispatch('getFullTree'));
    },

    getFullTree({ commit }) {
      return request().get('category/full').then(({ data }) => {
        commit('setTree', data);
        return data;
      });
    },

    getWithChildren({ commit }, id) {
      if (id) {
        return request().get(`category/children/${id}`).then(({ data }) => {
          commit('setCatalog', data.children);
          return data;
        });
      }

      return request().get('category/full').then(({ data }) => {
        commit('setCatalog', data);
        return data;
      });
    },
  },
};
