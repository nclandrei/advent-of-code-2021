(ns adventofcode.day1
  (:require [clojure.java.io :as io]))

(defn measurements []
  (let [xs (with-open [rdr (io/reader (io/file "data/day-1.txt"))]
             (->> (line-seq rdr)
                  (mapv #(Long/parseLong %))))]
    xs))

(defn number-of-increases [m]
  (let [[x & xs] m]
    (if (empty? xs)
      0
      (+ (number-of-increases xs) (if (< x (first xs)) 1 0)))))

(number-of-increases (measurements))
