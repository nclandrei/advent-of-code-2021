(ns adventofcode.day3
  (:require [clojure.java.io :as io]))

(defn transform-data []
  (with-open [rdr (io/reader (io/file "data/day-3.txt"))]
             (->> (line-seq rdr)
                  (mapv #(apply str %)))))

(defn parse-numbers [s]
  (map (fn [el] (map #(Integer/parseInt (str %)) el)) s))

(def col-cnt
  (count (first (parse-numbers (transform-data)))))

(def el-cnt
  (count (transform-data)))

(def init-data
  (take col-cnt (repeat 0)))

(defn sum-arrays [a1 a2]
  (if (empty? a1) a1
                  (conj (sum-arrays (rest a1) (rest a2)) (+ (first a1) (first a2)))))

(defn digits-count [d]
  (reduce #(sum-arrays %1 %2) init-data d))

(defn base-two-from-decimal [s]
  (->> s
       (reduce conj '())
       (map-indexed (fn [idx item] [item idx]))
       (reduce #(+ %1 (* (first %2) (Math/pow 2 (second %2)))) 0)))

(defn epsilon [d]
  (base-two-from-decimal (map #(if (>= % (/ el-cnt 2)) 1 0) d)))

(defn gamma [d]
  (base-two-from-decimal (map #(if (> % (/ el-cnt 2)) 0 1) d)))

(defn part-1 []
  (let [data (digits-count (parse-numbers (transform-data)))]
    (* (epsilon data) (gamma data))))

(part-1)
