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
