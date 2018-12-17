import React from "react";
import { Route } from "react-router-dom";

import PageWrapper from "../../components/PageWrapper";
import Table from "../../components/Table";
import UsersForm from "./UsersForm";
import { entityListEnhancer } from "../../utils";
import { getUsers, deleteUser } from "../../actions/userActions";

export default entityListEnhancer({ getItems: getUsers })(
  ({ match, location, history, items, updateItems, ...props }) =>
    match.url === location.pathname ? (
      location.search === "?add" ? (
        <UsersForm {...{ newUser: true, ...props }} />
      ) : (
        <PageWrapper
          {...{
            breadcrumb: [{ label: props.texts.USERS }],
            content: (
              <div>
                <Table
                  {...{
                    onClick: item => history.push(`/users/${item.id}`),
                    onDelete: deleteUser,
                    updateItems,
                    items,
                    columns: [
                      { field: "name", label: props.texts.FIRST_NAME },
                      { field: "surname", label: props.texts.SURNAME },
                      { field: "email", label: props.texts.EMAIL }
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
    )
);
