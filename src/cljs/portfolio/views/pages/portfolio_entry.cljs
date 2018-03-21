(ns portfolio.views.pages.portfolio-entry
  (:require [portfolio.router :refer [link-for]]
            [goog.string :as gstring]
            [portfolio.cdn :refer [resource-url]]
            [re-frame.core :refer [subscribe]]
            [goog.string.format]))


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

;; TODO Rather than varargs, look up how this might be done with polymorphism
(defn example-image [& content]
  (let [{filename :filename
         width :width
         caption :caption}
        ;; TODO This isn't a good way to do this
        (first content)]
    (example [:img.project__example-image {:src (resource-url filename)
                                           :width width
                                           :alt caption}]
             caption
             :auto)))

(defn example-video [& content]
  (let [{filename :filename
         caption :caption}
        ;; TODO this isn't a good way to do this
        (first content)]
    (example [:div.project__aspect-wrap
              [:iframe.project__example-video {:src (gstring/format "https://www.youtube.com/embed/%s?rel=0&controls=1&showinfo=0" filename)}
                                              :allowFullScreen true
                                              :frameBorder 0]]
            caption
            :wide)))

(defn intro [text] [:h1 text])

(defn section [title] [:h1.project__section title])

(defn subsection [title] [:h2.project__subsection title])

(defn description [& content] (apply conj [:p.project__description] content))

(defn skills [& skills]
  [:ul.project__skills (map-indexed (fn [idx skill] [:li.project__skill {:key idx} skill]) skills)])

(def test-db [[:description "Test description"]
              [:p "Some elem"]])

(defn render-with [content]
  (map-indexed
   (fn [idx elem]
     (let [elem-name (first elem)
           elem-body (rest elem)]
       (with-meta (apply (case elem-name
                           :description description
                           :skills skills
                           :subsection subsection
                           :example-image example-image
                           :example-video example-video
                           ;; Throw error
                           (fn [c] [:div c]))
                         elem-body)
         {:key idx})))
   content))

(render-with test-db)

(defn portfolio-entry []
  (let [entry (subscribe [:current-portfolio-entry])]
    [:div.portfolio
        (company :name (:name @entry)
                 :show-nav true
                 ;; TODO description could be another sub
                 :description (:description @entry)
                 :content (render-with (:content @entry)))]))
