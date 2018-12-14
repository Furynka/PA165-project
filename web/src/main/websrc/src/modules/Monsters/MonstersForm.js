import React from "react";
import { withRouter } from "react-router-dom";
import { compose, withHandlers } from "recompose";
import { reduxForm, Field, SubmissionError } from "redux-form";
import { map, get } from "lodash";
import { Row, Col } from "antd";

import PageWrapper from "../../components/PageWrapper";
import { Input } from "../../components/form";
import Button from "../../components/Button";
import {
  getMonsterById,
  createMonster,
  updateMonster
} from "../../actions/monsterActions";
import { validation, entityEnhancer } from "../../utils";

const MonstersForm = ({
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
        { label: texts.MONSTERS, to: "/monsters" },
        {
          label: get(entity, "name", texts.NEW_MONSTER)
        }
      ],
      content: (
        <form {...{ onSubmit: handleSubmit }}>
          <Row {...{ gutter: 16 }}>
            {map(
              [
                {
                  name: "name",
                  label: texts.NAME,
                  validate: [validation.required[language]]
                },
                {
                  name: "height",
                  label: texts.HEIGHT,
                  type: "number",
                  validate: [
                    validation.required[language],
                    validation.isNumeric[language]
                  ]
                },
                {
                  name: "weight",
                  label: texts.WEIGHT,
                  type: "number",
                  validate: [
                    validation.required[language],
                    validation.isNumeric[language]
                  ]
                },
                {
                  name: "power",
                  label: texts.POWER,
                  type: "number",
                  validate: [
                    validation.required[language],
                    validation.isNumeric[language],
                    validation.isNumericBetween0and100[language]
                  ]
                },
                {
                  name: "agility",
                  label: texts.AGILITY,
                  type: "number",
                  validate: [
                    validation.required[language],
                    validation.isNumeric[language],
                    validation.isNumericBetween0and100[language]
                  ]
                },
                {
                  name: "speed",
                  label: texts.SPEED,
                  type: "number",
                  validate: [
                    validation.required[language],
                    validation.isNumeric[language]
                  ]
                }
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
                  label: texts.SAVE_AND_CLOSE,
                  type: "submit",
                  primary: true,
                  style: { marginRight: 8, marginBottom: 8 }
                },
                {
                  label: texts.STORNO,
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
  entityEnhancer({ getEntity: getMonsterById }),
  withHandlers({
    onSubmit: ({ history, entity, texts }) => async formData => {
      if (get(entity, "id")) {
        if (await updateMonster(formData)) {
          history.push("/monsters");
        } else {
          throw new SubmissionError({ speed: texts.SAVE_FAILED });
        }
      } else {
        if (await createMonster(formData)) {
          history.push("/monsters");
        } else {
          throw new SubmissionError({ speed: texts.CREATION_FAILED });
        }
      }
    }
  }),
  reduxForm({ form: "MonstersForm", enableReinitialize: true })
)(MonstersForm);
