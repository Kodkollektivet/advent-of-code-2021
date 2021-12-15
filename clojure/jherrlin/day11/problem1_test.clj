(ns day-6-problem-1-test
  (:require [clojure.test :as t]
            [day-11-problem-1 :as problem-1]))


(->>
 "6594254334
3856965822
6375667284
7252447257
7468496589
5278635756
3287952832
7993992245
5957959665
6394862637"
     (problem-1/parse-input)
     (problem-1/debug-print )

     ;; ((fn [x] (println "Inc all") x))
     ;; (problem-1/increases-energy-level-of-all-octopuses)
     ;; (problem-1/debug-print true)

     ;; ((fn [x] (println "Inc adjacent") x))
     ;; (problem-1/increases-energy-level-of-adjacent-octopuses-2)
     ;; (problem-1/debug-print true)

     ;; ((fn [x] (println "Reset") x))
     ;; (problem-1/reset-levels-of-octopuses)
     ;; (problem-1/debug-print true)


     ((fn [x] (println "Inc all") x))
     (problem-1/increases-energy-level-of-all-octopuses)
     (problem-1/debug-print)

     ((fn [x] (println "Inc adjacent") x))
     (problem-1/increases-energy-level-of-adjacent-octopuses-2)
     (problem-1/debug-print)

     ((fn [x] (println "Inc adjacent") x))
     (problem-1/increases-energy-level-of-adjacent-octopuses-2)
     (problem-1/debug-print)

     ((fn [x] (println "Inc adjacent") x))
     (problem-1/increases-energy-level-of-adjacent-octopuses-2)
     (problem-1/debug-print)

     ((fn [x] (println "Inc adjacent") x))
     (problem-1/increases-energy-level-of-adjacent-octopuses-2)
     (problem-1/debug-print)

     ((fn [x] (println "Inc adjacent") x))
     (problem-1/increases-energy-level-of-adjacent-octopuses-2)
     (problem-1/debug-print)

     ((fn [x] (println "Inc adjacent") x))
     (problem-1/increases-energy-level-of-adjacent-octopuses-2)
     (problem-1/debug-print)

     ((fn [x] (println "Inc adjacent") x))
     (problem-1/increases-energy-level-of-adjacent-octopuses-2)
     (problem-1/debug-print)

     ((fn [x] (println "Inc adjacent") x))
     (problem-1/increases-energy-level-of-adjacent-octopuses-2)
     (problem-1/debug-print)

     ((fn [x] (println "Reset") x))
     (problem-1/reset-levels-of-octopuses)
     (problem-1/debug-print true)


     ;; (problem-1/octopuses-to-inc-due-to-flashing-neighbour)
     ;; (count)
     #_(get-in [[1 1] :neighbours])
     #_(problem-1/octopuses-with-flashing-neighbour)
     )

(->> nil
     (filter true?)
     (take 1)
     (empty?))

(->> "6594254334
3856965822
6375667284
7252447257
7468496589
5278635756
3287952832
7993992245
5957959665
6394862637"
     (problem-1/parse-input)
     (problem-1/debug-print true)

     ((fn [x] (println "Inc all") x))
     (problem-1/increases-energy-level-of-all-octopuses)
     (problem-1/debug-print true)

     ((fn [x] (println "Inc adjacent") x))
     (problem-1/octopuses-with-flashing-neighbour)
     #_(problem-1/debug-print true)
     (take 1)
     (first)
     (second)
     :neighbours
     #_(second)
     )

