(ns pll-order.core)

(def ^:const EdgeL 0)
(def ^:const EdgeB 1)
(def ^:const EdgeR 2)
(def ^:const EdgeF 3)
(def ^:const CornerBL 4)
(def ^:const CornerBR 5)
(def ^:const CornerFR 6)
(def ^:const CornerFL 7)

(defn auf-moves
  "Perform a certain number of U moves to a permutation function"
 [count perm]
  (let [u-move (multicycle [0 1 2 3] [4 5 6 7])]
    (nth (iterate (comp u-move) identity-perm) count)))

(def plls {
  :h (multicycle [EdgeL EdgeR] [EdgeF EdgeB])
  :z (multicycle [EdgeF EdgeL] [EdgeB EdgeR])
  :u1 (cycle-perm  EdgeL EdgeF EdgeR)
  :u2 (cycle-perm  EdgeR EdgeF EdgeL)
  :a1 (cycle-perm  CornerBL CornerBR CornerFR)
  :a2 (cycle-perm  CornerFR CornerBR CornerBL)
  :e (multicycle [CornerFR CornerFL] [CornerBR CornerBL])
  :f (multicycle [CornerFR CornerBR] [EdgeF EdgeB])
  :g1 (auf-moves 1 (multicycle [CornerFR CornerFL CornerBL] [EdgeF EdgeB EdgeL]))
  :g2 (multicycle [CornerBL CornerFL CornerFR] [EdgeF EdgeL EdgeB])
  ;; TODO: add auf-moves to the next two perms
  :g3 (multicycle [CornerBR CornerFL CornerFR] [EdgeF EdgeB EdgeR])
  :g4 (multicycle [CornerFL CornerBR CornerFR] [EdgeB EdgeF EdgeR])
})
