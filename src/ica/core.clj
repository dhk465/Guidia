(ns ica.core
  "the main namespace to prepare the chatbot"
  (:gen-class)
  (:require [clojure.java.io :as io]
            [cheshire.core :as cheshire :refer :all]
            [ica.simple :as simple])
  (:use [ica.opennlp :only (tokenize)]
        [ica.Data]
        [ica.comparer]
        [inflections.core])	
  (:import [ica.Data Park Tree]))

(def bot-name "Guidia")

(def greetings-park
  "It contains a vector of greetings for parks
  that are printed in the beginning of the chat."
  [(format
    "%s=> Now I am your guide to Prague parks."
    bot-name
    bot-name)
  (format
    "%s=> Tell me what you would like to see or like to do in Prague."
    bot-name)
  (format
    "%s=> I can suggest a park in Prague for you."
    bot-name)
  (format
    "%s=> Type 'forget' if you want to start over again."
    bot-name)])

(def greetings-tree
  "It contains a vector of greetings for trees
  that are printed in the beginning of the chat."
  [(format
    "%s=> Now I am your guide to Prague's trees."
    bot-name
    bot-name)
  (format
    "%s=> Describe the tree you saw."
    bot-name)
  (format
    "%s=> I will try to name it."
    bot-name)
  (format
    "%s=> Type 'picture' and I will say what it is."
    bot-name)
  (format
    "%s=> Type 'forget' if you want to start over again."
    bot-name)])

(def greetings-main
  "It contains a vector of initial greetings 
  that are printed in the beginning of the chat."
    [(format 
      "%s=> Hello, my name is %s, your guide to Prague."
      bot-name
      bot-name)
    (format 
      "%s=> Type 'park' if you want a guide to Prague's parks."
      bot-name)
 
    (format 
      "%s=> Or type 'tree' if you want a guide to Prague's trees."
      bot-name)
    (format 
      "%s=> Or type 'quit' if you want to say goodbye."
      bot-name)])

(def quitwords
  "It slurps a list of words from 'recog_phrases.json'
  that are used to quit chatbot's main loop."
  (get
    (first
      (cheshire/parsed-seq
        (io/reader "src/ica/recog_phrases.json")
        true))
    :quitwords))

(defn word-exists? [quit-words sentence]
  "It takes a word or a sentence from the user and a list of quit words
  and iterates through them to find if they match
  in order to quit from the chatbot."
  (loop [lst quit-words]
    (when-not (empty? lst)
      (if 
        (some 
          #(when (= (first lst) %) %)
          (map clojure.string/lower-case (tokenize sentence)))
        true
        (recur (rest lst))))))

(defn print-names [comparer-result user-record]
  "It takes in a vector of parks and prints it in a sentence."
  (if (or (= 0 (count comparer-result))
          (and (= user-record user-tree) (> (count comparer-result) 1)))
    (println (format "%s=> Sorry. Nothing seems to match your descriptions."
                     bot-name))
    (if (= 1 (count comparer-result))
      (do
        (print (format "%s=> I would say " bot-name))
        (print (:name (first comparer-result)))
        (println "."))
      (do
        (print (format "%s=> I would say " bot-name))
        (loop [comparer-res comparer-result]
          (when-not (empty? comparer-res)
            (if (empty? (rest comparer-res))
              (do
              (print "and ")
              (print (:name (first comparer-res)))
              (println "."))
              (do
                (print (:name (first comparer-res)))
                (print ", ")))
            (recur (rest comparer-res))))))))

(defn greet [greetings]
  "It contains a procedural structure of a chatbot interface.
  It prints out greetings and (TODO: more contents)."
  (loop [grts greetings]
    (when-not (empty? grts)
      (doseq [timer (range (count greetings))]
        (Thread/sleep 100)) ;; Change back to 500 for release
      (println (first grts))
      (recur (rest grts)))))

(defn interface [user-input lst-data user-record]
  "It cascades other functions, get-userrecord and data-comparer-main,
  and returns results of the park search in a single command.
  In doing so, it takes a string of the user input."
  (try
    (get-userrecord user-input user-record)
    (catch Exception e
      (println (format "%s=> I don't get what you mean." bot-name))))
  (let* [matches (data-comparer-main lst-data user-record)]
    (print-names matches user-record)))

(defn guide-main []
  "It loops the chatbot interface until a quitword is given.
  While it loops, it collects keywords from the user
  to find the user's preferences in parks.
  If the user says 'forget' instead,
  it resets the userpark to empty its data."
  (greet greetings-park)
  (print "User=> ")
  (loop [user-input (do (flush) (read-line))]
    (when-not (word-exists? quitwords user-input)
      (if (= (clojure.string/lower-case user-input) "forget")
        (do
          (reset-userrecord user-park)
          (println
            (format
              "%s=> Now tell me about your new park."
              bot-name))
          (print "User=> "))
        (do
          (interface user-input lst-park user-park)
          (println
            (format
              "%s=> If you want something more specific,"
              bot-name)
            "tell me more what you wish.")
          (print "User=> ")))
      (recur (do (flush) (read-line))))))

(defn ask-for-pic []
  ""
  (println "Write the path to your picture or drag and drop your picture:")
  (try
    (println (simple/guess simple/nippy (do (flush) 
             (clojure.string/trim (clojure.string/replace (read-line) "'" "")))))
    (catch Exception e (println "404: a non-existing file."))))

(defn tree-main []
  ""
  (greet greetings-tree)
  (print "User=> ")
  (loop [user-input (do (flush) (read-line))]
    (when-not (word-exists? quitwords user-input)
     (if (= (clojure.string/lower-case user-input) "forget")
      (do
          (reset-userrecord user-tree)
          (println
            (format
              "%s=> Now tell me about your new tree."
              bot-name))
          (print "User=> "))
     (do
       (if (= (clojure.string/lower-case user-input) "picture")
         (do
           (ask-for-pic)
           (print "User=> "))
         (do
          (interface user-input lst-tree user-tree)
          (println
            (format
              "%s=> If you want something more specific,"
              bot-name)
            "tell me more what you wish.")
          (print "User=> ")))))
     (recur (do (flush) (read-line))))))

(defn -main [& args]
  "It allows user to run the chatbot on command 'lein run'."
  (greet greetings-main)
  (print "User=> ")
  (let* [user-input (clojure.string/lower-case (do (flush) (read-line)))]
    (when-not (word-exists? quitwords user-input)
      (if (= user-input "park") 
            (guide-main)
        (if (= user-input "tree")
              (tree-main)))))
  (println (format "%s=> Bye!" bot-name)))
