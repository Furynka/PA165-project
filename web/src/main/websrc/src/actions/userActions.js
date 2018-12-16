import * as c from "./constants";
import { getByUrl, postByUrl, deleteByUrl, putByUrl } from "../utils";

const FIND_ALL_URL = "/persons/all";
const FIND_PERSON_URL = "/persons/findPerson/id";
const DELETE_PERSON_URL = "/persons/delete";
const REGISTER_PERSON_URL = "/persons/register";

//TODO - delete in final build
const DEBUG_URI_PREFIX = "http://localhost:8080";

export const getUserById = async id => await getByUrl(`${DEBUG_URI_PREFIX}${c.API}${FIND_PERSON_URL}/${id}`);

export const getUsers = async () => await getByUrl(`${DEBUG_URI_PREFIX}${c.API}${FIND_ALL_URL}`);

export const createUser = async user =>
  await postByUrl(`${DEBUG_URI_PREFIX}${c.API}${REGISTER_PERSON_URL}`, {
    body: JSON.stringify(user)
  });

export const updateUser = async user =>
  await putByUrl(`${DEBUG_URI_PREFIX}${c.API}${FIND_ALL_URL}/${user.id}`, {
    body: JSON.stringify(user)
  });

export const deleteUser = async id => await deleteByUrl(`${DEBUG_URI_PREFIX}${c.API}${DELETE_PERSON_URL}/${id}`);
