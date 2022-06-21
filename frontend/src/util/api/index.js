import axios from "axios";

const API_URL = "http://localhost:8080/api";

function callApi(endpoint, method = "GET", params, body) {
  return axios({
    method: method,
    url: `${API_URL}/${endpoint}`,
    data: body,
    params: params
  }).catch((err) => {
    console.log(err);
  });
}

export default callApi;
