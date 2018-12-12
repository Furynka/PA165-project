import { languages, CZ, EN } from "../enums";
import { emailFormatCheck, hasValue } from "./utils";

export const required = {
  [languages.CZ]: value => (hasValue(value) ? undefined : CZ.REQUIRED),
  [languages.EN]: value => (hasValue(value) ? undefined : EN.REQUIRED)
};

export const email = {
  [languages.CZ]: value =>
    emailFormatCheck(value) ? undefined : CZ.ENTER_VALID_EMAIL_ADDRESS,
  [languages.EN]: value =>
    emailFormatCheck(value) ? undefined : EN.ENTER_VALID_EMAIL_ADDRESS
};

export const isNumeric = {
  [languages.CZ]: value =>
    !hasValue(value) || !isNaN(Number(value))
      ? undefined
      : CZ.ENTER_VALID_NUMBER,
  [languages.EN]: value =>
    !hasValue(value) || !isNaN(Number(value))
      ? undefined
      : EN.ENTER_VALID_NUMBER
};

export const isNumericBetween0and100 = {
  [languages.CZ]: value =>
    !hasValue(value) ||
    !isNaN(Number(value)) ||
    Number(value) < 0 ||
    Number(value) > 100
      ? undefined
      : CZ.ENTER_NUMBER_BETWEEN_0_AND_100,
  [languages.EN]: value =>
    !hasValue(value) ||
    !isNaN(Number(value)) ||
    Number(value) < 0 ||
    Number(value) > 100
      ? undefined
      : EN.ENTER_NUMBER_BETWEEN_0_AND_100
};
