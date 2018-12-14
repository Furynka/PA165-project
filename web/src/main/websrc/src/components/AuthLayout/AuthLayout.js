import React from "react";
import { compose } from "recompose";
import { Layout } from "antd";
import { connect } from "react-redux";

import LanguagesSelect from "../LanguagesSelect";

const { Header, Content } = Layout;

const AuthLayout = ({ children, texts }) => (
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
      <div />
      <h1 {...{ style: { margin: 0, color: "#fff" } }}>{texts.APP_NAME}</h1>
      <LanguagesSelect />
    </Header>
    <Layout>
      <Content>{children}</Content>
    </Layout>
  </Layout>
);

export default compose(
  connect(({ app: { texts, language } }) => ({ texts, language }))
)(AuthLayout);
