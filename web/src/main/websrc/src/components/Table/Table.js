import * as React from "react";
import { connect } from "react-redux";
import {
  compose,
  withProps,
  defaultProps,
  mapProps,
  withState
} from "recompose";
import { Table, Modal } from "antd";
import { map, noop, isEmpty, filter, findIndex, forEach } from "lodash";
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
  texts,
  onDelete,
  items,
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
            label: texts.ADD,
            onClick: () => history.push(`${match.path}?add`),
            style: { marginBottom: 8, marginRight: 8 }
          }}
        />
      )}
      {deleting && (
        <Button
          {...{
            label: texts.DELETE,
            onClick: () =>
              confirm({
                title: texts.DELETE_ITEMS,
                content: texts.DELETE_ITEMS_TEXT,
                okText: texts.OK,
                cancelText: texts.CANCEL,
                onOk: () => {
                  forEach(
                    filter(
                      items,
                      (_, i) =>
                        findIndex(selectedRowKeys, key => key === i) !== -1
                    ),
                    ({ id }) => onDelete(id)
                  );
                  setSelectedRowKeys([]);
                }
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
  withRouter,
  connect(({ app: { texts } }) => ({ texts })),
  defaultProps({
    items: [],
    columns: [],
    pagination: {
      showSizeChanger: true,
      pageSizeOptions: ["10", "20", "50", "100"]
    },
    scroll: { x: true },
    onClick: noop,
    onDelete: noop,
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
      setSelectedRowKeys,
      texts
    }) => ({
      locale: { emptyText: texts.NO_DATA },
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
  mapProps(({ editing, checkboxes, ...rest }) => rest)
)(TableComponent);
