import React from "react";
import { withRouter } from "react-router-dom";
import { compose, withHandlers } from "recompose";
import { reduxForm, Field, SubmissionError } from "redux-form";
import { map, get } from "lodash";
import { Row, Col } from "antd";

import PageWrapper from "../../components/PageWrapper";
import { Input } from "../../components/form";
import Button from "../../components/Button";
import { getUserById, createUser, updateUser } from "../../actions/userActions";
import { validation, entityEnhancer } from "../../utils";

const UsersForm = ({
  handleSubmit,
  newUser,
  history,
  texts,
  language,
  entity
}) => (
  <PageWrapper
    {...{
      breadcrumb: [
        { label: texts.USERS, to: "/users" },
        {
          label: `${
            !newUser
              ? get(entity, "name") || get(entity, "surname")
                ? `${get(entity, "name")}${
                    get(entity, "surname") ? ` ${get(entity, "surname")}` : ""
                  }`
                : "-"
              : texts.NEW_USER
          }${get(entity, "administrator") ? ` (${texts.ADMINISTRATOR})` : ""}`
        }
      ],
      content: (
        <form {...{ onSubmit: handleSubmit }}>
          <Row>
            <Col {...{ lg: 12 }}>
              <Row>
                {map(
                  [
                    {
                      name: "name",
                      label: texts.FIRST_NAME,
                      validate: [validation.required[language]],
                      show: true
                    },
                    {
                      name: "surname",
                      label: texts.SURNAME,
                      validate: [validation.required[language]],
                      show: true
                    },
                    {
                      name: "email",
                      label: texts.EMAIL,
                      validate: [
                        validation.required[language],
                        validation.email[language]
                      ],
                      show: true
                    },
                    {
                      name: "password",
                      label: texts.PASSWORD,
                      validate: [validation.password[language]],
                      type: "password",
                      show: newUser
                    },
                    {
                      name: "password2",
                      label: texts.PASSWORD_AGAIN,
                      validate: [validation.password[language]],
                      type: "password",
                      show: newUser
                    }
                  ],
                  ({ show, ...field }, key) =>
                    show && (
                      <Col {...{ key }}>
                        <Field
                          {...{
                            component: Input,
                            ...field,
                            disabled: !newUser
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
                  label: texts.SAVE_AND_CLOSE,
                  type: "submit",
                  primary: true,
                  style: { marginRight: 8, marginBottom: 8 },
                  show: newUser
                },
                {
                  label: !newUser ? texts.CLOSE : texts.STORNO,
                  onClick: () => history.push("/users"),
                  style: { marginRight: 8, marginBottom: 8 },
                  show: true
                }
              ],
              ({ show, ...button }, key) =>
                show && <Button {...{ key, ...button }} />
            )}
          </div>
        </form>
      )
    }}
  />
);

export default compose(
  withRouter,
  entityEnhancer({ getEntity: getUserById }),
  withHandlers({
    onSubmit: ({ history, newUser, texts, entity }) => async formData => {
      if (newUser && formData.password !== formData.password2) {
        throw new SubmissionError({ password2: texts.PASSWORDS_ARE_NOT_SAME });
      }

      const user = { ...entity, ...formData };

      if (!newUser) {
        if (await updateUser(user)) {
          history.push("/users");
        } else {
          throw new SubmissionError({ email: texts.SAVE_FAILED });
        }
      } else {
        if (await createUser(user)) {
          history.push("/users");
        } else {
          throw new SubmissionError({ email: texts.CREATION_FAILED });
        }
      }
    }
  }),
  reduxForm({ form: "UsersForm", enableReinitialize: true })
)(UsersForm);
