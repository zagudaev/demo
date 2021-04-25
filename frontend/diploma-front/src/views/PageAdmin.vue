<template>
  <app-content-block :width="1152" class="page page-admin">
    <p class="title">Администрирование</p>

    <!-- Category -->
    <app-tree-viewer
      v-model="selectedCategory"
      :items="$store.state.category.tree"
      @select="onSelectCategory"
    >
      <template #title>Категории:</template>

      <template #end="{item}">
        <app-button label="Добавить" @click="addCategory(item)"/>
      </template>

      <template #editor>
        <div v-if="Object.keys(selectedCategory).length > 0"
             class="node-editor"
        >
          <p class="title">Выбранная категория</p>

          <div>
            <img v-if="refreshFlag" :src="categoryImage" class="node-editor__picture">
          </div>

          <div class="node-editor__inputs">
            <app-input label="Название" v-model="selectedCategory.name"
                       readonly
                       class="node-editor__input" />
            <app-text-area label="Описание" v-model="selectedCategory.description"
                           readonly
                       class="node-editor__input" />
<!--            <app-input label="Дата автоматического удаления" v-model="selectedCategory.deprecatedFrom"-->
<!--                       readonly-->
<!--                       class="node-editor__input" />-->
          </div>

          <div class="spacer"></div>

          <div class="node-editor__buttons">
            <app-button label="Добавить подкатегорию" @click="addCategory(selectedCategory)" />
            <app-button label="Изменить" @click="changeCategory" />
            <app-button label="Удалить" @click="deprecateCategory" />
          </div>
        </div>
      </template>

    </app-tree-viewer>

    <app-modal-window :show="addingCategory || changingCategory" @close="closeCategoryModal">
      <template #header>
        <p v-if="addingCategory" class="title">Добавление категории</p>
        <p v-else-if="changingCategory" class="title">Изменение категории</p>
      </template>

      <template #content>
        <app-input label="Название" v-model="changeableCategory.name"
                   class="modal-window__input" />
        <app-text-area label="Описание" v-model="changeableCategory.description"
                       class="modal-window__input" />
<!--        <app-input label="Дата автоматического удаления" v-model="changeableCategory.deprecatedFrom"-->
<!--                   class="modal-window__input" />-->
        <div class="file-loader">
          <app-file-loader v-model="changeableCategory.picture.content"
                           label="Загрузить изображение" />
        </div>
      </template>

      <template #buttons>
        <app-button label="Добавить" v-if="addingCategory" @click="endCategoryAdding(true)" />
        <app-button label="Изменить" v-else-if="changingCategory" @click="endCategoryChanging(true)" />
        <app-button label="Отмена" @click="closeCategoryModal" />
      </template>
    </app-modal-window>

    <!-- Product -->
    <app-table v-model="selectedProduct"
               :items="$store.state.product.list"
               :headers="tableProductHeaders"
               v-if="Object.keys(selectedCategory).length"
    >
      <template #table-header>
        <p class="title">Товары категории "{{selectedCategory.name}}":</p>
      </template>

      <template #item="{item}">
        <td><p v-if="item.name">{{item.name}}</p></td>
        <td><p v-if="item.description">{{item.description}}</p></td>
        <td><p v-if="item.price">{{item.price}}</p></td>
        <td><p v-if="item.quantity">{{item.quantity}}</p></td>
<!--        <td><p v-if="item.deprecatedFrom">{{item.deprecatedFrom}}</p></td>-->
        <td class="table-item__buttons">
          <div class="holder">
            <app-button label="Изменить" @click="changeProduct(item)" />
            <app-button label="Удалить" @click="deleteProduct(item)" />
          </div>
        </td>
      </template>

      <template #buttons>
        <app-button label="Добавить" @click="addProduct" />
      </template>
    </app-table>

    <app-modal-window :show="addingProduct || changingProduct" @close="closeProductModal">
      <template #header>
        <p v-if="addingProduct" class="title">Добавление продукта</p>
        <p v-else-if="changingProduct" class="title">Изменение категории</p>
      </template>

      <template #content>
        <app-input label="Название"
                   v-model="changeableProduct.name"
                   class="modal-window__input"
        />
        <app-text-area label="Описание"
                       v-model="changeableProduct.description"
                       class="modal-window__input"
        />
        <app-input label="Цена"
                   v-model="changeableProduct.price"
                   class="modal-window__input"
        />
        <app-input label="Количество"
                   v-model="changeableProduct.quantity"
                   class="modal-window__input"
        />

        <app-input label="Артикул"
                   v-model="changeableProduct.article"
                   class="modal-window__input"
        />
        <!--        <app-input label="Дата автоматического удаления"-->
<!--                   v-model="changeableProduct.deprecatedFrom"-->
<!--                   class="modal-window__input"-->
<!--        />-->
        <div class="file-loader">
          <app-file-loader v-for="(item, index) in changeableProduct.pictures" :key="`${index}_${item.content}`"
                           v-model="item.content"
                           @change="onProductFileChange($event)"
                           label="Загрузить изображение"
          />
        </div>
      </template>

      <template #buttons>
        <app-button label="Добавить" v-if="addingProduct" @click="endProductAdding(true)"/>
        <app-button label="Изменить" v-else-if="changingProduct" @click="endProductChanging(true)"/>
        <app-button label="Отмена" @click="closeProductModal"/>
      </template>
    </app-modal-window>

  </app-content-block>
</template>

