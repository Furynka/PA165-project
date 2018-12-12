import * as React from "react";
import { compose, mapProps } from "recompose";
import { connect } from "react-redux";
import { get } from "lodash";

import Dropdown from "../Dropdown";
import { setLanguage } from "../../actions/appActions";
import { languagesOptions, languages, languagesLabels } from "../../enums";

const LanguagesSelect = ({ ...props }) => <Dropdown {...props} />;

export default compose(
  connect(
    ({ app: { language } }) => ({
      language
    }),
    { setLanguage }
  ),
  mapProps(({ language, setLanguage, ...rest }) => ({
    buttonStyle: { height: 48, width: 56, fontSize: 18 },
    label: get(languagesLabels, language),
    items: languagesOptions,
    onClick: ({ value }) => setLanguage(get(languages, value, languages.EN)),
    ...rest
  }))
)(LanguagesSelect);
