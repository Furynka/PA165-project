import * as c from "./constants";
import { getByUrl, postByUrl, deleteByUrl, putByUrl } from "../utils";

const ENTITY_URL = "/weapons";

export const getWeaponById = async id =>
  await getByUrl(`${c.API}${ENTITY_URL}/${id}`);

export const getWeapons = async () => await getByUrl(`${c.API}${ENTITY_URL}`);

export const createWeapon = async weapon =>
  await postByUrl(`${c.API}${ENTITY_URL}/create`, {
    body: JSON.stringify(weapon)
  });

export const updateWeapon = async weapon =>
  await putByUrl(`${c.API}${ENTITY_URL}/${weapon.id}`, {
    body: JSON.stringify(weapon)
  });

export const deleteWeapon = async id =>
  await deleteByUrl(`${c.API}${ENTITY_URL}/${id}`);
