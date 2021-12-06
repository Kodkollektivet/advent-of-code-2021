(ns day-4-problem-1-test
  (:require [clojure.test :as t]
            [day-4-problem-1 :as problem-1]))


(t/deftest parse-input
  (t/testing "Test input"
    (t/is
     (= (problem-1/parse-input (slurp "test-input.txt"))
        {:bingo-numbers
         '(7 4 9 5 11 17 23 2 0 14 21 24 10 16 13 6 15 25 12 22 18 20 8 19 3 26 1),
         :number-of-boards 3,
         :bingo-boards
         {0
          [[{:number 22, :marked false, :x 0, :y 0, :position [0 0]}
            {:number 13, :marked false, :x 1, :y 0, :position [1 0]}
            {:number 17, :marked false, :x 2, :y 0, :position [2 0]}
            {:number 11, :marked false, :x 3, :y 0, :position [3 0]}
            {:number 0, :marked false, :x 4, :y 0, :position [4 0]}]
           [{:number 8, :marked false, :x 0, :y 1, :position [0 1]}
            {:number 2, :marked false, :x 1, :y 1, :position [1 1]}
            {:number 23, :marked false, :x 2, :y 1, :position [2 1]}
            {:number 4, :marked false, :x 3, :y 1, :position [3 1]}
            {:number 24, :marked false, :x 4, :y 1, :position [4 1]}]
           [{:number 21, :marked false, :x 0, :y 2, :position [0 2]}
            {:number 9, :marked false, :x 1, :y 2, :position [1 2]}
            {:number 14, :marked false, :x 2, :y 2, :position [2 2]}
            {:number 16, :marked false, :x 3, :y 2, :position [3 2]}
            {:number 7, :marked false, :x 4, :y 2, :position [4 2]}]
           [{:number 6, :marked false, :x 0, :y 3, :position [0 3]}
            {:number 10, :marked false, :x 1, :y 3, :position [1 3]}
            {:number 3, :marked false, :x 2, :y 3, :position [2 3]}
            {:number 18, :marked false, :x 3, :y 3, :position [3 3]}
            {:number 5, :marked false, :x 4, :y 3, :position [4 3]}]
           [{:number 1, :marked false, :x 0, :y 4, :position [0 4]}
            {:number 12, :marked false, :x 1, :y 4, :position [1 4]}
            {:number 20, :marked false, :x 2, :y 4, :position [2 4]}
            {:number 15, :marked false, :x 3, :y 4, :position [3 4]}
            {:number 19, :marked false, :x 4, :y 4, :position [4 4]}]],
          1
          [[{:number 3, :marked false, :x 0, :y 0, :position [0 0]}
            {:number 15, :marked false, :x 1, :y 0, :position [1 0]}
            {:number 0, :marked false, :x 2, :y 0, :position [2 0]}
            {:number 2, :marked false, :x 3, :y 0, :position [3 0]}
            {:number 22, :marked false, :x 4, :y 0, :position [4 0]}]
           [{:number 9, :marked false, :x 0, :y 1, :position [0 1]}
            {:number 18, :marked false, :x 1, :y 1, :position [1 1]}
            {:number 13, :marked false, :x 2, :y 1, :position [2 1]}
            {:number 17, :marked false, :x 3, :y 1, :position [3 1]}
            {:number 5, :marked false, :x 4, :y 1, :position [4 1]}]
           [{:number 19, :marked false, :x 0, :y 2, :position [0 2]}
            {:number 8, :marked false, :x 1, :y 2, :position [1 2]}
            {:number 7, :marked false, :x 2, :y 2, :position [2 2]}
            {:number 25, :marked false, :x 3, :y 2, :position [3 2]}
            {:number 23, :marked false, :x 4, :y 2, :position [4 2]}]
           [{:number 20, :marked false, :x 0, :y 3, :position [0 3]}
            {:number 11, :marked false, :x 1, :y 3, :position [1 3]}
            {:number 10, :marked false, :x 2, :y 3, :position [2 3]}
            {:number 24, :marked false, :x 3, :y 3, :position [3 3]}
            {:number 4, :marked false, :x 4, :y 3, :position [4 3]}]
           [{:number 14, :marked false, :x 0, :y 4, :position [0 4]}
            {:number 21, :marked false, :x 1, :y 4, :position [1 4]}
            {:number 16, :marked false, :x 2, :y 4, :position [2 4]}
            {:number 12, :marked false, :x 3, :y 4, :position [3 4]}
            {:number 6, :marked false, :x 4, :y 4, :position [4 4]}]],
          2
          [[{:number 14, :marked false, :x 0, :y 0, :position [0 0]}
            {:number 21, :marked false, :x 1, :y 0, :position [1 0]}
            {:number 17, :marked false, :x 2, :y 0, :position [2 0]}
            {:number 24, :marked false, :x 3, :y 0, :position [3 0]}
            {:number 4, :marked false, :x 4, :y 0, :position [4 0]}]
           [{:number 10, :marked false, :x 0, :y 1, :position [0 1]}
            {:number 16, :marked false, :x 1, :y 1, :position [1 1]}
            {:number 15, :marked false, :x 2, :y 1, :position [2 1]}
            {:number 9, :marked false, :x 3, :y 1, :position [3 1]}
            {:number 19, :marked false, :x 4, :y 1, :position [4 1]}]
           [{:number 18, :marked false, :x 0, :y 2, :position [0 2]}
            {:number 8, :marked false, :x 1, :y 2, :position [1 2]}
            {:number 23, :marked false, :x 2, :y 2, :position [2 2]}
            {:number 26, :marked false, :x 3, :y 2, :position [3 2]}
            {:number 20, :marked false, :x 4, :y 2, :position [4 2]}]
           [{:number 22, :marked false, :x 0, :y 3, :position [0 3]}
            {:number 11, :marked false, :x 1, :y 3, :position [1 3]}
            {:number 13, :marked false, :x 2, :y 3, :position [2 3]}
            {:number 6, :marked false, :x 3, :y 3, :position [3 3]}
            {:number 5, :marked false, :x 4, :y 3, :position [4 3]}]
           [{:number 2, :marked false, :x 0, :y 4, :position [0 4]}
            {:number 0, :marked false, :x 1, :y 4, :position [1 4]}
            {:number 12, :marked false, :x 2, :y 4, :position [2 4]}
            {:number 3, :marked false, :x 3, :y 4, :position [3 4]}
            {:number 7, :marked false, :x 4, :y 4, :position [4 4]}]]}}))))

