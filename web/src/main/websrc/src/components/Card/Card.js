import * as React from "react";
import { compose, renameProp, withProps, mapProps } from "recompose";
import { Card } from "antd";

const CardComponent = ({ ...props }) => <Card {...props} />;

export default compose(
  renameProp("content", "children"),
  withProps(({ title, rightTitle }) => ({
    title:
      title || rightTitle ? (
        <div
          style={{
            width: "100%",
            maxWidth: "100%",
            display: "flex",
            flexWrap: "wrap",
            justifyContent:
              title && rightTitle
                ? "space-between"
                : title
                ? "flex-start"
                : "flex-end",
            alignItems: "center"
          }}
        >
          {title && <div>{title}</div>}
          {rightTitle && <div>{rightTitle}</div>}
        </div>
      ) : (
        undefined
      )
  })),
  mapProps(({ extra, rightTitle, ...rest }) => rest)
)(CardComponent);
