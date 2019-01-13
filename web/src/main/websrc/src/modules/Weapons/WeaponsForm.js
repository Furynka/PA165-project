import React from "react";
import { withRouter } from "react-router-dom";
import { compose, withHandlers, withState, lifecycle } from "recompose";
import { reduxForm, Field, SubmissionError } from "redux-form";
import { map, get, filter, find, isEmpty } from "lodash";
import { Row, Col, Tag } from "antd";

import PageWrapper from "../../components/PageWrapper";
import { Input } from "../../components/form";
import Button from "../../components/Button";
import Card from "../../components/Card";
import Select from "../../components/Select";
import Spinner from "../../components/Spinner";
import {
  getWeaponById,
  createWeapon,
  updateWeapon
} from "../../actions/weaponActions";
import { validation, entityEnhancer } from "../../utils";
import { statusTypes } from "../../enums";

const WeaponsForm = ({
  handleSubmit,
  history,
  texts,
  language,
  entity,
  selectedStatus,
  setSelectedStatus,
  newStatus,
  setNewStatus,
  isNewEntity
}) =>
  !isNewEntity && entity === null ? (
    <Spinner />
  ) : (
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
                            <div
                              {...{
                                key: `weapons-status-${get(
                                  newStatus,
                                  "length"
                                )}`,
                                style: { marginBottom: 8 }
                              }}
                            >
                              {map(newStatus, (status, key) => (
                                <Tag
                                  {...{
                                    key,
                                    closable: true,
                                    afterClose: () =>
                                      setNewStatus(
                                        filter(newStatus, st => st !== status)
                                      )
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
                                      s => !find(newStatus, st => st === s)
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
                                    setNewStatus(
                                      newStatus
                                        ? [...newStatus, selectedStatus]
                                        : [selectedStatus]
                                    );
                                    setSelectedStatus(null);
                                  },
                                  disabled:
                                    !selectedStatus ||
                                    isEmpty(
                                      filter(
                                        statusTypes,
                                        s => !find(newStatus, st => st === s)
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
  withState("newStatus", "setNewStatus", null),
  withState("loaded", "setLoaded", false),
  withHandlers({
    onSubmit: ({ history, entity, texts, newStatus }) => async formData => {
      const weapon = { ...entity, ...formData, status: newStatus };

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
  lifecycle({
    componentWillReceiveProps(nextProps) {
      const { entity } = nextProps;
      const { setLoaded, loaded, setNewStatus } = this.props;

      if (!loaded && entity) {
        setLoaded(true);
        setNewStatus(get(entity, "status"));
      }
    }
  }),
  reduxForm({ form: "WeaponsForm", enableReinitialize: true })
)(WeaponsForm);
