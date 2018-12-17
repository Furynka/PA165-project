import React from "react";
import { withRouter } from "react-router-dom";
import { compose, withHandlers } from "recompose";
import { reduxForm, Field, SubmissionError } from "redux-form";
import { map, get } from "lodash";
import { Row, Col } from "antd";

import PageWrapper from "../../components/PageWrapper";
import { Input } from "../../components/form";
import Button from "../../components/Button";
import { getAreaById, createArea, updateArea } from "../../actions/areaActions";
import { validation, entityEnhancer } from "../../utils";

const AreasForm = ({
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
        { label: texts.AREAS, to: "/areas" },
        { label: get(entity, "name", texts.NEW_AREA) }
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
                      label: texts.NAME,
                      validate: [validation.required[language]]
                    },
                    {
                      name: "description",
                      label: texts.DESCRIPTION,
                      type: "textarea"
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
                  onClick: () => history.push("/areas"),
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
  entityEnhancer({ getEntity: getAreaById }),
  withHandlers({
    onSubmit: ({ history, entity, texts }) => async formData => {
      const area = { ...entity, ...formData };

      if (get(entity, "id")) {
        if (await updateArea(area)) {
          history.push("/areas");
        } else {
          throw new SubmissionError({ description: texts.SAVE_FAILED });
        }
      } else {
        if (await createArea(area)) {
          history.push("/areas");
        } else {
          throw new SubmissionError({ description: texts.CREATION_FAILED });
        }
      }
    }
  }),
  reduxForm({ form: "AreasForm", enableReinitialize: true })
)(AreasForm);
