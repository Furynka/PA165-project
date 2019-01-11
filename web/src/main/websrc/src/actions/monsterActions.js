import * as c from "./constants";
import { getByUrl, postByUrl, deleteByUrl, postByUrlData, putByUrl } from "../utils";

const ENTITY_URL = "/monsters";

export const getMonsterById = async id =>
  await getByUrl(`${c.API}${ENTITY_URL}/${id}`);

export const getMonsters = async () => await getByUrl(`${c.API}${ENTITY_URL}`);

export const createMonster = async monster =>
  await postByUrl(`${c.API}${ENTITY_URL}`, {
    body: JSON.stringify(monster)
  });

export const updateMonster = async monster =>
  await putByUrl(`${c.API}${ENTITY_URL}`, {
    body: JSON.stringify(monster)
  });

export const deleteMonster = async id =>
  await deleteByUrl(`${c.API}${ENTITY_URL}/${id}`);

export const findStrongestMonster = async () =>
  await getByUrl(`${c.API}${ENTITY_URL}/strongestMonster`);

export const monstersFromSameArea = async monster =>
  await postByUrlData(`${c.API}${ENTITY_URL}/monstersFromSameArea`, {
    body: JSON.stringify(monster)
  });
