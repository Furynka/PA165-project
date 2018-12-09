import React from "react";
import { withRouter } from "react-router-dom";
import { compose, withHandlers } from "recompose";
import { reduxForm, Field } from "redux-form";
import { map } from "lodash";
import { Row, Col, message } from "antd";

import { Input } from "../../components/form";
import Button from "../../components/Button";

const ProfileForm = ({ handleSubmit }) => (
  <div>
    <h2>Edit profile</h2>
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
                { name: "name", label: "Username", disabled: true },
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
              label: "Update profile",
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
      message.success("Profile updated!");
    }
  }),
  reduxForm({
    form: "ProfileForm",
    enableReinitialize: true
  })
)(ProfileForm);
