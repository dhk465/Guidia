(defproject classify "0.1"
  :description "An example of using experiment/classification on mnist."
  :repositories {"hellonico"   {:sign-releases false :url "https://repository.hellonico.info/repository/hellonico"}}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [thinktopic/experiment "0.9.22"]
                 [net.mikera/imagez "0.12.0"]
                 [thinktopic/think.image "0.4.8" ]
                 [thinktopic/think.datatype "0.3.17"]]
  :jvm-opts ["-Xmx8000m"])
