(ns portfolio.views.components
  (:require [portfolio.router :refer [link-for]]
            [cljsjs.react :as react]
            [re-frame.core :refer [dispatch subscribe]]))

(defn router-link
  "Create an anchor tag that is aware of the current location.

  You may pass any keys that you would normally pass to
  an anchor tag in the keys dict.

  Additionally, you should pass the following params:

  `destination` accepts a keyword indicating the navigation destination.
  The string passed to `active-class` will be appended to the list of class
  names on the anchor tag when the `destination` is active.
  "
  [{:keys [destination active-class params] :as keys :or {:params {}}} & children]
  (let [location (subscribe [:location])]
   [:a (-> keys
           (dissoc :destination :active-class)
           (assoc :href (link-for destination params))
           ((fn [ks]
              (if (= destination @location)
                (update-in ks [:class-name] #(str % " " active-class))
                ks))))
    children]))

(defn site-header []
  (let [header-visible (subscribe [:header-visible])]
    [:div.site-header
     {:class-name (when (not @header-visible) "site-header--hidden")}
     (into [:nav
            (map (fn [[destination label]]
                   [router-link {:class-name "site-header__link"
                                 :active-class "site-header__link--active"
                                 :key destination
                                 :destination destination} label])
                 {:home "home"
                  :portfolio "portfolio"})])]))
                  ;; :notes "notes"})])]))

(defn site-header-spacer []
  [:div.site-header-spacer])

(defn site-footer []
  [:div.site-footer
   (into [:nav.site-footer__nav]
         (map (fn [[href label]]
                [:a.site-footer__elem {:href href} label])
              {"https://www.linkedin.com/in/jakezerrer/" "linkedin"
               "https://www.instagram.com/telekid" "instagram"
               "https://github.com/telekid" "github"
               "https://github.com/telekid/jakezerrer.com" "source"}))
   [:div.site-footer__copyright.site-footer__elem (str "© " (.getFullYear (js/Date.)) " jake zerrer")]])

(defn page-wrap
  [props & children]
  (into [:div.page-wrap props [site-header]] children))

(defn center-page [props & children]
  (into [:div.center-page props] children))

(defn <> [& children]
  (apply array (map react/as-element (keep identity children))))
