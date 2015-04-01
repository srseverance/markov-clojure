(ns markov.core
  (:require [clojure.java.io :as io])
  (:gen-class))

(def gettysburg-address "/Volumes/Macintosh HD/Users/srseverance/Learning/corpus/gettysburg-address.txt")
(def simple-txt "/Volumes/Macintosh HD/Users/srseverance/Learning/Clojure/markov/resources/simple.txt")
(def sicp "/Volumes/Macintosh HD/Users/srseverance/Downloads/sicp.texi")
(def the-common-law "/Volumes/Macintosh HD/Users/srseverance/Downloads/the-common-law.txt")
(def flatland "/Volumes/Macintosh HD/Users/srseverance/Downloads/flatland.txt")

(def number-of-prefix 2)
(def starting-prefix (repeat number-of-prefix :not-a-word))

(defn add-state [state-map prefix suffix]
  (update-in state-map [prefix] #(conj (or %1 []) %2) suffix))

(defn build [file-name]
  (with-open [r (io/reader file-name)]
    (->> r
       line-seq
       (mapcat #(clojure.string/split % #"\s+"))
       (filter #(not= "" %))
       (concat starting-prefix)
       (partition (inc number-of-prefix) 1)
       (reduce #(add-state %1 (butlast %2) (last %2)) {}))))

(defn generate [state-map n]
  (letfn [(do-the-needful [prefix n]
            (lazy-seq
             (let [suffix (rand-nth (state-map prefix))]
               (if (or (zero? n) (nil? suffix))
                 nil
                 (cons suffix
                       (do-the-needful (-> prefix pop (conj suffix))
                                 (dec n)))))))]
    (doseq [s (interpose " " (do-the-needful (into clojure.lang.PersistentQueue/EMPTY starting-prefix) n))]
      (print s))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [[file-name n-str] args]
    (generate (build file-name) (Integer/parseInt n-str))
    (println)))
