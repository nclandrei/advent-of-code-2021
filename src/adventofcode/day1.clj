(ns adventofcode.day1
  (:require [clojure.java.io :as io]))

(defn part-1 []
  (let [xs (with-open [rdr (io/reader (io/file "data/day-1.txt"))]
    (->> (line-seq rdr)
      (mapv #(Long/parseLong %))
         (println)))]))

(part-1)

(comment
  (part-1)
  )
