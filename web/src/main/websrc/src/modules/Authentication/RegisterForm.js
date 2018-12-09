import React from "react";
import { withRouter } from "react-router-dom";
import { compose, withHandlers } from "recompose";
import { reduxForm, Field } from "redux-form";
import { map } from "lodash";
import { Row, Col, message } from "antd";

import ModalButton from "../../components/ModalButton";
import { Input } from "../../components/form";

const AuthenticationForm = ({ handleSubmit }) => (
  <ModalButton
    {...{
      label: "Sign up",
      title: "Sign up",
      buttonProps: { style: { marginBottom: 8, marginRight: 8 } },
      content: (
        <form
          {...{
            onSubmit: handleSubmit
          }}
        >
          <Row>
            {map(
              [
                { name: "name", label: "Username" },
                { name: "firstname", label: "First name" },
                { name: "surname", label: "Surname" },
                { name: "email", label: "Email" }
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
        </form>
      ),
      onOk: handleSubmit
    }}
  />
);

export default compose(
  withRouter,
  withHandlers({
    onSubmit: () => formData => {
      console.log(formData);
      message.success("You can sign in now!");
      return true;
    }
  }),
  reduxForm({
    form: "RegisterForm",
    enableReinitialize: true
  })
)(AuthenticationForm);
