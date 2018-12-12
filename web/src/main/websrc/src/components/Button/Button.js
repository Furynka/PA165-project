import * as React from "react";
import { compose, withProps, renameProp, mapProps } from "recompose";
import { Button } from "antd";

const ButtonComponent = ({ ...props }) => <Button {...props} />;

export default compose(
  renameProp("label", "children"),
  renameProp("type", "htmlType"),
  renameProp("transparent", "ghost"),
  withProps(({ primary, circle }) => ({
    type: primary ? "primary" : "default",
    shape: circle ? "circle" : undefined
  })),
  mapProps(({ primary, circle, ...rest }) => rest)
)(ButtonComponent);
