(ns pll-order.core-test
  (:require [clojure.test :refer :all]
            [pll-order.core :refer :all]))

(deftest alg-permutation-test
  (testing "alg-permutation"
    (testing "multiple routes"
      (let [alg1 '(U R2 F B R B2 R U2 L B2 R U' D' R2 F R' L B2 U2 F2)
            alg2 '(F2 U2 B2 L' R F' R2 D U R' B2 L' U2 R' B2 R' B' F' R2 U')]
        (is (cubes-equal (alg-permutation alg1) (alg-permutation alg2)))
        (is (not (cubes-equal (alg-permutation alg1) identity-perm)))
        (is (not (cubes-equal (alg-permutation alg2) identity-perm)))))
    (testing "identity moves"
      (let [alg '(U R2 F B R B2 R U2 L B2 R U' D' R2 F R' L B2 U2 F2
                  F2 U2 B2 L' R F' R2 D U R' B2 L' U2 R' B2 R' B' F' R2 U')]
        (is (cubes-equal identity-perm (alg-permutation alg)))))))
