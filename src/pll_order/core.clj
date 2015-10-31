(ns pll-order.core)
(load "core_perms")
(load "core_moves")
(load "core_plls")

(defn- scramble-sequence
  [s]
  (if (<= (count s) 1) s
      (let [el (rand-nth s)]
        (cons el (scramble-sequence (remove #(= el %) s))))))

(defn find-identity-perm
  []
  (let [s0 (keys plls)]
    (loop []
      (let [s (scramble-sequence s0)
            perms (map plls s)
            cube (apply comp perms)]
        (if (cubes-equal identity-perm cube) s (recur))))))

(defn -main
  "Find an order of PLLs to arrive at the identity."
  []
  (loop []
    (println (map #(subs (str %) 1) (find-identity-perm)))
    (recur)))
