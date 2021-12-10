(ns day-9-problem-1
  (:require [clojure.string :as str]))


(defn str->int [s] (Integer. s))

(defn get-neighbor-number [[x y] position-map]
  (get-in position-map [[x y] :number] 10))

(defn north-position [x y] [x (dec y)])
(defn south-position [x y] [x (inc y)])
(defn east-position [x y] [(inc x) y])
(defn west-position [x y] [(dec x) y])

(defn calc-it [input]
  (let [position-map (->> input
                          (str/split-lines)
                          (map-indexed vector)
                          (mapv (fn [[line-idx line]] (->> (str/split line #"")
                                                           (map-indexed vector)
                                                           (mapv (fn [[column-idx item]]
                                                                   {:number (str->int item)
                                                                    :x column-idx
                                                                    :y line-idx})))))
                          (apply concat)
                          (reduce (fn [m {:keys [x y] :as m'}]
                                    (assoc m [x y] m')) {}))]
    (->> position-map
         (map (fn [[k {:keys [number x y] :as m}]]
                (let [neighbor-positions (map (fn [f]
                                                (f x y))
                                              [north-position south-position east-position west-position])
                      neighbor-values    (map (fn [[x y]]
                                                (get-neighbor-number [x y] position-map))
                                              neighbor-positions)]
                  (assoc m
                         :neighbor-positions neighbor-positions
                         :neighbor-values neighbor-values
                         :smaller-than-neighbors? (every? #(< number %) neighbor-values)))))
         (filter :smaller-than-neighbors?)
         (map :number)
         (map inc)
         (apply +))))

(calc-it (slurp "test-input.txt")) ;; => 15
(calc-it (slurp "input.txt"))      ;; => 508
