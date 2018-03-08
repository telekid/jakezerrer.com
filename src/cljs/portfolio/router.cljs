(ns portfolio.router
  (:require [accountant.core :as accountant]
            [bidi.bidi :refer [path-for match-route]]
            [re-frame.core :refer [dispatch subscribe]]))

(declare routes)

(defn start []
  (accountant/configure-navigation!
   {:nav-handler #(dispatch [:navigate (match-route routes %)])
    :path-exists? #(boolean (match-route routes %))})
  (accountant/dispatch-current!))

(def routes
  ["" {"/portfolio"
       {"" :portfolio
        ["/" :portfolio-entry-id] :portfolio-entry}
       "" :home
       "/" :home
       true :not-found}])

(defn link
  ([route]
   (path-for routes route))
  ([route params]
   (apply path-for routes route (flatten (seq params)))))

(defn link-for
  "Generate a URL path for a given route."
  ;; TODO Remove hard-coded link URLs
  ([route] (str "http://localhost:10555" (link route)))
  ([route params] (str "http://localhost:10555" (link route params))))
