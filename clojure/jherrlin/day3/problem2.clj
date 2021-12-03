(ns day-3-problem-2
  (:require [day-3-problem-1 :as problem1]))

(defn determine-value-to-save [pred-fn frequencies']
  (let [zeroes (get frequencies' 0 0)
        ones   (get frequencies' 1 0)]
    (pred-fn zeroes ones)))

(defn calculate-most-commons [pred-fn parsed-data]
  (->> parsed-data
       problem1/rotate-vector
       (map (juxt frequencies identity))
       (map-indexed vector)
       (map (fn [[idx [freq coll]]]
              {:column-id   idx
               :frequencies freq
               :coll        coll
               :value-to-save (determine-value-to-save pred-fn freq)}))))

(defn determine-oxygen-generator-rating [select-value-to-save-fn diagnostic-vector]
  (loop [parsed' diagnostic-vector
         counter 0]
    (if (= 1 (count parsed'))
      parsed'
      (let [commons (->> parsed'
                         (calculate-most-commons select-value-to-save-fn)
                         (map (juxt :column-id identity))
                         (into {}))]
        (recur
         (filter (fn [row] (= (nth row counter)
                              (get-in commons [counter :value-to-save]))) parsed')
         (inc counter))))))

(defn determine-value-to-save-oxygen [zeroes ones]
  (if (>= ones zeroes) 1 0))

(defn determine-value-to-save-CO2 [zeroes ones]
  (if (<= zeroes ones) 0 1))

(defn life-support-rating [input]
  (*
   (->> (problem1/parse-input input)
        (determine-oxygen-generator-rating determine-value-to-save-oxygen)
        first
        (problem1/binary-coll-to-decimal))
   (->> (problem1/parse-input input)
        (determine-oxygen-generator-rating determine-value-to-save-CO2)
        first
        (problem1/binary-coll-to-decimal))))

;; test input
(life-support-rating problem1/test-input)
;; => 230

;; real input
(life-support-rating (slurp "./input.txt"))
;; => 7928162
