import { combineReducers } from 'redux';

import { reducer as form } from 'redux-form';

import app from './appReducer';

export default combineReducers({
  form,
  app,
});
