(ns portfolio.views
  (:require [re-frame.core :as re-frame]
            [goog.string :as gstring]
            [goog.string.format]))

(declare portfolio)

(defn main-panel []
  (portfolio))

(defn home [])

(defn company [& {:keys [name slug description content show-nav] :or {show-nav false}}]
  [:div.project
   [:div.project__overview-wrap
    (when show-nav
      [:nav.project__nav
       [:h1 "Portfolio"]
       [:a {:href "/"} "return home"]])
    [:div.project__overview
      [:h1.project__company-header name]
      [:p.project__company-description description]]]
   ;; There's probably a sexier way to write this, but I'm at a loss
   (apply conj [:div.project__images] content)])

(defn example [wrapped-component caption]
  [:div.project__image
   [:figure
    [:div.project__image-wrap wrapped-component]
    [:figcaption.project__image-description caption]]])

(defn example-image [& {:keys [filename caption width]}]
  (example [:img {:src (gstring/format "/img/portfolio/%s" filename)
                  :width width
                  :alt caption}]
           caption))

(defn example-video [& {:keys [filename caption]}]
  (example [:iframe {:src (gstring/format "https://www.youtube.com/embed/%s?rel=0&amp;controls=0&amp;showinfo=0" filename)
                     :alt caption
                     :width 600
                     :height 337
                     :allowFullScreen true
                     :frameBorder 0}]
           caption))

(defn intro [text] [:h1 text])

(defn section [title] [:h1.project__section title])

(defn description [text] [:p.project__description text])

(defn skills [skills] [:div.project__skills-wrap [:h1 "technologies used"]
                       [:ul.project__skills (map (fn [skill] [:li.project__skill skill]) skills)]])

(defn portfolio []
  [:div (company :name "Refuge"
                 :show-nav true
                 :slug "refuge"
                 :description "App for finding, sharing and supporting new music"
                 :content [
                           (skills ["Figma"])
                           (example-image :filename "refuge-landing.png"
                                          :caption "the refu.ge home page"
                                          :width 600)
                           (example-image :filename "refuge-live.png"
                                          :caption "See what musical tastemakers are listening to"
                                          :width 300)
                           (example-image :filename "refuge-playback.png"
                                          :caption "Simple playback interface"
                                          :width 300)
                           (example-image :filename "refuge-color-study.png"
                                          :caption "Color study for Refuge"
                                          :width 450)])
        (company :name "untapt"
                 :slug "untapt"
                 :description "untapt is a machine learning-driven hiring platform for software engineers."
                 :content [(intro "At untapt, I oversaw design and development
                                   for two large SPAs. The first was a site that allowed
                                   jobseekers to build a resume and apply to roles. The second was
                                   a lightweight ATS that hiring managers used to review
                                   applications.")
                           (skills ["AngularJS" "Figma" "Photoshop" "Marvel"])
                           (section "Resume Editor")
                           (description "A key feature of untapt is our Resume Editor. It
                                         allows engineers to quickly build a resume that
                                         they can later send to companies.")
                           (description "The editor provides feedback to candidates to help
                                         them ensure their resume is fit for the role they are
                                         applying to. Here, it suggests that I should add
                                         details about my experience with specific skills:")
                           (example-video :filename "AsLC9LyFMPs"
                                          :caption "Quick Editing")
                           (description "For various reasons, we decided to automatically
                                         infer candidates' technology skills from the content
                                         of their resumes, rather than having them list them
                                         manually. Skills required by a specific role are
                                         highlighted in orange:")
                           (example-video :filename "1Mi-tY-Vchc"
                                          :caption "Resume Recommendations")
                           (description "When designing the editor, we focused
                                         on making editing quick and intuitive. Editing a
                                         section is as easy as clicking on the content that
                                         you would like to change:")
                           (example-video :filename "aBUoGM_Z0kY"
                                          :caption "Example of automatic skill inference")
                           (section "Hiring Manager Interface")
                           (description "")
                           (example-image :filename "manager-landing.png"
                                          :caption "Mockup of our marketing website for hiring managers"
                                          :width 600)
                           (example-image :filename "manager-pipeline.png"
                                          :caption "Interface for quickly navigating through job applications"
                                          :width 600)
                           (example-image :filename "resume-versions.png"
                                          :caption "Resume editor, showing users' ability to manage multiple resumes"
                                          :width 600)])
        (company :name "vodka"
                 :slug "vodka"
                 :description "Custom iPad application for Broadway show \"Natasha, Pierre and the Great Comet of 1812\"."
                 :content [(skills ["React Native" "Figma" "Swift"])
                           (example-image :filename "vodka-screenshot.jpg"
                                          :caption "Control surface for realtime two-dimensional audio placement"
                                          :width 450)])])


