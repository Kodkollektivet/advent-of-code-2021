(ns day-9-problem-1
  (:require [clojure.string :as str]))


(defn str->int [s] (Integer. s))

(defn get-neighbor-number [[x y] position-map]
  (get-in position-map [[x y] :number] 10))

(defn north-position [x y] [x (dec y)])
(defn south-position [x y] [x (inc y)])
(defn east-position [x y] [(inc x) y])
(defn west-position [x y] [(dec x) y])

(defn parse-input
  "Takes a string and returns a `position-map`."
  [input]
  (->> input
       (str/split-lines)
       (map-indexed vector)
       (mapv (fn [[line-idx line]]
               (->> (str/split line #"")
                    (map-indexed vector)
                    (mapv (fn [[column-idx item]]
                            {:number (str->int item)
                             :x column-idx
                             :y line-idx})))))
       (apply concat)
       (reduce (fn [m {:keys [x y] :as m'}]
                 (assoc m [x y] m')) {})))

(defn find-lowest-points [position-map]
  (->> position-map
       (reduce-kv
        (fn [m k {:keys [number x y] :as m'}]
          (let [neighbor-positions (map (fn [f]
                                          (f x y))
                                        [north-position south-position east-position west-position])
                neighbor-values    (map (fn [[x y]]
                                          (get-neighbor-number [x y] position-map))
                                        neighbor-positions)]
            (assoc m k
                   (assoc m'
                          :neighbor-positions neighbor-positions
                          :neighbor-values neighbor-values
                          :smaller-than-neighbors? (every? #(< number %) neighbor-values)))))
        {})))

(defn calc-it [position-map-with-lowest-points]
  (->> position-map-with-lowest-points
       (filter :smaller-than-neighbors?)
       ;; (map :number)
       ;; (map inc)
       ;; (apply +)
       ))

(->> (slurp "test-input.txt")
     (parse-input)
     (find-lowest-points)
     (vals)
     (calc-it)
     )
;; => 15

(->> (slurp "input.txt")
     (parse-input)
     (find-lowest-points)
     (vals)
     (calc-it))
;; => 508
