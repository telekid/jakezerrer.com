(ns portfolio.views.pages.portfolio
  (:require [goog.string :as gstring]
            [portfolio.cdn :refer [resource-url]]
            [re-frame.core :refer [dispatch subscribe]]
            [portfolio.cdn :refer [resource-url]]
            [pine.re-frame.components :refer [link]]
            [portfolio.views.components :refer [page-wrap
                                                site-header
                                                site-header-spacer
                                                site-footer
                                                center-page]]
            [goog.string.format]))

(defn header [title]
  [:h1.portfolio-header title])

(defn entry [{:keys [name id description key]}]
  [link {:route-id :portfolio-entry
         :key key
         :params {:portfolio-entry {:id id}}
         :class-name "portfolio__entry"}
   [:img.portfolio__entry-image
    {:src (resource-url (str "portfolio/"
                             (clojure.string/lower-case name)
                             ".png"))}]
   [:div.portfolio__entry-text
    [:h1.portfolio__entry-name name]
    [:p.portfolio__entry-description description]]])

(defn portfolio-entry-list [{:keys [entries]}]
  [:div.portfolio-entry-list
    (map-indexed
     (fn [idx [entry-id {:keys [name description]}]]
       [entry {:name name
               :key idx
               :description description
               :id entry-id}])
     entries)])

(defn portfolio [props & children]
  (into [:div.portfolio props
         [center-page {}
          [site-header-spacer]
          [header "Projects"]
          [portfolio-entry-list {:entries (deref (subscribe [:portfolio-entries]))}]]
         [site-footer]]
        children))


