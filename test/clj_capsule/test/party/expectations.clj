(ns clj-capsule.test.party.expectations
  (:use clj-capsule.contact
				clj-capsule.party))


;
; Mocked JSON response and clojure data structure for get-parties
;
(def mock-parties-response
	{:status 200, 
		:headers {"date" "Tue, 26 Oct 2010 21:36:40 GMT", "server" "Apache", "set-cookie" "JSESSIONID=F822633D4FCC27735E6A259D6C1AB248.node1; Path=/; Secure", "connection" "close", "transfer-encoding" "chunked", "content-type" "application/json"}
		:body "{\"parties\":
						{
							\"person\":{
								\"id\":\"1\",
								\"contacts\":{
									\"email\":[
										{\"id\":\"10\",\"emailAddress\":\"s.holmes@home.com\"},
										{\"id\":\"11\",\"emailAddress\":\"s.holmes@work.com\"}]},
								\"firstName\":\"Sherlock\",
								\"lastName\":\"Holmes\"},
							\"organisation\":[
								{\"id\":\"2\",
									\"contacts\":\"\",
									\"name\":\"Scotland Yard\"},
								{\"id\":\"3\",
									\"contacts\":\"\",
									\"name\":\"Google\"}]}}"})

(def expected-parties
	{:person {
			:id "1"
			:contacts {
				:email [
					{:id "10" :emailAddress "s.holmes@home.com"}
					{:id "11" :emailAddress "s.holmes@work.com"}]}
			:firstName "Sherlock"
			:lastName "Holmes"}
	 :organisation [
			{:id "2" :contacts "" :name "Scotland Yard"}
			{:id "3" :contacts "" :name "Google"}
		]})

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


