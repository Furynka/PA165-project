import * as c from "./constants";
import { getByUrl, postByUrl, deleteByUrl, putByUrl } from "../utils";

const ENTITY_URL = "/persons";

export const getUserById = async id => await getByUrl(`${c.API}${ENTITY_URL}/findPerson/id/${id}`);

export const getUsers = async () => await getByUrl(`${c.API}${ENTITY_URL}/all`);

export const createUser = async user =>
  await postByUrl(`${c.API}${ENTITY_URL}/register`, {
    body: JSON.stringify(user)
  });

export const updateUser = async user =>
  await putByUrl(`${c.API}${ENTITY_URL}/${user.id}`, {
    body: JSON.stringify(user)
  });

export const deleteUser = async id => await deleteByUrl(`${c.API}${ENTITY_URL}/${id}`);
