(ns portfolio.routes
  (:require [clojure.java.io :as io]
            [clojure.tools.logging :as log]
            [pine.router :refer [set-routes! match-route]]
            [pine.ring :refer [make-handler]]
            [portfolio.route-definitions :refer [routes] :rename {routes route-definitions}]
            [ring.util.response :refer [response resource-response]]))

(set-routes! route-definitions)

(defn get-spa []
  "Get the index file for the SPA from the filesystem."
  (-> "public/index.html"
      io/resource
      io/input-stream))

(defn not-found [request]
  {:status 404})

(defn serve-spa [request]
  (-> (get-spa)
      response
      (assoc-in [:headers "Content-Type"] "text/html; charset=utf-8")))

(defn serve-public-asset [request]
  (if-let [res (resource-response (:uri request))]
    res
    {:status 404}))

(def handlers {:not-found not-found
               :public-asset serve-public-asset})

(defn handler-lookup [route]
  (let [handler (route handlers)]
    (if handler
      handler
      serve-spa)))

(defn home-routes [endpoint]
  (make-handler handler-lookup))
