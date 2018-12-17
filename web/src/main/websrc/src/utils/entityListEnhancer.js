import { compose, lifecycle, withState, withHandlers } from "recompose";

const entityListEnhancer = ({ getItems }) =>
  compose(
    withState("items", "setItems", []),
    withHandlers({
      updateItems: ({ setItems }) => async () => {
        try {
          setItems(await getItems());
        } catch {
          setItems([]);
        }
      }
    }),
    lifecycle({
      async componentDidMount() {
        const { updateItems } = this.props;

        updateItems();
      },
      async componentWillReceiveProps(nextProps) {
        const { location, match, updateItems } = this.props;
        const { location: nextLocation } = nextProps;

        if (
          match.path === nextLocation.pathname &&
          (location.pathname !== nextLocation.pathname ||
            location.search !== nextLocation.search)
        ) {
          updateItems();
        }
      }
    })
  );

export default entityListEnhancer;
