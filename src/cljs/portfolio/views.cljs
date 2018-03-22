(ns portfolio.views
  (:require [re-frame.core :as re-frame :refer [subscribe dispatch]]
            [bidi.bidi :refer [route-seq]]
            [portfolio.views.components :refer [page-wrap]]
            [portfolio.views.utils :as utils]
            [portfolio.views.pages.portfolio :refer [portfolio]]
            [portfolio.views.pages.portfolio-entry :refer [portfolio-entry]]
            [portfolio.views.pages.home :refer [home]]
            [portfolio.views.pages.not-found :refer [not-found]]))

(defn main-panel []
  ;; TODO Refactor let binding into destructure
  (let [location (subscribe [:location])
        route-params (subscribe [:route-params])]
    [:div.container
     [page-wrap {}
      (utils/page-case
        @location
        {:portfolio portfolio
         :home home
         :portfolio-entry portfolio-entry
         :notes (fn [] [:div "Notes"])
         :not-found not-found
         ;; TODO Figure out how to handle server-side routes that
         ;; exist, just not on the client
         :static not-found}
        @route-params)]]))

