import * as c from '../actions/constants';

const initialState = {
  sample: {},
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case c.CONSTANT:
      return { ...state, ...action.payload };
    default:
      return state;
  }
};

export default reducer;
