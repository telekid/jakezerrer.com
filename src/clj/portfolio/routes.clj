(ns portfolio.routes
  (:require [clojure.java.io :as io]
            [bidi.bidi :as bidi :refer [match-route]]
            [bidi.ring :refer [make-handler ->Resources ->ResourcesMaybe]]
            [clojure.tools.logging :as log]
            [portfolio.route-definitions :refer [routes] :rename {routes route-definitions}]
            [ring.util.response :refer [response]])
  ;; TODO I don't think this needs to be here
  (:import [bidi.ring Resources]))

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

(defn handler-lookup [route]
  (case route
    :not-found not-found
    :static (->Resources {:prefix ""})
    serve-spa))

(defn home-routes [endpoint]
  (make-handler
   route-definitions
   handler-lookup))
