(ns ica.dataComparer
  "a namespace that consists of functions which compare one park record to another"
  (:gen-class)
  (:use [ica.parkData])
  (:import [ica.parkData Park]))

(defn data-comparer-helper-1 [position park-stored input-park]
  "It takes a data about park and compares to data inputed by a user for one parameter."
   (= ((nth (rest (keys Bertramka)) position) input-park)
    ((nth (rest (keys Bertramka)) position) park-stored)))

(defn data-comparer-helper-2 [position input-lst-park input-park]
  "It takes in a list of parks, user inputted data and a parameter,
  adds the park to a locally stored vector if the chosen parameter in the park
 and user inputted data matches. Returns that vector of parks."
  (with-local-vars [lst-curr-park []]
    (loop [curr-parks input-lst-park]
      (when-not  (empty? curr-parks)
       (if  (= (data-comparer-helper-1 position (first curr-parks) input-park) true)
        (var-set lst-curr-park (conj @lst-curr-park (first curr-parks))))
       (recur (rest curr-parks))))
   (var-get lst-curr-park)))

(defn data-comparer-main [lst-park input-park] ;; need fix, returns empty list if custom park is applied
  "It takes a vector with the data about parks and a record that was created from a user input.
  It returns a vector of matched parks."
  (with-local-vars [loc-lst-park lst-park]
   (loop [position 0]
    (when (<= position 7 )
     (var-set loc-lst-park (data-comparer-helper-2 position @loc-lst-park input-park))
     (recur (+ position 1))))
   (var-get loc-lst-park)))
