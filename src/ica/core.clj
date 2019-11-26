(ns ica.core
  "the main namespace to prepare the chatbot"
  (:gen-class)
  (:require [clojure.string :as string]
            [clojure.java.io :as io]
            [cheshire.core :as cheshire :refer :all]))

(use 'opennlp.nlp)
(use 'opennlp.treebank)
(use 'ica.parkData)
(def get-sentences (make-sentence-detector "models/en-sent.bin"))
(def tokenize (make-tokenizer "models/en-token.bin"))
(def detokenize (make-detokenizer "models/english-detokenizer.xml"))
(def pos-tag (make-pos-tagger "models/en-pos-maxent.bin"))
(def chunker (make-treebank-chunker "models/en-chunker.bin"))

(use 'ica.suggest)

(def quit-words
  "It slurps a list of words from 'recog_phrases.json' that are used to quit chatbot's main loop."
  (get (first (cheshire/parsed-seq (io/reader "src/ica/recog_phrases.json") true)) :quitwords))

(defn ask [question]
  "It takes a string of question to show the user so that it further takes a user input.
  It returns the user input as a lazy-seq of tokenized words/strings."
  (println question)
  (tokenize (read-line)))

(defn word-exists? [quit-words word]
  "It takes a word from the user and a list of quit words
  and iterates through are used to quit from the chatbot."
  (loop [lst quit-words]
    (when (seq lst)
      (if (= (first lst) word)
        true
        (recur (rest lst))))))

(defn data-comparer-helper-1 [position park-stored input-park]
  "It takes a data about park and compares to data inputed by a user for one parameter."
   (= ((nth (rest (keys Bertramka )) position) input-park) 
    ((nth (rest (keys Bertramka )) position) park-stored)))

 (defn data-comparer-helper-2 [position input-lst-park input-park]
  "It takes in a list of parks, user inputted data and a parameter, 
  adds the park to a locally stored vector if the chosen parameter in the park 
 and user inputted data matches. Returns that vector of parks."
  (with-local-vars [lst-curr-park []]
    (loop [curr-parks input-lst-park]
      (when-not  (empty? curr-parks)
       (if  (= (data-comparer-helper-1 position (first curr-parks) input-park) true)
        (var-set lst-curr-park (conj @lst-curr-park (first curr-parks))))
       (recur (rest curr-parks))))
   (var-get lst-curr-park)))

 (defn data-comparer-main [lst-park input-park]
  "It takes a vector with the data about parks, a record that was created from a user input,
  it returns a vector of matched parks"
   (with-local-vars [loc-lst-park lst-park]
   (loop [position 0]
    (when (<= position 7 )
     (var-set loc-lst-park (data-comparer-helper-2 position @loc-lst-park input-park))
     (recur (+ position 1))))
   (var-get loc-lst-park)))

; to-do: main with (recognize) fn
(defn -main [& args]
  "It allows user to run the chatbot on command 'lein run'."
  (loop []
    (when (not (word-exists? quit-words (string/join " " (ask "What's on your mind?"))))
      (recur)))
  (println "Bye!"))