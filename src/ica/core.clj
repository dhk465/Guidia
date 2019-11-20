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

(def quit-words
  "It slurps a list of words from 'quit_words.txt' that are used to quit chatbot's main loop."
  (string/split-lines (slurp (io/reader "src/ica/quit_words.txt"))))

(def recogs
  "It parses the strings from reg_phrases.json into a hashmap that is used to recognize certain keywords from the chat."
  (first (cheshire/parsed-seq (io/reader "src/ica/recog_phrases.json") true)))

(defn ask [question]
  "It takes a string of question to show the user so that it further takes a user input.
  It returns the user input as a lazy-seq of tokenized words/strings."
  (println question)
  (tokenize (read-line)))

(defn word-exists? [quit-words word]
  "It takes a word from the user and a list of quit words
  and iterates through are used to quit from the chatbot"
  (loop [lst quit-words]
    (when (seq lst)
      (if (= (first lst) word)
        true
        (recur (rest lst))))))

; (defn sentence-compare [input-sent stored-sent]
;   (when (< i (count [1 2 3]))
;       (println (get [1 2 3] i))(recur (+ i 1))))

(defn -main [& args]
  "It allows user to run the chatbot on command 'lein run'."
  (loop []
    (when (not (word-exists? quit-words (string/join " " (ask "What's on your mind?"))))
      (recur)))
  (println "Bye!"))
