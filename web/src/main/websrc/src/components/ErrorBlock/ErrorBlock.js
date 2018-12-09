import * as React from "react";

const ErrorBlock = ({ error, ...props }) =>
  error ? (
    <span {...{ style: { color: "#FF4136" }, ...props }}>{error}</span>
  ) : (
    <span />
  );

export default ErrorBlock;
