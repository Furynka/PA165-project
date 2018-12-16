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
  match,
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
          label:
            get(entity, "name") || get(entity, "surname")
              ? `${get(entity, "name")}${
                  get(entity, "surname") ? ` ${get(entity, "surname")}` : ""
                }`
              : texts.NEW_USER
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
                      validate: [
                        validation.required[language],
                        validation.email[language]
                      ]
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
                  style: { marginRight: 8, marginBottom: 8 }
                },
                {
                  label: texts.STORNO,
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
  entityEnhancer({ getEntity: getUserById }),
  withHandlers({
    onSubmit: ({ history, entity, texts }) => async formData => {
      if (get(entity, "id")) {
        if (await updateUser(formData)) {
          history.push("/users");
        } else {
          throw new SubmissionError({ email: texts.SAVE_FAILED });
        }
      } else {
        if (await createUser(formData)) {
          history.push("/users");
        } else {
          throw new SubmissionError({ email: texts.CREATION_FAILED });
        }
      }
    }
  }),
  reduxForm({ form: "UsersForm", enableReinitialize: true })
)(UsersForm);
