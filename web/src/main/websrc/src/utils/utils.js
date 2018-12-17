import { isArray } from "lodash";

/**
 * Checks if variable has value.
 */
export const hasValue = item =>
  item !== undefined && item !== null && item !== "";

/**
 * Checks email validity.
 */
export const emailFormatCheck = value =>
  /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i.test(value);

/**
 * Checks password validity.
 */
export const passwordSecurityCheck = value =>
  /^^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{5,}$/i.test(value);

export const asyncForEach = async (array, callback) => {
  if (isArray(array)) {
    for (let index = 0; index < array.length; index++) {
      if (!(await callback(array[index], index, array))) {
        return false;
      }
    }
  }

  return true;
};