(t/deftest mark-number-on-bingo-board
  (t/is
   (=
    (problem-1/mark-number-on-bingo-board
     [2 1]
     [[{:number 22, :marked false, :x 0, :y 0, :position [0 0]}
       {:number 13, :marked false, :x 1, :y 0, :position [1 0]}
       {:number 17, :marked false, :x 2, :y 0, :position [2 0]}
       {:number 11, :marked false, :x 3, :y 0, :position [3 0]}
       {:number 0, :marked false, :x 4, :y 0, :position [4 0]}]
      [{:number 8, :marked false, :x 0, :y 1, :position [0 1]}
       {:number 2, :marked false, :x 1, :y 1, :position [1 1]}
       {:number 23, :marked false, :x 2, :y 1, :position [2 1]}
       {:number 4, :marked false, :x 3, :y 1, :position [3 1]}
       {:number 24, :marked false, :x 4, :y 1, :position [4 1]}]
      [{:number 21, :marked false, :x 0, :y 2, :position [0 2]}
       {:number 9, :marked false, :x 1, :y 2, :position [1 2]}
       {:number 14, :marked false, :x 2, :y 2, :position [2 2]}
       {:number 16, :marked false, :x 3, :y 2, :position [3 2]}
       {:number 7, :marked false, :x 4, :y 2, :position [4 2]}]
      [{:number 6, :marked false, :x 0, :y 3, :position [0 3]}
       {:number 10, :marked false, :x 1, :y 3, :position [1 3]}
       {:number 3, :marked false, :x 2, :y 3, :position [2 3]}
       {:number 18, :marked false, :x 3, :y 3, :position [3 3]}
       {:number 5, :marked false, :x 4, :y 3, :position [4 3]}]
      [{:number 1, :marked false, :x 0, :y 4, :position [0 4]}
       {:number 12, :marked false, :x 1, :y 4, :position [1 4]}
       {:number 20, :marked false, :x 2, :y 4, :position [2 4]}
       {:number 15, :marked false, :x 3, :y 4, :position [3 4]}
       {:number 19, :marked false, :x 4, :y 4, :position [4 4]}]]
     )
    [[{:number 22, :marked false, :x 0, :y 0, :position [0 0]}
      {:number 13, :marked false, :x 1, :y 0, :position [1 0]}
      {:number 17, :marked false, :x 2, :y 0, :position [2 0]}
      {:number 11, :marked false, :x 3, :y 0, :position [3 0]}
      {:number 0, :marked false, :x 4, :y 0, :position [4 0]}]
     [{:number 8, :marked false, :x 0, :y 1, :position [0 1]}
      {:number 2, :marked false, :x 1, :y 1, :position [1 1]}
      {:number 23, :marked true, :x 2, :y 1, :position [2 1]}
      {:number 4, :marked false, :x 3, :y 1, :position [3 1]}
      {:number 24, :marked false, :x 4, :y 1, :position [4 1]}]
     [{:number 21, :marked false, :x 0, :y 2, :position [0 2]}
      {:number 9, :marked false, :x 1, :y 2, :position [1 2]}
      {:number 14, :marked false, :x 2, :y 2, :position [2 2]}
      {:number 16, :marked false, :x 3, :y 2, :position [3 2]}
      {:number 7, :marked false, :x 4, :y 2, :position [4 2]}]
     [{:number 6, :marked false, :x 0, :y 3, :position [0 3]}
      {:number 10, :marked false, :x 1, :y 3, :position [1 3]}
      {:number 3, :marked false, :x 2, :y 3, :position [2 3]}
      {:number 18, :marked false, :x 3, :y 3, :position [3 3]}
      {:number 5, :marked false, :x 4, :y 3, :position [4 3]}]
     [{:number 1, :marked false, :x 0, :y 4, :position [0 4]}
      {:number 12, :marked false, :x 1, :y 4, :position [1 4]}
      {:number 20, :marked false, :x 2, :y 4, :position [2 4]}
      {:number 15, :marked false, :x 3, :y 4, :position [3 4]}
      {:number 19, :marked false, :x 4, :y 4, :position [4 4]}]]
    )))

