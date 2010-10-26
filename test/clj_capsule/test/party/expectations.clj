(ns clj-capsule.test.party.expectations
  (:use clj-capsule.contact
				clj-capsule.party))


;
; Mocked JSON response and clojure data structure for get-parties
;
(def mock-parties-response
	{:status 200, 
	 :headers {"date" "Tue, 19 Oct 2010 20:07:52 GMT", "server" "Apache", "set-cookie" "JSESSIONID=852B874C9E08DBFD69CB029089116472.node7; Path=/; Secure", "connection" "close", "transfer-encoding" "chunked", "content-type" "application/json"},
	 :body "{\"parties\":[{\"person\":{\"id\":\"1\"}},
												{\"person\":{\"id\":\"2\"}},
												{\"organisation\":{\"id\":\"3\"}}]}"})

(def expected-parties
	[{:person {:id "1"}}
	 {:person {:id "2"}}
	 {:organisation {:id "3"}}])

;
; Mocked JSON response and expected clojure data structure for get-party
;
(def mock-person-response
	{:status 200, 
	 :headers {"date" "Tue, 19 Oct 2010 20:07:52 GMT", "server" "Apache", "set-cookie" "JSESSIONID=852B874C9E08DBFD69CB029089116472.node7; Path=/; Secure", "connection" "close", "transfer-encoding" "chunked", "content-type" "application/json"},
	 :body "{\"person\":{\"id\":\"1\",
												\"contacts\":{\"email\":[{\"id\":\"10\",\"type\":\"Home\",\"emailAddress\":\"s.holmes@home.com\"},
																		 						 {\"id\":\"11\",\"type\":\"Work\",\"emailAddress\":\"s.holmes@work.com\"}]},
												\"pictureURL\":\"https:\\/\\/assets0.capsulecrm.com\\/theme\\/default\\/images\\/person_avatar_70.png?542930\",
												\"firstName\":\"Sherlock\",
												\"lastName\":\"Holmes\",
												\"jobTitle\":\"Detective\",
												\"organisationId\":\"1000\",
												\"organisationName\":\"Scotland Yard\"}}"})

(def expected-person
	{:id "1"
	 :contacts {:email [{:id "10" :type "Home" :emailAddress "s.holmes@home.com"}
										 	{:id "11" :type "Work" :emailAddress "s.holmes@work.com"}]}
	 :pictureURL "https://assets0.capsulecrm.com/theme/default/images/person_avatar_70.png?542930"
	 :firstName "Sherlock"
	 :lastName "Holmes"
	 :jobTitle "Detective"
	 :organisationId "1000"
	 :organisationName "Scotland Yard"})


