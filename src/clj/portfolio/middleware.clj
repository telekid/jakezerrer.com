(ns portfolio.middleware
  (:require [portfolio.routes :refer [get-spa]]))

(defn not-found-body [handler]
  "Intercept all 404s, and set the :body to the SPA index file."
  (fn [request]
    (let [response (handler request)]
      (if (= (:status response) 404)
        (-> response
            (assoc :body (get-spa))
            (assoc-in [:headers "Content-Type"] "text/html; charset=utf-8"))
        response))))
