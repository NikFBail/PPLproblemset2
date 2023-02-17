(ns lab2)

;;; Collections ;;;
(defn add-values-for-keys [hm keys]
  "takes a hash map and a set of keys, reutrns the sum of all values bound to those keys"
  (reduce + (filter identity (map hm keys))))

(= (add-values-for-keys {:a 2 :b true :c "apple" :d 5} #{:a :d}) 7)
(= (add-values-for-keys {:a 2 :b 3 :c 1 :d 5} #{}) 0)
(= (add-values-for-keys {:a 2 :b 3 :c 1 :d 5} #{:x :y}) 0)
(= (add-values-for-keys {:a 2 :b 3 :c 1 :d 5} #{:a :d}) 7)

;;; Recursion ;;;
    ;;; Task 1 ;;;
(defn min1 [coll]
  "takes a non-empty collection of numbers, returns the minimum element in the collection"
  (loop [c coll m (first coll)] ; m cannot be initialized as nil
    (if (empty? (rest c))
      (min m (first c))
      (recur (rest c) (min m (first c))))))

(= -10 (min1 [0 -8 7 -10 10 7 0]))
(= 100 (min1 [100]))

    ;;; Task 2 ;;;
(defn take1 [num coll]
  "takes a number and a collection, returns the first n elements in the same order"
  (loop [c coll n num t []]
    (if (or (< n 1) (empty? c))
      t
      (recur (rest c) (- n 1) (conj t (first c))))))

(= (take1 5 (range 100)) [0 1 2 3 4])
(= (take1 5 (range)) [0 1 2 3 4])
(= (take1 0 [1 2 3]) [])
(= (take1 -1 [1 2 3]) [])
(= (take1 6 [1 2 3]) [1 2 3])

;;; Macros ;;;
    ;;; Task 1 ;;;
(defmacro safe-nth [coll num]
  "takes a collection and a number, returns the n-th element of the list if it exists, nil if not"
  `(if (and (> ~num 0) (<= ~num (count ~coll))) (nth ~coll ~num)))

(= (safe-nth [1 2 3 4 5] 7) nil)
(= (safe-nth [1 2 3 4 5] 2) 3)
(= (safe-nth [1 2 3 4 5] -1) nil)

;;; Task 2 ;;;
(->> [1 2 3] (map inc) (reduce +))

;(macroexpand `(->> [1 2 3] (map inc) (reduce +)))
; (clojure.core/reduce clojure.core/+ (clojure.core/map clojure.core/inc [1 2 3]))
(->> `(1 2 3 4 5) (cons 0))
(->> [:a :c] (add-values-for-keys {:a 2 :b "hi" :c 5}) (even?))

;;; ->> passes the left-most expression as the last argument to the function on the right.
;;; If there are more than one function, it passes the resulting expression of the first two expressions
;;; down the line as the last argument.

;;; Extra Credit ;;;
(defn add-values-for-keys-thread [hm keys]
"takes a hash map and a set of keys, reutrns the sum of all values bound to those keys using thread macro"
(->> keys (map hm) (filter identity) (reduce +)))

(= (add-values-for-keys-thread {:a 2 :b true :c "apple" :d 5} #{:a :d}) 7)
(= (add-values-for-keys-thread {:a 2 :b 3 :c 1 :d 5} #{}) 0)
(= (add-values-for-keys-thread {:a 2 :b 3 :c 1 :d 5} #{:x :y}) 0)
(= (add-values-for-keys-thread {:a 2 :b 3 :c 1 :d 5} #{:a :d}) 7)
