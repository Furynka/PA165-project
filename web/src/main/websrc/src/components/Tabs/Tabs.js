import React from "react";
import { map } from "lodash";
import { Tabs } from "antd";

const { TabPane } = Tabs;

const TabsComponent = ({ id, items }) => (
  <Tabs
    {...{
      animation: true,
      id: id || "tabs"
    }}
  >
    {map(items, ({ title, content, disabled }, key) => (
      <TabPane {...{ key, tab: title, disabled }}>{content}</TabPane>
    ))}
  </Tabs>
);

export default TabsComponent;
