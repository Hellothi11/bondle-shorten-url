import axios from 'axios';

const instance = axios.create({
  baseURL: process.env.REACT_APP_API_HOST,
  withCredentials: false,
  headers: {
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS',
  },
});

export default instance;
