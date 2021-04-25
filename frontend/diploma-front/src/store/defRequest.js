import Axios from 'axios';
import store from './store';

let baseURL = '';
if (process.env.NODE_ENV === 'production') baseURL = process.env.VUE_APP_BASE_URL;

const request = () => {
  const req = Axios.create({
    baseURL: 'http://192.168.0.92:7070/',
  });

  req.interceptors.response.use(
    (r) => r,
    (error) => {
      if (error.response && (error.response.status === 401 || error.response.status === 403)) {}
      return Promise.reject(error);
    },
  );

  return req;
};

export default request;
