import React from "react";
import { withRouter } from "react-router-dom";
import { compose, withHandlers, withState } from "recompose";
import { reduxForm, Field, SubmissionError } from "redux-form";
import { map, get, filter, find, isEmpty } from "lodash";
import { Row, Col, Tag } from "antd";

import PageWrapper from "../../components/PageWrapper";
import { Input } from "../../components/form";
import Button from "../../components/Button";
import Card from "../../components/Card";
import Select from "../../components/Select";
import {
  getWeaponById,
  createWeapon,
  updateWeapon
} from "../../actions/weaponActions";
import { validation, entityEnhancer } from "../../utils";
import { statusTypes } from "../../enums";

const WeaponsForm = ({
  handleSubmit,
  match,
  history,
  texts,
  language,
  entity,
  setEntity,
  selectedStatus,
  setSelectedStatus
}) => (
  <PageWrapper
    {...{
      breadcrumb: [
        { label: texts.WEAPONS, to: "/weapons" },
        {
          label: get(entity, "name", texts.NEW_WEAPON)
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
                <Col {...{ style: { marginBottom: 16 } }}>
                  <Card
                    {...{
                      title: texts.STATUS,
                      content: (
                        <div>
                          <div {...{ style: { marginBottom: 8 } }}>
                            {map(get(entity, "status"), (status, key) => (
                              <Tag
                                {...{
                                  key,
                                  closable: true,
                                  onClose: () =>
                                    setEntity({
                                      ...entity,
                                      status: filter(
                                        get(entity, "status"),
                                        st => st !== status
                                      )
                                    })
                                }}
                              >
                                {status}
                              </Tag>
                            ))}
                          </div>
                          <div
                            {...{ style: { display: "flex", marginTop: 8 } }}
                          >
                            <Select
                              {...{
                                label: texts.STATUS,
                                value: selectedStatus,
                                items: map(
                                  filter(
                                    statusTypes,
                                    s =>
                                      !find(
                                        get(entity, "status"),
                                        st => st === s
                                      )
                                  ),
                                  st => ({ value: st, label: st })
                                ),
                                style: { maxWidth: 300, marginRight: 8 },
                                onChange: item =>
                                  setSelectedStatus(get(item, "value"))
                              }}
                            />
                            <Button
                              {...{
                                label: texts.ADD,
                                primary: true,
                                onClick: () => {
                                  setEntity({
                                    ...entity,
                                    status: get(entity, "status")
                                      ? [
                                          ...get(entity, "status"),
                                          selectedStatus
                                        ]
                                      : [selectedStatus]
                                  });
                                  setSelectedStatus(null);
                                },
                                disabled:
                                  !selectedStatus ||
                                  isEmpty(
                                    filter(
                                      statusTypes,
                                      s =>
                                        !find(
                                          get(entity, "status"),
                                          st => st === s
                                        )
                                    )
                                  )
                              }}
                            />
                          </div>
                        </div>
                      )
                    }}
                  />
                </Col>
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
                  onClick: () => history.push("/weapons"),
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
  entityEnhancer({ getEntity: getWeaponById }),
  withState("selectedStatus", "setSelectedStatus", null),
  withHandlers({
    onSubmit: ({ history, entity, texts }) => async formData => {
      const weapon = { ...entity, ...formData };

      if (get(entity, "id")) {
        if (await updateWeapon(weapon)) {
          history.push("/weapons");
        } else {
          throw new SubmissionError({ description: texts.SAVE_FAILED });
        }
      } else {
        if (await createWeapon(weapon)) {
          history.push("/weapons");
        } else {
          throw new SubmissionError({ description: texts.CREATION_FAILED });
        }
      }
    }
  }),
  reduxForm({ form: "WeaponsForm", enableReinitialize: true })
)(WeaponsForm);
