import { compose, lifecycle, withState, withProps } from "recompose";
import { get, isEmpty } from "lodash";

const entityEnhancer = ({ getEntity }) =>
  compose(
    withState("entity", "setEntity", null),
    withState("isNewEntity", "setIsNewEntity", false),
    lifecycle({
      async componentDidMount() {
        const { match, setEntity, setIsNewEntity } = this.props;
        if (get(match, "params.id")) {
          try {
            setEntity(await getEntity(get(match, "params.id")));
          } catch {
            setEntity({});
          }
        } else {
          setIsNewEntity(true);
        }
      }
    }),
    withProps(({ entity }) => ({
      initialValues: !isEmpty(entity) ? entity : {}
    }))
  );

export default entityEnhancer;
