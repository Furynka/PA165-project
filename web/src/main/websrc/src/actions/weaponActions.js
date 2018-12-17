import * as c from "./constants";
import { getByUrl, postByUrl, deleteByUrl, putByUrl } from "../utils";

const ENTITY_URL = "/weapons";

export const getWeaponById = async id =>
  await getByUrl(`http://localhost:8080${c.API}${ENTITY_URL}/${id}`);

export const getWeapons = async () => await getByUrl(`http://localhost:8080${c.API}${ENTITY_URL}`);

export const createWeapon = async weapon =>
  await postByUrl(`http://localhost:8080${c.API}${ENTITY_URL}`, {
    body: JSON.stringify(weapon)
  });

export const updateWeapon = async weapon =>
  await putByUrl(`http://localhost:8080${c.API}${ENTITY_URL}/${weapon.id}`, {
    body: JSON.stringify(weapon)
  });

export const deleteWeapon = async id =>
  await deleteByUrl(`http://localhost:8080${c.API}${ENTITY_URL}/${id}`);

export const getWeaponByName = async name =>
  await getByUrl(`http://localhost:8080${c.API}${ENTITY_URL}/by_name/${name}`);
