(ns day-6-problem-1-test
  (:require [clojure.test :as t]
            [clojure.string :as str]
            [day-6-problem-1 :as problem-1]
            [clojure.set :as set]))


(=
 (problem-1/next-generation
  {0 {:idx 0, :amount 0},
   1 {:idx 1, :amount 1},
   2 {:idx 2, :amount 1},
   3 {:idx 3, :amount 2},
   4 {:idx 4, :amount 1},
   5 {:idx 5, :amount 0},
   6 {:idx 6, :amount 0},
   7 {:idx 7, :amount 0},
   8 {:idx 8, :amount 0}})
 {0 {:idx 0, :amount 1},
  1 {:idx 1, :amount 1},
  2 {:idx 2, :amount 2},
  3 {:idx 3, :amount 1},
  4 {:idx 4, :amount 0},
  5 {:idx 5, :amount 0},
  6 {:idx 6, :amount 0},
  7 {:idx 7, :amount 0},
  8 {:idx 8, :amount 0}})

(=
 (problem-1/next-generation
  {0 {:idx 0, :amount 1},
   1 {:idx 1, :amount 0},
   2 {:idx 2, :amount 0},
   3 {:idx 3, :amount 0},
   4 {:idx 4, :amount 1},
   5 {:idx 5, :amount 1},
   6 {:idx 6, :amount 3},
   7 {:idx 7, :amount 1},
   8 {:idx 8, :amount 2}})
 {0 {:idx 0, :amount 0},
  1 {:idx 1, :amount 0},
  2 {:idx 2, :amount 0},
  3 {:idx 3, :amount 1},
  4 {:idx 4, :amount 1},
  5 {:idx 5, :amount 3},
  6 {:idx 6, :amount 2},
  7 {:idx 7, :amount 2},
  8 {:idx 8, :amount 1}})
