(ns portfolio.views.pages.not-found
  (:require [portfolio.views.components :refer [page-wrap
                                                site-header-spacer
                                                site-footer
                                                center-page]]))

(defn not-found []
  [:div.not-found
   [center-page {}
    [site-header-spacer]
    [:div.not-found__content
     [:h1.not-found__header "404 That's Not a Thing"]
     [:div.not-found__video-wrap
      [:div.not-found__video-stretch
       [:iframe.not-found__video {:width 560
                                  :height 315
                                  :src "https://www.youtube.com/embed/F7lbSZ3j3Ks"
                                  :frame-border 0
                                  :allow "autoplay; encrypted-media"
                                  :allow-full-screen true}]]]]]
   [site-footer]])
