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
      [:li [:a {:href (link-for :portfolio-entry {:portfolio-entry-id 123})} "Portfolio entry"]]
      [:li [:a {:href "https://www.linkedin.com/in/jakezerrer/"} "linkedin"]]
      [:li [:a {:href "https://www.instagram.com/telekid/"} "instagram"]]
      [:li [:a {:href "https://github.com/telekid"} "github"]]]]]])
