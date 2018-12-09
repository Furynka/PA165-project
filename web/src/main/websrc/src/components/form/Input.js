import React from "react";
import { compose, defaultProps } from "recompose";
import { Row, Col } from "antd";

import Input from "../Input";
import ErrorBlock from "../ErrorBlock";

const FormInput = ({
  meta: { touched, error },
  input,
  label,
  type,
  placeholder,
  disabled,
  gutter
}) => (
  <Row {...{ gutter, style: { marginBottom: 16 } }}>
    <Col>
      <strong>{label}</strong>
    </Col>
    <Col>
      <Input
        {...{
          ...input,
          type,
          placeholder,
          disabled
        }}
      />
    </Col>
    {touched && (
      <Col>
        <ErrorBlock {...{ error }} />
      </Col>
    )}
  </Row>
);

export default compose(defaultProps({ gutter: 16 }))(FormInput);
