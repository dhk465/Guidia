(ns ica.parkData
  "a namespace that contains information about Prague's parks"
  (:gen-class)
  (:require [clojure.java.io :as io]
            [cheshire.core :as cheshire :refer :all])
  (:use [inflections.core]
        [ica.opennlp :only (tokenize)]))

(defrecord Park [name dogs bicycle skating refreshment sportsfield playground parking])
(def lst-park
 "It creates a vector where the data about the parks will be appended."
 [])

(def user-park (Park. "user" (ref nil) (ref nil) (ref nil) (ref nil) (ref nil) (ref nil) (ref nil)))

;;                       name     dogs cycl sktng refr sfied pgr prkg
(def Bertramka (Park. "Bertramka" (ref nil) (ref true) (ref false) (ref true) (ref false) (ref nil)  (ref true)))
(def lst-park (conj lst-park Bertramka ))

;;                                                 name    dogs cycl sktng refr sfied pgr prkg
(def Frantiskanska-zahrada (Park. "Františkánská zahrada" (ref false) (ref true) (ref false) (ref true) (ref false) (ref true) (ref false)))
(def lst-park (conj lst-park Frantiskanska-zahrada))
;;                              name    dogs cycl  sktng  refr sfied pgr   prkg
(def Obora-hvezda (Park. "Obora Hvězda" (ref true) (ref true)  (ref false)  (ref true) (ref false) (ref true) (ref true)))
(def lst-park (conj lst-park Obora-hvezda))
;;                 name   dogs cycl sktng refr sfied pgr   prkg
(def Kampa (Park. "Kampa" (ref nil) (ref true)  (ref true)  (ref true) (ref false) (ref true)  (ref false)))
(def lst-park (conj lst-park Kampa))
;;                                       name   dogs cycl sktng refr spfld pgr   prkg
(def Kinskeho-zahrada (Park. "Kínského zahrada" (ref nil) (ref false) (ref false) (ref true) (ref false) (ref true) (ref false)))
(def lst-park (conj lst-park Kinskeho-zahrada))
;;                       name   dogs cycl  sktng  refr sfied pgr prkg
(def Klamovka (Park. "Klamovka" (ref true) (ref false) (ref false) (ref true) (ref false) (ref true) (ref true)))
(def lst-park (conj lst-park Klamovka))
;;                         name   dogs cycl sktng  refr sfied pgr  prkg
(def Landronka (Park. "Landronka" (ref true) (ref false) (ref true)  (ref true) (ref false) (ref true) (ref true)))
(def lst-park (conj lst-park Landronka))
;;                  name dogs  cycl sktng refr sfied pgr prkg
(def Letna (Park. "Letná" (ref nil) (ref false) (ref true) (ref true) (ref false) (ref true) (ref true)))
(def lst-park (conj lst-park Letna))
;;                  name    dogs cycl sktng refr sfied pgr  prkg
(def Petrin (Park. "Petřín" (ref nil)  (ref true)  (ref true) (ref true) (ref false) (ref true)  (ref true)))
(def lst-park (conj lst-park Petrin))
;;                                  name    dogs cycl sktng refr sfied pgr   prkg
(def Riegrovy-sady (Park. "Riegrovy sady" (ref true)  (ref true)  (ref true) (ref true)  (ref true) (ref true)  (ref false)))
(def lst-park (conj lst-park Riegrovy-sady))
;;                        name    dogs cycl sktng refr  sfied pgr  prkg
(def Stromovka (Park. "Stromovka" (ref true)  (ref true)  (ref true) (ref true) (ref false) (ref true) (ref true)))
(def lst-park (conj lst-park Stromovka))
;;                      name    dogs cycl sktng refr sfied pgr prkg
(def Vysehrad (Park. "Vyšehrad" (ref nil) (ref true) (ref false) (ref true) (ref false) (ref true) (ref true)))
(def lst-park (conj lst-park Vysehrad))
;;                                name    dogs cycl sktng refr sfied pgr  prkg
(def Vojanovy-sady (Park. "Vojanovy sady" (ref nil)  (ref nil)  (ref nil)   (ref nil)  (ref nil)   (ref nil)  (ref nil)))
(def lst-park (conj lst-park Vojanovy-sady))

(def recogs
  "It parses the strings from reg_phrases.json into a hashmap that is used to recognize certain keywords from the chat."
  (first (cheshire/parsed-seq (io/reader "src/ica/recog_phrases.json") true)))

(defn recognize [sentence]
  "It reads a string and checks if there is anything in the string that matches
  the words in recog_phrases.json. It returns a list of hashmaps containing keys and matched words."
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

; MVP limitations: negatives do not register as false meaning that users can't make a negative wish
;                  phrases containing "or" are both considered as "and"

(defn get-usermaps [sentence]
  (with-local-vars [klice '()]
    (loop [mapper (recognize sentence)]
      (if (empty? mapper)
        (merge (map #(assoc {} (first %) (not (recognize-neg sentence))) (var-get klice)))
        (when (not (recognize-neg sentence))
          (var-set klice (conj @klice (keys (first mapper))))
          (recur (rest mapper)))))))

; negation not

(defn get-userpark [sentence]
  (loop [lst-usermaps (get-usermaps sentence)]
    (when-not (empty? lst-usermaps)
      (dosync (ref-set ((first (keys (first lst-usermaps))) user-park) (first (vals (first lst-usermaps)))))
      (recur (rest lst-usermaps)))))
