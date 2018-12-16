import React from "react";
import { withRouter } from "react-router-dom";
import { compose, withHandlers, withState } from "recompose";
import { reduxForm, Field, SubmissionError } from "redux-form";
import { map } from "lodash";
import { Row, Col, message } from "antd";

import ModalButton from "../../components/ModalButton";
import { Input } from "../../components/form";
import { validation } from "../../utils";
import { createUser } from "../../actions/userActions";

const RegisterForm = ({ handleSubmit, texts, language, closeModal, setCloseModal }) => (
  <ModalButton
    {...{
      closeOnOk: false,
      close: closeModal,
      onClose: () => setCloseModal(false),
      label: texts.SIGN_UP,
      title: texts.REGISTRATION,
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
                },
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
        </form>
      ),
      onOk: handleSubmit,
      modalProps: {
        okButtonLabel: texts.SIGN_UP,
        cancelButtonLabel: texts.CANCEL
      }
    }}
  />
);

export default compose(
  withRouter,
  withState("closeModal", "setCloseModal", false),
  withHandlers({
    onSubmit: ({ texts, setCloseModal }) => async formData => {
      if (formData.password !== formData.password2) {
        throw new SubmissionError({ password2: texts.PASSWORDS_ARE_NOT_SAME });
      }

      if (await createUser(formData)) {
        message.success(texts.YOU_CAN_SIGN_IN_NOW);
        setCloseModal(true);
      } else {
        throw new SubmissionError({ password2: texts.REGISTRATION_FAILED });
      }
    }
  }),
  reduxForm({
    form: "RegisterForm",
    enableReinitialize: true
  })
)(RegisterForm);
