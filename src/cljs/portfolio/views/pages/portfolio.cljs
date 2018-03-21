(ns portfolio.views.pages.portfolio
  (:require [portfolio.router :refer [link-for]]
            [goog.string :as gstring]
            [portfolio.cdn :refer [resource-url]]
            [re-frame.core :refer [dispatch subscribe]]
            [goog.string.format]))

(defn portfolio []
  (let [entries (subscribe [:portfolio-entries])]
    [:div
     (map
      (fn [[entry-id entry]]
          [:a
           {:href (link-for :portfolio-entry {:id entry-id})}
           "Portfolio Entry"])
      @entries)]))


