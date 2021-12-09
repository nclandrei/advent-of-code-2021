(ns adventofcode.day1
  (:require [clojure.java.io :as io]))

(defn measurements []
  (let [xs (with-open [rdr (io/reader (io/file "data/day-1.txt"))]
             (->> (line-seq rdr)
                  (mapv #(Long/parseLong %))))]
    xs))

(defn part-1 [m]
  (let [[x & xs] m]
    (if (empty? xs)
      0
      (+ (part-1 xs) (if (< x (first xs)) 1 0)))))

(defn part-2 [m]
  (let [[w x y z & _] m]
    (if (nil? z)
      0
      (+ (part-2 (rest m))
         (if (< (+ w x y) (+ x y z)) 1 0)))))

(part-1 (measurements))

(part-2 (measurements))
