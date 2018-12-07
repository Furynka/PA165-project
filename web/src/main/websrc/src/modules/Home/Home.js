import React from "react";
import PageWrapper from "../../components/PageWrapper";

import { text } from "../../constants";

export default () => (
  <PageWrapper
    {...{
      breadcrumb: [{ label: "Home" }],
      content: (
        <div>
          <h1>Welcome to {text.APP_NAME}!</h1>
        </div>
      )
    }}
  />
);
