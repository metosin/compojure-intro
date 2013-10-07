(ns compojure-intro.handler
  (:require [compojure.handler :as handler]
            [compojure.route :refer [resources not-found]]
            [ring.middleware.json :as middleware]
            [ring.util.response :refer [response content-type]]
            [cheshire.core :as cheshire]
            [compojure.core :refer :all]))

(defn json [form]
  (-> form
    cheshire/encode
    response
    (content-type "application/json; charset=utf-8")))

(def ping-route
  (GET "/ping" [] "pong"))

(defn info-route [version]
  (GET "/info" [] (json {:version version
                         :date    (java.util.Date.)})))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/hello" [name] (str "hello, " name))
  (GET "/user/:id" [id] (str "user #" id))

  (GET "/pizza/:id" [id] (json {:id 1 :name "quatro"}))
  (POST "/pizza" {data :json-params} (json data))

  (context "/api" []
    (context "/v1" []
      ping-route)
    (context "/v2" []
      ping-route
      (info-route 2)))

  (resources "/")
  (not-found "Not Found"))

(def app
  (->
    app-routes
    handler/site
    middleware/wrap-json-body
    middleware/wrap-json-params
    #_middleware/wrap-json-response))
