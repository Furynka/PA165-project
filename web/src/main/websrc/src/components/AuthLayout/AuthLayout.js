import React from "react";
import { compose, pure } from "recompose";
import { Layout } from "antd";

import { text } from "../../constants";

const { Header, Content } = Layout;

const AuthLayout = ({ children }) => (
  <Layout {...{ style: { minHeight: "100vh" } }}>
    <Header
      {...{
        style: {
          padding: "0 16px",
          display: "flex",
          alignItems: "center",
          justifyContent: "center"
        }
      }}
    >
      <h1 {...{ style: { margin: 0, color: "#fff" } }}>{text.APP_NAME}</h1>
    </Header>
    <Layout>
      <Content>{children}</Content>
    </Layout>
  </Layout>
);

export default compose(pure)(AuthLayout);
