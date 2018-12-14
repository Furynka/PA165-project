import React from "react";
import { compose } from "recompose";
import { connect } from "react-redux";

import PageWrapper from "../../components/PageWrapper";

const Home = ({ texts }) => (
  <PageWrapper
    {...{
      breadcrumb: [{ label: texts.HOME }],
      content: (
        <div>
          <h1>{texts.WELCOME_TEXT}</h1>
        </div>
      )
    }}
  />
);

export default compose(connect(({ app: { texts } }) => ({ texts })))(Home);
