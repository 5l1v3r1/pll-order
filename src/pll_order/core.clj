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

(defn compute-percent-solved
  []
  (loop [i 50000 c 0]
    (if (zero? i)
        (float (/ c 500))
        (let [s (scramble-sequence (keys plls))
              perms (map plls s)
              cube (apply comp perms)]
          (if (cubes-equal identity-perm cube)
              (recur (dec i) (inc c))
              (recur (dec i) c))))))

(defn -main
  "Find an order of PLLs to arrive at the identity."
  []
  (println "Finding an identity order...")
  (println (map #(subs (str %) 1) (find-identity-perm)))
  (println "Computing statistics...")
  (println "Roughly" (compute-percent-solved) "% are solved."))
