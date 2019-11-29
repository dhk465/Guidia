(ns ica.dataComparer
  "a namespace that consists of functions which compare one park record to another"
  (:gen-class)
  (:use [ica.parkData])
  (:import [ica.parkData Park]))

(defn data-comparer-helper-1 [position park-stored input-park]
  "It takes a data about park and compares to data inputed by a user for one parameter."
   (= ((nth (rest (keys Bertramka)) position) input-park)
    ((nth (rest (keys Bertramka)) position) park-stored)))

(defn data-comparer-helper-2 [park-stored input-park]
  "It takes in a list of parks, user inputted data and a parameter,
  adds the park to a locally stored vector if the chosen parameter in the park
 and user inputted data matches. Returns that vector of parks."
   (with-local-vars [counter 0]
    (loop [position 0]
      (when (<= position 7)
       (if  (= (data-comparer-helper-1 position park-stored input-park) true)
        (var-set counter (+ 1 @counter))
       )
       (recur (+ 1 position))
      )
    )
   (var-get counter)
   )
 )

(defn data-comparer-helper-3 [lst-park input-park]
  (with-local-vars [simularity-count-vector []]
   (loop [lst-park-loc lst-park]
    (when-not (empty? lst-park-loc)
     (var-set simularity-count-vector (conj @simularity-count-vector (data-comparer-helper-2 (first lst-park-loc) input-park)))
     (recur (rest lst-park-loc))
    )
   )
   (var-get simularity-count-vector)
  )
)

(defn data-comparer-main [lst-park input-park]
  (with-local-vars [highest-match-counter 0
                    matched-parks-vec [] ]
   (let [simularity-count-vector (data-comparer-helper-3 lst-park input-park)]
    (doseq [simularity-counter simularity-count-vector]
     (if (< highest-match-counter simularity-counter)
      (var-set highest-match-counter simularity-counter)
     )
    )
    (loop [position 0]
     (when (< position (count simularity-count-vector))
      (if (= (nth simularity-count-vector))
       ()
      )
      (recur (+ 1 position))
    )
   )
  )
)