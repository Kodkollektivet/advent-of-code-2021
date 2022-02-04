(ns day15-problem1
  (:require [clojure.data.priority-map :refer [priority-map]]
            [clojure.string :as str]))

(def test-input
  "1163751742
1381373672
2136511328
3694931569
7463417111
1319128137
1359912421
3125421639
1293138521
2311944581")


(defn split-string-to-coll
  "Split string on each character into a vector."
  [s]
  (str/split s #""))

(defn string-to-matris
  "Make a matris out of a string.
  The number of newlines counts the Y-axis.

  Example output:
  `[[# # # # # # # # #]
    [# b . A . @ . a #]
    [# # # # # # # # #]]`"
  [s]
  (->> s
       str/trim
       (str/split-lines)
       (map str/trim)
       (mapv split-string-to-coll)))

(defn matris-to-nodes
  "A `node` is a `hash-map` with the value from the matris together with position.

  Example output:
  `({:y 0, :x 0, :value #}
    {:y 0, :x 1, :value A}
    {:y 0, :x 2, :value #})`"
  [matris]
  (->> (for [[y-index row]   (map-indexed vector matris)
             [x-index value] (map-indexed vector row)]
         {:y     y-index
          :x     x-index
          :xy    [x-index y-index]
          :value value})))

(defn neighbour-ranges
  "Returns a collection of two arrays that contains the x and y ranges for
  positions to neighbour, including the x-index y-index provided.

  Example output for input: `3 3`
  `[[2 3 4] [2 3 4]]`"
  [x-index y-index]
  [(vec (range (- x-index 1) (+ x-index 2)))
   (vec (range (- y-index 1) (+ y-index 2)))])

(defn neighbour-positions-4
  "Returns a collection with `[x y]` positions of neighbours in all 4 directions.

  Example output for input: `1 1`
  [[0 1] [2 1] [1 0] [1 2]]"
  [x-index y-index]
  [[(dec x-index)      y-index]
   [(inc x-index)      y-index]
   [     x-index  (dec y-index)]
   [     x-index  (inc y-index)]])

(defn text-to-graph [s]
  (let [graph' (->> s
                    string-to-matris
                    matris-to-nodes
                    (map #(assoc % :value (Integer. (:value %))))
                    (map (juxt :xy identity))
                    (into {}))]
    (->> graph'
         (map (fn [[k {:keys [x y] :as v}]]
                [k (assoc v :neighbours (->> (neighbour-positions-4 x y)
                                             (map (partial get graph'))
                                             (remove nil?)
                                             (vec)))]))
         (into {}))))

(defn map-vals [m f]
  (->> (for [[k v] m]
         [k (f v)])
       (into {})))

(defn remove-keys [m pred]
  (->> (keys m)
       (filter (complement pred))
       (select-keys m)))

(defn dijkstra
  "Computes single-source shortest path distances in a directed graph.

  Given a node n, (f n) should return a map with the successors of n
  as keys and their (non-negative) distance from n as vals.

  Returns a map from nodes to their distance from start."
  [start f]
  (loop [q (priority-map start 0)
         r {}]
    (if-let [[v d] (peek q)]
      (let [dist (-> (f v)
                     (remove-keys r)
                     (map-vals (partial + d)))]
        (recur
         (merge-with min (pop q) dist)
         (assoc r v d)))
      r)))

(defn f [graph' v]
  (->> (get-in graph' [v :neighbours])
       (map (juxt :xy :value))
       (into {})))

(let [graph (text-to-graph #_test-input (slurp "./input.txt"))]
  (->> (dijkstra
        [0 0]
        (partial f graph))
       (sort-by (fn [[[x y] _]]
                  (+ x y)))
       (last)))
;; => [[9 9] 40]
;; => [[99 99] 741]
