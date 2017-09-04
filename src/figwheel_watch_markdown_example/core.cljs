(ns figwheel-watch-markdown-example.core
  (:require
    [reagent.core :as reagent :refer [atom]]
    [cljsjs.showdown])
  (:require-macros
    [figwheel-watch-markdown-example.macros :as macros]))

(enable-console-print!)

(println "This text is printed from src/figwheel-watch-markdown-example/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state
  (atom {:reload-count 0}))

(defn make-html [s]
  (.makeHtml
    (doto (js/showdown.Converter.)
      (.setFlavor "github"))
    s))

(defn render-markdown [s]
  [:div
   {:dangerouslySetInnerHTML
    {:__html (make-html s)}}])

(defn hello-world []
  [:div
   [:h1 "Reload my Markdown"]
   [:p (str "This page has been reloaded " (:reload-count @app-state) " times.")]
   (into
     [:ul]
     (for [[filename markdown] (macros/all-markdowns)]
       [:li
        [:h2 (str "resource/public/" filename)]
        [render-markdown markdown]]))])

(reagent/render-component [hello-world] (js/document.getElementById "app"))

(defn on-js-reload []
  (swap! app-state update-in [:reload-count] inc))
