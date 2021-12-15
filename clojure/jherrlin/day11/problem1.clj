(ns day-11-problem-1
  (:require [clojure.string :as str]))


(defn individual-state [[x y] generation]
  (get-in generation [[x y]]))

(defn neighbour-idxs [[^int x ^int y]]
  [(vec (range (- x 1) (+ x 2)))
   (vec (range (- y 1) (+ y 2)))])

(defn neighbour-states
  [[current-x current-y] generation [x-indexs y-indexs]]
  (vec (for [x x-indexs
             y y-indexs
             :when (not (= [current-x current-y] [x y]))]
         (individual-state [x y] generation))))

(defn neighbours
  "Get neighbours state."
  [current-x-y generation]
  (->> (neighbour-idxs   current-x-y)
       (neighbour-states current-x-y generation)))

(defn get-board-length [parsed-input]
  (->> parsed-input (keys) (apply concat) (apply max)))

(defn add-neighbours [parsed-input]
  (let [board-length (get-board-length parsed-input)]
    (->> (for [x-index (range (inc board-length))
               y-index (range (inc board-length))]
           [[x-index y-index] {:neighbours (->> parsed-input
                                                (neighbours [x-index y-index])
                                                (remove nil?)
                                                (mapv #(dissoc % :number)))}])
         (into {})
         (reduce-kv (fn [m k v]
                      (assoc m k (merge (get parsed-input k)
                                        v)))
                    {}))))

(defn parse-input
  "Takes a string and returns a `position-map`."
  [input]
  (let [str->int #(Integer. %)]
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
                   (assoc m [x y] m')) {})
         (add-neighbours))))

(defn debug-string
  ([position-map-with-neighbours]
   (debug-string false position-map-with-neighbours))
  ([format? position-map-with-neighbours]
   (let [board-length (get-board-length position-map-with-neighbours)]
     (->> (for [y-index (range (inc board-length))
                x-index (range (inc board-length))]
            [y-index x-index])
          (map (fn [[y x]]
                 (get position-map-with-neighbours [x y])))
          (map :number)
          (partition (inc board-length))
          (map (fn [row] (map (fn [item] (if format?
                                           (format "%1s" item)
                                           (format "%3s" item)))
                              row)))
          (map-indexed vector)
          (map (fn [[idx row]]
                 (str idx " | " (str/join row))))
          (str/join "\n")))))

(defn debug-print
  ([position-map-with-neighbours]
   (debug-print false position-map-with-neighbours))
  ([format? position-map-with-neighbours]
   (println "")
   (println (debug-string format? position-map-with-neighbours))
   (println "")
   position-map-with-neighbours))

(defn octopuses-with-flashing-neighbour [position-map-with-neighbours]
  (->> position-map-with-neighbours
       (filter (comp #{10} :number second))))

(defn increases-energy-level-of-adjacent-octopuses [position-map-with-neighbours]
  (let [positions-to-inc (octopuses-with-flashing-neighbour position-map-with-neighbours)
        new-gen (reduce
                 (fn [m {:keys [x y]}]
                   (update-in m [[x y] :number] inc))
                 position-map-with-neighbours
                 positions-to-inc)]
    new-gen))

(defn increases-energy-level-of-adjacent-octopuses [position-map-with-neighbours]
  (loop [gen position-map-with-neighbours]
    (let [neighbours-to-flashing (octopuses-with-flashing-neighbour gen)
          current-octopus        (->> neighbours-to-flashing (take 1) (first) (second))
          positions-to-inc       (:neighbours current-octopus)]
      (if (empty? neighbours-to-flashing)
        gen
        (recur (-> (reduce
                    (fn [m {:keys [x y]}]
                      (update-in m [[x y] :number] (fn [number] (if (= number 10)
                                                                  number
                                                                  (inc number)))))
                    gen
                    positions-to-inc)
                   (update-in [[(:x current-octopus) (:y current-octopus)] :number] inc)))))))

(defn increases-energy-level-of-all-octopuses [position-map-with-neighbours]
  (reduce-kv
   (fn [m k v]
     (assoc m k (update v :number inc)))
   {}
   position-map-with-neighbours))

(defn flashing-level? [n] (< 9 n))

(defn reset-levels-of-octopuses [position-map-with-neighbours]
  (reduce-kv
   (fn [m k v]
     (assoc m k (update v :number (fn [n] (if (< 9 n) 0 n)))))
   {}
   position-map-with-neighbours))

(defn count-number-of-flashes [position-map-with-neighbours]
  (->> position-map-with-neighbours
       (filter (comp flashing-level? :number second))
       count))

(defn run-steps [steps init-generation]
  (loop [gen     init-generation
         flashes 0
         count   0]
    (if (= count steps)
      flashes
      (let [g (->> gen
                   (increases-energy-level-of-all-octopuses)
                   (increases-energy-level-of-adjacent-octopuses))]
        (recur
         (reset-levels-of-octopuses g)
         (+ flashes (count-number-of-flashes g))
         (inc count))))))

;; test input
(->> (slurp "test-input2.txt")
     (parse-input)
     (run-steps 100))
;; => 1656

;; real input
(->> (slurp "input.txt")
     (parse-input)
     (run-steps 100))
;; => 1627
