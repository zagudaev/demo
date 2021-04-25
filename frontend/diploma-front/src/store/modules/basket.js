import request from '../defRequest';

export default {
  namespaced: true,
  state: {
    basketArray: [],
  },
  mutations: {
    addTo(state, { product, quantity }) {
      state.basketArray.push({
        id: product.id,
        name: product.name,
        description: product.description,
        quantity,
      });
    },
    removeFrom(state, data) {
      const index = state.basketArray.indexOf(data);
      state.basketArray.splice(index, 1);
    },
    change(state, data) {
      const item = state.basketArray.find((val) => data.item.id === val.id);
      item[data.key] = data.ev;
    },
    clear(state) {
      state.basketArray = [];
    },
  },
  actions: {
    createProposal(context, params) {
      return request().post('/proposal', params)
        .then(({ data }) => {
          console.log(data);
        });
    },
  },
};
