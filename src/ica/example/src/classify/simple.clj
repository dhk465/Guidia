(ns classify.simple
 (:require
  [clojure.java.io :as io]
  [cortex.util :as util]
  [cortex.nn.execute :as execute]
  [mikera.image.core :as i]
  [mikera.image.filters :as filters]
  [think.image.patch :as patch]))

(defn image-file->observation
  "It creates an observation from an input file."
  [image-path]
  { :labels ["test"]
    :data (patch/image->patch (-> (i/load-image image-path) ((filters/grayscale)) (i/resize 50 50))
      :datatype :float
      :colorspace :gray)})

(def categories
  "It creates a hashmap of indices with their labels e.g. 'linden' and 'spruce'."
  (zipmap (range) '("linden" "spruce")))

(defn guess
  "It predicts a category of an input image from a nippy model and previously defined categories."
  ([nippy image-path] (guess nippy image-path categories))
  ([nippy image-path mappings]
  (let [obs (image-file->observation image-path) ]
  (-> (execute/run nippy [obs])
   first
   :labels
   util/max-index
   mappings))))

(def nippy
  "It reads and memorize the contents of the trained network nippy model."
  (util/read-nippy-file "trained-network.nippy"))