<script>
import AppContentBlock from '../components/AppContentBlock.vue';
import AppTreeViewer from '../components/tree/AppTreeViewer.vue';
import AppButton from '../components/common/AppButton.vue';
import AppInput from '../components/common/AppInput.vue';
import AppTextArea from '../components/common/AppTextArea.vue';
import AppFileLoader from '../components/common/AppFileLoader.vue';
import AppModalWindow from '../components/AppModalWindow.vue';
import AppTable from '../components/AppTable.vue';
import recursiveFind from '../_helper/find';

export default {
  name: 'PageAdmin',
  components: {
    AppTable,
    AppModalWindow,
    AppFileLoader,
    AppTextArea,
    AppInput,
    AppButton,
    AppTreeViewer,
    AppContentBlock,
  },
  data() {
    return {
      addingCategory: false,
      changingCategory: false,
      selectedCategory: {},
      changeableCategory: {},
      refreshFlag: true,

      addingProduct: false,
      changingProduct: false,
      selectedProduct: {},
      changeableProduct: {},
      tableProductHeaders: [
        'Название',
        'Описание',
        'Цена',
        'Кол-во',
        // 'Дата авто-удаления',
        '',
      ],
      productImageCount: 0,
    };
  },
  created() {
    this.$store.dispatch('category/getFullTree');
  },
  computed: {
    categoryImage() {
      return `${process.env.VUE_APP_BASE_URL}picture/${this.selectedCategory?.picture?.id}`;
    },
  },
  methods: {
    addCategory(item) {
      const parentId = item ? item.id : null;
      this.changeableCategory = {
        parentId,
        name: 'Новая категория',
        description: 'Описание',
        deprecatedFrom: null,
        picture: {
          content: {},
        },
      };
      this.addingCategory = true;
    },
    endCategoryAdding(status) {
      if (status) {
        this.$store.dispatch('category/create', this.changeableCategory);
      } else {
        this.changeableCategory = {};
      }
      this.addingCategory = false;
    },
    changeCategory() {
      this.changeableCategory = JSON.parse(JSON.stringify(this.selectedCategory));
      this.changeableCategory.picture = { content: {} };
      this.changingCategory = true;
    },
    endCategoryChanging(status) {
      if (status) {
        this.$store.dispatch('category/change', this.changeableCategory).then(() => {
          this.refreshFlag = false;
          this.selectedCategory = recursiveFind((val) => val.id === this.selectedCategory.id,
            this.$store.state.category.tree, 'children');
          setTimeout(() => { this.refreshFlag = true; }, 0);
        });
      } else {
        this.changeableCategory = {};
      }
      this.changingCategory = false;
    },
    deprecateCategory() {
      this.$store.dispatch('category/deprecate', this.selectedCategory);
    },
    closeCategoryModal() {
      if (this.addingCategory) this.endCategoryAdding(false);
      if (this.changingCategory) this.endCategoryChanging(false);
    },
    onSelectCategory() {
      this.$store.dispatch('product/getByCategoryId', this.selectedCategory.id);
    },

    addProduct() {
      this.changeableProduct = {
        categoryId: this.selectedCategory.id,
        name: 'Новый товар',
        description: 'Описание',
        price: '100',
        quantity: '15',
        deprecatedFrom: null,
        article: '',
        pictures: [
          { content: {} },
        ],
      };
      this.addingProduct = true;
    },
    endProductAdding(status) {
      if (status) {
        this.$store.dispatch('product/create', this.changeableProduct);
      }
      this.addingProduct = false;
    },
    changeProduct(item) {
      this.changeableProduct = JSON.parse(JSON.stringify(item));
      this.changeableProduct.categoryId = this.changeableProduct.category.id;
      delete (this.changeableProduct.category);
      this.changeableProduct.pictures = [{ content: {} }];
      this.changingProduct = true;
    },
    endProductChanging(status) {
      if (status) {
        this.$store.dispatch('product/change', this.changeableProduct);
      } else {
        this.changeableCategory = {};
      }
      this.changingProduct = false;
    },
    deleteProduct(item) {
      this.$store.dispatch('product/delete', item.id);
    },
    closeProductModal() {
      if (this.addingProduct) this.endProductAdding(false);
      if (this.changingProduct) this.endProductChanging(false);
    },
    onProductFileChange(ev) {
      if (ev.file) {
        this.changeableProduct.pictures.push({
          content: {},
        });
      }
      const arr = this.changeableProduct.pictures;
      for (let i = 0; i < arr.length - 1; i += 1) {
        if (!arr[i].content.file) arr.splice(i, 1);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
  .icon-plus {
    margin-left: 6px;
    cursor: pointer;
  }

  .node-editor {
    flex: 1;
    display: flex;
    flex-direction: column;

    &__picture {
      max-height: 200px;
      pointer-events: none;
    }

    &__inputs {
    }

    &__input {
      margin: 10px 0;
    }

    &__buttons {
      display: flex;
      flex-wrap: wrap;

      .button {
        margin: 5px 10px 0 0;

        &:first-child {
          margin-left: 0;
        }

        &:last-child {
          margin-right: 0;
        }
      }
    }
  }

  td.table-item__buttons {
    min-width: 220px;
    max-width: 220px;
    width: 220px;
    .holder {
      display: flex;
      justify-content: flex-end;
      .button {
        margin-left: 5px;
      }
    }
  }

  .modal-window {
    &__input {
      margin: 5px 0;
    }
  }

  .file-loader {
    margin-top: 7px;
  }
</style>
