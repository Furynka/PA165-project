import React from "react";
import { compose, pure, defaultProps, withState, withProps } from "recompose";
import { map, findIndex } from "lodash";
import { withRouter } from "react-router-dom";
import { Layout, Icon } from "antd";

import Menu from "../Menu";
import Dropdown from "../Dropdown";
import { text } from "../../constants";

const { Header, Content, Sider } = Layout;

const LayoutComponent = ({
  children,
  collapsed,
  setCollapsed,
  menuProps,
  history
}) => (
  <Layout {...{ style: { minHeight: "100vh" } }}>
    <Header
      {...{
        style: {
          padding: "0 16px",
          display: "flex",
          alignItems: "center",
          justifyContent: "space-between"
        }
      }}
    >
      <h1 {...{ style: { margin: 0, color: "#fff" } }}>{text.APP_NAME}</h1>
      <Dropdown
        {...{
          label: <Icon {...{ type: "user", style: { fontSize: 24 } }} />,
          buttonStyle: { height: 48 },
          onClick: item => history.push(item.value),
          items: [
            { value: "/profile", label: "Profile" },
            { value: "/", label: "Sign out" }
          ]
        }}
      />
    </Header>
    <Layout>
      <Sider
        {...{
          width: 150,
          style: { background: "#fff", minHeight: "calc(100vh - 64px)" },
          collapsible: true,
          collapsed,
          onCollapse: collapsed => setCollapsed(collapsed)
        }}
      >
        <Menu {...menuProps} />
      </Sider>
      <Content {...{ style: { padding: 8 } }}>{children}</Content>
    </Layout>
  </Layout>
);

export default compose(
  pure,
  withRouter,
  defaultProps({
    items: []
  }),
  withState("collapsed", "setCollapsed", false),
  withProps(({ items, history, location }) => ({
    menuProps: {
      onClick: item => history.push(item.value),
      selectedKey: findIndex(items, ({ value }) => value === location.pathname),
      items: map(items, ({ icon, label, ...item }) => ({
        label: (
          <span>
            <Icon {...{ type: icon }} />
            <span>{label}</span>
          </span>
        ),
        ...item
      }))
    }
  }))
)(LayoutComponent);
