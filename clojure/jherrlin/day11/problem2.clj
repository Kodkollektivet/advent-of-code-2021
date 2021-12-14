(ns day-11-problem-2
  (:require [day-11-problem-1 :as problem-1]))


(defn first-step-during-which-all-octopuses-flash
  [init-generation]
  (loop [gen     init-generation
         count   0]
    (if (->> gen (vals) (map :number) (every? #{0}))
      count
      (recur
       (->> gen
            (problem-1/increases-energy-level-of-all-octopuses)
            (problem-1/increases-energy-level-of-adjacent-octopuses)
            (problem-1/reset-levels-of-octopuses))
       (inc count)))))

(->> (slurp "test-input2.txt")
     (problem-1/parse-input)
     (first-step-during-which-all-octopuses-flash))
;; => 195


(->> (slurp "input.txt")
     (problem-1/parse-input)
     (first-step-during-which-all-octopuses-flash))
;; => 329
