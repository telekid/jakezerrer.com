(ns portfolio.views
  (:require [re-frame.core :as re-frame]
            [goog.string :as gstring]
            [portfolio.cdn :refer [resource-url]]
            ;; [portfolio.router :refer [r]]
            [goog.string.format]))

(declare portfolio home)

(defn main-panel []
  [:div.container (home) (portfolio)])

(defn home []
  [:div.home__wrap
   [:div.home
    [:h1.home__header "Jake Zerrer"]
    [:nav.home__links
     [:ul
      [:li [:a {:href "/portfolio"} "Portfolio"]]
      [:li [:a {:href "https://www.linkedin.com/in/jakezerrer/"} "linkedin"]]
      [:li [:a {:href "https://www.instagram.com/telekid/"} "instagram"]]
      [:li [:a {:href "https://github.com/telekid"} "github"]]]]]])

(defn company [& {:keys [name slug description content show-nav] :or {show-nav false}}]
  [:div.project
   [:div.project__overview-wrap
    (when show-nav
      [:nav.project__nav
       [:h1 "Portfolioooooo cat"]
       [:a {:href "/"} "return home"]])
    [:div.project__overview
      [:h1.project__company-header name]
      [:p.project__company-description description]]]
   ;; There's probably a sexier way to write this, but I'm at a loss
   (apply conj [:div.project__images] content)])

(defn example [wrapped-component caption width]
  [:figure.project__example
   {:class (gstring/format "project__example--%s" (name width))}
   wrapped-component
   [:figcaption.project__example-description caption]])

(defn example-image [& {:keys [filename caption width]}]
  (example [:img.project__example-image {:src (resource-url filename)
                                         :width width
                                         :alt caption}]
           caption
           :auto))

(defn example-video [& {:keys [filename caption]}]
  (example [:div.project__aspect-wrap
            [:iframe.project__example-video {:src (gstring/format "https://www.youtube.com/embed/%s?rel=0&controls=1&showinfo=0" filename)
                                             :allowFullScreen true
                                             :frameBorder 0}]]
           caption
           :wide))

(defn intro [text] [:h1 text])

(defn section [title] [:h1.project__section title])

(defn subsection [title] [:h2.project__subsection title])

(defn description [& content] (apply conj [:p.project__description] content))

(defn skills [skills]
  [:ul.project__skills (map-indexed (fn [idx skill] [:li.project__skill {:key idx} skill]) skills)])

(defn portfolio []
  [:div.portfolio
        (company :name "untapt"
                 :show-nav true
                 :slug "untapt"
                 :description "untapt is a machine learning-driven hiring platform for software engineers."
                 :content [(section "untapt")
                           (description "At untapt, I oversaw design and development for two large web
                                         applications used by over 30,000 software engineers and dozens
                                         of technology companies.")
                           (description "The "
                                        [:i "Candidate Interface"]
                                        " app helped software engineers
                                         build beautiful resumes and apply to technology roles.
                                         The application leveraged ML to help candidates increase
                                         their odds of landing a successful interview.")
                           (description "The second application was a lightweight "
                                   [:abbr {:title "Applicant Tracking System"} "ATS"]
                                   " that hiring managers used to review and manage
                                   job applications. This "
                                   [:i "Manager Interface"]
                                   " was designed with ease-of-use and rapid review in mind.")
                           (skills ["Figma" "Photoshop" "Marvel" "AngularJS"])
                           (subsection "Resume Editor")
                           (description "When designing the editor, we focused
                                         on making editing quick and intuitive. Editing a
                                         section is as easy as clicking on the content that
                                         you would like to change:")
                           (example-video :filename "aBUoGM_Z0kY"
                                          :caption "Example of automatic skill inference")
                           (description "A key feature of the " [:i "Candidate Experience"]
                                        " was the Resume Editor. It
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
                                          :caption "Resume Recommendations")])
                           ;; (subsection "Hiring Manager Interface")
                           ;; (description "The "
                           ;;              [:i "Hiring Manager Interface"])
                           ;;              " "

                           ;; (example-image :filename "manager-landing.png"
                           ;;                :caption "Mockup of our marketing website for hiring managers"
                           ;;                :width 600)
                           ;; (example-image :filename "manager-pipeline.png"
                           ;;                :caption "Interface for quickly navigating through job applications"
                           ;;                :width 600)
                           ;; (example-image :filename "resume-versions.png"
                           ;;                :caption "Resume editor, showing users' ability to manage multiple resumes"
                           ;;                :width 600)])
        (company :name "vodka"
                 :slug "vodka"
                 :description "Custom iPad application for Broadway show \"Natasha, Pierre and the Great Comet of 1812\"."
                 :content [(section "Vodka")
                           (description "Nick Pope, sound designer for the Broadway show "
                                        [:i "Natasha, Pierre and the Great Comet of 1812,"]
                                        " approached me to help him solve an unusual design
                                        problem.")
                           (description "Unlike most Broadway shows, the actors in "
                                        [:i "Great Comet"]
                                        " moved not only on stage, but around the audience. Nick needed
                                         to find a way to position the voices of actors and musicians within
                                         the 2D plane that makes up the ground plan of the theater.")
                           (description "Though he had a sophisticated computerized mixing system that could
                                         take care of the actual audio computations (like panning and summing),
                                         the native software interface was too cumbersome to allow them make
                                         changes quickly.")
                           (skills ["React Native" "Figma" "Swift"])
                           (example-image :filename "vodka-screenshot.jpg"
                                          :caption "Control surface for realtime two-dimensional audio placement"
                                          :width 450)])
        (company :name "Refuge"
                 :slug "refuge"
                 :description "App for finding, sharing and supporting new music."
                 :content [
                           (section "Refuge")
                           (description "Refuge was a side project that nearly snowballed into a startup.")
                           (description "The goal of Refuge was to build a social music platform that focused
                                         on helping users find, share and support new artists. The platform would
                                         help artists build a brand and gain exposure without the help of a
                                         record label.")
                           (description "Though Refuge never left the design & prototyping phase, to date, it remains one of my most beloved projects.")
                           (description "To learn more about Refuge, check out our "
                                         [:a {:href "https://paper.dropbox.com/doc/Refuge-Overview-for-YC-R61kLiZFtC1MiOzmxPNjM"}]
                                         "Y Combinator Pitch Document"
                                         "(note: may have trouble loading) and a sample "
                                         [:a {:href "https://projects.invisionapp.com/share/RBF07PMDY#/screens/269781422_Live_Feed"}]
                                         "Invision Prototype"
                                         ".")
                           (skills ["Figma", "Invision"])])])
                           ;; (subsection "Selected Prototype Screenshots")
                           ;; (description "Early Refuge prototyping took place during a rapid ")])])
                           ;; (example-image :filename "refuge-landing.png"
                           ;;                :caption "the refu.ge home page"
                           ;;                :width 600)
                           ;; (example-image :filename "refuge-live.png"
                           ;;                :caption "See what musical tastemakers are listening to"
                           ;;                :width 300)
                           ;; (example-image :filename "refuge-playback.png"
                           ;;                :caption "Simple playback interface"
                           ;;                :width 300)
                           ;; (example-image :filename "refuge-color-study.png"
                           ;;                :caption "Color study for Refuge"
                           ;;                :width 450)])])

