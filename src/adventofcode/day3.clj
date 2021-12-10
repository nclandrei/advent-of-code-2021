(ns adventofcode.day3
  (:require [clojure.java.io :as io]))

(apply max (vals (get {
  0 { 0 2 1 4}
 1 { 0 2 1 4}
 2 { 0 2 1 4}
 } 0)))

(def init
  (map #({% { 0 0 1 0}}) (range 0 12)))

(defn transform-data []
  (with-open [rdr (io/reader (io/file "data/day-3.txt"))]
             (->> (line-seq rdr)
                  (mapv #(apply str %)))))

(defn base-two-from-decimal [s]
  (->> s
       (map #(Integer/parseInt (str %)))
       (map-indexed (fn [idx item] [item idx]))
       (reduce #(+ %1 (* 2 (Math/pow (first %2) (second %2)))) 0)))


(transform-data)