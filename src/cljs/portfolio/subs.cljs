(ns portfolio.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :location
 (fn [db]
   (:handler (:location db))))

(re-frame/reg-sub
 :route-params
 (fn [db]
   (:route-params (:location db))))

(re-frame/reg-sub
 :portfolio-entries
 (fn [db]
   (:portfolio db)))

(re-frame/reg-sub
 :current-portfolio-entry
 :<- [:location]
 :<- [:route-params]
 :<- [:portfolio-entries]
 (fn [[location route-params portfolio-entries] [_]]
   (when (= location :portfolio-entry)
     ((keyword (:id route-params)) portfolio-entries))))

;; (re-frame/reg-sub
;;  :current-portfolio-name
;;  :<- [:current-portfolio-entry]
;;  (fn [[entry] _]
;;    (print "entry" entry)))
