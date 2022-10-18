import axios from "axios";

const API_URL = process.env.REACT_APP_API_URL;

function callApi(endpoint, method = "GET", data, withCredentials = false) {
  return axios({
    method: method,
    url: `${API_URL}/${endpoint}`,
    headers: {
      Authorization: `Bearer ${data.token}`
    },
    data: data.body,
    params: data.params,
    withCredentials: withCredentials
  })
}

export default callApi;
