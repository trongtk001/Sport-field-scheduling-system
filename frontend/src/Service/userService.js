import callApi from "../util/api";

function getUserInfo(userID) {

  const url = `users/${userID}`;

  return callApi(url, 'GET', null, null, true).then(res => res.data);
}

function editUserInfo(userID, userInfo) {
  const url = `users/${userID}`;

  return callApi(url, 'PUT', null, userInfo, true).then(res => res.data);
}

export { getUserInfo, editUserInfo }