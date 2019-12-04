(ns ica.opennlp
  "a namespace that contains definitions premade for natural language processing"
  (:gen-class))

(use 'opennlp.nlp)
(use 'opennlp.treebank)
(def tokenize (make-tokenizer "models/en-token.bin"))
