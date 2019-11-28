(ns ica.interface
  "a namespace that prepares an interface for the chatbot"
  (:gen-class)
  (:use [ica.core]
        [ica.suggest]))

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
      (for [timer "..."]
        (print timer)
        (Thread/sleep 1000))
      (println (first grts))
      (recur (rest grts)))
  
  (data-comparer-main lst-park (read-line)))