(t/deftest board-has-bingo?
  (t/testing "Board has bingo"
    (t/is
     (=
      (problem-1/board-has-bingo?
       [[{:number 22, :marked true, :x 0, :y 0, :position [0 0]}
         {:number 13, :marked true, :x 1, :y 0, :position [1 0]}
         {:number 17, :marked true, :x 2, :y 0, :position [2 0]}
         {:number 11, :marked true, :x 3, :y 0, :position [3 0]}
         {:number 0, :marked true, :x 4, :y 0, :position [4 0]}]
        [{:number 8, :marked true, :x 0, :y 1, :position [0 1]}
         {:number 2, :marked true, :x 1, :y 1, :position [1 1]}
         {:number 23, :marked true, :x 2, :y 1, :position [2 1]}
         {:number 4, :marked false, :x 3, :y 1, :position [3 1]}
         {:number 24, :marked false, :x 4, :y 1, :position [4 1]}]
        [{:number 21, :marked false, :x 0, :y 2, :position [0 2]}
         {:number 9, :marked false, :x 1, :y 2, :position [1 2]}
         {:number 14, :marked false, :x 2, :y 2, :position [2 2]}
         {:number 16, :marked false, :x 3, :y 2, :position [3 2]}
         {:number 7, :marked false, :x 4, :y 2, :position [4 2]}]
        [{:number 6, :marked false, :x 0, :y 3, :position [0 3]}
         {:number 10, :marked false, :x 1, :y 3, :position [1 3]}
         {:number 3, :marked false, :x 2, :y 3, :position [2 3]}
         {:number 18, :marked false, :x 3, :y 3, :position [3 3]}
         {:number 5, :marked true, :x 4, :y 3, :position [4 3]}]
        [{:number 1, :marked false, :x 0, :y 4, :position [0 4]}
         {:number 12, :marked false, :x 1, :y 4, :position [1 4]}
         {:number 20, :marked false, :x 2, :y 4, :position [2 4]}
         {:number 15, :marked true, :x 3, :y 4, :position [3 4]}
         {:number 19, :marked false, :x 4, :y 4, :position [4 4]}]]
       )
      true)))

  (t/testing "Board has not bingo"
    (t/is
     (=
      (problem-1/board-has-bingo?
       [[{:number 22, :marked false, :x 0, :y 0, :position [0 0]}
         {:number 13, :marked false, :x 1, :y 0, :position [1 0]}
         {:number 17, :marked false, :x 2, :y 0, :position [2 0]}
         {:number 11, :marked false, :x 3, :y 0, :position [3 0]}
         {:number 0, :marked false, :x 4, :y 0, :position [4 0]}]
        [{:number 8, :marked false, :x 0, :y 1, :position [0 1]}
         {:number 2, :marked false, :x 1, :y 1, :position [1 1]}
         {:number 23, :marked false, :x 2, :y 1, :position [2 1]}
         {:number 4, :marked false, :x 3, :y 1, :position [3 1]}
         {:number 24, :marked false, :x 4, :y 1, :position [4 1]}]
        [{:number 21, :marked false, :x 0, :y 2, :position [0 2]}
         {:number 9, :marked false, :x 1, :y 2, :position [1 2]}
         {:number 14, :marked false, :x 2, :y 2, :position [2 2]}
         {:number 16, :marked false, :x 3, :y 2, :position [3 2]}
         {:number 7, :marked false, :x 4, :y 2, :position [4 2]}]
        [{:number 6, :marked false, :x 0, :y 3, :position [0 3]}
         {:number 10, :marked false, :x 1, :y 3, :position [1 3]}
         {:number 3, :marked false, :x 2, :y 3, :position [2 3]}
         {:number 18, :marked false, :x 3, :y 3, :position [3 3]}
         {:number 5, :marked false, :x 4, :y 3, :position [4 3]}]
        [{:number 1, :marked false, :x 0, :y 4, :position [0 4]}
         {:number 12, :marked false, :x 1, :y 4, :position [1 4]}
         {:number 20, :marked false, :x 2, :y 4, :position [2 4]}
         {:number 15, :marked false, :x 3, :y 4, :position [3 4]}
         {:number 19, :marked false, :x 4, :y 4, :position [4 4]}]])
      nil)))
  )

