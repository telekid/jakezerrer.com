(ns portfolio.config
  (:require [environ.core :refer [env]]
            [portfolio.middleware :refer [not-found-body]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.gzip :refer [wrap-gzip]]
            [ring.middleware.logger :refer [wrap-with-logger]]))

(defn config []
  {:http-port  (Integer. (or (env :port) 10555))
   :middleware [[wrap-defaults (-> site-defaults
                                   (assoc-in [:responses :not-modified-responses] false))]
                wrap-with-logger
                wrap-gzip
                not-found-body]})
