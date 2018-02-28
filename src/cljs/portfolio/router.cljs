(ns portfolio.router
  (:require [bidi.router :refer [start-router!]]
            [bidi.bidi :refer [path-for]]
            [re-frame.core :refer [dispatch subscribe]]))

(def routes
  ["" {"/portfolio"
       {"" :portfolio
        ["/" :portfolio-entry-id] :portfolio-entry}
       "" :home
       "/" :home
       true :not-found}])


(defn route-ancestry [routes route]
  ;; (loop [history [] branch routes]
  (let [to-search (take-nth 1 routes)]
    to-search))
      ;; (if (equals (type branch) cljs.core/PersistentArrayMap)
        ;; (recur)))

(route-ancestry routes :home)

(let [router (start-router! routes {:on-navigate #(dispatch [:navigate %])})])

(defn link
  ([route]
   (path-for routes route))
  ([route params]
   (apply path-for routes route (flatten (seq params)))))

(defn link-for
  "Generate a URL path for a given route."
  ([route] (str "#" (link route)))
  ([route params] (str "#" (link route params))))
