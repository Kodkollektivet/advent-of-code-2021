(ns day-2-problem-1
  (:require [clojure.string :as str]))

(def test-input
  "forward 5
down 5
forward 8
up 3
down 8
forward 2")

(defn str->int
  "Parse string to int."
  [s]
  (Integer. s))

(defn parse-input
  "Parse input to collection of `[:forward 5]`, or return `nil`."
  [s]
  (some->> s
           (str/split-lines)
           (map #(str/split % #"\s"))
           (map (fn [[course steps]]
                  [(keyword course) (str->int steps)]))))

(defn calculate-instructions
  "Calculate the instructions."
  [instructions]
  (loop [x                       0
         y                       0
         [[course steps] & rest] instructions]
    (if (nil? course)
      (* x y)
      (recur
       (if (#{:forward} course)
         (+ x steps)
         x)
       (case course
           :up      (- y steps)
           :down    (+ y steps)
           y)
       rest))))

;; Test input
(calculate-instructions (parse-input test-input))
;; => 150

;; Real input
(->> (slurp "input.txt") (parse-input) (calculate-instructions))
;; => 1936494
