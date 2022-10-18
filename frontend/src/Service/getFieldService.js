import callApi from "../util/api";

function getFieldList(type, page) {
  let url = "field";

  if (type !== "all") {
    url = `field/${type}`;
  }

  return callApi(url, 'GET', { page: page, size: "6" }, null).then(res => res.data);
}

export { getFieldList }
