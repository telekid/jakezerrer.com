(ns portfolio.views
  (:require [re-frame.core :as re-frame]
            [goog.string :as gstring]
            [goog.string.format]))

(declare portfolio)

(def data {:projects [
                      {:project "Refuge"
                       :slug "refuge"
                       :description "App for finding, sharing and supporting new music."
                       :examples [{:filename "refuge-landing.png"
                                   :caption "the refu.ge home page"
                                   :width 600}
                                  {:filename "refuge-live.png"
                                   :caption "See what musical tastemakers are listening to"
                                   :width 300}
                                  {:filename "refuge-playback.png"
                                   :caption "Simple playback interface"
                                   :width 300}
                                  {:filename "refuge-color-study.png"
                                   :caption "Color study for Refuge"
                                   :width 450}]}
                      {:project "untapt"
                       :slug "untapt"
                       :description "untapt is a machine learning-driven hiring platform for software engineers."
                       :examples [{:filename "manager-landing.png"
                                   :caption "Mockup of our marketing website for hiring managers"
                                   :width 600}
                                  {:filename "manager-pipeline.png"
                                   :caption "Interface for quickly navigating through job applications"
                                   :width 600}
                                  {:filename "resume-versions.png"
                                   :caption "Resume editor, showing users' ability to manage multiple resumes"
                                   :width 600}]}
                      {:project "Vodka"
                       :slug "vodka"
                       :description "Custom iPad application for Broadway show \"Natasha, Pierre and the Great Comet of 1812\"."
                       :examples [{:filename "vodka-screenshot.jpg"
                                   :caption "Control surface for realtime two-dimensional audio placement"
                                   :width 450}]}]})

(defn main-panel []
  (let [portfolio-data (re-frame/subscribe [:portfolio])]
    (portfolio @portfolio-data)))

(defn home [])

(defn portfolio [content]
  [:div.portfolio (map (fn [project]
                         [:div.project
                          [:div.project__overview-wrap
                           [:nav.project__nav
                            [:h1 "Portfolio"]
                            [:a {:href "/"} "return home"]]
                           [:div.project__overview
                            [:h1.project__header (:project project)]
                            [:p.project__description (:description project)]]]
                          [:ul.project__images
                           (map (fn [image]
                                 [:li.project__image
                                  [:figure
                                   [:img {:src (gstring/format "/img/portfolio/%s" (:filename image))
                                          :width (:width image)
                                          :alt (:caption image)}]
                                   [:figcaption.project__image-description (:caption image)]]])
                                (:examples project))]])
                       content)])



