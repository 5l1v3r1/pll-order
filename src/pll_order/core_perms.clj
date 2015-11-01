(ns pll-order.core)

(def identity-perm identity)

(defn- cycle-to-map
  "Turn a cycle (list of indices) into a map"
  [indices]
  (first (reduce (fn [[m l] i] [(assoc m i l) i])
                 [{} (last indices)]
                 indices)))

(defn cycle-perm
  "Create a forward cycle permutation"
  [& indices]
  (let [m (cycle-to-map indices)]
    #(or (get m %) %)))

(defn multicycle
  "Create an application of multiple cycles"
  [& cycles]
  (apply comp (map #(apply cycle-perm %) cycles)))
