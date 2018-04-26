(ns portfolio.views.components
  (:require [re-frame.core :refer [dispatch subscribe]]
            [pine.re-frame.components :refer [view link]]))

(defn site-header []
  (let [header-visible (subscribe [:header-visible])]
    [:div.site-header
     {:class-name (when (not @header-visible) "site-header--hidden")}
     (into [:nav
            (map (fn [[destination label]]
                   [link {:route-id destination
                          :class-name "site-header__link"
                          :active-class "site-header__link--active"
                          :key destination}
                    label])
                 [[:home "home"]
                  [:portfolio "portfolio"]])])]))

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
