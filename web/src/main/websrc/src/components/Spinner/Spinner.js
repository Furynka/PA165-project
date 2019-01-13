import * as React from "react";
import { Spin } from "antd";

const Spinner = ({ ...props }) => (
  <div
    {...{
      style: {
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        width: "calc(100% - 4em)",
        margin: "2em"
      }
    }}
  >
    <Spin {...props } />
  </div>
);

export default Spinner;
