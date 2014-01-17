(ns compojure-intro.handler
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.json :as middleware]
            [ring.util.response :refer [response content-type]]
            [cheshire.core :as cheshire]
            [compojure.core :refer :all]))

(defn json [form]
  (-> form
    cheshire/encode
    response
    (content-type "application/json; charset=utf-8")))

(defn ping-route [version]
  (GET "/ping" []
    (json {:ping "pong"
           :date (java.util.Date.)
           :version version})))

(defroutes app-routes

  ; static route
  (GET "/" [] "Hello World")

  ; query paramters
  (GET "/hello" [name] (str "hello, " name))

  ; path parameters returning json
  (GET "/pizza/:id" [id]
    (json {:id id
           :name "Quatro"
           :toppings [:ham :olives :etc]}))

  ; echo params in json
  (POST "/pizza" {data :params} (json data))

  ; contexts /api/v2/ping etc.
  (context "/api" []
    (context "/v:version" [version]
      (ping-route version)))

  ; serve public resources
  (route/resources "/")

  ; nothing matched
  (route/not-found "Not Found"))

(def app
  (->
    app-routes
    handler/site
    middleware/wrap-json-body
    middleware/wrap-json-params
    #_middleware/wrap-json-response))
