(ns ica.core)

(use 'clojure.pprint)
(use 'opennlp.nlp)
(use 'opennlp.treebank)
(def get-sentences (make-sentence-detector "models/en-sent.bin"))
(def tokenize (make-tokenizer "models/en-token.bin"))
(def detokenize (make-detokenizer "models/english-detokenizer.xml"))
(def pos-tag (make-pos-tagger "models/en-pos-maxent.bin"))
(def name-find (make-name-finder "models/namefind/en-ner-person.bin"))
(def chunker (make-treebank-chunker "models/en-chunker.bin"))

(defn ask [question]
  "Takes a string of question to show the user so that it further takes a user input. Returns the user input as a string."
  (println question)
  (read-line))

(defn -main [& args]
  "Allows user to run the chatbot on command 'lein run'"
  (ask "What's on your mind?"))
