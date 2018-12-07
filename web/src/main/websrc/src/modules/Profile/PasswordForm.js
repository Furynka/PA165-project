import React from "react";
import { withRouter } from "react-router-dom";
import { compose, withHandlers } from "recompose";
import { reduxForm, Field } from "redux-form";
import { map } from "lodash";
import { Row, Col, message } from "antd";

import { Input } from "../../components/form";
import Button from "../../components/Button";

const PasswordForm = ({ handleSubmit }) => (
  <div>
    <h2>Password change</h2>
    <form
      {...{
        onSubmit: handleSubmit
      }}
    >
      <Row>
        <Col {...{ lg: 12 }}>
          <Row>
            {map(
              [
                { name: "password", label: "Password", type: "password" },
                { name: "password2", label: "Password again", type: "password" }
              ],
              ({ ...field }, key) => (
                <Col {...{ key }}>
                  <Field
                    {...{
                      component: Input,
                      ...field
                    }}
                  />
                </Col>
              )
            )}
          </Row>
        </Col>
      </Row>
      <div
        {...{
          style: {
            width: "100%",
            display: "flex"
          }
        }}
      >
        {map(
          [
            {
              label: "Change password",
              type: "submit",
              primary: true,
              style: { marginRight: 8, marginBottom: 8 }
            }
          ],
          (button, key) => (
            <Button {...{ key, ...button }} />
          )
        )}
      </div>
    </form>
  </div>
);

export default compose(
  withRouter,
  withHandlers({
    onSubmit: () => formData => {
      console.log(formData);
      message.success("Password changed!");
    }
  }),
  reduxForm({
    form: "PasswordForm",
    enableReinitialize: true
  })
)(PasswordForm);
