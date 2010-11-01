(ns clj-capsule.test.core
  (:use [clj-capsule.core] :reload)
  (:use clojure.test
        clojure.contrib.mock
        [clj-http.client :as client]))

(deftest test-with-query-params
  (binding [*connection* {:url "http://account-name.capsulecrm.com/api"
                          :options {:content-type "application/json"}}]
    (with-query-params {:q "a query"}
      (is (= (:options *connection*) {:content-type "application/json" :query-params {:q "a query"}})))))