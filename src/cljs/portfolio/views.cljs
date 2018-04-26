(ns portfolio.views
  (:require [re-frame.core :as re-frame :refer [subscribe dispatch]]
            [pine.re-frame.components :refer [view]]
            [portfolio.views.components :refer [page-wrap]]
            [portfolio.views.pages.portfolio :refer [portfolio]]
            [portfolio.views.pages.portfolio-entry :refer [portfolio-entry]]
            [portfolio.views.pages.home :refer [home]]
            [portfolio.views.pages.not-found :refer [not-found]]))

(defn main-panel []
  [:div.container
   [page-wrap {}
    [view :home [home]]
    [view :portfolio :portfolio-entry [portfolio]]
    [view :portfolio-entry [portfolio-entry]]
    [view :not-found [not-found]]
    [view :static [not-found]]]])
