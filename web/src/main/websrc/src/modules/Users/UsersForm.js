import React from "react";
import { withRouter } from "react-router-dom";
import { compose, withHandlers } from "recompose";
import { reduxForm, Field } from "redux-form";
import { map } from "lodash";
import { Row, Col } from "antd";

import PageWrapper from "../../components/PageWrapper";
import { Input } from "../../components/form";
import Button from "../../components/Button";

const UsersForm = ({ handleSubmit, match, history }) => (
  <PageWrapper
    {...{
      breadcrumb: [
        { label: "Users", to: "/users" },
        { label: match.params.id ? `User ${match.params.id}` : "New user" }
      ],
      content: (
        <form {...{ onSubmit: handleSubmit }}>
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
                display: "flex",
                flexWrap: "wrap"
              }
            }}
          >
            {map(
              [
                {
                  label: "Uložit a zavřít",
                  type: "submit",
                  primary: true,
                  style: { marginRight: 8, marginBottom: 8 }
                },
                {
                  label: "Zrušit",
                  onClick: () => history.push("/users"),
                  style: { marginRight: 8, marginBottom: 8 }
                }
              ],
              (button, key) => (
                <Button {...{ key, ...button }} />
              )
            )}
          </div>
        </form>
      )
    }}
  />
);

export default compose(
  withRouter,
  withHandlers({
    onSubmit: ({ history }) => formData => {
      console.log(formData);
      history.push("/users");
    }
  }),
  reduxForm({ form: "UsersForm", enableReinitialize: true })
)(UsersForm);
