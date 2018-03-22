(ns portfolio.views.pages.home
  (:require [portfolio.views.components :refer [page-wrap
                                                <>
                                                site-header
                                                site-footer
                                                center-page]]
            [portfolio.cdn :refer [resource-url]]
            [portfolio.router :refer [link-for]]))

(defn single-image [{:keys [src offset width z-offset]}]
  [:div.home-rotate-wrap
   {:style {:position :absolute
            :width width
            :height width}}
   [:img {:src src
          :style {:transform (str "translateZ(" z-offset "px)")}}]])

(defn image-repeat [{:keys [src img-width offset quantity z-separation] :as keys}]
  (let [individual-width (- img-width (* offset (- quantity 1)))]
    (into [:div
           (-> keys
               (dissoc :src :img-width :offset :quantity :z-separation)
               (assoc-in [:style :width] img-width)
               (assoc-in [:style :height] img-width))]
      (for [i (range quantity)]
        ^{:key i}
         [single-image {:src src
                        :z-offset (- (* z-separation i) (/ (* z-separation (- quantity 1)) 2))
                        :offset (* i offset)
                        :width individual-width}]))))

(defn content []
  [:div.home-page-content
   [:h1 "Hi there. I'm Jake."]
   [:p "I'm a product designer and a software engineer. I delight in making complex things feel simple."]
   [:p "This site is here to keep track of my past work. Have a look around."]
   [:nav.home-page-content--nav
    [:a.home-page-content--nav-button {:href (link-for :portfolio)} "view portfolio"]]])
    ;; [:a.home-page-content--nav-button {:href (link-for :notes)} "view notes"]]])

(defn home []
  [:div.home-page
   [:div.home-page__wrap
    [image-repeat {:src (resource-url "dunes.jpg")
                   :class-name "home-image-repeat"
                   :img-width 320
                   :offset 10
                   :z-separation 30
                   :quantity 3}]
    [content]]
   [site-footer]])
