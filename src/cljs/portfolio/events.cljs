(ns portfolio.events
  (:require [re-frame.core :as re-frame]
            [portfolio.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-fx
 :navigate
 (fn [cofx [_ location]]
   {:db (assoc (:db cofx) :location location)
    :scroll-to-top nil}))
