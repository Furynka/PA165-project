import * as c from "./constants";
import { getByUrl, postByUrl, deleteByUrl, putByUrlData, putByUrl } from "../utils/actions";

const ENTITY_URL = "/persons";

export const getUserById = async id => await getByUrl(`${c.API}${ENTITY_URL}/${id}`);

export const getUserByEmail = async email =>
  await getByUrl(`${c.API}${ENTITY_URL}/email?encodedEmail=` + encodeURIComponent(email));

export const getUsers = async () => await getByUrl(`${c.API}${ENTITY_URL}`);

export const createUser = async user =>
  await postByUrl(`${c.API}${ENTITY_URL}`, {
    body: JSON.stringify(user)
  });

export const updateUser = async user =>
  await putByUrl(`${c.API}${ENTITY_URL}`, {
    body: JSON.stringify(user)
  });

export const deleteUser = async id => await deleteByUrl(`${c.API}${ENTITY_URL}/${id}`);

export const authenticate = async (email, password) => {
  let response = await putByUrlData(`${c.API}${ENTITY_URL}/authenticate`, {
    body: JSON.stringify({ email, password })
  });

  return response;
};

export const changePassword = async (id, password) =>
  await postByUrl(`${c.API}${ENTITY_URL}/changePassword`, {
    body: JSON.stringify({ id, password })
  });
