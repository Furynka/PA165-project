import * as React from "react";
import { compose, pure, defaultProps, withProps, mapProps } from "recompose";
import { map, get, noop, find, toString, isNaN } from "lodash";
import { Menu } from "antd";

const { Item } = Menu;

const MenuComponent = ({ ...props }) => (
  <Menu
    {...{
      ...props
    }}
  />
);

export default compose(
  pure,
  defaultProps({
    mode: "inline",
    items: [],
    labelFunction: item => get(item, "label"),
    style: {},
    onClick: noop
  }),
  withProps(
    ({ items, labelFunction, defaultSelectedKey, onClick, selectedKey }) => ({
      onClick: e => {
        const item = find(items, (_, key) => key === Number(e.key));
        if (item) {
          onClick(item);
        }
      },
      defaultSelectedKeys:
        !isNaN(defaultSelectedKey) && defaultSelectedKey >= 0
          ? [toString(defaultSelectedKey)]
          : undefined,
      selectedKeys:
        !isNaN(selectedKey) && selectedKey >= 0
          ? [toString(selectedKey)]
          : undefined,
      children: map(items, ({ items, ...item }, key) => (
        <Item {...{ key, ...item }}>{labelFunction(item)}</Item>
      ))
    })
  ),
  mapProps(
    ({
      items,
      labelFunction,
      groupLabelFunction,
      defaultSelectedKey,
      selectedKey,
      ...rest
    }) => rest
  )
)(MenuComponent);
