import * as React from "react";
import { compose, withProps, mapProps, defaultProps } from "recompose";
import { Dropdown } from "antd";

import Menu from "../Menu";
import Button from "../Button";

const DropdownComponent = ({ ...props }) => <Dropdown {...props} />;

export default compose(
  defaultProps({ buttonStyle: {}, trigger: ["click"] }),
  withProps(({ items, label, buttonStyle, onClick }) => ({
    overlay: <Menu {...{ items, onClick, mode: "vertical" }} />,
    children: <Button {...{ label, transparent: true, style: buttonStyle }} />
  })),
  mapProps(({ items, label, buttonStyle, onClick, ...rest }) => rest)
)(DropdownComponent);
