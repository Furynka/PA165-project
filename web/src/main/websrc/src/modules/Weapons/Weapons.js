import React from "react";
import { map } from "lodash";
import { Route } from "react-router-dom";

import PageWrapper from "../../components/PageWrapper";
import Table from "../../components/Table";
import WeaponsForm from "./WeaponsForm";

export default ({ match, location, history, ...props }) =>
  match.url === location.pathname ? (
    location.search === "?add" ? (
      <WeaponsForm {...props} />
    ) : (
      <PageWrapper
        {...{
          breadcrumb: [{ label: "Weapons" }],
          content: (
            <div>
              <Table
                {...{
                  onClick: item => history.push(`/weapons/${item.id}`),
                  items: map([1, 2, 3, 4, 5, 6, 7, 8, 9, 10], item => ({
                    id: item,
                    name: `Weapon ${item}`,
                    description: `Weapon ${item} description.`
                  })),
                  columns: [
                    { field: "name", label: "Name" },
                    { field: "description", label: "Description" }
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
  );
