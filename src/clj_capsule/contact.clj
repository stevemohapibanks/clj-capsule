(ns clj-capsule.contact)

;; Data structures used to store responses from the API
(defstruct address				:id :type :street :city :state :zip :country)
(defstruct email					:id :type :email-address)
(defstruct phone					:id :type :phone-number)
