(ns day-5-problem-2-test
  (:require [clojure.test :as t]
            [day-5-problem-2 :as problem-2]))


(t/deftest north-east?
  (t/is (problem-2/north-east? {:x1 4 :y1 4 :x2 9 :y2 1})))

(t/deftest south-east?
  (t/is (problem-2/south-east? {:x1 4 :y1 4 :x2 9 :y2 9})))

(t/deftest south-west?
  (t/is (problem-2/south-west? {:x1 4 :y1 4 :x2 0 :y2 9})))

(t/deftest north-west?
  (t/is (problem-2/north-west? {:x1 4 :y1 4 :x2 0 :y2 0})))

(t/deftest to-north-east
  (t/is
   (= (problem-2/to-north-east {:x1 4 :y1 4 :x2 8 :y2 0})
      '({:x 4, :y 4} {:x 5, :y 3} {:x 6, :y 2} {:x 7, :y 1} {:x 8, :y 0}))))

(t/deftest to-south-east
  (t/is
   (= (problem-2/to-south-east {:x1 4 :y1 4 :x2 8 :y2 8})
      '({:x 4, :y 4} {:x 5, :y 5} {:x 6, :y 6} {:x 7, :y 7} {:x 8, :y 8}))))

(t/deftest to-south-west
  (t/is
   (= (problem-2/to-south-west {:x1 4 :y1 4 :x2 0 :y2 8})
      '({:x 4, :y 4} {:x 3, :y 5} {:x 2, :y 6} {:x 1, :y 7} {:x 0, :y 8}))))

(t/deftest to-north-west
  (t/is
   (= (problem-2/to-north-west {:x1 4 :y1 4 :x2 0 :y2 0})
      '({:x 4, :y 4} {:x 3, :y 3} {:x 2, :y 2} {:x 1, :y 1} {:x 0, :y 0}))))
