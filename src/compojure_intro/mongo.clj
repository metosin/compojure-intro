(ns compojure-intro.mongo
  (:require [monger.operators :refer :all]
            [monger.core :as m]
            [monger.collection :as mc])
  (:import [org.bson.types ObjectId]))

(def uri "mongodb://127.0.0.1/clojure-intro")

(m/connect-via-uri! uri)

(comment
  ;; remove all
  (mc/remove :pizza)

  ;; add some pizzas
  (mc/insert :pizza {:name "quatro"})
  (mc/insert :pizza {:name "opera"})

  ;; list 'em
  (mc/find-maps :pizza))