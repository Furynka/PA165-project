import * as c from "./constants";
import { getByUrl, postByUrl, deleteByUrl, putByUrl } from "../utils";

const ENTITY_URL = "/areas";

export const getAreaById = async id =>
  await getByUrl(`${c.API}${ENTITY_URL}/${id}`);

export const getAreas = async () => await getByUrl(`${c.API}${ENTITY_URL}`);

export const createArea = async area =>
  await postByUrl(`${c.API}${ENTITY_URL}`, {
    body: JSON.stringify(area)
  });

export const updateArea = async area =>
  await putByUrl(`${c.API}${ENTITY_URL}/${area.id}`, {
    body: JSON.stringify(area)
  });

export const deleteArea = async id =>
  await deleteByUrl(`${c.API}${ENTITY_URL}/${id}`);
