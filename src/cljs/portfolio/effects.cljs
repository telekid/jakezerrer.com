(ns portfolio.effects
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-fx
 :scroll-to-top
 (fn [] (.scroll js/window 0 0)))
