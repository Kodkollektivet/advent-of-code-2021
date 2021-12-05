(ns day-5-problem-2
  (:require [day-5-problem-1 :as problem-1]))


(defn north-east?
  "Is x2,y2 north east of x1,y1 ?"
  [{:keys [x1 y1 x2 y2]}]
  (and (< x1 x2) (< y2 y1)))

(defn south-east?
  "Is x2,y2 south east of x1,y1 ?"
  [{:keys [x1 y1 x2 y2]}]
  (and (< x1 x2) (< y1 y2)))

(defn south-west?
  "Is x2,y2 south west of x1,y1 ?"
  [{:keys [x1 y1 x2 y2]}]
  (and (< x2 x1) (< y1 y2)))

(defn north-west?
  "Is x2,y2 north west of x1,y1 ?"
  [{:keys [x1 y1 x2 y2]}]
  (and (< x2 x1) (< y2 y1)))

(defn to-north-east
  "Calculate points from x1,y1 to x2,y2 when heading north east."
  [{:keys [x1 y1 x2 y2]}]
  (map (fn [x y] {:x x :y y})
       (take (inc (- x2 x1)) (iterate inc x1))
       (take (inc (- y1 y2)) (iterate dec y1))))

(defn to-south-east
  "Calculate points from x1,y1 to x2,y2 when heading south east."
  [{:keys [x1 y1 x2 y2]}]
  (map (fn [x y] {:x x :y y})
       (take (inc (- x2 x1)) (iterate inc x1))
       (take (inc (- y2 y1)) (iterate inc y1))))

(defn to-south-west
  "Calculate points from x1,y1 to x2,y2 when heading south west."
  [{:keys [x1 y1 x2 y2]}]
  (map (fn [x y] {:x x :y y})
       (take (inc (- x1 x2)) (iterate dec x1))
       (take (inc (- y2 y1)) (iterate inc y1))))

(defn to-north-west
  "Calculate points from x1,y1 to x2,y2 when heading north west."
  [{:keys [x1 y1 x2 y2]}]
  (map (fn [x y] {:x x :y y})
       (take (inc (- x1 x2)) (iterate dec x1))
       (take (inc (- y1 y2)) (iterate dec y1))))

(defn points-in-line [{:keys [start end x1 y1 x2 y2] :as m}]
  (let [ranges (fn [a b]
                 (apply range
                        ((juxt #(apply min %)
                               #(inc (apply max %)))
                         [a b])))]
    (cond
      (= 0 (- x1 x2)) ;; vertical line
      (->> (ranges y1 y2)
           (map (fn [y-point]
                  {:x x1
                   :y y-point})))
      (= 0 (- y1 y2)) ;; horizontal line
      (->> (ranges x1 x2)
           (map (fn [x-point]
                  {:x x-point
                   :y y1})))
      (north-east? m)
      (to-north-east m)

      (south-east? m)
      (to-south-east m)

      (south-west? m)
      (to-south-west m)

      (north-west? m)
      (to-north-west m))))

;; test input
(->> (slurp "test-input.txt")
     (problem-1/parse-input)
     (map points-in-line)
     (apply concat)
     #_(problem-1/debug-print)
     (frequencies)
     (filter (comp #(< 1 %) second))
     (count))
;; => 12


;; test input
(->> (slurp "input.txt")
     (problem-1/parse-input)
     (map points-in-line)
     (apply concat)
     #_(problem-1/debug-print)
     (frequencies)
     (filter (comp #(< 1 %) second))
     (count))
;; => 18144
