import React from "react";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import { compose, withHandlers } from "recompose";
import { reduxForm, Field, SubmissionError, reset } from "redux-form";
import { map, get } from "lodash";
import { Row, Col, message } from "antd";

import { Input } from "../../components/form";
import { validation } from "../../utils";
import { changePassword } from "../../actions/userActions";
import Button from "../../components/Button";

const PasswordForm = ({ handleSubmit, texts, language }) => (
  <div>
    <h2>{texts.PASSWORD_CHANGE}</h2>
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
                {
                  name: "password",
                  label: texts.PASSWORD,
                  type: "password",
                  validate: [validation.password[language]]
                },
                {
                  name: "password2",
                  label: texts.PASSWORD_AGAIN,
                  type: "password",
                  validate: [validation.password[language]]
                }
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
              label: texts.CHANGE_PASSWORD,
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
  connect(
    null,
    { reset }
  ),
  withHandlers({
    onSubmit: ({ texts, loggedUser, reset }) => async ({
      password,
      password2
    }) => {
      if (password !== password2) {
        throw new SubmissionError({ password2: texts.PASSWORDS_ARE_NOT_SAME });
      }
      if (await changePassword(get(loggedUser, "id"), password)) {
        message.success(texts.PASSWORD_CHANGED);
        reset("PasswordForm");
      } else {
        message.error(texts.SAVE_FAILED);
      }
    }
  }),
  reduxForm({
    form: "PasswordForm",
    enableReinitialize: true
  })
)(PasswordForm);
