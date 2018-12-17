import * as React from "react";
import { compose, mapProps, defaultProps, renameProp } from "recompose";
import { noop } from "lodash";
import { Modal, Icon } from "antd";

import Button from "../Button";

const ModalComponent = ({ ...props }) => <Modal {...props} />;

export default compose(
  defaultProps({
    bodyStyle: {},
    centered: true,
    closable: true,
    content: undefined,
    maskClosable: true,
    style: {},
    title: "",
    visible: false,
    zIndex: 99999,
    okButtonLabel: "OK",
    cancelButtonLabel: "Cancel"
  }),
  renameProp("content", "children"),
  mapProps(
    ({
      title,
      closable,
      footer,
      onCancel,
      onOk,
      children,
      bodyStyle,
      okButtonLabel,
      cancelButtonLabel,
      ...rest
    }) => ({
      bodyStyle: { padding: 0 },
      title: null,
      closable: false,
      footer: null,
      onCancel,
      children: (
        <div>
          {(title || closable) && (
            <div
              {...{
                style: {
                  borderBottom: "1px solid #e8e8e8",
                  display: "flex",
                  flexWrap: "nowrap",
                  justifyContent: "center",
                  alignItems: "flex-start",
                  padding: "10px 16px"
                }
              }}
            >
              <div
                {...{
                  style: {
                    display: "flex",
                    alignItems: "center",
                    width: closable ? "calc(100% - 38px)" : "100%",
                    maxWidth: closable ? "calc(100% - 38px)" : "100%",
                    wordBreak: "break-word",
                    fontSize: 16,
                    fontWeight: 500,
                    lineHeight: "22px",
                    color: "rgba(0,0,0,.85)"
                  }
                }}
              >
                {title}
              </div>
              {closable && (
                <Button
                  {...{
                    label: (
                      <div
                        {...{
                          style: {
                            width: "100%",
                            height: "100%",
                            display: "flex",
                            justifyContent: "center",
                            alignItems: "center"
                          }
                        }}
                      >
                        <Icon
                          {...{
                            type: "close",
                            style: { fontSize: 16 }
                          }}
                        />
                      </div>
                    ),
                    style: {
                      marginLeft: 8,
                      padding: 0,
                      width: 30,
                      height: 24
                    },
                    onClick: onCancel || noop
                  }}
                />
              )}
            </div>
          )}
          <div
            {...{
              style: {
                padding: 16,
                wordBreak: "break-word",
                ...bodyStyle
              }
            }}
          >
            {children}
          </div>
          {footer !== null && (
            <div {...{ style: { borderTop: "1px solid #e8e8e8" } }}>
              {footer || (
                <div
                  {...{
                    style: {
                      width: "100%",
                      display: "flex",
                      justifyContent: "flex-end",
                      alignItems: "center",
                      flexWrap: "wrap",
                      padding: "10px 16px 0px 0px"
                    }
                  }}
                >
                  <Button
                    {...{
                      label: cancelButtonLabel,
                      onClick: onCancel || noop,
                      style: { margin: "0px 0px 10px 16px" }
                    }}
                  />
                  <Button
                    {...{
                      primary: true,
                      label: okButtonLabel,
                      onClick: onOk || noop,
                      style: { margin: "0px 0px 10px 16px" }
                    }}
                  />
                </div>
              )}
            </div>
          )}
        </div>
      ),
      ...rest
    })
  )
)(ModalComponent);
