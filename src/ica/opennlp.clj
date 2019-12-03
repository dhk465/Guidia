(ns ica.opennlp
  "a namespace that contains definitions premade for natural language processing"
  (:gen-class))

(use 'opennlp.nlp)
(use 'opennlp.treebank)
(def get-sentences (make-sentence-detector "models/en-sent.bin"))
(def tokenize (make-tokenizer "models/en-token.bin"))
(def detokenize (make-detokenizer "models/english-detokenizer.xml"))
(def pos-tag (make-pos-tagger "models/en-pos-maxent.bin"))
(def chunker (make-treebank-chunker "models/en-chunker.bin"))
