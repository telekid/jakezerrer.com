(ns portfolio.views
  (:require [re-frame.core :as re-frame :refer [subscribe dispatch]]
            [bidi.bidi :refer [route-seq]]
            [portfolio.views.utils :as utils]
            [portfolio.views.pages.portfolio :refer [portfolio]]
            [portfolio.views.pages.portfolio-entry :refer [portfolio-entry]]
            [portfolio.views.pages.home :refer [home]]))

(defn main-panel []
  ;; TODO Refactor let binding into destructure
  (let [location (subscribe [:location])
        route-params (subscribe [:route-params])]
    [:div.container
     (utils/page-case
      @location
      {:portfolio portfolio
       :portfolio-entry portfolio-entry
       :notes (fn [] [:div "Notes"])
       :notes-entry (fn [params]
                      [:div "Notes Entry" (:id params)])
       :not-found (fn [] [:div "not found"])
       ;; TODO Figure out how to handle server-side routes that
       ;; exist, just not on the client
       :static (fn [] [:div "Not found"])}
      @route-params)]))

