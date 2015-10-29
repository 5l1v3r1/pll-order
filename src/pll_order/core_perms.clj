(ns pll-order.core)

(def identity-perm identity)

(defn cycle-to-map
  "Turn a cycle (list of indices) into a map"
  [& indices]
  (loop [i indices l (last indices) m {}]
    (if (seq i)
        (recur (rest i) (first i) (assoc m (first i) l))
        m)))

(defn cycle-perm
  "Create a forward cycle permutation"
  [& indices]
  (let [m (apply cycle-to-map indices)]
    #(or (get m %) %)))

(defn multicycle
  "Create an application of multiple cycles"
  [& cycles]
  (loop [f identity-perm c cycles]
    (if (seq c)
        (recur (comp f (apply cycle-perm (first c)))
               (rest c))
        f)))
