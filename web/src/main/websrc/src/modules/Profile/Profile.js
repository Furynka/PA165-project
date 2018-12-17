import React from "react";
import { Divider } from "antd";

import PageWrapper from "../../components/PageWrapper";
import ProfileForm from "./ProfileForm";
import PasswordForm from "./PasswordForm";

export default props => (
  <PageWrapper
    {...{
      breadcrumb: [{ label: props.texts.PROFILE }],
      content: (
        <div>
          <ProfileForm
            {...{
              ...props,
              initialValues: props.loggedUser
                ? {
                    ...props.loggedUser
                  }
                : undefined
            }}
          />
          <Divider />
          <PasswordForm {...props} />
        </div>
      )
    }}
  />
);
