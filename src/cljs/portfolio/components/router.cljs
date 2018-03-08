(ns portfolio.components.router
  (:require [com.stuartsierra.component :as component]
            [portfolio.router :refer [start] :rename {start router}]))

(defrecord RouterComponent []
  component/Lifecycle
  (start [component]
    (print "Starting router")
    (assoc component :router (router)))
  (stop [component]
    (print "Stopping router" component)
    component))

(defn new-router-component []
  (map->RouterComponent {}))
