import React from "react";
import { compose, defaultProps } from "recompose";
import { Row, Col } from "antd";

import Select from "../Select";
import ErrorBlock from "../ErrorBlock";

const FormSelect = ({
  meta: { touched, error },
  input,
  label,
  gutter,
  ...props
}) => (
  <Row {...{ gutter, style: { marginBottom: 16 } }}>
    <Col>
      <strong>{label}</strong>
    </Col>
    <Col>
      <Select
        {...{
          ...input,
          ...props,
          isField: true
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

export default compose(defaultProps({ gutter: 16 }))(FormSelect);
