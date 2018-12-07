import * as React from "react";
import {
  compose,
  pure,
  withProps,
  defaultProps,
  mapProps,
  withState
} from "recompose";
import { Table, Modal } from "antd";
import { map, noop, isEmpty } from "lodash";
import { withRouter } from "react-router-dom";

import Button from "../Button";

const { confirm } = Modal;

const TableComponent = ({
  adding,
  deleting,
  selectedRowKeys,
  setSelectedRowKeys,
  history,
  match,
  ...props
}) => (
  <div {...{ style: { display: "flex", flexDirection: "column" } }}>
    <div
      {...{
        style: { display: "flex", alignItems: "center", flexWrap: "wrap" }
      }}
    >
      {adding && (
        <Button
          {...{
            primary: true,
            label: "Add",
            onClick: () => history.push(`${match.path}?add`),
            style: { marginBottom: 8, marginRight: 8 }
          }}
        />
      )}
      {deleting && (
        <Button
          {...{
            label: "Delete",
            onClick: () =>
              confirm({
                title: "Delete items",
                content: "Do you want to delete these items?",
                onOk: () => setSelectedRowKeys([])
              }),
            disabled: isEmpty(selectedRowKeys),
            style: { marginBottom: 8, marginRight: 8 }
          }}
        />
      )}
    </div>
    <Table {...props} />
  </div>
);

export default compose(
  pure,
  withRouter,
  defaultProps({
    items: [],
    columns: [],
    pagination: {
      showSizeChanger: true,
      pageSizeOptions: ["10", "20", "50", "100"]
    },
    scroll: { x: true },
    onClick: noop,
    checkboxes: true,
    adding: true,
    editing: true,
    deleting: true
  }),
  withState("selectedRowKeys", "setSelectedRowKeys", []),
  withProps(
    ({
      columns,
      items,
      onClick,
      checkboxes,
      editing,
      selectedRowKeys,
      setSelectedRowKeys
    }) => ({
      onRow: editing ? item => ({ onClick: () => onClick(item) }) : noop,
      dataSource: map(items, ({ ...item }, key) => ({
        key,
        ...item
      })),
      columns: map(columns, ({ field, label, ...column }) => ({
        dataIndex: field,
        title: label,
        ...column
      })),
      rowSelection: checkboxes
        ? {
            selectedRowKeys,
            onChange: selectedRowKeys => setSelectedRowKeys(selectedRowKeys),
            fixed: true
          }
        : undefined
    })
  ),
  mapProps(({ items, ...rest }) => rest)
)(TableComponent);
