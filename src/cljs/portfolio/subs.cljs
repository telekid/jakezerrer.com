(ns portfolio.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :portfolio
 (fn [db]
   (:portfolio db)))

(re-frame/reg-sub
 :location
 (fn [db]
   (:handler (:location db))))
