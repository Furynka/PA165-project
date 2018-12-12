import * as c from "./constants";
import { getByUrl, postByUrl, deleteByUrl, putByUrl } from "../utils";

const ENTITY_URL = "/monsters";

export const getMonsterById = async id =>
  await getByUrl(`${c.API}${ENTITY_URL}/${id}`);

export const getMonsters = async () => await getByUrl(`${c.API}${ENTITY_URL}`);

export const createMonster = async monster =>
  await postByUrl(`${c.API}${ENTITY_URL}/create`, {
    body: JSON.stringify(monster)
  });

export const updateMonster = async monster =>
  await putByUrl(`${c.API}${ENTITY_URL}/${monster.id}`, {
    body: JSON.stringify(monster)
  });

export const deleteMonster = async id =>
  await deleteByUrl(`${c.API}${ENTITY_URL}/${id}`);
