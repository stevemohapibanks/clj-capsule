(ns clj-capsule.core
  (:require [clj-http.client :as client]))

(def *connection* {})
(def *auth-details* (ref {:account nil :token nil}))
(def *default-options* {:content-type :json :accept :json})

(defn set-auth-details
  [account token]
  (dosync (alter *auth-details* assoc :account account :token token)))
  
(defmacro with-connection-to
  [path & body]
  `(binding [*connection* (assoc *connection* :url (format "https://%s.capsulecrm.com/api/%s" (:account @*auth-details*) ~path)
                                              :options {:basic-auth [(:token @*auth-details*) "x"] :content-type :json :accept :json})]
    (try ~@body
      (catch Exception ex# (println ex#)))))
      
(defmacro with-query-params
  [params & body]
  `(binding [*connection* (assoc *connection* :url (:url *connection*)
                                              :options (assoc (:options *connection*) :query-params ~params))]
      ~@body))