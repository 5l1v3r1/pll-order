(ns pll-order.core)

(def ^:const EdgeL 0)
(def ^:const EdgeR 1)
(def ^:const EdgeF 2)
(def ^:const EdgeB 3)
(def ^:const CornerBL 4)
(def ^:const CornerBR 5)
(def ^:const CornerFR 6)
(def ^:const CornerFL 7)

(defn cycle-pieces
  "Cycle the pieces at the given indices"
  [startPieces & startIndices]
  (loop [pieces startPieces indices startIndices value (last indices)]
    (if (nil? (first indices))
      pieces
      (let [index (first indices) newPieces (assoc pieces index value)]
        (recur newPieces (rest indices) (pieces index))))))

(defn multicycle
  "Run zero or more cycles on a piece vector"
  [pieces & permutations]
  (reduce (partial apply cycle-pieces) pieces permutations))

(def plls {
  :h #(multicycle % (list EdgeL EdgeR) (list EdgeF EdgeB))
  :a1 #(cycle-pieces % CornerBL CornerBR CornerFR)
  :a2 #(cycle-pieces % CornerFR CornerBR CornerBL)
})
