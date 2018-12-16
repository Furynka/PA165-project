import fetch from "./fetch";

export const getByUrl = async (url, options) => {
  try {
    const response = await fetch(url, options);

    return await response.json();
  } catch (error) {
    console.log(error);
    return null;
  }
};

export const putByUrl = async (url, options) => {
  try {
    const response = await fetch(url, { method: "PUT", ...options });

    return response.ok;
  } catch (error) {
    console.log(error);
    return false;
  }
};

export const postByUrl = async (url, options) => {
  try {
    const response = await fetch(url, { method: "POST", ...options });

    return response.ok;
  } catch (error) {
    console.log(error);
    return false;
  }
};

export const deleteByUrl = async (url, options) => {
  try {
    const response = await fetch(url, { method: "DELETE", ...options });

    return response.ok;
  } catch (error) {
    console.log(error);
    return false;
  }
};