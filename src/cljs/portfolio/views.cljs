(ns portfolio.views
  (:require [re-frame.core :as re-frame]
            [goog.string :as gstring]
            [portfolio.cdn :refer [resource-url]]
            ;; [portfolio.router :refer [r]]
            [goog.string.format]))

(declare portfolio home)

(defn main-panel []
  ;; [:div.container (home) (portfolio)]
  [:div.container (portfolio)])

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
       [:h1 "Jake Zerrer"]
       [:p "Hi, I'm Jake. I'm a code-literate product designer. I turn complex ideas into simple things."]
       [:p "This is my portfolio. Enjoy!"]])
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
        (company :show-nav true
                 :content [(section "Standard Approach")
                           (description "My approach to design is a direct byproduct of my multidisciplinary background. My experience designing for theater impressed upon me the importance of storytelling and empathy in any design process; and my technical background has fostered a methodology defined by logic and empiricism.")
                           (description "More formally, my standard approach to any new problem looks something like this:")
                           [:div.project__process-box
                            [:ol
                             [:li "Definition"]
                             [:li "Research"]
                             [:li "Divergent Thinking"]
                             [:li "Convergent Thinking"]]]
                           (subsection "Definition")
                           (description "During the definition stage, I work with collaborators to define and contextualize the problem (or product.) What are we setting out to do? Why? How will we measure our results?")
                           (subsection "Research")
                           (description "The research process varies heavily from project to project. Some projects demand a long, formal research process; others, simple exercises in empathy building. What can we learn about its users? What about its environment? Its competitive landscape?")
                           (description "Formal processes like User Personas and Journey Maps may be useful. Selecting the right methodology is as important as the research itself.")
                           (subsection "Divergent Thinking")
                           (description "Creativity, chaos, and play all converge here. This step is similar to brainstorming; it is the exploration of our problem space. Ideas proliferate - good and bad - ready to be whittled down in the final step.")
                           (subsection "Convergent Thinking")
                           (description "Convergence synthesizes the results of Research and Divergence into a product.")])

        (company :name "untapt"
                 :slug "untapt"
                 :description "untapt is a machine learning-driven hiring platform for software engineers."
                 :content [(section "untapt")
                           (description "At untapt, I led design and development for two large web
                                         applications used by over 30,000 software engineers and dozens
                                         of technology companies.")
                           (description "The first app was the "
                                        [:i "Candidate Interface"]
                                        ". It allowed software engineers to
                                         build beautiful resumes and apply to technology roles.
                                         The application leveraged "
                                        [:abbr {:title "Machine Learning"} "ML"]
                                        " to help candidates increase
                                         their odds of landing a successful interview.")
                           (description "The second application was a lightweight "
                                   [:abbr {:title "Applicant Tracking System"} "ATS"]
                                   " that hiring managers used to review and manage
                                   job applications. This "
                                   [:i "Manager Interface"]
                                   " was designed with simplicity and rapid review in mind.")
                           (skills ["Figma" "Photoshop" "Marvel" "AngularJS"])
                           (subsection "Resume Editor")
                           (description "Though I worked on dozens of initiatives during my three year tenure at untapt, my favorite design and engineering project was definitely our resume editor.")
                           (description "When designing the editor, I focused
                                         on making changes quick and intuitive. To edit their resume, the candidate simply clicks on the content they wish to update. That opens an editor panel, where they can enter their changes:")
                           (example-video :filename "AsLC9LyFMPs"
                                          :caption "Quick Editing")
                           (description "Rather than requiring candidates to list skills manually, we decided to build a "
                                        [:abbr {:title "Named Entity Recognition"} "NER"]
                                        " system that automatically identified skills mentioned in the body of the resume. Here, note that the skill \"React\" is automatically identified:")
                           (example-video :filename "aBUoGM_Z0kY"
                                          :caption "Example of automatic skill inference")
                           (description "The editor provides feedback to help candidates ensure their resume is appropriate for the role they are
                                         applying to. Here, it suggests that they should add
                                         details about their experience with specific skills:")
                           (example-video :filename "1Mi-tY-Vchc"
                                          :caption "Resume Recommendations")])
        (company :name "vodka"
                 :slug "vodka"
                 :description "Custom iPad application for Broadway show \"Natasha, Pierre and the Great Comet of 1812\"."
                 :content [(section "Vodka")
                           (description "Nick Pope, sound designer for the Broadway show "
                                        [:i "Natasha, Pierre and the Great Comet of 1812,"]
                                        " approached me to help him solve an unusual design
                                        problem: what's the best way to pan sound two dimensionally around a theater?")
                           (description "Unlike most Broadway shows, the actors in "
                                        [:i "Great Comet"]
                                        " moved not only on stage, but around the audience. Nick needed
                                         to find a way to position the voices of actors and musicians within
                                         the 2D plane that makes up the ground plan of the theater.")
                           (description "Though he had a sophisticated computerized mixing system that could
                                         take care of the actual audio computations (like panning and summing),
                                         the native software interface was too cumbersome to allow them make
                                         changes quickly.")
                           (description "To read more about Nick's experience with Vodka, check out "
                                        [:a {:href ""} "this interview"]
                                        ".")
                           (skills ["React Native" "Figma" "Swift"])
                           (example-image :filename "vodka-screenshot.jpg"
                                          :caption "Control surface for realtime two-dimensional audio placement"
                                          :width 450)
                           (description "Vodka served as a multitouch front-end to Meyer Sound's CueStation software.
                                         The interface was fairly simple; it showed the positions of actors overlaid
                                         upon a groundplan of the theater. An operator could use their fingers to drag
                                         actor symbols around across the groundplan. Vodka would communicate those positional
                                         changes to CueStation via "
                                        [:abbr {:title "Open Sound Control"} "OSC"]
                                        ", and then CueStation would manage the panning of the audio.")
                           (description "Here, you can see two characters (Natasha and Pierre) move across the
                                         groundplan displayed in Vodka on an iPad simulator. In the background,
                                         you can see the audio server reflecting those changes in real time:")
                           (example-video :filename "RoLmiUkNmZY"
                                          :caption "Vodka trajectory simulation")
                           (description "It was critical to minimize the risk that any malfunction within Vodka
                                         would disrupt the production. To facilitate this, we chose to offload all
                                         state management to the audio server. Any change to state on either Vodka
                                         or the server would be immediately forwarded to the other party.")
                           (description "The following video demonstrates this bidirectional communication. Here,
                                         we rename buses on the server, and then those changes are immediately
                                         reflected in Vodka:")
                           (example-video :filename "wta_Qli0BZ4"
                                          :caption "Vodka bus naming")])

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
                           (description "To learn more about Refuge, check out the "
                                         [:a {:href "https://paper.dropbox.com/doc/Refuge-Overview-for-YC-R61kLiZFtC1MiOzmxPNjM"}
                                          "Y Combinator Pitch Document"]
                                         " (note: large document, some sections have trouble loading.)")
                           (skills ["Figma", "Invision"])
                           (subsection "Invision Prototype")
                           (description "To get a feel for Refuge, feel free to play around with the following " [:a {:href "https://projects.invisionapp.com/share/RBF07PMDY#/screens/269781422_Live_Feed"} "Invision Prototype"] ".")
                           [:div.project__prototype-wrap
                            [:iframe.project__prototype {:width "438"
                                                         :height "930"
                                                         :src "//invis.io/FEF8LERM5"
                                                         :frameBorder 0
                                                         :allowFullScreen true}]]])])

