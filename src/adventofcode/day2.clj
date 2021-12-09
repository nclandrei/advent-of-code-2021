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

(defn update-positions-1 [m el]
  (cond
    (= (first el) :forward) (assoc m :horizontal (+ (get m :horizontal) (second el)))
    (= (first el) :down) (assoc m :vertical (+ (get m :vertical) (second el)))
    (= (first el) :up) (assoc m :vertical (- (get m :vertical) (second el)))))

(defn update-positions-2 [m el]
  (cond
    (= (first el) :forward) (assoc m :horizontal (+ (get m :horizontal) (second el))
                                     :vertical (+ (get m :vertical) (* (get m :aim) (second el))))
    (= (first el) :down) (assoc m :aim (+ (get m :aim) (second el)))
    (= (first el) :up) (assoc m :aim (- (get m :aim) (second el)))))

(defn final-position [l v]
  (let [fun (if (= v 1) update-positions-1 update-positions-2)]
    (reduce #(fun %1 %2) {:horizontal 0 :vertical 0 :aim 0} l)))

(defn part-1 []
  (let [data (final-position (map #(transform-pair %) (transform-data)) 1)]
    (* (get data :horizontal) (get data :vertical))))

(defn part-2 []
  (let [data (final-position (map #(transform-pair %) (transform-data)) 2)]
    (* (get data :horizontal) (get data :vertical))))
