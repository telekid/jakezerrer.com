(ns portfolio.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :portfolio-entries
 (fn [db]
   (:portfolio db)))

(re-frame/reg-sub
 :current-portfolio-entry
 :<- [:pine/active-routes]
 :<- [:pine/route-params]
 :<- [:portfolio-entries]
 (fn [[active-routes route-params portfolio-entries] [_]]
   ((keyword (:id (:portfolio-entry route-params))) portfolio-entries)))

(re-frame/reg-sub
 :header-visible
 :<- [:pine/active-routes]
 (fn [active-routes _]
  (not (contains? active-routes :home))))
