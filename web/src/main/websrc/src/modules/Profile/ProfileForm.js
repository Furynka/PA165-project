import React from "react";
import { withRouter } from "react-router-dom";
import { compose, withHandlers } from "recompose";
import { reduxForm, Field } from "redux-form";
import { map } from "lodash";
import { Row, Col, message } from "antd";

import { Input } from "../../components/form";
import { validation } from "../../utils";
import { updateUser } from "../../actions/userActions";
import Button from "../../components/Button";

const ProfileForm = ({ handleSubmit, texts, language }) => (
  <div>
    <h2>{texts.EDIT_PROFILE}</h2>
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
                  name: "name",
                  label: texts.FIRST_NAME,
                  validate: [validation.required[language]]
                },
                {
                  name: "surname",
                  label: texts.SURNAME,
                  validate: [validation.required[language]]
                },
                {
                  name: "email",
                  label: texts.EMAIL,
                  validate: [validation.required[language], validation.email[language]]
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
              label: texts.UPDATE_PROFILE,
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
    onSubmit: ({ texts }) => async ({ id, name, surname, email, administrator }) => {
      if (await updateUser({ id, name, surname, email, administrator })) {
        message.success(texts.PROFILE_UPDATED);
      } else {
        message.success(texts.SAVE_FAILED);
      }
    }
  }),
  reduxForm({
    form: "ProfileForm",
    enableReinitialize: true
  })
)(ProfileForm);
