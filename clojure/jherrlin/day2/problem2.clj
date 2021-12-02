(ns day-2-problem-2
  (:require [day-2-problem-1 :as problem1]))

(defn calculate-instructions
  "Calculate the instructions."
  [instructions]
  (loop [x                       0
         y                       0
         aim                     0
         [[course steps] & rest] instructions]
    (if (nil? course)
      (* x y)
      (recur
       (if (#{:forward} course)
         (+ x steps)
         x)
       (if (#{:forward} course)
         (+ y (* aim steps))
         y)
       (case course
         :up   (- aim steps)
         :down (+ aim steps)
         aim)
       rest))))

;; Test input
(-> problem1/test-input problem1/parse-input calculate-instructions)
;; => 900

;; Real input
(-> (slurp "input.txt") problem1/parse-input calculate-instructions)
;; => 1997106066
