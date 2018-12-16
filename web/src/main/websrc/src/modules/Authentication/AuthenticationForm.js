import React from "react";
import { withRouter } from "react-router-dom";
import { compose, withHandlers } from "recompose";
import { reduxForm, Field } from "redux-form";
import { map } from "lodash";

import Button from "../../components/Button";
import { Input } from "../../components/form";
import { validation } from "../../utils";
import RegisterForm from "./RegisterForm";

const AuthenticationForm = ({ handleSubmit, texts, language }) => (
  <div
    {...{
      style: {
        height: "calc(100vh - 64px)",
        width: "100vw",
        display: "flex",
        justifyContent: "center",
        alignItems: "center"
      }
    }}
  >
    <form
      {...{
        onSubmit: handleSubmit,
        style: {
          width: 400,
          maxWidth: "calc(100% - 2em)",
          padding: "1em",
          display: "flex",
          flexDirection: "column"
        }
      }}
    >
      {map(
        [
          {
            name: "email",
            label: texts.EMAIL,
            validate: [validation.email[language]]
          },
          { name: "password", label: texts.PASSWORD, type: "password" }
        ],
        ({ name, ...field }) => (
          <Field {...{ key: name, component: Input, name, ...field }} />
        )
      )}
      <div
        {...{
          style: {
            width: "100%",
            display: "flex",
            flexWrap: "wrap"
          }
        }}
      >
        <Button
          {...{
            label: texts.SIGN_IN,
            type: "submit",
            primary: true,
            style: { marginBottom: 8, marginRight: 8 }
          }}
        />
        <RegisterForm {...{ texts, language }} />
      </div>
    </form>
  </div>
);

export default compose(
  withRouter,
  withHandlers({
    onSubmit: ({ history }) => ({ username, password }) => {
      console.log("Sign In:", username, password);
      history.push("/home");
    }
  }),
  reduxForm({
    form: "AuthenticationForm",
    enableReinitialize: true
  })
)(AuthenticationForm);
