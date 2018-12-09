import * as React from "react";
import { compose, pure, withProps, mapProps, defaultProps } from "recompose";
import { Dropdown } from "antd";

import Menu from "../Menu";
import Button from "../Button";

const DropdownComponent = ({ ...props }) => <Dropdown {...props} />;

export default compose(
  pure,
  defaultProps({ buttonStyle: {}, trigger: ["click"] }),
  withProps(({ items, label, buttonStyle, onClick }) => ({
    overlay: <Menu {...{ items, onClick }} />,
    children: <Button {...{ label, transparent: true, style: buttonStyle }} />
  })),
  mapProps(({ ...rest }) => rest)
)(DropdownComponent);
