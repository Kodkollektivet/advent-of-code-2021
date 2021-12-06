(ns day-6-problem-1
  (:require [clojure.string :as str]))


(defn str->int [s] (Integer. s))

(defn parse-input [input]
  (let [init                 (reduce (fn [m n] (assoc m n 0)) {} (range 9))
        lanternfish-in-state (->> (str/split input #",")
                                  (map #(-> % str/trim str->int))
                                  (frequencies))]
    (->> (merge init lanternfish-in-state)
         (reduce (fn [m [k v]] (assoc m k {:idx k :amount v})) {}))))

(defn next-generation [current-generation]
  (let [available-states '(7 6 5 4 3 2 1 0 :last)]
    (reduce (fn [m state]
              (if (= :last state)
                (-> m
                    (assoc 8 {:idx 8 :amount (get-in current-generation [0 :amount])})
                    (update-in [6 :amount] (fn [current-6s] (+ current-6s (get-in current-generation [0 :amount])))))
                (assoc m state {:idx state :amount (get-in current-generation [(inc state) :amount])})))
            {} available-states)))

(defn count-lanternfish [number-of-generations init-generation]
  (->> (loop [counter            0
              current-generation init-generation]
         (if (= counter number-of-generations)
           current-generation
           (recur
            (inc counter)
            (next-generation current-generation))))
       (vals)
       (map :amount)
       (apply +)))

;; test input
(count-lanternfish 18 (parse-input (slurp "test-input.txt"))) ;; => 26
(count-lanternfish 80 (parse-input (slurp "test-input.txt"))) ;; => 5934

;; real input
(count-lanternfish 80 (parse-input (slurp "input.txt")))      ;; => 362666
