(ns portfolio.router
  (:require [accountant.core :as accountant]
            [portfolio.route-definitions :refer [routes]]
            [pine.router :refer [set-routes! match-route]]
            [re-frame.core :refer [dispatch subscribe]]))

(set-routes! routes)

(defn start []
  (accountant/configure-navigation!
   {:nav-handler #(dispatch [:handle-url-change %])
    :path-exists? #(boolean (match-route %))})
  (accountant/dispatch-current!))

