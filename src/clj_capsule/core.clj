(ns clj-capsule.core
  (:require [clj-http.client :as client]))

(def *account* "digital-science")
(def *token* "cd8d3b43afa1fad348fa7ee550c3cef4")
(def *session-details* (ref {:account nil :token nil}))

(defn set-session-details
  [account token]
  (dosync (alter *session-details* assoc :account account :token token)))

(defmacro in-capsule-session
  [{:keys [get post put delete with-query-params with-body]}
   & body]
  {:pre [(:account @*session-details*)
         (:token @*session-details*)]}
  `(let [http-fn# (cond
                   ~get client/get
                   ~post client/post
                   ~put client/put
                   ~delete client/delete)
         path# (or ~get ~post ~put ~delete)
         url# (format "https://%s.capsulecrm.com/api%s" (:account @*session-details*) path#)
         options# {:basic-auth [(:token @*session-details*) "x"]
                   :query-params ~with-query-params
                   :body ~with-body
                   :content-type :json
                   :accept :json}]
     (try
       (let [~'response (http-fn# url# options#)]
         ~@body)
       (catch Exception ex# (println ex#)))))



