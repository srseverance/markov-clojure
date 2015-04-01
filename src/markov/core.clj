(ns markov.core
  (:require [clojure.java.io :as io])
  (:gen-class))

(def gettysburg-address "/Volumes/Macintosh HD/Users/srseverance/Learning/corpus/gettysburg-address.txt")

(def sicp "/Volumes/Macintosh HD/Users/srseverance/Downloads/sicp.texi")

(def number-of-prefix 2)

(defn add-state [state-map prefix suffix]
  (update-in state-map [prefix] #(conj (or %1 []) %2) suffix))

(defn build [file-name]
  (with-open [source (io/reader file-name)]
    (->> source
         line-seq
         (mapcat #(clojure.string/split % #"\s+"))
         (cons :not-a-word)
         (cons :not-a-word)
         (filter #(not= ""%))
         (partition (inc number-of-prefix) 1)
         (reduce #(add-state %1 (butlast %2) (last %2)) {}))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (doseq [l gettysburg-address] (println l)))
