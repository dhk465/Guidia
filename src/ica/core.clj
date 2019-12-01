(ns ica.core
  "the main namespace to prepare the chatbot"
  (:gen-class)
  (:require [clojure.java.io :as io]
            [cheshire.core :as cheshire :refer :all])
  (:use [ica.opennlp :only (tokenize)]
        ;[ica.interface :only (interface)]
        [ica.parkData])
  (:import [ica.parkData Park]))

(def quit-words
  "It slurps a list of words from 'recog_phrases.json' that are used to quit chatbot's main loop."
  (get (first (cheshire/parsed-seq (io/reader "src/ica/recog_phrases.json") true)) :quitwords))

(defn ask [question] ;; not used anywhere, exists here in case it will be needed
  "It takes a string of question to show the user so that it further takes a user input.
  It returns the user input as a lazy-seq of tokenized words/strings."
  (println question)
  (tokenize (read-line)))

(defn word-exists? [quit-words word] ;; not used anywhere, exists here in case it will be needed
  "It takes a word from the user and a list of quit words
  and iterates through are used to quit from the chatbot."
  (loop [lst quit-words]
    (when (seq lst)
      (if (= (first lst) word)
        true
        (recur (rest lst))))))

(defn data-comparer-helper-1 [position park-stored input-park] ;;rewrite docstring
  "It takes a data about park and compares to data inputed by a user for one parameter."
   (= ((nth (rest (keys Bertramka)) position) input-park)
    ((nth (rest (keys Bertramka)) position) park-stored)))

(defn data-comparer-helper-2 [park-stored input-park]  ;;rewrite docstring
  "It takes in a list of parks, user inputted data and a parameter, 
  adds the park to a locally stored vector if the chosen parameter in the park
 and user inputted data matches. Returns that vector of parks."
   (with-local-vars [counter 0]
    (loop [position 0]
      (when (<= position 7)
       (if  (= (data-comparer-helper-1 position park-stored input-park) true)
        (var-set counter (+ 1 @counter)))
       (recur (+ 1 position))))
   (var-get counter)))

(defn data-comparer-helper-3 [lst-park input-park]
  (with-local-vars [simularity-count-vec []]
   (loop [lst-park-loc lst-park]
    (when-not (empty? lst-park-loc)
     (var-set simularity-count-vec (conj @simularity-count-vec (data-comparer-helper-2 (first lst-park-loc) input-park)))
     (recur (rest lst-park-loc))))
   (var-get simularity-count-vec)))

(defn data-comparer-helper-4 [lst-park simularity-count-vec highest-match-counter]
  (with-local-vars [matched-parks-vec []]
   (loop [position 0]
    (when (< position (count lst-park))
     (if 
      (= (nth simularity-count-vec position) highest-match-counter)
       (var-set matched-parks-vec (conj @matched-parks-vec (nth lst-park position))))
     (recur (+ 1 position))))
   (var-get matched-parks-vec)))

(defn data-comparer-helper-5 simularity-count-vec
   (with-local-vars [highest-match-counter 0]
   (doseq [simularity-counter simularity-count-vec]
     (if (< @highest-match-counter simularity-counter)
      (var-set highest-match-counter simularity-counter)))
   (var-get highest-match-counter)))

(defn data-comparer-main [lst-park input-park] ;; bug in the code
   (let* [simularity-count-vec (data-comparer-helper-3 lst-park input-park)
    highest-match-counter (data-comparer-helper-5 simularity-count-vec)]
    (data-comparer-helper-4 lst-park simularity-count-vec highest-match-counter)
   )
)

; to-do: main with (recognize) fn
(defn -main [& args]
  "It allows user to run the chatbot on command 'lein run'."
  ;(interface lst-park)
  (println "Bye!"))
