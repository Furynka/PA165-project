import * as React from "react";
import { compose, pure, withProps, renameProp } from "recompose";
import { Input, InputNumber } from "antd";

const { TextArea } = Input;

const InputComponent = ({ type, ...props }) =>
  type === "number" ? (
    <InputNumber {...props} />
  ) : type === "textarea" ? (
    <TextArea {...props} />
  ) : (
    <Input {...{ type, ...props }} />
  );

const defaultStyle = { width: "100%", maxWidth: "100%" };

export default compose(
  pure,
  renameProp("label", "placeholder"),
  withProps(({ style }) => ({
    style: style ? { ...defaultStyle, ...style } : defaultStyle
  }))
)(InputComponent);
