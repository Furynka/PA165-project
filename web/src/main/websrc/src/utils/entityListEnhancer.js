import { compose, lifecycle, withState } from "recompose";

const entityListEnhancer = ({ getItems }) =>
  compose(
    withState("items", "setItems", []),
    lifecycle({
      async componentDidMount() {
        const { setItems } = this.props;

        try {
          setItems(await getItems());
        } catch {
          setItems([]);
        }
      }
    })
  );

export default entityListEnhancer;
