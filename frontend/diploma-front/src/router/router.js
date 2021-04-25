import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    redirect: '/catalog',
  },
  {
    path: '/catalog',
    name: 'Catalog',
    component: () => import('../views/PageCatalog.vue'),
    props: (route) => ({ id: route.query.id }),
  },
  {
    path: '/product',
    name: 'Product',
    component: () => import('../views/PageProduct.vue'),
    props: (route) => ({ id: route.query.id }),
  },
  {
    path: '/basket',
    name: 'Basket',
    component: () => import('../views/PageBasket.vue'),
  },
  {
    path: '/admin',
    name: 'Admin',
    component: () => import('../views/PageAdmin.vue'),
  },
  {
    path: '/test',
    name: 'Test',
    component: () => import('../views/PageTest.vue'),
  },
  {
    path: '*',
    name: 'Page404',
    component: () => import('../views/Page404.vue'),
  },
];

const router = new VueRouter({
  routes,
});

export default router;
