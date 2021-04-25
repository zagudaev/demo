let proxyPath = '';
if (process.env.NODE_ENV === 'development') proxyPath = process.env.VUE_APP_BASE_URL;

module.exports = {
  lintOnSave: false,

  publicPath: '',

 // devServer: {
 //   proxy: proxyPath,
 // },

  css: {
    loaderOptions: {
      scss: {
        prependData: `
          @import "@/assets/scss/var.scss";
        `,
      },
    },
  },
};
