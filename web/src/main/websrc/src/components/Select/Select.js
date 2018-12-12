import * as React from "react";
import { compose, defaultProps, renameProp, withProps } from "recompose";
import { map, isEmpty, get, noop } from "lodash-es";
import { Select } from "antd";

const { Option, OptGroup } = Select;

const SelectComponent = ({ ...props }) => <Select {...props} />;

const defaultStyle = { width: "100%", maxWidth: "100%" };

export default compose(
  defaultProps({
    items: [],
    valueFunction: item => get(item, "value"),
    labelFunction: item => get(item, "label"),
    groupLabelFunction: item => get(item, "label"),
    onChange: noop
  }),
  renameProp("label", "placeholder"),
  withProps(
    ({ items, style, valueFunction, labelFunction, groupLabelFunction }) => ({
      onSelect: (a, b) => console.log(a, b),
      children: map(items, ({ items, ...item }, key) =>
        isEmpty(items) ? (
          <Option {...{ key, ...item, value: valueFunction(item) }}>
            {labelFunction({ items, ...item })}
          </Option>
        ) : (
          <OptGroup
            {...{
              key,
              ...item,
              label: groupLabelFunction({ items, ...item })
            }}
          >
            {map(items, (item, key) => (
              <Option {...{ key, ...item, value: valueFunction(item) }}>
                {labelFunction(item)}
              </Option>
            ))}
          </OptGroup>
        )
      ),
      style: style ? { ...defaultStyle, ...style } : defaultStyle
    })
  )
)(SelectComponent);
