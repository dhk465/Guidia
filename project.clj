(defproject ica1 "0.1.0-SNAPSHOT"
  :description "The first ICA Project in the Symbolic Computation class of Computing B.Sc. (grad. expected in 2021)"
  :url "https://github.com/dhk465/symbolic_comp_one/"
  :min-lein-version "2.0.0"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [clojure-opennlp "0.5.0"]
                 [cheshire "5.9.0"]
                 [inflections "0.13.2"]]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.9.0"]]
                   :plugins [[lein-marginalia "0.8.0"]]}
             :1.5 {:dependencies [[org.clojure/clojure "1.5.1"]]}
             :1.6 {:dependencies [[org.clojure/clojure "1.6.0"]]}
             :1.7 {:dependencies [[org.clojure/clojure "1.7.0"]]}
             :1.8 {:dependencies [[org.clojure/clojure "1.8.0"]]}}
  :aliases {"all" ["with-profile" "dev,1.5:dev,1.6:dev,1.7:dev,1.8:dev"]}
  :main ica.core)
