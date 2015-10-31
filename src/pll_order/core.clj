(ns pll-order.core)
(load "core_perms")
(load "core_moves")
(load "core_plls")

(defn find-identity-perm
  []
  (loop []
    (let [s (shuffle pll-keywords)
          perms (map plls s)
          cube (apply comp perms)]
      (if (cubes-equal identity-perm cube) s (recur)))))

(defn solved-indicator
  []
  (let [s (shuffle pll-keywords)
        perms (map plls s)
        cube (apply comp perms)]
    (if (cubes-equal identity-perm cube) 1 0)))

(defn compute-percent-solved
  []
  (let [count (reduce + (repeatedly 1000000 solved-indicator))]
    (float (/ count 10000))))

(defn -main
  "Find an order of PLLs to arrive at the identity."
  []
  (println "Finding an identity order...")
  (println (map #(subs (str %) 1) (find-identity-perm)))
  (println "Computing statistics...")
  (println "Roughly" (compute-percent-solved) "% are solved."))
