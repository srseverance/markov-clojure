(defproject markov "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "The BSD 2-Clause License"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :main ^:skip-aot markov.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
