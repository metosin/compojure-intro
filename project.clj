(defproject compojure-intro "0.1.0-SNAPSHOT"
  :description "Compojure Intro"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [cheshire "5.3.1"]
                 [ring/ring-json "0.2.0"]
                 [ring/ring-jetty-adapter "1.2.1"]
                 [com.novemberain/monger "1.7.0"]
                 [compojure "1.1.6"]]
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler compojure-intro.handler/app}
  :main compojure-intro.server
  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein2-eclipse "2.0.0"]
                             [lein-idea "1.0.1"]]
                   :dependencies [[ring-mock "0.1.5"]
                                  [midje "1.6.0"]
                                  [clj-http "0.7.8" :exclusions [commons-codec]]]}})
