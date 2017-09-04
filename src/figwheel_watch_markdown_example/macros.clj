(ns figwheel-watch-markdown-example.macros
  (:require [clojure.java.io :as io])
  (:import (java.io File)))

(defmacro all-markdowns []
  (try
    (vec
      (for [^File lesson (seq (.listFiles (io/file (io/resource "public/markdown"))))
            :when (.isFile lesson)]
        [(.getName lesson) (slurp lesson)]))
    (catch Exception ex
      ["Error" (with-out-str (println ex))])))
