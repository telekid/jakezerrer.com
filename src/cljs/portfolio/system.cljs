(ns portfolio.system
  (:require [com.stuartsierra.component :as component]
            [portfolio.components.router :refer [new-router-component]]
            [portfolio.components.ui :refer [new-ui-component]]))

(declare system)

(defn new-system []
  (component/system-map
   :router (new-router-component)

   :app-root (component/using
              (new-ui-component)
              [:router])))

(defn init []
  (set! system (new-system)))

(defn start []
  (set! system (component/start system)))

(defn stop []
  (set! system (component/stop system)))

(defn ^:export go []
  (init)
  (start))

(defn reset []
  (stop)
  (go))
