(ns clj-capsule.party
  (:use clojure.contrib.json
        clojure.contrib.prxml
        clj-capsule.core)
  (:require [clj-http.client :as client]))

(defn get-parties
  ([]
     (in-capsule-session {:get "/party"}
                         (-> response :body read-json :parties)))
  ([start limit]
     {:pre [(integer? start)
            (integer? limit)]}
     (in-capsule-session {:get "/party"
                          :with-query-params {"start" start "limit" limit}}
                         (-> response :body read-json :parties))))

(defn get-party
  [id]
  (in-capsule-session {:get (str "/party/" id)}
                      (let [party-json (-> response :body read-json)]
                        (or (:person party-json) (:organisation party-json)))))

(defn raw-search
  [query]
  (in-capsule-session {:get "/party"
                       :with-query-params {"q" query}}
                      (-> response :body read-json :parties)))

(defn- normalise-parties
  [parties type]
  (map #(merge {:type type} %)
       (cond
        (map? parties) [parties]
        (nil? parties) []
        :else parties)))

(defn search
  [query]
  (let [results (raw-search query)
        people (normalise-parties (:person results) "person")
        organisations (normalise-parties (:organisation results) "organisation")]
    (vec (concat people organisations))))

(defn tag-party
  [id & tags]
  (doseq [tag tags]
    (in-capsule-session {:post (str "/party/" id "/tag/" tag)})))
