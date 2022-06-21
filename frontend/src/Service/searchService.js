import callApi from "../util/api";

function searchService(searchValue) {
  return callApi("field/search", "GET", { q: searchValue, page: "1", size: "6" }, null).then(res => res.data)
}

function searchHavingTypeService(searchValue, type) {
  return callApi(`field/search/${type}`, "GET", { q: searchValue, page: "1", size: "6" }, null).then(res => res.data)
}

export { searchService, searchHavingTypeService };
