import React from "react";
import { withRouter } from "react-router-dom";
import {
  compose,
  withHandlers,
  withState,
  lifecycle,
  withProps
} from "recompose";
import { reduxForm, Field, SubmissionError } from "redux-form";
import { map, get, isEmpty, filter, find } from "lodash";
import { Row, Col, Spin } from "antd";

import PageWrapper from "../../components/PageWrapper";
import { Input, Select as FormSelect } from "../../components/form";
import Button from "../../components/Button";
import Table from "../../components/Table";
import Tabs from "../../components/Tabs";
import ModalButton from "../../components/ModalButton";
import Select from "../../components/Select";
import {
  getMonsterById,
  createMonster,
  updateMonster,
  monstersFromSameArea
} from "../../actions/monsterActions";
import { getWeapons } from "../../actions/weaponActions";
import { getAreas } from "../../actions/areaActions";
import { validation, entityEnhancer } from "../../utils";

const MonstersForm = ({
  handleSubmit,
  history,
  texts,
  language,
  entity,
  availableWeapons,
  selectedWeapon,
  setSelectedWeapon,
  setAvailableWeapons,
  updateAvailableWeapons,
  areas,
  newEffectiveWeapons,
  setNewEffectiveWeapons,
  monstersFromSameAreaList,
  setMonstersFromSameAreaList
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
          <Tabs
            {...{
              items: [
                {
                  title: texts.MONSTER,
                  content: (
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
                      <Col {...{ lg: 12 }}>
                        <Field
                          {...{
                            component: FormSelect,
                            name: "area",
                            label: texts.AREA,
                            items: areas,
                            labelFunction: item => get(item, "name"),
                            valueFunction: item => get(item, "id")
                          }}
                        />
                      </Col>
                    </Row>
                  )
                },
                {
                  title: texts.EFFECTIVE_WEAPONS,
                  content: (
                    <div {...{ style: { paddingBottom: 16 } }}>
                      <div
                        {...{
                          style: {
                            display: "flex",
                            justifyContent: "center",
                            alignItems: "center",
                            marginBottom: 16
                          }
                        }}
                      >
                        {availableWeapons === null ? (
                          <Spin />
                        ) : (
                          <div
                            {...{
                              style: {
                                display: "flex",
                                justifyContent: "center",
                                alignItems: "center",
                                width: "100%"
                              }
                            }}
                          >
                            {!isEmpty(availableWeapons) ? (
                              <Select
                                {...{
                                  label: texts.WEAPON,
                                  items: availableWeapons,
                                  valueFunction: item => get(item, "id"),
                                  labelFunction: item => get(item, "name"),
                                  style: { maxWidth: 300, marginRight: 8 },
                                  onChange: item => setSelectedWeapon(item)
                                }}
                              />
                            ) : (
                              <span {...{ style: { marginRight: 8 } }}>
                                {texts.NO_WEAPONS_AVAILABLE}
                              </span>
                            )}
                            <Button
                              {...{
                                label: texts.ADD,
                                primary: true,
                                onClick: () => {
                                  setNewEffectiveWeapons(
                                    newEffectiveWeapons
                                      ? [...newEffectiveWeapons, selectedWeapon]
                                      : [selectedWeapon]
                                  );
                                  setAvailableWeapons(null);
                                  updateAvailableWeapons(
                                    newEffectiveWeapons
                                      ? [...newEffectiveWeapons, selectedWeapon]
                                      : [selectedWeapon]
                                  );
                                },
                                disabled:
                                  !selectedWeapon || isEmpty(availableWeapons)
                              }}
                            />
                          </div>
                        )}
                      </div>
                      <Table
                        {...{
                          onClick: item => history.push(`/weapons/${item.id}`),
                          items: newEffectiveWeapons,
                          onDeleteFull: items => {
                            setNewEffectiveWeapons(
                              filter(
                                newEffectiveWeapons,
                                ({ id }) => !find(items, item => item.id === id)
                              )
                            );
                            setAvailableWeapons(null);
                            updateAvailableWeapons(
                              filter(
                                newEffectiveWeapons,
                                ({ id }) => !find(items, item => item.id === id)
                              )
                            );
                          },
                          columns: [
                            { field: "name", label: texts.NAME },
                            { field: "description", label: texts.DESCRIPTION }
                          ],
                          adding: false
                        }}
                      />
                    </div>
                  )
                }
              ]
            }}
          />
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
            {get(entity, "area") && (
              <ModalButton
                {...{
                  label: texts.MONSTERS_FROM_SAME_AREA,
                  title: texts.MONSTERS_FROM_SAME_AREA,
                  onClick: async () => {
                    const newMonstersFromSameAreaList = await monstersFromSameArea(
                      entity
                    );
                    setMonstersFromSameAreaList(newMonstersFromSameAreaList);
                  },
                  style: {
                    marginBottom: 16
                  },
                  modalProps: { width: 1200 },
                  content: (
                    <Table
                      {...{
                        onClick: item => history.push(`/monsters/${item.id}`),
                        items: monstersFromSameAreaList,
                        columns: [
                          { field: "name", label: texts.NAME },
                          { field: "height", label: texts.HEIGHT },
                          { field: "weight", label: texts.WEIGHT },
                          { field: "power", label: texts.POWER },
                          { field: "agility", label: texts.AGILITY },
                          { field: "speed", label: texts.SPEED }
                        ],
                        adding: false,
                        deleting: false,
                        checkboxes: false
                      }}
                    />
                  )
                }}
              />
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
  withProps(({ initialValues }) => ({
    initialValues: { ...initialValues, area: get(initialValues, "area.id") }
  })),
  withState("availableWeapons", "setAvailableWeapons", null),
  withState("areas", "setAreas", null),
  withState("selectedWeapon", "setSelectedWeapon", null),
  withState("initialized", "setInitialized", false),
  withState("newEffectiveWeapons", "setNewEffectiveWeapons", []),
  withState("monstersFromSameAreaList", "setMonstersFromSameAreaList", []),
  withHandlers({
    onSubmit: ({
      history,
      entity,
      texts,
      newEffectiveWeapons
    }) => async formData => {
      const monster = {
        ...entity,
        ...formData,
        areaId: formData.area,
        effectiveWeaponsIds: map(newEffectiveWeapons, ({ id }) => id),
        area: undefined,
        effectiveWeapons: undefined
      };

      if (get(entity, "id")) {
        if (await updateMonster(monster)) {
          history.push("/monsters");
        } else {
          throw new SubmissionError({ area: texts.SAVE_FAILED });
        }
      } else {
        if (await createMonster(monster)) {
          history.push("/monsters");
        } else {
          throw new SubmissionError({ area: texts.CREATION_FAILED });
        }
      }
    },
    updateAvailableWeapons: ({
      setAvailableWeapons,
      selectedWeapon,
      setSelectedWeapon
    }) => async newEffectiveWeapons => {
      try {
        const weapons = await getWeapons();

        setAvailableWeapons(
          filter(
            weapons,
            weapon =>
              !find(newEffectiveWeapons, ({ id }) => weapon.id === id) &&
              weapon.id !== get(selectedWeapon, "id")
          )
        );
      } catch {
        setAvailableWeapons([]);
      }

      setSelectedWeapon(null);
    }
  }),
  lifecycle({
    async componentWillMount() {
      const { setAreas } = this.props;
      try {
        const areas = await getAreas();
        setAreas(areas);
      } catch {
        setAreas([]);
      }
    },
    async componentWillReceiveProps(nextProps) {
      const { isNewEntity, entity } = nextProps;
      const {
        updateAvailableWeapons,
        initialized,
        setInitialized,
        setNewEffectiveWeapons
      } = this.props;

      if (!initialized && (isNewEntity || !isEmpty(entity))) {
        setInitialized(true);
        setNewEffectiveWeapons(get(entity, "effectiveWeapons", []));
        updateAvailableWeapons(get(entity, "effectiveWeapons", []));
      }
    }
  }),
  reduxForm({ form: "MonstersForm", enableReinitialize: true })
)(MonstersForm);
