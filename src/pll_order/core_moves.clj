(ns pll-order.core)

(defn- ring-cycles
  [stickers]
  (multicycle (take-nth 3 stickers)
              (take-nth 3 (drop 1 stickers))
              (take-nth 3 (drop 2 stickers))))

(defn- surface-cycles
  [stickers]
  (multicycle (take-nth 2 stickers)
              (take-nth 2 (drop 1 stickers))))

(defn- face-permutation
  "Generate a permutation for a clockwise turn of the given face"
  [face]
  (let [rings {\R '(8 5 2 27 30 33 17 14 11 26 23 20)
               \L '(0 3 6 18 21 24 9 12 15 35 32 29)
               \F '(6 7 8 36 39 42 11 10 9 53 50 47)
               \B '(3 2 1 45 48 51 15 16 17 44 41 38)
               \U '(20 19 18 47 46 45 29 28 27 38 37 36)
               \D '(24 25 26 42 43 44 33 34 35 51 52 53)}
        surfaces {\R '(36 37 38 41 44 43 42 39)
                  \L '(45 46 47 50 53 52 51 48)
                  \F '(18 19 20 23 26 25 24 21)
                  \B '(27 28 29 32 35 34 33 30)
                  \U '(0 1 2 5 8 7 6 3)
                  \D '(9 10 11 14 17 16 15 12)}]
    (comp (ring-cycles (get rings face))
          (surface-cycles (get surfaces face)))))

(defn- move-permutation
  "Turn a move symbol into a permutation"
  [moveSymbol]
  (let [s (str moveSymbol)]
    (cond (= 1 (count s))
          (face-permutation s)
          (= \2 (get s 1))
          (comp (face-permutation s) (face-permutation s))
          :else
          (comp (face-permutation s) (face-permutation s) (face-permutation s)))))

(defn alg-permutation
  "Turn a list of symbols representing moves into a permutation"
  [& moves]
  (if (seq moves)
      (comp (move-permutation (first moves)) (apply alg-permutation (rest moves)))
      identity-perm))

