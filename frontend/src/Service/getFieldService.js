import callApi from "../util/api";

function getFieldList(type) {
  let url = "field";

  if (type !== "all") {
    url = `field/${type}`;
  }

  return callApi(url, 'GET', {page:"1", size:"6"}, null).then(res => res.data);
}

export default getFieldList
