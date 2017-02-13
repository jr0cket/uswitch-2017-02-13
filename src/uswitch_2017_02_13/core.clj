(ns uswitch-2017-02-13.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

;;#57
;; (= __ ((fn foo [x] (when (> x 0) (conj (foo (dec x)) x))) 5))

;; '(5 4 3 2 1)

#_(defn comp-dojo-reversed [& set-of-functions]
  (fn compose-fn
    ([] (str "go away..."))
    ([one] (compose-fn one set-of-functions))
    ([i [f & more]]
     (cond (nil? i) nil
           (nil? f) i
           :else (recur (f i) more)))))


(defn comp-dojo [& xs]
  (fn [& ys]
    (reduce #(%2 %1)
            (apply
             (last xs) ys)
            (rest (reverse xs)))))


(reduce + [1 2 3])

;; (reduce [count inc inc] [1 2 3]) - doest work

(reduce #(%2 %1) [1 2 3] [count inc inc])
(reduce #(%2 %1) [4 3 2 1] [rest])


(= [3 2 1] ((comp-dojo rest reverse) [1 2 3 4]))

(rest (reverse [1 2 3 4]))
;; (reverse (rest [1 2 3 4]))

(= 5 ((comp-dojo (partial + 3) second) [1 2 3 4]))

(= true ((comp-dojo zero? #(mod % 8) +) 3 5 7 9))

(= "HELLO" ((comp-dojo #(.toUpperCase %) #(apply str %) take) 5 "hello world"))


;; Golf score 62
#_(fn [& x]
  (fn [& y]
    (reduce #(%2 %1)
            (apply (last x) y) (rest (reverse x)))))
