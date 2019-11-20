(ns ica.core)

(use 'opennlp.nlp)
(use 'opennlp.treebank)
(use 'src.ica.parkData)
(def get-sentences (make-sentence-detector "models/en-sent.bin"))
(def tokenize (make-tokenizer "models/en-token.bin"))
(def detokenize (make-detokenizer "models/english-detokenizer.xml"))
(def pos-tag (make-pos-tagger "models/en-pos-maxent.bin"))
(def chunker (make-treebank-chunker "models/en-chunker.bin"))

<<<<<<< HEAD
(def quit-words
  "It slurps a list of words from 'quit-words.txt' that are used to quit chatbot's main loop."
  (clojure.string/split-lines (slurp (clojure.java.io/resource "ica/quit-words.txt"))))
=======
(use 'ica.suggest)

(def quit-words
  "It slurps a list of words from 'recog_phrases.json' that are used to quit chatbot's main loop."
  (get (first (cheshire/parsed-seq (io/reader "src/ica/recog_phrases.json") true)) :quitwords))
>>>>>>> 441eb35... replaces the quitwords text file with a row in recog json and adds suggest.clj to 'recognize' significant words from user inputs

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

; to-do: main with (recognize) fn
(defn -main [& args]
  "It allows user to run the chatbot on command 'lein run'."
  (loop []
    (when (not (word-exists? quit-words (clojure.string/join " " (ask "What's on your mind?"))))
      (recur)))
  (println "Bye!"))