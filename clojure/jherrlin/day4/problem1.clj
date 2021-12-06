(ns day-4-problem-1
  (:require [clojure.string :as str]))


(defn str->int
  "Convert string to int."
  [s]
  (Integer. s))

(defn item-placeholder-fns [n]
  (for [y (range n)
        x (range n)]
    (fn [number]
      {:number   number
       :marked   false
       :x        x
       :y        y
       :position [x y]})))

(defn parse-input
  "Parse input"
  [input]
  (let [board-xy-lenght    5
        [numbers & boards] (str/split-lines input)
        boards             (->> boards
                                (remove str/blank?)
                                (map #(-> % str/trim (str/split #"\s+")))
                                (mapv (fn [row] (mapv (fn [item] (str->int item)) row)))
                                (partition board-xy-lenght)
                                (map (fn [board]
                                       (->> board
                                            (apply concat)
                                            (map vector (item-placeholder-fns board-xy-lenght))
                                            (mapv (fn [[f number]] (f number)))
                                            (partition board-xy-lenght)
                                            (mapv #(into [] %)))))
                                (map-indexed vector)
                                (into {}))]
    {:bingo-numbers    (->> (str/split numbers #",")
                            (map str->int))
     :number-of-boards (count boards)
     :bingo-boards     boards}))

(defn mark-number-on-bingo-board
  "Mark number on board."
  [[x y] board]
  (assoc-in board [y x :marked] true))

(defn board-has-number-in-position [number board]
  (->> board
       (apply concat)
       (filter (comp #{number} :number))
       (first)
       :position))

(defn board-has-bingo? [board]
  (let [flatten-board (apply concat board)]
    (->> (map vector
              (cycle (range 5))
              (cycle [:x :y]))
         (take 10)
         (map (fn [[position axis]]
                (->> flatten-board
                     (filter (comp #{position} axis))
                     (every? :marked))))
         (some true?))))

(defn calc-score [input]
  (let [{:keys [bingo-numbers bingo-boards]} input]
    (loop [[current-number & rest] bingo-numbers
           prev-number             nil
           boards                  bingo-boards]
      (let [boards-with-bingo (->> boards
                                   (map (fn [[idx board]]
                                          {:board-id idx
                                           :bingo?   (board-has-bingo? board)})))]
        (if-let [board-id-with-bingo (->> boards-with-bingo
                                          (filter (comp true? :bingo?))
                                          (first)
                                          :board-id)]
          (->> (get boards board-id-with-bingo)
               (apply concat)
               (filter (comp false? :marked))
               (map :number)
               (apply +)
               (* prev-number))
          (recur
           rest
           current-number
           (->> boards
                (map (fn [[idx board]]
                       {idx (if-let [[x y] (board-has-number-in-position current-number board)]
                              (mark-number-on-bingo-board [x y] board)
                              board)}))
                (into {}))))))))

(calc-score (parse-input (slurp "test-input.txt")))
;; => 4512

(calc-score (parse-input (slurp "input.txt")))
;; => 64084
