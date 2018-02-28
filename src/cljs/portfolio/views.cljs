(ns portfolio.views
  (:require [re-frame.core :as re-frame :refer [subscribe dispatch]]
            [bidi.bidi :refer [route-seq]]
            [portfolio.views.utils :as utils]
            [portfolio.views.pages.portfolio :refer [portfolio]]
            [portfolio.views.pages.home :refer [home]]))

(defn main-panel []
  (let [page (subscribe [:page])]
    [:div.container
     (utils/page-case
      @page {:portfolio portfolio :home home})]))

