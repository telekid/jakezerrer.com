(ns portfolio.views
  (:require [re-frame.core :as re-frame :refer [subscribe dispatch]]
            [bidi.bidi :refer [route-seq]]
            [portfolio.views.utils :as utils]
            [portfolio.views.pages.portfolio :refer [portfolio]]
            [portfolio.views.pages.home :refer [home]]))

(defn main-panel []
  (let [location (subscribe [:location])]
    [:div.container
     (utils/page-case
      @location {:portfolio portfolio
                 :home home
                 :not-found (fn [] [:div "not found"])})]))

