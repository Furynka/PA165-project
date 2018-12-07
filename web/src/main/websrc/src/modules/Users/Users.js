import React from "react";
import { map } from "lodash";
import { Route } from "react-router-dom";

import PageWrapper from "../../components/PageWrapper";
import Table from "../../components/Table";
import UsersForm from "./UsersForm";

export default ({ match, location, history, ...props }) =>
  match.url === location.pathname ? (
    location.search === "?add" ? (
      <UsersForm {...props} />
    ) : (
      <PageWrapper
        {...{
          breadcrumb: [{ label: "Users" }],
          content: (
            <div>
              <Table
                {...{
                  onClick: item => history.push(`/users/${item.id}`),
                  items: map([1, 2, 3, 4, 5, 6, 7, 8, 9, 10], item => ({
                    id: item,
                    name: `User ${item}`,
                    firstname: `First name`,
                    surname: "Surname",
                    email: "email@email.com"
                  })),
                  columns: [
                    { field: "name", label: "Username" },
                    { field: "firstname", label: "First name" },
                    { field: "surname", label: "Surname" },
                    { field: "email", label: "Email" }
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
        render: () => <UsersForm {...props} />
      }}
    />
  );
