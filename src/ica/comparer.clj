(ns ica.comparer
"a namespace that contains functions 
that compare user data with pre-exsiting data"
(:gen-class))

(defn data-comparer-helper-bool-comparison [position park-stored input-park lst-data]
  "It takes in two park records, a position in a record as a number,
  returns a boolean value
  if the both values on the given position have the value of 'true'."
  (and
    (= true @((nth (rest (keys (first lst-data))) position) input-park))
    (= true @((nth (rest (keys (first lst-data))) position) park-stored))))

(defn data-comparer-helper-count-matches [park-stored input-park lst-data]
  "It takes in a list of parks, user inputted data and a parameter,
  adds the park to a locally stored vector if the chosen parameter in the park
  and user inputted data matches. Returns that vector of parks."
  (with-local-vars [counter 0]
    (doseq [position (range (- (count (first lst-data)) 1))]
      (if (data-comparer-helper-bool-comparison position park-stored input-park lst-data)
        (var-set counter (+ 1 @counter))))
    (var-get counter)))

(defn data-comparer-helper-similarity-vector [lst-data input-park]
  "It takes in a vector that contains records of all parks
  and the record that is created from user input.Outputs a similarity vector."
  (with-local-vars [sim-vector (vector)]
    (loop [lst-data-loc lst-data]
      (when-not (empty? lst-data-loc)
        (var-set sim-vector
          (conj @sim-vector 
                (data-comparer-helper-count-matches  (first lst-data-loc) input-park lst-data)))
        (recur (rest lst-data-loc))))
    (var-get sim-vector)))

(defn data-comparer-helper-get-max-simil-park [lst-data sim-vector highest]
  "It takes in a vector of all parks, a vector
  that contains similarity count  and the maximum from the vector,
  returns a vector that contain park records
  that have the maximum similarity count in similarity count vector."
  (with-local-vars [park-matches (vector)]
    (loop [position 0]
      (when (< position (count lst-data))
        (if (= (nth sim-vector position) highest)
          (var-set park-matches (conj @park-matches (nth lst-data position))))
        (recur (+ 1 position))))
    (var-get park-matches)))

(defn data-comparer-find-max [sim-vector]
  "It takes in a vector of numbers and returns the maximum."
  (with-local-vars [highest 0]
    (doseq [sim-counter sim-vector]
      (if (< @highest sim-counter)
        (var-set highest sim-counter)))
    (var-get highest)))

(defn data-comparer-main [lst-data input-park]
  "It takes in a vector of park records and a record
  that was created from a user input and
  returns a vector of the best matched parks."
   (let* [sim-vector (data-comparer-helper-similarity-vector lst-data input-park)
          highest (data-comparer-find-max sim-vector)]
      (data-comparer-helper-get-max-simil-park lst-data sim-vector highest)))
