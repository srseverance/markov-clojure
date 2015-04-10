(ns markov.core
  (:require [clojure.java.io :as io])
  (:gen-class))

(def number-of-prefix 2)
(def starting-prefix (repeat number-of-prefix :not-a-word))

(defn add-state [state-map prefix suffix]
  (update-in state-map [prefix] #(conj (or %1 []) %2) suffix))

(defn file->words [file-name]
  (with-open [r (io/reader file-name)]
    (doall
     (->> r
          line-seq
          (mapcat #(clojure.string/split % #"\s+"))
          (filter #(not= "" %))))))

(defn build [words]
  (->> words
       (concat starting-prefix)
       (partition (inc number-of-prefix) 1)
       (reduce #(add-state %1 (butlast %2) (last %2)) {})))

(defn generate [state-map]
  (letfn [(do-the-needful [prefix]
            (lazy-seq
             (let [suffix (rand-nth (state-map prefix))]
               (when suffix
                 (cons suffix
                       (do-the-needful (-> prefix pop (conj suffix))))))))]
    (do-the-needful (into clojure.lang.PersistentQueue/EMPTY starting-prefix))))

(defn print-words [words]
  (doseq [s (interpose " " words)]
    print s))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [[file-name n-str] args
        n (Integer/parseInt n-str)]
    (->> file-name
         file->words
         build
         generate
         (take n)
         print-words)))
