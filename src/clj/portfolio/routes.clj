(ns portfolio.routes
  (:require [clojure.java.io :as io]
            [bidi.bidi :as bidi :refer [match-route]]
            [bidi.ring :refer [make-handler ->Resources ->ResourcesMaybe]]
            [clojure.tools.logging :as log]
            [portfolio.route-definitions :refer [routes] :rename {routes route-definitions}]
            [ring.util.response :refer [response]])
  (:import [bidi.ring Resources]))

(defn serve-spa [request]
  (-> "public/index.html"
      io/resource
      io/input-stream
      response
      (assoc :headers {"Content-Type" "text/html; charset=utf-8"})))

(defn handler-lookup [route]
  (if (= :static route)
    (->Resources {:prefix ""})
    serve-spa))

(defn home-routes [endpoint]
  (make-handler
   route-definitions
   handler-lookup))
