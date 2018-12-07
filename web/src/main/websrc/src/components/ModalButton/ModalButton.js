import * as React from "react";
import { compose, pure, withState, mapProps, defaultProps } from "recompose";

import Button from "../Button";
import Modal from "../Modal";

const ModalButton = ({ modalProps, buttonProps, ...props }) => (
  <div {...props}>
    <Modal {...modalProps} />
    <Button {...buttonProps} />
  </div>
);
export default compose(
  pure,
  defaultProps({ onOk: () => true }),
  withState("visible", "setVisible", false),
  mapProps(
    ({
      label,
      primary,
      buttonProps,
      visible,
      setVisible,
      title,
      onOk,
      content,
      modalProps,
      ...rest
    }) => ({
      buttonProps: {
        ...buttonProps,
        label,
        primary,
        onClick: () => setVisible(true)
      },
      modalProps: {
        ...modalProps,
        title,
        visible,
        content,
        onOk: async (...params) => {
          if (await onOk(...params)) {
            setVisible(false);
          }
        },
        onCancel: () => setVisible(false)
      },
      ...rest
    })
  )
)(ModalButton);
