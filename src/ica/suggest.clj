(ns ica.suggest
  "a namespace that sets up the park suggestions"
  (:gen-class)
  (:require [clojure.java.io :as io]
            [cheshire.core :as cheshire :refer :all]
            [clojure.string :as string])
  (:use [ica.core :only (tokenize)]))

(def recogs
  "It parses the strings from reg_phrases.json into a hashmap that is used to recognize certain keywords from the chat."
  (first (cheshire/parsed-seq (io/reader "src/ica/recog_phrases.json") true)))

(defn recognize [sentence]
  "It reads a string and checks if there is anything that matches the words in recog_phrases.json.
  It returns a list of matched words."
  (for [word (tokenize sentence)
        feature (keys recogs)
        :when (some (if (= (last word) "y")
                      #(when (or (= word %) (= (string/join [(string/join (drop-last word)) "ies"]) %)) %)
                      #(when (or (= word %) (= (string/join [word "s"]) %)) %))
                    (get recogs feature))]
        word))
