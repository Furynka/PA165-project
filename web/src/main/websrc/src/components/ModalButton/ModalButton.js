import * as React from "react";
import {
  compose,
  withState,
  mapProps,
  defaultProps,
  lifecycle
} from "recompose";
import { noop } from "lodash";

import Button from "../Button";
import Modal from "../Modal";

const ModalButton = ({ modalProps, buttonProps, ...props }) => (
  <div {...props}>
    <Modal {...modalProps} />
    <Button {...buttonProps} />
  </div>
);
export default compose(
  defaultProps({
    onOk: () => true,
    closeOnOk: true,
    close: false,
    onClose: noop
  }),
  withState("visible", "setVisible", false),
  lifecycle({
    componentWillReceiveProps(nextProps) {
      const { close } = nextProps;
      const { visible, setVisible, onClose } = this.props;

      if (close && visible) {
        setVisible(false);
        onClose();
      }
    }
  }),
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
      closeOnOk,
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
          const ok = await onOk(...params);
          if (closeOnOk && ok) {
            setVisible(false);
          }
        },
        onCancel: () => setVisible(false)
      },
      ...rest
    })
  )
)(ModalButton);
