(ns ica.core)

(use 'clojure.pprint) 
(use 'opennlp.nlp)
(use 'opennlp.treebank) 
(def get-sentences (make-sentence-detector "models/en-sent.bin"))
(def tokenize (make-tokenizer "models/en-token.bin"))
(def detokenize (make-detokenizer "models/english-detokenizer.xml"))
(def pos-tag (make-pos-tagger "models/en-pos-maxent.bin"))
(def chunker (make-treebank-chunker "models/en-chunker.bin"))

(defn ask-tokenize [question]
  (println question)
  (tokenize (read-line)))

(defn -main[& args]
  (ask-tokenize "How've you been?"))