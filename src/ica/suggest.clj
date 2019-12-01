(ns ica.suggest
  "a namespace that sets up the park suggestions"
  (:gen-class)
  (:require [clojure.java.io :as io]
            [cheshire.core :as cheshire :refer :all])
  (:use [inflections.core]
        [ica.opennlp :only (tokenize)]
        [ica.parkData])
  (:import [ica.parkData Park]))

(def recogs
  "It parses the strings from reg_phrases.json into a hashmap that is used to recognize certain keywords from the chat."
  (first (cheshire/parsed-seq (io/reader "src/ica/recog_phrases.json") true)))

(defn recognize [sentence]
  "It reads a string and checks if there is anything in the string that matches
  the words in recog_phrases.json. It returns a list of hashmaps containing keys and matched words."
  (for [phrase (tokenize sentence)
        key (keys recogs)
        :when (some #(when (= (singular phrase) %) %) (get recogs key))]
        (hash-map key (singular phrase))))

; (defn boolefy-vals [word-map]
;   "It turns values of the map into a boolean value of true.
;   It does not change the value if the key is :architecture."
;     (loop [mapped word-map
;            chain (keys word-map)]
;       (if (not (empty? chain))
;         (if (or (not= (first chain) :architecture) (not= (first chain) :name))
;           (do (println (first chain))
;           (recur (alter mapped assoc (first chain) true) (rest chain)))
;           mapped))))
;
; (defn imagine-park [park sentence]
;   "It returns a record of ica.parkData.Park
;   from a map of matched words from (recognize)
;   while unmentioned keys are paired with a value of nil."
;   (if (= (get park :name) "user")
;     (map->Park (boolefy-vals park))
;     (map->Park (assoc (boolefy-vals (recognize sentence)) :name "user"))))
