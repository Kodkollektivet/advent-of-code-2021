(ns day-5-problem-1
  (:require [clojure.string :as str]))

(defn str->int [s] (Integer. s))

(defn parse-input [input]
  (->> input
       (str/split-lines)
       (map #(let [[_ x1 y1 x2 y2] (re-find #"^([0-9]+),([0-9]+) -> ([0-9]+),([0-9]+)$" %)
                   [x1 y1 x2 y2]   (map str->int [x1 y1 x2 y2])]
               {:start [x1 y1]
                :end   [x2 y2]
                :x1    x1
                :y1    y1
                :x2    x2
                :y2    y2}))))

(defn only-consider-horizontal-and-vertical-lines [lines]
  (filter (fn [{:keys [x1 y1 x2 y2]}]
            (or (= x1 x2)
                (= y1 y2)))
          lines))

(defn points-in-line [{:keys [start end x1 y1 x2 y2]}]
  (let [ranges (fn [a b]
                 (apply range
                        ((juxt #(apply min %)
                               #(->> % (apply max) inc))
                         (sort [a b]))))]
    (if (= 0 (- x1 x2))
      (->> (ranges y1 y2)
           (map (fn [y-point]
                  {:x x1
                   :y y-point})))
      (->> (ranges x1 x2)
           (map (fn [x-point]
                  {:x x-point
                   :y y1}))))))

(defn debug-print
  [xys]
  (->> xys
       (reduce
        (fn [matrix {:keys [x y]}]
          (update-in matrix [y x] inc))
        (mapv
         (fn [_]
           (mapv (fn [a] 0)
                 (range 10)))
         (range 10)))
       (map (fn [row] (str/join "" (map (fn [item] (if (= item 0) "." (str item))) row))))
       (reduce
        (fn [acc s]
          (str acc s "\n"))
        "")
       (println)))

(->> (slurp "test-input.txt")
     (parse-input)
     (only-consider-horizontal-and-vertical-lines)
     (map points-in-line)
     (apply concat)
     ;; (debug-print)
     (frequencies)
     (filter (comp #(< 1 %) second))
     (count)
     )

(->> (slurp "input.txt")
     (parse-input)
     (only-consider-horizontal-and-vertical-lines)
     (map points-in-line)
     (apply concat)
     ;; (debug-print)
     (frequencies)
     (filter (comp #(< 1 %) second))
     (count)
     )
