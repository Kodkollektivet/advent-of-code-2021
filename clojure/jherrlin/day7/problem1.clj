(ns day-7-problem-1
  (:require [clojure.string :as str]))

(defn str->int [s] (Integer. s))

(defn calculate-fuel-cost-to-position [to-position positions-to-calc]
  (reduce (fn [total [position how-many]]
            (+ total (-> (- position to-position)
                         (Math/abs)
                         (* how-many)))
            ) 0 positions-to-calc))

(defn calculate-all-fuel-costs [frequency-coll]
  (map (fn [[position _]]
         (calculate-fuel-cost-to-position position frequency-coll))
       frequency-coll))

;; test input
(->> (str/split (slurp "test-input.txt") #",")
     (map #(-> % str/trim str->int))
     (frequencies)
     (into [])
     (sort-by second #(compare %2 %1))
     (calculate-all-fuel-costs)
     (apply min))
;; => 37

;; real input
(->> (str/split (slurp "input.txt") #",")
     (map #(-> % str/trim str->int))
     (frequencies)
     (into [])
     (sort-by second #(compare %2 %1))
     (calculate-all-fuel-costs)
     (apply min))
;; => 349812
