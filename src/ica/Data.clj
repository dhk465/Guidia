(ns ica.Data
  "a namespace that contains information about Prague's parks"
  (:gen-class)
  (:require [clojure.java.io :as io]
            [cheshire.core :as cheshire :refer :all])
  (:use [inflections.core]
        [ica.opennlp :only (tokenize)]))

(defrecord Park
  [name dogs bicycle skating refreshment sportsfield playground parking])

(def lst-park
  "It creates a vector where the data about the parks will be appended."
  (vector))

(def user-park
  (Park. "user"
    (ref nil) (ref nil) (ref nil) (ref nil) (ref nil) (ref nil) (ref nil)))

(def Bertramka
  (Park. "Bertramka"
    (ref nil) (ref true) (ref false) (ref true) (ref false) (ref nil) (ref true)))

(def lst-park (conj lst-park Bertramka))

(def Frantiskanska-zahrada
  (Park. "Františkánská zahrada"
    (ref false) (ref true) (ref false) (ref true) (ref false) (ref true) (ref false)))

(def lst-park (conj lst-park Frantiskanska-zahrada))

(def Obora-hvezda
  (Park. "Obora Hvězda"
    (ref true) (ref true) (ref false) (ref true) (ref false) (ref true) (ref true)))

(def lst-park (conj lst-park Obora-hvezda))

(def Kampa
  (Park. "Kampa"
    (ref nil) (ref true) (ref true) (ref true) (ref false) (ref true) (ref false)))

(def lst-park (conj lst-park Kampa))

(def Kinskeho-zahrada
  (Park. "Kínského zahrada"
    (ref nil) (ref false) (ref false) (ref true) (ref false) (ref true) (ref false)))

(def lst-park (conj lst-park Kinskeho-zahrada))

(def Klamovka
  (Park. "Klamovka"
    (ref true) (ref false) (ref false) (ref true) (ref true) (ref true) (ref true)))

(def lst-park (conj lst-park Klamovka))

(def Ladronka
  (Park. "Ladronka"
    (ref true) (ref false) (ref true) (ref true) (ref false) (ref true) (ref true)))

(def lst-park (conj lst-park Ladronka))

(def Letna
  (Park. "Letná"
    (ref nil) (ref false) (ref true) (ref true) (ref false) (ref true) (ref true)))

(def lst-park (conj lst-park Letna))

(def Petrin
  (Park. "Petřín"
    (ref nil) (ref true) (ref true) (ref true) (ref false) (ref true) (ref true)))

(def lst-park (conj lst-park Petrin))

(def Riegrovy-sady
  (Park. "Riegrovy sady"
    (ref true) (ref true) (ref true) (ref true) (ref true) (ref true) (ref false)))

(def lst-park (conj lst-park Riegrovy-sady))

(def Stromovka
  (Park. "Stromovka"
    (ref true) (ref true) (ref true) (ref true) (ref false) (ref true) (ref true)))

(def lst-park (conj lst-park Stromovka))

(def Vysehrad
  (Park. "Vyšehrad"
    (ref nil) (ref true) (ref false) (ref true) (ref false) (ref true) (ref true)))

(def lst-park (conj lst-park Vysehrad))

(def Vojanovy-sady
  (Park. "Vojanovy sady"
    (ref nil) (ref nil) (ref nil) (ref nil) (ref nil) (ref nil) (ref nil)))

(def lst-park (conj lst-park Vojanovy-sady))

(def lst-tree 
"It creates a vector where the data about the trees will be appended."
 [])

(defrecord Tree [name leaves needles cones evergreen flowers])
(def user-tree (Tree. (ref nil) (ref nil) (ref nil) (ref nil) (ref nil) (ref nil)))
(def Spruce (Tree. "Spruce" (ref false) (ref true) (ref true) (ref true) (ref false)))
(def lst-tree (conj lst-tree Spruce))
(def Linden (Tree. "Linden" (ref true) (ref false) (ref false) (ref false) (ref true)))
(def lst-tree (conj lst-tree Linden))

(def recogs
  "It parses the strings from reg_phrases.json into a hashmap that is used to 
  recognize certain keywords from the chat."
  (first (cheshire/parsed-seq (io/reader "src/ica/recog_phrases.json") true)))

(defn recognize [sentence]
  "It reads a string and checks if there is anything in the string that matches
  the words in recog_phrases.json. It returns a list of hashmaps containing 
  keys and matched words."
  (for [phrase (tokenize sentence)
        key (keys recogs)
        :when (some #(when (= (singular phrase) %) %) (get recogs key))]
        (when-not (= key :negative)
          (hash-map key (singular phrase)))))

(defn recognize-neg [sentence]
  "It returns true if the input sentence contains a negative expression."
  (with-local-vars [stat false]
    (loop [phrases (tokenize sentence)]
      (when-not (empty? phrases)
        (if (some #(when (= (first phrases) %) %) (get recogs :negative))
          (var-set stat (not @stat))
          (recur (rest phrases)))))
    @stat))

(defn get-usermaps [sentence]
  "It locally defines a map of keywords to be either true or false from the
  input string of the user. It returns a list of maps."
  (with-local-vars [kkeys '()]
    (loop [mapper (recognize sentence)]
      (if (empty? mapper)
        (merge (map #(assoc {} (first %) (not (recognize-neg sentence))) 
        (var-get kkeys)))
        (when (not (recognize-neg sentence))
          (var-set kkeys (conj @kkeys (keys (first mapper))))
          (recur (rest mapper)))))))

(defn decide-record [user-record]
  (if (=  8 (count user-record))
   user-park
    (if (= 6 (count user-record))
     user-tree)))

(defn reset-userrecord [user-record]
  "It loops through all keys but the name of the user-park and sets them to 
  nil."
  (loop [kkeys (rest (keys user-record))]
    (when-not (empty? kkeys)
      (dosync (ref-set ((first kkeys) (decide-record user-record)) nil))
      (recur (rest kkeys)))))

(defn get-userpark [sentence]
  "It utilizes get-usermaps and sets values of user-park in regard to the 
  output of get-usermaps."
  (loop [lst-usermaps (get-usermaps sentence)]
    (when-not (empty? lst-usermaps)
      (dosync (ref-set ((first (keys (first lst-usermaps))) user-park) 
      (first (vals (first lst-usermaps)))))
      (recur (rest lst-usermaps)))))