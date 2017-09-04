(defproject figwheel-watch-markdown-example "0.1.0-SNAPSHOT"
  :description "How to make figwheel watch markdown (or other slurped resources)"
  :url "http://github.com/timothypratley/figwheel-watch-markdown-example"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.7.1"

  :dependencies [[org.clojure/clojure "1.9.0-alpha17"]
                 [org.clojure/clojurescript "1.9.908"]
                 [org.clojure/core.async  "0.3.443"]
                 [reagent "0.7.0"]
                 [cljsjs/showdown "1.4.2-0"]]

  :plugins [[lein-figwheel "0.5.13"]
            [lein-cljsbuild "1.1.7" :exclusions [[org.clojure/clojure]]]]

  :source-paths ["src"]

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]
                :watch-paths ["src" "resources/public/markdown/"]
                :figwheel {:on-jsload "figwheel-watch-markdown-example.core/on-js-reload"
                           :open-urls ["http://localhost:3449/index.html"]}

                :compiler {:main figwheel-watch-markdown-example.core
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/figwheel_watch_markdown_example.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true
                           :preloads [devtools.preload]}}
               {:id "min"
                :source-paths ["src"]
                :compiler {:output-to "resources/public/js/compiled/figwheel_watch_markdown_example.js"
                           :main figwheel-watch-markdown-example.core
                           :optimizations :advanced
                           :pretty-print false}}]}

  :figwheel {:css-dirs ["resources/public/css"]}

  :profiles {:dev {:dependencies [[binaryage/devtools "0.9.4"]
                                  [figwheel-sidecar "0.5.13"]
                                  [com.cemerick/piggieback "0.2.2"]]
                   :source-paths ["src" "dev"]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                                     :target-path]}})
