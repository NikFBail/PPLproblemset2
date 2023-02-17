(ns ProblemSet2)

;;; Problem 1
;; Write a function that takes a number n and computes the nth Fibonacci number

(defn fib [number]
  "Takes a number n and returns the nth Fibonacci number"
  (loop [n number t 0])
    (cond
      (= n 0) 0
      (= n 1) 1
      :else
        (recur 


      (defn fib [n]
        "Takes a number n and returns the nth Fibonacci number"
        (loop [num n])
          (cond
            (= n 0) 0
            (= n 1) 1
            :else
              (+ fib[(- n 1)] fib[(- n 2)])))

;;; Problem 2
;; Write a recursive(not tail-recursive) Clojure function count-seqs that takes a sequence that has nested sequences in it
;; and returns the total number of sequences in it (including the outer sequence)

(defn count-seqs [coll]
  "Takes a sequences that has nested sequences, and returns the total number of sequences")



;;; Problem 3
;; Use sort to do:
;; 1. Sort a sequence of strings by the length of the strings
;; 2. Sort a sequence of hashmaps that have the keys name, age, hometown, by the age field
;; 3. Sort a sequence of BigIntegers by their values



;;; Problem 4
;; Write a macro impl to represent logical implication that takes two parameters, p and q
