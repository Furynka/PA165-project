import * as React from "react";
import {
  compose,
  defaultProps,
  renameProp,
  withProps,
  mapProps
} from "recompose";
import { map, get, noop, find, isEmpty } from "lodash";
import { Select } from "antd";

const { Option } = Select;

const SelectComponent = ({ ...props }) => <Select {...props} />;

const defaultStyle = { width: "100%", maxWidth: "100%" };

export default compose(
  defaultProps({
    items: [],
    valueFunction: item => get(item, "value"),
    labelFunction: item => get(item, "label"),
    onChange: noop
  }),
  renameProp("label", "placeholder"),
  withProps(
    ({ items, style, valueFunction, labelFunction, onChange, isField }) => ({
      onSelect: value => {
        if (isField) {
          onChange(value);
        } else {
          const item = find(items, item => item.id === value);
          if (!isEmpty(item)) {
            onChange(item);
          }
        }
      },
      children: map(items, ({ items, ...item }, key) => (
        <Option {...{ key, ...item, value: valueFunction(item) }}>
          {labelFunction({ items, ...item })}
        </Option>
      )),
      style: style ? { ...defaultStyle, ...style } : defaultStyle
    })
  ),
  mapProps(({ onChange, ...rest }) => rest)
)(SelectComponent);
