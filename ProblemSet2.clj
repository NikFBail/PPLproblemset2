(ns ProblemSet2)

;;; Problem 1
;; Write a function that takes a number n and computes the nth Fibonacci number

(defn fib [n]
  "Takes a number n and returns the nth Fibonacci number"
  (if (< n 2) ;;; covers the two base cases, n = 0 and n = 1
    n
    (loop [i 1 last 0 next 1] ;;; loop for the recur
      (if (>= i n)
        next
        ;;; everytime the if statement doesn't run, we add the last number to next
        (recur (inc i) next (+ last next)))))) ;;; increase i by one, last becomes what next used to be

(= 0 (fib 0))
(= 1 (fib 1))
(= 5 (fib 5))
(= 13 (fib 13))


;;; Problem 2
;; Write a recursive(not tail-recursive) Clojure function count-seqs that takes a sequence that has nested sequences in it
;; and returns the total number of sequences in it (including the outer sequence)
(def nested-vectors [[1] [2 [3]] [[[4 5] 6 [7 8]] [9 []] ]])

;; doesn't work I'm such a loser wah
(defn count-seqs [seq]
  "Takes a sequences that has nested sequences, and returns the total number of sequences"
  (if (seq? seq)
    (apply + (map count-seqs seq))
    (if (coll? seq) 1 0)))

;; Alternate code that also doesn't work ahahahahahahaha :(
(defn count-seqs [coll]
  "Takes a sequence that has nested sequences, and returns the total number of sequences"
  (cond
    (empty? coll) 0
    (sequential? (first coll)) (+ (count-seqs (first coll)) (count-seqs (rest coll)))
    :else (count-seqs (rest coll)) 1))


;;; Problem 3
;; Use sort to do:
;; 1. Sort a sequence of strings by the length of the strings

;; The function determines if length of string a is shorter than string b
;; If it is, then it returns true, and therefore sort puts a first
(sort (fn [a b] (< (count a) (count b))) ["rainbow" "apple" "dog" "banana" "orange"])

;; 2. Sort a sequence of hashmaps that have the keys name, age, hometown, by the age field

;; Uses sort-by to sort hashmaps
(sort-by :age
  [{:name "Sarah" :age 32 :hometown "Morris"}
  {:name "John" :age 2 :hometown "Fargo"}
  {:name "Jack" :age 69 :hometown "Bemidji"}
  {:name "Banana Man" :age 598 :hometown "Mount Wannahockaloogie"}
  {:name "Dad" :age 52 :hometown "Grocery Store"}
  {:name "Mom" :age 50 :hometown "Mailman's House"}])

;; 3. Sort a sequence of BigIntegers by their values

;; The < operator is used to compare BigIntegers
(sort < [100N 10N 500N 50N 200N 0N 999N])

;;; Problem 4
;; Write a macro impl to represent logical implication that takes two parameters, p and q

;; Everything needs to be quoted for Macros, that's why ` is in there
(defmacro impl [p q]
  "Macro that represents logical implication"
  `(or (not ~p) ~q))

(= "yes" (if (impl (< 2 3) (< 5 6)) "yes" "no"))
(= "yes" (if (impl (> 2 3) (< 5 (/ 1 0))) "yes" "no"))
(= "yes" (if (impl (> 2 3) (< 5 6)) "yes" "no"))
(= "no" (if (impl (< 2 3) (> 5 6)) "yes" "no"))
(= "no" (if (impl (< 2 3) nil) "yes" "no"))
