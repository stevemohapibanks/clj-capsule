(ns clj-capsule.test.party
  (:use clj-capsule.core 
				clj-capsule.party 
				clj-capsule.contact :reload)
  (:use clj-capsule.test.party.expectations
				clojure.test
				clojure.contrib.mock
				[clj-http.client :as client :only (get)]))

(deftest test-get-parties
	(let [expected-options {:basic-auth ["token" "x"] :content-type :json :accept :json}]

		(set-auth-details "account-name" "token")

		(testing "with no range limit set"
			(expect [client/get 
								(has-args ["https://account-name.capsulecrm.com/api/party" expected-options]
									(returns mock-parties-response))]
				(is (= expected-parties (get-parties)))
				(is (map? (:person (get-parties))))
				(is (vector? (:organisation (get-parties))))
				(is (= 2 (count (:organisation (get-parties)))))))

		(testing "with range limits set"
			(expect [client/get 
								(has-args ["https://account-name.capsulecrm.com/api/party"
													 {:query-params {"start" 1, "limit" 10}, :basic-auth ["token" "x"], :content-type :json, :accept :json}]
									(returns mock-parties-response))]
				(is (= expected-parties (get-parties 1 10)))))
		(set-auth-details nil nil)))

(deftest test-get-party
	(let [expected-options {:basic-auth ["token" "x"] :content-type :json :accept :json}]
		(set-auth-details "account-name" "token")
		(expect [client/get 
							(has-args ["https://account-name.capsulecrm.com/api/party/1" expected-options]
								(returns mock-person-response))]
			(is (= expected-person (get-party "1"))))
		(set-auth-details nil nil)))

; (deftest test-search
; 	(let [session (make-session "account-name" "token")
; 				expected-options {:basic-auth ["token" "x"] :content-type :json :accept :json}
; 				expected-response "<tag>some data</tag>"]
; 		(expect [client/get
; 							(has-args ["https://account-name.capsulecrm.com/api/party"
; 								 				 {:query-params {"q" "This is my query"} :basic-auth ["token" "x"], :content-type :json, :accept :json}]
; 								(returns expected-response))]
; 			(is (= expected-response (search session "This is my query"))))))
