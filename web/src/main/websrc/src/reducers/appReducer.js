import * as c from "../actions/constants";
import { EN, CZ, languages } from "../enums";
import { storage } from "../utils";

const initialState = {
  language: storage.get("language") || languages.CZ,
  texts: storage.get("language") === languages.EN ? EN : CZ
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case c.APP:
      return { ...state, ...action.payload };
    default:
      return state;
  }
};

export default reducer;
