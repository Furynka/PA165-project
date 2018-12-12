import React from "react";

import AuthLayout from "../../components/AuthLayout";
import AuthenticationForm from "./AuthenticationForm";

export default props => (
  <AuthLayout>
    <AuthenticationForm {...props} />
  </AuthLayout>
);
