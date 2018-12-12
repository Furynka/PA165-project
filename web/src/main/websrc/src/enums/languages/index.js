export { default as CZ } from "./CZ";
export { default as EN } from "./EN";

export const languages = {
  CZ: "CZ",
  EN: "EN"
};

export const languagesLabels = {
  [languages.CZ]: languages.CZ,
  [languages.EN]: languages.EN
};

export const languagesOptions = [
  { value: languages.CZ, label: languagesLabels.CZ },
  { value: languages.EN, label: languagesLabels.EN }
];
