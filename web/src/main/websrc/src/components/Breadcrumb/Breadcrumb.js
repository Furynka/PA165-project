import * as React from "react";
import { compose, pure, withProps, defaultProps, mapProps } from "recompose";
import { Breadcrumb } from "antd";
import { withRouter } from "react-router-dom";
import { map } from "lodash";

const { Item } = Breadcrumb;

const BreadcrumbComponent = ({ ...props }) => <Breadcrumb {...props} />;

export default compose(
  pure,
  withRouter,
  defaultProps({ items: [] }),
  withProps(({ items, history }) => ({
    children: map(items, (item, key) => (
      <Item {...{ key }}>
        <span
          {...{
            onClick: () => history.push(item.to)
          }}
        >
          {item.label}
        </span>
      </Item>
    ))
  })),
  mapProps(({ items, ...rest }) => rest)
)(BreadcrumbComponent);
