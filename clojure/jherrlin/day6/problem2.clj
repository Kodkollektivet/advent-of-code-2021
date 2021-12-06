(ns day-6-problem-2
  (:require [clojure.string :as str]
            [day-6-problem-1 :as problem-1]))


(problem-1/count-lanternfish 256 (problem-1/parse-input (slurp "input.txt")))
;; => 1640526601595
