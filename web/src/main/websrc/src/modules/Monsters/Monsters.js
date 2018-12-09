import React from "react";
import { map } from "lodash";
import { Route } from "react-router-dom";

import PageWrapper from "../../components/PageWrapper";
import Table from "../../components/Table";
import MonstersForm from "./MonstersForm";

export default ({ match, location, history, ...props }) =>
  match.url === location.pathname ? (
    location.search === "?add" ? (
      <MonstersForm {...props} />
    ) : (
      <PageWrapper
        {...{
          breadcrumb: [{ label: "Monsters" }],
          content: (
            <div>
              <Table
                {...{
                  onClick: item => history.push(`/monsters/${item.id}`),
                  items: map([1, 2, 3, 4, 5, 6, 7, 8, 9, 10], item => ({
                    id: item,
                    name: `Monster ${item}`,
                    height: item * 100,
                    weight: item * 100,
                    power: item * 100,
                    agility: item * 100,
                    speed: item * 100
                  })),
                  columns: [
                    { field: "name", label: "Name" },
                    { field: "height", label: "Height" },
                    { field: "weight", label: "Weight" },
                    { field: "power", label: "Power" },
                    { field: "agility", label: "Agility" },
                    { field: "speed", label: "Speed" }
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
  );
