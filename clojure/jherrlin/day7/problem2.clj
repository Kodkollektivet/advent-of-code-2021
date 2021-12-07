(ns day-7-problem-2
  (:require [clojure.string :as str]))


(defn str->int [s] (Integer. s))

(defn iter-range [n]
  (take n (iterate inc 1)))

(defn calculate-fuel-cost-to-position [to-position positions-to-calc]
  (reduce (fn [total [position how-many]]
            (+ total (let [number-of-positions (- position to-position)]
                       (if (= number-of-positions 0)
                         0
                         (->> (iter-range (Math/abs number-of-positions))
                              (apply +)
                              (* how-many)))))
            ) 0 positions-to-calc))

(defn calculate-all-fuel-costs [frequency-coll]
  (pmap (fn [[position _]]
          (calculate-fuel-cost-to-position position frequency-coll))
        frequency-coll))

(defn add-positions-to-check [frequencies']
  (merge
   (->> frequencies' keys (apply max) inc range
        (reduce (fn [m n] (assoc m n 0)) {}))
   frequencies'))


;; test input
(->> (str/split (slurp "test-input.txt") #",")
     (map #(-> % str/trim str->int))
     (frequencies)
     (add-positions-to-check)
     (into [])
     (sort-by second #(compare %2 %1))
     (calculate-all-fuel-costs)
     (apply min))
;; => 168

;; real input
(->> (str/split (slurp "input.txt") #",")
     (map #(-> % str/trim str->int))
     (frequencies)
     (add-positions-to-check)
     (into [])
     (sort-by second #(compare %2 %1))
     (calculate-all-fuel-costs)
     (apply min))
;; 99763899