(t/deftest abc
  (t/is
   (= (->> (slurp "test-input2.txt")
           (problem-1/parse-input)
           (problem-1/add-neighbours)
           (problem-1/increase-energy-levels)
           (problem-1/reset-levels-of-octopuses)
           (problem-1/debug-string))
      "6594254334
3856965822
6375667284
7252447257
7468496589
5278635756
3287952832
7993992245
5957959665
6394862637")))


(t/deftest parse-input
  (t/is
   (=
    (problem-1/parse-input "11111
19991
19191
19991
11111")
    {[4 3] {:number 1, :x 4, :y 3},
     [2 2] {:number 1, :x 2, :y 2},
     [0 0] {:number 1, :x 0, :y 0},
     [1 0] {:number 1, :x 1, :y 0},
     [2 3] {:number 9, :x 2, :y 3},
     [3 3] {:number 9, :x 3, :y 3},
     [1 1] {:number 9, :x 1, :y 1},
     [3 4] {:number 1, :x 3, :y 4},
     [4 2] {:number 1, :x 4, :y 2},
     [3 0] {:number 1, :x 3, :y 0},
     [4 1] {:number 1, :x 4, :y 1},
     [1 4] {:number 1, :x 1, :y 4},
     [1 3] {:number 9, :x 1, :y 3},
     [0 3] {:number 1, :x 0, :y 3},
     [2 4] {:number 1, :x 2, :y 4},
     [0 2] {:number 1, :x 0, :y 2},
     [2 0] {:number 1, :x 2, :y 0},
     [0 4] {:number 1, :x 0, :y 4},
     [3 1] {:number 9, :x 3, :y 1},
     [2 1] {:number 9, :x 2, :y 1},
     [4 4] {:number 1, :x 4, :y 4},
     [1 2] {:number 9, :x 1, :y 2},
     [3 2] {:number 9, :x 3, :y 2},
     [0 1] {:number 1, :x 0, :y 1},
     [4 0] {:number 1, :x 4, :y 0}})))

(t/deftest add-neighbours
  (t/is
   (=
    (->> (slurp "test-input.txt")
         (problem-1/parse-input)
         (problem-1/add-neighbours))
    {[4 3]
     {:number 1,
      :x 4,
      :y 3,
      :neighbours
      [{:x 3, :y 2} {:x 3, :y 3} {:x 3, :y 4} {:x 4, :y 2} {:x 4, :y 4}]},
     [2 2]
     {:number 1,
      :x 2,
      :y 2,
      :neighbours
      [{:x 1, :y 1}
       {:x 1, :y 2}
       {:x 1, :y 3}
       {:x 2, :y 1}
       {:x 2, :y 3}
       {:x 3, :y 1}
       {:x 3, :y 2}
       {:x 3, :y 3}]},
     [0 0]
     {:number 1, :x 0, :y 0, :neighbours [{:x 0, :y 1} {:x 1, :y 0} {:x 1, :y 1}]},
     [1 0]
     {:number 1,
      :x 1,
      :y 0,
      :neighbours
      [{:x 0, :y 0} {:x 0, :y 1} {:x 1, :y 1} {:x 2, :y 0} {:x 2, :y 1}]},
     [2 3]
     {:number 9,
      :x 2,
      :y 3,
      :neighbours
      [{:x 1, :y 2}
       {:x 1, :y 3}
       {:x 1, :y 4}
       {:x 2, :y 2}
       {:x 2, :y 4}
       {:x 3, :y 2}
       {:x 3, :y 3}
       {:x 3, :y 4}]},
     [3 3]
     {:number 9,
      :x 3,
      :y 3,
      :neighbours
      [{:x 2, :y 2}
       {:x 2, :y 3}
       {:x 2, :y 4}
       {:x 3, :y 2}
       {:x 3, :y 4}
       {:x 4, :y 2}
       {:x 4, :y 3}
       {:x 4, :y 4}]},
     [1 1]
     {:number 9,
      :x 1,
      :y 1,
      :neighbours
      [{:x 0, :y 0}
       {:x 0, :y 1}
       {:x 0, :y 2}
       {:x 1, :y 0}
       {:x 1, :y 2}
       {:x 2, :y 0}
       {:x 2, :y 1}
       {:x 2, :y 2}]},
     [3 4]
     {:number 1,
      :x 3,
      :y 4,
      :neighbours
      [{:x 2, :y 3} {:x 2, :y 4} {:x 3, :y 3} {:x 4, :y 3} {:x 4, :y 4}]},
     [4 2]
     {:number 1,
      :x 4,
      :y 2,
      :neighbours
      [{:x 3, :y 1} {:x 3, :y 2} {:x 3, :y 3} {:x 4, :y 1} {:x 4, :y 3}]},
     [3 0]
     {:number 1,
      :x 3,
      :y 0,
      :neighbours
      [{:x 2, :y 0} {:x 2, :y 1} {:x 3, :y 1} {:x 4, :y 0} {:x 4, :y 1}]},
     [4 1]
     {:number 1,
      :x 4,
      :y 1,
      :neighbours
      [{:x 3, :y 0} {:x 3, :y 1} {:x 3, :y 2} {:x 4, :y 0} {:x 4, :y 2}]},
     [1 4]
     {:number 1,
      :x 1,
      :y 4,
      :neighbours
      [{:x 0, :y 3} {:x 0, :y 4} {:x 1, :y 3} {:x 2, :y 3} {:x 2, :y 4}]},
     [1 3]
     {:number 9,
      :x 1,
      :y 3,
      :neighbours
      [{:x 0, :y 2}
       {:x 0, :y 3}
       {:x 0, :y 4}
       {:x 1, :y 2}
       {:x 1, :y 4}
       {:x 2, :y 2}
       {:x 2, :y 3}
       {:x 2, :y 4}]},
     [0 3]
     {:number 1,
      :x 0,
      :y 3,
      :neighbours
      [{:x 0, :y 2} {:x 0, :y 4} {:x 1, :y 2} {:x 1, :y 3} {:x 1, :y 4}]},
     [2 4]
     {:number 1,
      :x 2,
      :y 4,
      :neighbours
      [{:x 1, :y 3} {:x 1, :y 4} {:x 2, :y 3} {:x 3, :y 3} {:x 3, :y 4}]},
     [0 2]
     {:number 1,
      :x 0,
      :y 2,
      :neighbours
      [{:x 0, :y 1} {:x 0, :y 3} {:x 1, :y 1} {:x 1, :y 2} {:x 1, :y 3}]},
     [2 0]
     {:number 1,
      :x 2,
      :y 0,
      :neighbours
      [{:x 1, :y 0} {:x 1, :y 1} {:x 2, :y 1} {:x 3, :y 0} {:x 3, :y 1}]},
     [0 4]
     {:number 1, :x 0, :y 4, :neighbours [{:x 0, :y 3} {:x 1, :y 3} {:x 1, :y 4}]},
     [3 1]
     {:number 9,
      :x 3,
      :y 1,
      :neighbours
      [{:x 2, :y 0}
       {:x 2, :y 1}
       {:x 2, :y 2}
       {:x 3, :y 0}
       {:x 3, :y 2}
       {:x 4, :y 0}
       {:x 4, :y 1}
       {:x 4, :y 2}]},
     [2 1]
     {:number 9,
      :x 2,
      :y 1,
      :neighbours
      [{:x 1, :y 0}
       {:x 1, :y 1}
       {:x 1, :y 2}
       {:x 2, :y 0}
       {:x 2, :y 2}
       {:x 3, :y 0}
       {:x 3, :y 1}
       {:x 3, :y 2}]},
     [4 4]
     {:number 1, :x 4, :y 4, :neighbours [{:x 3, :y 3} {:x 3, :y 4} {:x 4, :y 3}]},
     [1 2]
     {:number 9,
      :x 1,
      :y 2,
      :neighbours
      [{:x 0, :y 1}
       {:x 0, :y 2}
       {:x 0, :y 3}
       {:x 1, :y 1}
       {:x 1, :y 3}
       {:x 2, :y 1}
       {:x 2, :y 2}
       {:x 2, :y 3}]},
     [3 2]
     {:number 9,
      :x 3,
      :y 2,
      :neighbours
      [{:x 2, :y 1}
       {:x 2, :y 2}
       {:x 2, :y 3}
       {:x 3, :y 1}
       {:x 3, :y 3}
       {:x 4, :y 1}
       {:x 4, :y 2}
       {:x 4, :y 3}]},
     [0 1]
     {:number 1,
      :x 0,
      :y 1,
      :neighbours
      [{:x 0, :y 0} {:x 0, :y 2} {:x 1, :y 0} {:x 1, :y 1} {:x 1, :y 2}]},
     [4 0]
     {:number 1, :x 4, :y 0, :neighbours [{:x 3, :y 0} {:x 3, :y 1} {:x 4, :y 1}]}})))


(t/deftest next-gen-with-flashes-count
  (t/is
   (=
    (->> (slurp "test-input.txt")
         (problem-1/parse-input)
         (problem-1/add-neighbours)
         (problem-1/next-gen-with-flashes-count)
         #_(problem-1/debug-print))
    {[4 3]
     {:number 4,
      :x 4,
      :y 3,
      :neighbours
      [{:x 3, :y 2} {:x 3, :y 3} {:x 3, :y 4} {:x 4, :y 2} {:x 4, :y 4}]},
     [2 2]
     {:number 10,
      :x 2,
      :y 2,
      :neighbours
      [{:x 1, :y 1}
       {:x 1, :y 2}
       {:x 1, :y 3}
       {:x 2, :y 1}
       {:x 2, :y 3}
       {:x 3, :y 1}
       {:x 3, :y 2}
       {:x 3, :y 3}]},
     [0 0]
     {:number 3, :x 0, :y 0, :neighbours [{:x 0, :y 1} {:x 1, :y 0} {:x 1, :y 1}]},
     [1 0]
     {:number 4,
      :x 1,
      :y 0,
      :neighbours
      [{:x 0, :y 0} {:x 0, :y 1} {:x 1, :y 1} {:x 2, :y 0} {:x 2, :y 1}]},
     [2 3]
     {:number 14,
      :x 2,
      :y 3,
      :neighbours
      [{:x 1, :y 2}
       {:x 1, :y 3}
       {:x 1, :y 4}
       {:x 2, :y 2}
       {:x 2, :y 4}
       {:x 3, :y 2}
       {:x 3, :y 3}
       {:x 3, :y 4}]},
     [3 3]
     {:number 12,
      :x 3,
      :y 3,
      :neighbours
      [{:x 2, :y 2}
       {:x 2, :y 3}
       {:x 2, :y 4}
       {:x 3, :y 2}
       {:x 3, :y 4}
       {:x 4, :y 2}
       {:x 4, :y 3}
       {:x 4, :y 4}]},
     [1 1]
     {:number 12,
      :x 1,
      :y 1,
      :neighbours
      [{:x 0, :y 0}
       {:x 0, :y 1}
       {:x 0, :y 2}
       {:x 1, :y 0}
       {:x 1, :y 2}
       {:x 2, :y 0}
       {:x 2, :y 1}
       {:x 2, :y 2}]},
     [3 4]
     {:number 4,
      :x 3,
      :y 4,
      :neighbours
      [{:x 2, :y 3} {:x 2, :y 4} {:x 3, :y 3} {:x 4, :y 3} {:x 4, :y 4}]},
     [4 2]
     {:number 5,
      :x 4,
      :y 2,
      :neighbours
      [{:x 3, :y 1} {:x 3, :y 2} {:x 3, :y 3} {:x 4, :y 1} {:x 4, :y 3}]},
     [3 0]
     {:number 4,
      :x 3,
      :y 0,
      :neighbours
      [{:x 2, :y 0} {:x 2, :y 1} {:x 3, :y 1} {:x 4, :y 0} {:x 4, :y 1}]},
     [4 1]
     {:number 4,
      :x 4,
      :y 1,
      :neighbours
      [{:x 3, :y 0} {:x 3, :y 1} {:x 3, :y 2} {:x 4, :y 0} {:x 4, :y 2}]},
     [1 4]
     {:number 4,
      :x 1,
      :y 4,
      :neighbours
      [{:x 0, :y 3} {:x 0, :y 4} {:x 1, :y 3} {:x 2, :y 3} {:x 2, :y 4}]},
     [1 3]
     {:number 12,
      :x 1,
      :y 3,
      :neighbours
      [{:x 0, :y 2}
       {:x 0, :y 3}
       {:x 0, :y 4}
       {:x 1, :y 2}
       {:x 1, :y 4}
       {:x 2, :y 2}
       {:x 2, :y 3}
       {:x 2, :y 4}]},
     [0 3]
     {:number 4,
      :x 0,
      :y 3,
      :neighbours
      [{:x 0, :y 2} {:x 0, :y 4} {:x 1, :y 2} {:x 1, :y 3} {:x 1, :y 4}]},
     [2 4]
     {:number 5,
      :x 2,
      :y 4,
      :neighbours
      [{:x 1, :y 3} {:x 1, :y 4} {:x 2, :y 3} {:x 3, :y 3} {:x 3, :y 4}]},
     [0 2]
     {:number 5,
      :x 0,
      :y 2,
      :neighbours
      [{:x 0, :y 1} {:x 0, :y 3} {:x 1, :y 1} {:x 1, :y 2} {:x 1, :y 3}]},
     [2 0]
     {:number 5,
      :x 2,
      :y 0,
      :neighbours
      [{:x 1, :y 0} {:x 1, :y 1} {:x 2, :y 1} {:x 3, :y 0} {:x 3, :y 1}]},
     [0 4]
     {:number 3, :x 0, :y 4, :neighbours [{:x 0, :y 3} {:x 1, :y 3} {:x 1, :y 4}]},
     [3 1]
     {:number 12,
      :x 3,
      :y 1,
      :neighbours
      [{:x 2, :y 0}
       {:x 2, :y 1}
       {:x 2, :y 2}
       {:x 3, :y 0}
       {:x 3, :y 2}
       {:x 4, :y 0}
       {:x 4, :y 1}
       {:x 4, :y 2}]},
     [2 1]
     {:number 14,
      :x 2,
      :y 1,
      :neighbours
      [{:x 1, :y 0}
       {:x 1, :y 1}
       {:x 1, :y 2}
       {:x 2, :y 0}
       {:x 2, :y 2}
       {:x 3, :y 0}
       {:x 3, :y 1}
       {:x 3, :y 2}]},
     [4 4]
     {:number 3, :x 4, :y 4, :neighbours [{:x 3, :y 3} {:x 3, :y 4} {:x 4, :y 3}]},
     [1 2]
     {:number 14,
      :x 1,
      :y 2,
      :neighbours
      [{:x 0, :y 1}
       {:x 0, :y 2}
       {:x 0, :y 3}
       {:x 1, :y 1}
       {:x 1, :y 3}
       {:x 2, :y 1}
       {:x 2, :y 2}
       {:x 2, :y 3}]},
     [3 2]
     {:number 14,
      :x 3,
      :y 2,
      :neighbours
      [{:x 2, :y 1}
       {:x 2, :y 2}
       {:x 2, :y 3}
       {:x 3, :y 1}
       {:x 3, :y 3}
       {:x 4, :y 1}
       {:x 4, :y 2}
       {:x 4, :y 3}]},
     [0 1]
     {:number 4,
      :x 0,
      :y 1,
      :neighbours
      [{:x 0, :y 0} {:x 0, :y 2} {:x 1, :y 0} {:x 1, :y 1} {:x 1, :y 2}]},
     [4 0]
     {:number 3, :x 4, :y 0, :neighbours [{:x 3, :y 0} {:x 3, :y 1} {:x 4, :y 1}]}})))

(t/deftest do-gen
  (t/is
   (=
    (problem-1/do-gen
     {:gen (->> (slurp "test-input.txt")
                (problem-1/parse-input)
                (problem-1/add-neighbours))
      :flashes-count-total 0})
    {:gen
     {[4 3]
      {:number 4,
       :x 4,
       :y 3,
       :neighbours
       [{:x 3, :y 2} {:x 3, :y 3} {:x 3, :y 4} {:x 4, :y 2} {:x 4, :y 4}]},
      [2 2]
      {:number 0,
       :x 2,
       :y 2,
       :neighbours
       [{:x 1, :y 1}
        {:x 1, :y 2}
        {:x 1, :y 3}
        {:x 2, :y 1}
        {:x 2, :y 3}
        {:x 3, :y 1}
        {:x 3, :y 2}
        {:x 3, :y 3}]},
      [0 0]
      {:number 3,
       :x 0,
       :y 0,
       :neighbours [{:x 0, :y 1} {:x 1, :y 0} {:x 1, :y 1}]},
      [1 0]
      {:number 4,
       :x 1,
       :y 0,
       :neighbours
       [{:x 0, :y 0} {:x 0, :y 1} {:x 1, :y 1} {:x 2, :y 0} {:x 2, :y 1}]},
      [2 3]
      {:number 0,
       :x 2,
       :y 3,
       :neighbours
       [{:x 1, :y 2}
        {:x 1, :y 3}
        {:x 1, :y 4}
        {:x 2, :y 2}
        {:x 2, :y 4}
        {:x 3, :y 2}
        {:x 3, :y 3}
        {:x 3, :y 4}]},
      [3 3]
      {:number 0,
       :x 3,
       :y 3,
       :neighbours
       [{:x 2, :y 2}
        {:x 2, :y 3}
        {:x 2, :y 4}
        {:x 3, :y 2}
        {:x 3, :y 4}
        {:x 4, :y 2}
        {:x 4, :y 3}
        {:x 4, :y 4}]},
      [1 1]
      {:number 0,
       :x 1,
       :y 1,
       :neighbours
       [{:x 0, :y 0}
        {:x 0, :y 1}
        {:x 0, :y 2}
        {:x 1, :y 0}
        {:x 1, :y 2}
        {:x 2, :y 0}
        {:x 2, :y 1}
        {:x 2, :y 2}]},
      [3 4]
      {:number 4,
       :x 3,
       :y 4,
       :neighbours
       [{:x 2, :y 3} {:x 2, :y 4} {:x 3, :y 3} {:x 4, :y 3} {:x 4, :y 4}]},
      [4 2]
      {:number 5,
       :x 4,
       :y 2,
       :neighbours
       [{:x 3, :y 1} {:x 3, :y 2} {:x 3, :y 3} {:x 4, :y 1} {:x 4, :y 3}]},
      [3 0]
      {:number 4,
       :x 3,
       :y 0,
       :neighbours
       [{:x 2, :y 0} {:x 2, :y 1} {:x 3, :y 1} {:x 4, :y 0} {:x 4, :y 1}]},
      [4 1]
      {:number 4,
       :x 4,
       :y 1,
       :neighbours
       [{:x 3, :y 0} {:x 3, :y 1} {:x 3, :y 2} {:x 4, :y 0} {:x 4, :y 2}]},
      [1 4]
      {:number 4,
       :x 1,
       :y 4,
       :neighbours
       [{:x 0, :y 3} {:x 0, :y 4} {:x 1, :y 3} {:x 2, :y 3} {:x 2, :y 4}]},
      [1 3]
      {:number 0,
       :x 1,
       :y 3,
       :neighbours
       [{:x 0, :y 2}
        {:x 0, :y 3}
        {:x 0, :y 4}
        {:x 1, :y 2}
        {:x 1, :y 4}
        {:x 2, :y 2}
        {:x 2, :y 3}
        {:x 2, :y 4}]},
      [0 3]
      {:number 4,
       :x 0,
       :y 3,
       :neighbours
       [{:x 0, :y 2} {:x 0, :y 4} {:x 1, :y 2} {:x 1, :y 3} {:x 1, :y 4}]},
      [2 4]
      {:number 5,
       :x 2,
       :y 4,
       :neighbours
       [{:x 1, :y 3} {:x 1, :y 4} {:x 2, :y 3} {:x 3, :y 3} {:x 3, :y 4}]},
      [0 2]
      {:number 5,
       :x 0,
       :y 2,
       :neighbours
       [{:x 0, :y 1} {:x 0, :y 3} {:x 1, :y 1} {:x 1, :y 2} {:x 1, :y 3}]},
      [2 0]
      {:number 5,
       :x 2,
       :y 0,
       :neighbours
       [{:x 1, :y 0} {:x 1, :y 1} {:x 2, :y 1} {:x 3, :y 0} {:x 3, :y 1}]},
      [0 4]
      {:number 3,
       :x 0,
       :y 4,
       :neighbours [{:x 0, :y 3} {:x 1, :y 3} {:x 1, :y 4}]},
      [3 1]
      {:number 0,
       :x 3,
       :y 1,
       :neighbours
       [{:x 2, :y 0}
        {:x 2, :y 1}
        {:x 2, :y 2}
        {:x 3, :y 0}
        {:x 3, :y 2}
        {:x 4, :y 0}
        {:x 4, :y 1}
        {:x 4, :y 2}]},
      [2 1]
      {:number 0,
       :x 2,
       :y 1,
       :neighbours
       [{:x 1, :y 0}
        {:x 1, :y 1}
        {:x 1, :y 2}
        {:x 2, :y 0}
        {:x 2, :y 2}
        {:x 3, :y 0}
        {:x 3, :y 1}
        {:x 3, :y 2}]},
      [4 4]
      {:number 3,
       :x 4,
       :y 4,
       :neighbours [{:x 3, :y 3} {:x 3, :y 4} {:x 4, :y 3}]},
      [1 2]
      {:number 0,
       :x 1,
       :y 2,
       :neighbours
       [{:x 0, :y 1}
        {:x 0, :y 2}
        {:x 0, :y 3}
        {:x 1, :y 1}
        {:x 1, :y 3}
        {:x 2, :y 1}
        {:x 2, :y 2}
        {:x 2, :y 3}]},
      [3 2]
      {:number 0,
       :x 3,
       :y 2,
       :neighbours
       [{:x 2, :y 1}
        {:x 2, :y 2}
        {:x 2, :y 3}
        {:x 3, :y 1}
        {:x 3, :y 3}
        {:x 4, :y 1}
        {:x 4, :y 2}
        {:x 4, :y 3}]},
      [0 1]
      {:number 4,
       :x 0,
       :y 1,
       :neighbours
       [{:x 0, :y 0} {:x 0, :y 2} {:x 1, :y 0} {:x 1, :y 1} {:x 1, :y 2}]},
      [4 0]
      {:number 3,
       :x 4,
       :y 0,
       :neighbours [{:x 3, :y 0} {:x 3, :y 1} {:x 4, :y 1}]}},
     :flashes-count-total 9}
    ))
  )
