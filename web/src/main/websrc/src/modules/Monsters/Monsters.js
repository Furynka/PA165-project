import React from "react";
import { Route } from "react-router-dom";
import { get } from "lodash";

import PageWrapper from "../../components/PageWrapper";
import Table from "../../components/Table";
import Button from "../../components/Button";
import MonstersForm from "./MonstersForm";
import { entityListEnhancer } from "../../utils";
import {
  getMonsters,
  deleteMonster,
  findStrongestMonster
} from "../../actions/monsterActions";

export default entityListEnhancer({ getItems: getMonsters })(
  ({ match, location, history, items, updateItems, ...props }) =>
    match.url === location.pathname ? (
      location.search === "?add" ? (
        <MonstersForm {...props} />
      ) : (
        <PageWrapper
          {...{
            breadcrumb: [{ label: props.texts.MONSTERS }],
            content: (
              <div>
                <Table
                  {...{
                    onClick: item => history.push(`/monsters/${item.id}`),
                    onDelete: deleteMonster,
                    actionBar: items && (
                      <Button
                        {...{
                          label: props.texts.STRONGEST_MONSTER,
                          onClick: async () => {
                            const strongestMonster = await findStrongestMonster();

                            if (strongestMonster) {
                              history.push(
                                `/monsters/${get(strongestMonster, "id")}`
                              );
                            }
                          },
                          style: { marginBottom: 8, marginRight: 8 }
                        }}
                      />
                    ),
                    updateItems,
                    items,
                    columns: [
                      { field: "name", label: props.texts.NAME },
                      { field: "height", label: props.texts.HEIGHT },
                      { field: "weight", label: props.texts.WEIGHT },
                      { field: "power", label: props.texts.POWER },
                      { field: "agility", label: props.texts.AGILITY },
                      { field: "speed", label: props.texts.SPEED }
                    ]
                  }}
                />
              </div>
            )
          }}
        />
      )
    ) : (
      <Route
        {...{
          path: `${match.url}/:id`,
          render: () => <MonstersForm {...props} />
        }}
      />
    )
);
