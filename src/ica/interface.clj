(ns ica.interface)

(def bot-name "Guidia")

(def greetings
  "It contains a vector of greetings that are printed in the beginning of the chat."
  [(format "Hello, my name is %s, your guide to Prague parks." bot-name)
  "Tell me what you would like to see or like to do in Prague."
  "I can suggest a park in Prague for you."])

(loop [grts greetings]
  (when-not (empty? grts)
    (for [timer "..."]
      (print timer)
      (Thread/sleep 1000))
    (println (first grts))
    (recur (rest grts))))
