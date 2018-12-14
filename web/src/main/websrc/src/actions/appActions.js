import * as c from "./constants";
import { EN, CZ, languages } from "../enums";
import { storage } from "../utils";

export const setLanguage = newLanguage => dispatch => {
  dispatch({
    type: c.APP,
    payload: {
      language: newLanguage,
      texts: newLanguage === languages.CZ ? CZ : EN
    }
  });

  storage.set("language", newLanguage);
};
