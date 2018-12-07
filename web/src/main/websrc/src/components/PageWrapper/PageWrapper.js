import * as React from "react";
import { compose, pure, withProps, mapProps } from "recompose";

import Card from "../Card";
import Breadcrumb from "../Breadcrumb";

const PageWrapper = ({ ...props }) => <Card {...props} />;

export default compose(
  pure,
  withProps(({ breadcrumb }) => ({
    title: <Breadcrumb {...{ items: breadcrumb }} />
  })),
  mapProps(({ breadcrumb, ...rest }) => rest)
)(PageWrapper);
