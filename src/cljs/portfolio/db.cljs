(ns portfolio.db)

(def default-db
  {:location {:handler :portfolio}
   :portfolio
   {:untapt
    {:name "untapt"
     :show-nav true
     :description "Machine learning-powered hiring platform for software engineers"
     :content
     [
      [:intro "untapt"]
      [:description "At untapt, I led design and development for two large web applications used by over 30,000 software engineers and dozens of technology companies."]
      [:description "The first app was the " [:i "Candidate Interface"] ". It allowed software engineers to build beautiful resumes and apply to technology roles. The application leveraged " [:abbr {:title "Machine Learning"} "ML"] " to help candidates increase their odds of landing a successful interview."]
      [:description "The second application was a lightweight " [:abbr {:title "Applicant Tracking System"} "ATS"] " that hiring managers used to review and manage job applications. This " [:i "Manager Interface"] " was designed with simplicity and rapid review in mind."]
      [:skills "Figma" "Photoshop" "Marvel" "AngularJS"]
      [:subsection "Resume Editor"]
      [:description "Though I worked on dozens of initiatives during my three year tenure at untapt, my favorite design and engineering project was definitely our resume editor."]
      [:description "When designing the editor, I focused on making changes quick and intuitive. To edit their resume, the candidate simply clicks on the content they wish to update. That opens an editor panel, where they can enter their changes:"]
      [:example-video {:filename "AsLC9LyFMPs" :caption "Quick Editing"}]
      [:description "Rather than requiring candidates to list skills manually, we decided to build a " [:abbr {:title "Named Entity Recognition"} "NER"] " system that automatically identified skills mentioned in the body of the resume. Here, note that the skill \"React\" is automatically identified:"]
      [:example-video {:filename "aBUoGM_Z0kY" :caption "Example of automatic skill inference"}]
      [:description "The editor provides feedback to help candidates ensure their resume is appropriate for the role they are applying to. Here, it suggests that they should add details about their experience with specific skills:"]
      [:example-video {:filename "1Mi-tY-Vchc" :caption "Resume Recommendations"}]]}
    :vodka
    {:name "Vodka"
     :description "Sound localization software for the Broadway Show \"Natasha, Pierre and the Great Comet of 1812\""
     :content
     [
      [:intro "Vodka"]
      [:description "Nick Pope, sound designer for the Broadway show " [:i "Natasha, Pierre and the Great Comet of 1812,"] " approached me to help him solve an unusual design problem: what's the best way to pan sound two dimensionally around a theater?"]
      [:description "Unlike most Broadway shows, the actors in " [:i "Great Comet"] " moved not only on stage, but around the audience. Nick needed to find a way to position the voices of actors and musicians within the 2D plane that makes up the ground plan of the theater."]
      [:description "Though he had a sophisticated computerized mixing system that could take care of the actual audio computations (like panning and summing), the native software interface was too cumbersome to allow them make changes quickly."]
      [:description "To read more about Nick's experience with Vodka, check out " [:a {:href "https://meyersound.com/news/all-the-house-is-a-stage/"} "this interview"] "."]
      [:skills "React Native" "Figma" "Swift"]
      [:example-image {:filename "vodka-screenshot.jpg" :width 450 :caption "Control surface for realtime two-dimensional audio placement"}]
      [:description "Vodka served as a multitouch front-end to Meyer Sound's CueStation software. The interface was fairly simple; it showed the positions of actors overlaid upon a groundplan of the theater. An operator could use their fingers to drag actor symbols around across the groundplan. Vodka would communicate those positional changes to CueStation via " [:abbr {:title "Open Sound Control"} "OSC"] ", and then CueStation would manage the panning of the audio."]
      [:description "Here, you can see two characters (Natasha and Pierre) move across the groundplan displayed in Vodka on an iPad simulator. In the background, you can see the audio server reflecting those changes in real time:"]
      [:example-video {:filename "RoLmiUkNmZY" :caption "Vodka trajectory simulation"}]
      [:description "It was critical to minimize the risk that any malfunction within Vodka would disrupt the production. To facilitate this, we chose to offload all state management to the audio server. Any change to state on either Vodka or the server would be immediately forwarded to the other party."]
      [:description "The following video demonstrates this bidirectional communication. Here, we rename buses on the server, and then those changes are immediately reflected in Vodka:"]
      [:example-video {:filename "wta_Qli0BZ4" :caption "Vodka bus naming"}]]}
    :refuge
    {:name "Refuge"
     :description "App for finding, sharing and supporting new music"
     :content
     [
      [:intro "Refuge"]
      [:description "Refuge was a side project that nearly snowballed into a startup."]
      [:description "The goal of Refuge was to build a social music platform that focused on helping users find, share and support new artists. The platform would help artists build a brand and gain exposure without the help of a record label."]
      [:description "Though Refuge never left the design & prototyping phase, to date, it remains one of my most beloved projects."]
      [:description "To learn more about Refuge, check out the " [:a {:href "https://paper.dropbox.com/doc/Refuge-Overview-for-YC-R61kLiZFtC1MiOzmxPNjM"} "Y Combinator Pitch Document"] " (note: large document, some sections have trouble loading.)"]
      [:skills "Figma" "Invision"]
      [:subsection "Invision Prototype"]
      [:description "To get a feel for Refuge, feel free to play around with the following " [:a {:href "https://projects.invisionapp.com/share/RBF07PMDY#/screens/269781422_Live_Feed"} "Invision Prototype"] "."]
      [:invision-prototype {:prototype-id "FEF8LERM5"}]]}}})
