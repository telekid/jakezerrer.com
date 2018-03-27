(ns portfolio.views.pages.portfolio-entry
  (:require [portfolio.router :refer [link-for]]
            [goog.string :as gstring]
            [portfolio.cdn :refer [resource-url]]
            [re-frame.core :refer [subscribe]]
            [portfolio.views.components :refer [site-header
                                                site-header-spacer
                                                page-wrap
                                                site-footer
                                                <>
                                                center-page]]
            [goog.string.format]))


(defn company [& {:keys [name slug description content show-nav] :or {show-nav false}}]
  [:div.portfolio-entry
   [:div.portfolio-entry__wrap
    [site-header-spacer]
    (into [center-page {}]
          content)]
   [site-footer]])

(defn example [wrapped-component caption width]
  [:figure.project__example
   {:class (gstring/format "project__example--%s" (name width))}
   wrapped-component
   [:figcaption.project__example-description caption]])

(defn example-image [{:keys [filename width caption]}]
  (example [:img.project__example-image {:src (resource-url filename)
                                         :width width
                                         :alt caption}]
           caption
           :auto))

(defn example-video [& content]
  (let [{filename :filename
         caption :caption}
        ;; TODO this isn't a good way to do this
        (first content)]
    [example [:div.project__aspect-wrap
              [:iframe.project__example-video {:src (gstring/format "https://www.youtube.com/embed/%s?rel=0&controls=1&showinfo=0" filename)}
                                              :allowFullScreen true
                                              :frameBorder 0]]
            caption
            :wide]))

(defn intro [text] [:h1.portfolio-entry__intro text])

(defn section [title] [:h1.project__section title])

(defn subsection [title] [:h2.project__subsection title])

(defn description [& content] (apply conj [:p.project__description] content))

(defn skills [& skills]
  [:ul.project__skills (map-indexed (fn [idx skill] [:li.project__skill {:key idx} skill]) skills)])

(defn invision-prototype [{:keys [prototype-id]}]
  [:div.project__prototype-wrap
   [:iframe.project__prototype {:width "438"
                                :height "930"
                                :src (str "//invis.io/" prototype-id)
                                :frame-border 0
                                :allow-full-screen true}]])

;; TODO Make render-with accept a map of keys to functions
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
                           :intro intro
                           :invision-prototype invision-prototype
                           ;; Throw error
                           (fn [c] [:div c]))
                         elem-body)
         {:key idx})))
   content))

(defn portfolio-entry []
  (let [entry (subscribe [:current-portfolio-entry])]
    [:div.portfolio
        [company :name (:name @entry)
                 :description (:description @entry)
                 :content (render-with (:content @entry))]]))