(t/deftest board-has-number-in-position
  (t/is
   (=
    (problem-1/board-has-number-in-position
     21
     [[{:number 22, :marked false, :x 0, :y 0, :position [0 0]}
       {:number 13, :marked false, :x 1, :y 0, :position [1 0]}
       {:number 17, :marked false, :x 2, :y 0, :position [2 0]}
       {:number 11, :marked false, :x 3, :y 0, :position [3 0]}
       {:number 0, :marked false, :x 4, :y 0, :position [4 0]}]
      [{:number 8, :marked false, :x 0, :y 1, :position [0 1]}
       {:number 2, :marked false, :x 1, :y 1, :position [1 1]}
       {:number 23, :marked false, :x 2, :y 1, :position [2 1]}
       {:number 4, :marked false, :x 3, :y 1, :position [3 1]}
       {:number 24, :marked false, :x 4, :y 1, :position [4 1]}]
      [{:number 21, :marked false, :x 0, :y 2, :position [0 2]}
       {:number 9, :marked false, :x 1, :y 2, :position [1 2]}
       {:number 14, :marked false, :x 2, :y 2, :position [2 2]}
       {:number 16, :marked false, :x 3, :y 2, :position [3 2]}
       {:number 7, :marked false, :x 4, :y 2, :position [4 2]}]
      [{:number 6, :marked false, :x 0, :y 3, :position [0 3]}
       {:number 10, :marked false, :x 1, :y 3, :position [1 3]}
       {:number 3, :marked false, :x 2, :y 3, :position [2 3]}
       {:number 18, :marked false, :x 3, :y 3, :position [3 3]}
       {:number 5, :marked false, :x 4, :y 3, :position [4 3]}]
      [{:number 1, :marked false, :x 0, :y 4, :position [0 4]}
       {:number 12, :marked false, :x 1, :y 4, :position [1 4]}
       {:number 20, :marked false, :x 2, :y 4, :position [2 4]}
       {:number 15, :marked false, :x 3, :y 4, :position [3 4]}
       {:number 19, :marked false, :x 4, :y 4, :position [4 4]}]])
    [0 2]))

  (t/is
   (=
    (problem-1/board-has-number-in-position
     123
     [[{:number 22, :marked false, :x 0, :y 0, :position [0 0]}
       {:number 13, :marked true, :x 0, :y 1, :position [0 1]}
       {:number 17, :marked false, :x 0, :y 2, :position [0 2]}
       {:number 11, :marked false, :x 0, :y 3, :position [0 3]}
       {:number 0, :marked false, :x 0, :y 4, :position [0 4]}]
      [{:number 8, :marked false, :x 1, :y 0, :position [1 0]}
       {:number 2, :marked false, :x 1, :y 1, :position [1 1]}
       {:number 23, :marked true, :x 1, :y 2, :position [1 2]}
       {:number 4, :marked false, :x 1, :y 3, :position [1 3]}
       {:number 24, :marked false, :x 1, :y 4, :position [1 4]}]
      [{:number 21, :marked false, :x 2, :y 0, :position [2 0]}
       {:number 9, :marked false, :x 2, :y 1, :position [2 1]}
       {:number 14, :marked false, :x 2, :y 2, :position [2 2]}
       {:number 16, :marked false, :x 2, :y 3, :position [2 3]}
       {:number 7, :marked true, :x 2, :y 4, :position [2 4]}]
      [{:number 6, :marked true, :x 3, :y 0, :position [3 0]}
       {:number 10, :marked false, :x 3, :y 1, :position [3 1]}
       {:number 3, :marked false, :x 3, :y 2, :position [3 2]}
       {:number 18, :marked false, :x 3, :y 3, :position [3 3]}
       {:number 5, :marked false, :x 3, :y 4, :position [3 4]}]
      [{:number 1, :marked true, :x 4, :y 0, :position [4 0]}
       {:number 12, :marked false, :x 4, :y 1, :position [4 1]}
       {:number 20, :marked false, :x 4, :y 2, :position [4 2]}
       {:number 15, :marked false, :x 4, :y 3, :position [4 3]}
       {:number 19, :marked false, :x 4, :y 4, :position [4 4]}]])
    nil))
  )
