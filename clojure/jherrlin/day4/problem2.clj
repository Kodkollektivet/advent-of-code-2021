(ns day-4-problem-2
  (:require [day-4-problem-1 :as problem-1]))

(defn calc-score [input]
  (let [{:keys [bingo-numbers bingo-boards]} input]
    (loop [[current-number & rest] bingo-numbers
           last-board-id           nil
           prev-number             nil
           boards                  bingo-boards]
      (let [boards-with-bingo      (->> boards
                                        (map (fn [[idx board]]
                                               {:board-id idx
                                                :bingo?   (problem-1/board-has-bingo? board)})))
            all-boards-have-bingo? (fn [boards] (every? :bingo? boards))
            boards-without-bingo   (->> boards-with-bingo (filter (comp nil? :bingo?)))]

        (if (and last-board-id (all-boards-have-bingo? boards-with-bingo))
          (->> (get boards last-board-id)
               (apply concat)
               (filter (comp false? :marked))
               (map :number)
               (apply +)
               (* prev-number))
          (recur
           rest
           (if (and (nil? last-board-id) (->> boards-without-bingo
                                              (count)
                                              (= 1)))
             (->> boards-without-bingo
                  first
                  :board-id)
             last-board-id)
           current-number
           (->> boards
                (map (fn [[idx board]]
                       {idx (if-let [[x y] (problem-1/board-has-number-in-position current-number board)]
                              (problem-1/mark-number-on-bingo-board [x y] board)
                              board)}))
                (into {}))))))))

(calc-score (problem-1/parse-input (slurp "test-input.txt")))
;; => 1924


(calc-score (problem-1/parse-input (slurp "input.txt")))
;; => 12833
