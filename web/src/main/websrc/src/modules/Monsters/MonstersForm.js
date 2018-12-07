import React from "react";
import { withRouter } from "react-router-dom";
import { compose, withHandlers } from "recompose";
import { reduxForm, Field } from "redux-form";
import { map } from "lodash";
import { Row, Col } from "antd";

import PageWrapper from "../../components/PageWrapper";
import { Input } from "../../components/form";
import Button from "../../components/Button";

const MonstersForm = ({ handleSubmit, match, history }) => (
  <PageWrapper
    {...{
      breadcrumb: [
        { label: "Monsters", to: "/monsters" },
        {
          label: match.params.id ? `Monster ${match.params.id}` : "New monster"
        }
      ],
      content: (
        <form {...{ onSubmit: handleSubmit }}>
          <Row {...{ gutter: 16 }}>
            {map(
              [
                { name: "name", label: "Username" },
                { name: "height", label: "Height", type: "number" },
                { name: "weight", label: "Weight", type: "number" },
                { name: "power", label: "Power", type: "number" },
                { name: "agility", label: "Agility", type: "number" },
                { name: "speed", label: "Speed", type: "number" }
              ],
              ({ ...field }, key) => (
                <Col {...{ key, lg: 12 }}>
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
                  label: "Uložit a zavřít",
                  type: "submit",
                  primary: true,
                  style: { marginRight: 8, marginBottom: 8 }
                },
                {
                  label: "Zrušit",
                  onClick: () => history.push("/monsters"),
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
      history.push("/monsters");
    }
  }),
  reduxForm({ form: "MonstersForm", enableReinitialize: true })
)(MonstersForm);
