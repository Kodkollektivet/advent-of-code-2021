(ns day-1-problem-1
  (:require [clojure.string :as str]))

;; the number of times a depth measurement increases

(defn str->int [s]
  (Integer. s))

(def test-input
  "199
200
208
210
200
207
240
269
260
263")

(defn count-measurements [measurements]
  (->> measurements
       (reduce (fn [aggregated current]
                 (let [previous (->> aggregated last last)]
                   (cond
                     (empty? aggregated)
                     (conj aggregated [:first current])

                     (< previous current)
                     (conj aggregated [:increased current])

                     :else
                     (conj aggregated [:decreased current]))))
               [])
       (filter (comp #{:increased} first))
       (count)))

;; test input
(->> test-input
     (str/split-lines)
     (map str->int)
     (count-measurements))
;; => 7

;; real input
(->> (slurp "input1.txt")
     (str/split-lines)
     (map str->int)
     (count-measurements))
;; => 1692
