(ns ica.core
  "the main namespace to prepare the chatbot"
  (:gen-class)
  (:require [clojure.java.io :as io]
            [cheshire.core :as cheshire :refer :all])
  (:use [ica.opennlp :only (tokenize)]
        [ica.parkData]
        [inflections.core])
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
   (and
    (= true @((nth (rest (keys Bertramka)) position) input-park))
    (= true @((nth (rest (keys Bertramka)) position) park-stored))))

(defn data-comparer-helper-2 [park-stored input-park]  ;;rewrite docstring
  "It takes in a list of parks, user inputted data and a parameter,
  adds the park to a locally stored vector if the chosen parameter in the park
  and user inputted data matches. Returns that vector of parks."
  (with-local-vars [counter 0]
    (doseq [position (range 7)]
      (if (data-comparer-helper-1 position park-stored input-park)
        (var-set counter (+ 1 @counter))))
    (var-get counter)))

(defn data-comparer-helper-3 [lst-park input-park]
  (with-local-vars [sim-vector []]
   (loop [lst-park-loc lst-park]
    (when-not (empty? lst-park-loc)
     (var-set sim-vector (conj @sim-vector (data-comparer-helper-2 (first lst-park-loc) input-park)))
     (recur (rest lst-park-loc))))
   (var-get sim-vector)))

(defn data-comparer-helper-4 [lst-park sim-vector highest]
  (with-local-vars [park-matches []]
    (loop [position 0]
      (when (< position (count lst-park))
        (if (= (nth sim-vector position) highest)
          (var-set park-matches (conj @park-matches (nth lst-park position))))
        (recur (+ 1 position))))
    (var-get park-matches)))

(defn data-comparer-find-max [sim-vector]
  (with-local-vars [highest 0]
    (doseq [sim-counter sim-vector]
      (if (< @highest sim-counter)
        (var-set highest sim-counter)))
    (var-get highest)))

(defn data-comparer-main [lst-park input-park] ;; bug in the code
   (let* [sim-vector (data-comparer-helper-3 lst-park input-park)
          highest (data-comparer-find-max sim-vector)]
     (data-comparer-helper-4 lst-park sim-vector highest)))

(def bot-name "Guidia")

(def greetings
  "It contains a vector of greetings that are printed in the beginning of the chat."
  [(format "Hello, my name is %s, your guide to Prague parks." bot-name)
  "Tell me what you would like to see or like to do in Prague."
  "I can suggest a park in Prague for you."])

(defn interface [lst-park]
  "It contains a procedural structure of a chatbot interface.
  It prints out greetings and (TODO: more contents)."
  (loop [grts greetings]
    (when-not (empty? grts)
      (doseq [timer "..."]
        (print timer)
        (Thread/sleep 1000))
      (println (first grts))
      (recur (rest grts))))

  (data-comparer-main lst-park (read-line)))

(defn -main [& args]
  "It allows user to run the chatbot on command 'lein run'."
  (println "Bye!"))
