import React from "react";
import { Route } from "react-router-dom";

import PageWrapper from "../../components/PageWrapper";
import Table from "../../components/Table";
import AreasForm from "./AreasForm";
import { entityListEnhancer } from "../../utils";
import { getAreas, deleteArea } from "../../actions/areaActions";

export default entityListEnhancer({ getItems: getAreas })(
  ({ match, location, history, items, updateItems, ...props }) =>
    match.url === location.pathname ? (
      location.search === "?add" ? (
        <AreasForm {...props} />
      ) : (
        <PageWrapper
          {...{
            breadcrumb: [{ label: props.texts.AREAS }],
            content: (
              <div>
                <Table
                  {...{
                    onClick: item => history.push(`/areas/${item.id}`),
                    onDelete: deleteArea,
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
          render: () => <AreasForm {...props} />
        }}
      />
    )
);
