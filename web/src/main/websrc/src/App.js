import React from "react";
import { Route, BrowserRouter as Router, Switch } from "react-router-dom";
import { Provider } from "react-redux";
import { map, filter, get } from "lodash";
import { connect } from "react-redux";
import { compose, withState, lifecycle } from "recompose";

import Layout from "./components/Layout";

import Areas from "./modules/Areas";
import Authentication from "./modules/Authentication";
import Home from "./modules/Home";
import Monsters from "./modules/Monsters";
import Profile from "./modules/Profile";
import Users from "./modules/Users";
import Weapons from "./modules/Weapons";
import { storage } from "./utils";

const App = ({ store, texts, language, loggedUser, setLoggedUser }) => {
  const moduleProps = { texts, language, loggedUser, setLoggedUser };

  const menuRoutes = filter(
    [
      {
        path: "/home",
        label: texts.HOME,
        icon: "home",
        component: Home,
        show: true
      },
      {
        path: "/users",
        label: texts.USERS,
        icon: "user",
        component: Users,
        show: get(loggedUser, "administrator")
      },
      {
        path: "/monsters",
        label: texts.MONSTERS,
        icon: "usb",
        component: Monsters,
        show: true
      },
      {
        path: "/weapons",
        label: texts.WEAPONS,
        icon: "thunderbolt",
        component: Weapons,
        show: true
      },
      {
        path: "/areas",
        label: texts.AREAS,
        icon: "environment",
        component: Areas,
        show: true
      }
    ],
    "show"
  );

  return (
    <Provider {...{ store }}>
      <Router {...{ basename: "/pa165" }}>
        <Switch>
          <Route
            {...{
              exact: true,
              path: "/",
              render: props => (
                <Authentication {...{ ...moduleProps, ...props }} />
              )
            }}
          />
          <Layout
            {...{
              items: map(menuRoutes, ({ path, label, icon }) => ({
                value: path,
                label,
                icon
              }))
            }}
          >
            {map(menuRoutes, ({ path, component: Component }, key) => (
              <Route
                {...{
                  key,
                  path,
                  render: props => (
                    <Component {...{ ...moduleProps, ...props }} />
                  )
                }}
              />
            ))}
            <Route
              {...{
                path: "/profile",
                render: props => <Profile {...{ ...moduleProps, ...props }} />
              }}
            />
          </Layout>
        </Switch>
      </Router>
    </Provider>
  );
};

export default compose(
  connect(({ app: { texts, language } }) => ({ texts, language })),
  withState("loggedUser", "setLoggedUser", null),
  lifecycle({
    componentWillMount() {
      const { setLoggedUser } = this.props;

      try {
        const user = JSON.parse(storage.get("user"));
        setLoggedUser(user);
      } catch {
        setLoggedUser(null);
      }
    }
  })
)(App);
