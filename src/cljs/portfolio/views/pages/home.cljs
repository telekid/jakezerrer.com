(ns portfolio.views.pages.home
  (:require [portfolio.router :refer [link-for]]))

(defn home []
  [:div.home__wrap
   [:div.home
    [:h1.home__header "Jake Zerrer"]
    [:nav.home__links
     [:ul
      [:li [:a {:href (link-for :home)} "Home"]]
      [:li [:a {:href (link-for :portfolio)} "Portfolio"]]
      [:li [:a {:href (link-for :notes-entry {:id 123})} "Notes Entry"]]
      [:li [:a {:href "https://www.linkedin.com/in/jakezerrer/"} "LinkedIn"]]
      [:li [:a {:href "https://www.instagram.com/telekid/"} "Instagram"]]
      [:li [:a {:href "https://github.com/telekid"} "Github"]]]]]])
