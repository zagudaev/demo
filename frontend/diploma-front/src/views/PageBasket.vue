<template>
  <app-content-block :width="1152" class="page page-basket">
    <p class="title">Корзина</p>
    <app-table v-model="selectedItem"
               :items="$store.state.basket.basketArray"
               :headers="headers"
    >
      <template #item="{item}">
        <td><p v-if="item.name">{{item.name}}</p></td>
        <td><p v-if="item.description">{{item.description}}</p></td>
        <td><p v-if="item.price">{{item.price}}</p></td>
        <td><app-input type="number"
                       v-if="item.quantity"
                       :value="item.quantity"
                       @input="onChangeQuantity(item, $event)"
                       class="num-input"
        /></td>
        <td class="table-item__buttons">
          <div class="holder">
            <app-button label="Удалить" @click="deleteProduct(item)" />
          </div>
        </td>
      </template>
    </app-table>

    <div class="proposal-button_container">
      <app-button label="Оформить заявку" @click="onProposal" />
    </div>

    <app-modal-window :show="isRequest" @close="isRequest = false">
      <template #header>
        <p class="title">Оформление заявки</p>
      </template>

      <template #content>
        <app-input label="ФИО"
                   v-model="proposalForm.customer"
                   class="modal-window__input"
        />
        <app-input label="Номер телефона"
                   v-model="proposalForm.contact"
                   class="modal-window__input"
        />
        <app-input label="Адрес доставки"
                   v-model="proposalForm.address"
                   class="modal-window__input"
        />
        <app-text-area label="Комментарий"
                       v-model="proposalForm.comment"
                       class="modal-window__input"
        />
      </template>

      <template #buttons>
        <app-button label="Оформить заявку" @click="onProposalEnd" />
      </template>
    </app-modal-window>

  </app-content-block>
</template>

<script>
import AppContentBlock from '../components/AppContentBlock.vue';
import AppTable from '../components/AppTable.vue';
import AppButton from '../components/common/AppButton.vue';
import AppInput from '../components/common/AppInput.vue';
import AppModalWindow from '../components/AppModalWindow.vue';
import AppTextArea from '../components/common/AppTextArea.vue';

export default {
  name: 'PageBasket',
  components: {
    AppTextArea,
    AppModalWindow,
    AppInput,
    AppButton,
    AppTable,
    AppContentBlock,
  },
  data() {
    return {
      selectedItem: {},
      headers: [
        'Название',
        'Описание',
        'Цена',
        'Кол-во',
        '',
      ],
      isRequest: false,
      proposalForm: {
        customer: '',
        contact: '',
        address: '',
        comment: '',
        productIds: [],
      },
    };
  },
  methods: {
    onChangeQuantity(item, ev) {
      this.$store.commit('basket/change', { item, key: 'quantity', ev });
    },
    deleteProduct(item) {
      this.$store.commit('basket/removeFrom', item);
    },

    onProposal() {
      this.isRequest = true;
    },
    onProposalEnd() {
      this.proposalForm.productIds = JSON.parse(JSON.stringify(this.$store.state.basket.basketArray));
      this.$store.dispatch('basket/createProposal', this.proposalForm).finally(() => {
        this.isRequest = false;
        this.proposalForm = {
          customer: '',
          contact: '',
          address: '',
          comment: '',
          productIds: [],
        };
      });
    },
  },
};
</script>

<style lang="scss" scoped>
  .page-basket {
    .proposal-button_container {
      margin: 20px 0 0;
      display: flex;
      flex-direction: row;
      justify-content: flex-end;
    }

    ::v-deep .num-input {
      width: 100px;
    }
  }
</style>
