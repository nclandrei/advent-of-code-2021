(ns adventofcode.day2
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn transform-data []
  (let [xs (with-open [rdr (io/reader (io/file "data/day-2.txt"))]
        (->> (line-seq rdr)
             (mapv #(str/split % #" "))
             ))]
    xs))

(defn transform-pair [l]
  (vector (keyword (first l)) (Integer/parseInt (second l))))

(defn update-positions [m el]
  (cond
    (= (first el) :forward) (assoc m :horizontal (+ (get m :horizontal) (second el)))
    (= (first el) :down) (assoc m :vertical (+ (get m :vertical) (second el)))
    (= (first el) :up) (assoc m :vertical (- (get m :vertical) (second el)))))

(defn final-position [l]
  (reduce #(update-positions %1 %2) {:horizontal 0 :vertical 0} l))

(defn part-1 []
  (let [data (final-position (map #(transform-pair %) (transform-data)))]
    (* (get data :horizontal) (get data :vertical))))