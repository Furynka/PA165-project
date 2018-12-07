import React from "react";
import { Divider } from "antd";

import PageWrapper from "../../components/PageWrapper";
import ProfileForm from "./ProfileForm";
import PasswordForm from "./PasswordForm";

export default () => (
  <PageWrapper
    {...{
      breadcrumb: [{ label: "Profile" }],
      content: (
        <div>
          <ProfileForm
            {...{
              initialValues: {
                name: "Username",
                firstname: "Jan",
                surname: "Novák",
                email: "jan.novak@gmail.com"
              }
            }}
          />
          <Divider />
          <PasswordForm />
        </div>
      )
    }}
  />
);
