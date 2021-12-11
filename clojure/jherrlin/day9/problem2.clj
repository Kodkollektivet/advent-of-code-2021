(ns day-9-problem-2
  (:require [day-9-problem-1 :as problem-1]))


(defn get-neighbor-number [position-map [x y]]
  (let [basin-wall 9]
    (get-in position-map [[x y] :number] basin-wall)))

(def neighbors
  {:north problem-1/north-position
   :south problem-1/south-position
   :east  problem-1/east-position
   :west  problem-1/west-position})

(def latitudes #{:north :south :east :west})

(defn find-lowest-points [position-map]
  (->> position-map
       (reduce-kv
        (fn [m k {:keys [number x y] :as m'}]
          (let [neighbor-positions (reduce-kv (fn [m k f]
                                                (assoc m k (f x y)))
                                              {}
                                              neighbors)
                neighbor-values    (reduce-kv (fn [m k [x y]]
                                                (assoc m k (get-neighbor-number position-map [x y])))
                                              {}
                                              neighbor-positions)]
            (assoc m k
                   (assoc m'
                          :neighbor-positions neighbor-positions
                          :neighbor-values neighbor-values
                          :smaller-than-neighbors? (->>  neighbor-values
                                                         (vals)
                                                         (every? #(< number %)))))))
        {})))

(def position-map-with-lowest-points
  (->> (slurp "input.txt")
       (problem-1/parse-input)
       (find-lowest-points)))

(def starting-points
  (->> position-map-with-lowest-points
       (vals)
       (filter :smaller-than-neighbors?)))

(defn neighbors-in-direction [position-map-with-lowest-points {:keys [latitude x y]}]
  (let [neighbor-positions (get-in position-map-with-lowest-points [[x y] :neighbor-positions])]
    (as-> neighbor-positions $
      #_(dissoc $ latitude)
      (map (fn [[k [x y]]]
             {:latitude k
              :x        x
              :y        y}) $))))

(defn branch? [visited-nodes position-map-with-lowest-points {:keys [latitude x y]}]
  (let [number        (get-in position-map-with-lowest-points [[x y] :number] 9)
        node-visited? (contains? @visited-nodes [x y])]
    #_(println "visited node:" [x y])
    (swap! visited-nodes conj [x y])
    (and (not node-visited?)
         (not (= number 9)))))

(defn get-nodes-in-direction [latitude x y]
  (->> (let [visited-nodes (atom #{})]
         (tree-seq
          (partial branch? visited-nodes position-map-with-lowest-points)
          (partial neighbors-in-direction position-map-with-lowest-points)
          {:latitude latitude #_:east
           :x        x #_9
           :y        y #_0}))
       (doall)
       (map (fn [{:keys [x y]}]
              [x y]))
       (set)
       (map #(get position-map-with-lowest-points %))
       (remove nil?)
       (remove (comp #{9} :number))
       ;; (count)
       ))

(defn calc-counts [x y]
  (->> #{:west}
       (map (fn [latitude]
              (get-nodes-in-direction latitude x y)))
       (apply concat)
       (set)
       (remove (comp #{9} :number))
       (count)))

(->> (slurp "input.txt")
     (problem-1/parse-input)
     (find-lowest-points)
     (vals)
     (filter :smaller-than-neighbors?)
     (map (fn [{:keys [x y]}]
            (calc-counts x y)))
     ;; (sort #(compare %2 %1))
     ;; (take 3)
     ;; (apply *)
     )
;; => 1134
;; => 1134


(->> (slurp "input.txt")
     (problem-1/parse-input)
     (find-lowest-points)
     (vals)
     (filter :smaller-than-neighbors?)
     (map (fn [{:keys [x y]}]
            (calc-counts x y)
            ))
     (sort #(compare %2 %1))
     (take 3)
     (apply *)
     )
;; => 1564640
