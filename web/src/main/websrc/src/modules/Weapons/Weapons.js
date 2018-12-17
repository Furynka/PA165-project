import React from "react";
import { Route } from "react-router-dom";

import PageWrapper from "../../components/PageWrapper";
import Table from "../../components/Table";
import WeaponsForm from "./WeaponsForm";
import { entityListEnhancer } from "../../utils";
import { getWeapons, deleteWeapon } from "../../actions/weaponActions";

export default entityListEnhancer({ getItems: getWeapons })(
  ({ match, location, history, items, updateItems, ...props }) =>
    match.url === location.pathname ? (
      location.search === "?add" ? (
        <WeaponsForm {...props} />
      ) : (
        <PageWrapper
          {...{
            breadcrumb: [{ label: props.texts.WEAPONS }],
            content: (
              <div>
                <Table
                  {...{
                    onClick: item => history.push(`/weapons/${item.id}`),
                    onDelete: deleteWeapon,
                    updateItems,
                    items,
                    columns: [
                      { field: "name", label: props.texts.NAME },
                      { field: "description", label: props.texts.DESCRIPTION }
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
          render: () => <WeaponsForm {...props} />
        }}
      />
    )
);
