(ns clj-capsule.party
  (:use clojure.contrib.json
        clj-capsule.core
        clj-capsule.contact)
  (:require [clj-http.client :as client]))

(defn get-parties
  ([] 
    (with-connection-to "party"
      (let [response (client/get (:url *connection*) (:options *connection*))]
        (-> response :body read-json :parties))))
  ([start limit]
    {:pre [(integer? start)
           (integer? limit)]}
    (with-connection-to "party"
      (with-query-params {"start" start "limit" limit}
        (let [response (client/get (:url *connection*) (:options *connection*))]
          (-> response :body read-json :parties))))))

(defn get-party
  [id]
  (with-connection-to (str "party/" id)
    (let [response (client/get (:url *connection*) (:options *connection*))
          party-json (-> response :body read-json)]
      (or (:person party-json) (:organisation party-json)))))

(defn search
  [query]
  (with-connection-to "party"
    (with-query-params {"q"  query}
      (let [response (client/get (:url *connection*) (:options *connection*))]
        (-> response :body read-json :parties)))))
  
