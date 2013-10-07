(ns compojure-intro.test.handler-itest
  (:require [midje.sweet :refer :all]
            [clj-http.client :as c]))

(def base-url "http://localhost:3000")

(comment
  (facts "about apis"
    (fact "v1 ping works"
      (let [{:keys [status body]} (c/get (str base-url "/api/v1/ping"))]
        status => 200
        body => "pong"))))
