(ns day-3-problem-1
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(def test-input
  "00100
11110
10110
10111
10101
01111
00111
11100
10000
11001
00010
01010")

(defn str->int
  "Parse string to Integer."
  [s]
  (Integer. s))

(defn parse-input
  "Parse string input into diagnostic vector.
  `[[0 0 1 0 0]]`."
  [input]
  (->> input
       (str/split-lines)
       (map #(str/split % #""))
       (mapv (fn [row] (mapv #(str->int %) row)))))

(defn count-columns
  "Count the columns in diagnostic vector."
  [diagnostic-vector]
  (-> diagnostic-vector first count))

(defn get-column
  "Get column in `diagnostic-vector` from `idx`."
  [idx diagnostic-vector]
  (mapv #(nth % idx) diagnostic-vector))

(defn column-is-gamma-or-epsilon
  "Find out if column is `:gamma` or `:epsilon`.
  In:  `[0 0 1 0 0]`
  Out: `([:gamma 7] [:epsilon 5])`"
  [column]
  (let [gamma-epsilon-frequencies (frequencies column)]
    (->> (set/rename-keys gamma-epsilon-frequencies
                          {0 :epsilon
                           1 :gamma})
         (into [])
         (sort-by second #(compare %2 %1)))))

(defn rotate-vector
  "Rotate matrix.
  In:  `[[1 2 3] [3 4 5] [6 7 8]]`
  Out: `[[1 3 6] [2 4 7] [3 5 8]]`"
  [diagnostic-vector]
  (apply map vector diagnostic-vector))

(defn gamma-and-epsilons-for-diagnostic-vector
  "Calculate the `:gamma` or `:epsilon` for the `diagnostic-vector`.
  In:  `[[0 0 1 0 0]
         [0 1 0 1 0]]`
  Out: `(:gamma :epsilon :gamma :gamma :epsilon)`"
  [diagnostic-vector]
  (->> diagnostic-vector
       (rotate-vector)
       (map (fn [row]
              (->> row
                   (column-is-gamma-or-epsilon)
                   (ffirst))))))

(->> test-input (parse-input)
     gamma-and-epsilons-for-diagnostic-vector)

(defn gamma-and-epsilons-coll-to-gamma-binary-coll
  "Calculate a binary collection from a `:gamma`/`:epsilon` collection.
  In:  `(:gamma :epsilon :gamma :gamma :epsilon)`
  Out: `(1 0 1 1 0)`"
  [gamma-and-epsilons-coll]
  (->> gamma-and-epsilons-coll
       (map #(if (= :gamma %) 1 0))))

(defn gamma-and-epsilons-coll-to-epsilon-binary-coll
  "Calculate a binary collection from a `:gamma`/`:epsilon` collection.
  In:  `(:gamma :epsilon :gamma :gamma :epsilon)`
  Out: `(0 1 0 0 1)`"
  [gamma-and-epsilons-coll]
  (->> gamma-and-epsilons-coll
       (map #(if (= :epsilon %) 1 0))))

(defn binary-coll-to-decimal
  "Decimal number from a binary collection.
  In:  `(0 1 0 0 1)`
  Out: `9`"
  [binary-coll]
  (Integer/parseInt (str/join "" binary-coll) 2))

;; test input
(let [parsed-input (->> test-input (parse-input))]
  (*
   (->> (gamma-and-epsilons-for-diagnostic-vector parsed-input)
        (gamma-and-epsilons-coll-to-epsilon-binary-coll)
        (binary-coll-to-decimal))
   (->> (gamma-and-epsilons-for-diagnostic-vector parsed-input)
        (gamma-and-epsilons-coll-to-gamma-binary-coll)
        (binary-coll-to-decimal))))
;; => 198

;; real input
(let [parsed-input (->> (slurp "./input.txt") (parse-input))]
  (*
   (->> (gamma-and-epsilons-for-diagnostic-vector parsed-input)
        (gamma-and-epsilons-coll-to-epsilon-binary-coll)
        (binary-coll-to-decimal))
   (->> (gamma-and-epsilons-for-diagnostic-vector parsed-input)
        (gamma-and-epsilons-coll-to-gamma-binary-coll)
        (binary-coll-to-decimal))))
;; => 3895776
