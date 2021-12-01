(ns day-1-problem-2
  (:require [clojure.string :as str]
            [day-1-problem-1 :as day1]))

(def test-input
"199
200
208
210
200
207
240
269
260
263")


(defn str->int [s]
  (Integer. s))

(def input (slurp "input1.txt"))

(defn input->measurements [input]
  (->> input
       (str/split-lines)
       (map str->int)))

(defn window-and-sum [idx measurements]
  (->> (drop idx measurements)
       (take 3)
       (apply +)))

(defn count-measurements [measurements]
  (let [indexed (map-indexed vector measurements)
        mapped  (into {} indexed)]
    (->> indexed
         (map (fn [[idx measure]]
                (when (contains? mapped (+ idx 2))
                  (window-and-sum idx measurements))))
         (remove nil?)
         (day1/count-measurements))))

(-> test-input input->measurements count-measurements)
;; => 5
(-> input input->measurements count-measurements)
;; => 1724
