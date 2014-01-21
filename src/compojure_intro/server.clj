(ns compojure-intro.server
  (:require [ring.adapter.jetty :as jetty]
            [compojure-intro.handler :as handler])
  (:gen-class))

(defn -main
  [& [port]]
  (let [port (Integer. (or port (System/getenv "PORT") 3000))]
    (jetty/run-jetty #'handler/app {:port  port
                                    :join? false})))
