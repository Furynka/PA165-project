import React from "react";
import { Route, BrowserRouter as Router, Switch } from "react-router-dom";
import { Provider } from "react-redux";
import { map } from "lodash";

import Layout from "./components/Layout";

import Areas from "./modules/Areas";
import Authentication from "./modules/Authentication";
import Home from "./modules/Home";
import Monsters from "./modules/Monsters";
import Profile from "./modules/Profile";
import Users from "./modules/Users";
import Weapons from "./modules/Weapons";

export default ({ store }) => {
  const menuRoutes = [
    { path: "/home", label: "Home", icon: "home", component: Home },
    { path: "/users", label: "Users", icon: "user", component: Users },
    { path: "/monsters", label: "Monsters", icon: "usb", component: Monsters },
    {
      path: "/weapons",
      label: "Weapons",
      icon: "thunderbolt",
      component: Weapons
    },
    { path: "/areas", label: "Areas", icon: "environment", component: Areas }
  ];

  return (
    <Provider {...{ store }}>
      <Router {...{ basename: "/pa165" }}>
        <Switch>
          <Route {...{ exact: true, path: "/", component: Authentication }} />
          <Layout
            {...{
              items: map(menuRoutes, ({ path, label, icon }) => ({
                value: path,
                label,
                icon
              }))
            }}
          >
            {map(menuRoutes, ({ path, component }, key) => (
              <Route {...{ key, path, component }} />
            ))}
            <Route {...{ path: "/profile", component: Profile }} />
          </Layout>
        </Switch>
      </Router>
    </Provider>
  );
};
