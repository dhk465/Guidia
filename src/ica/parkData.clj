(ns ica.parkData
  "a namespace that contains information about Prague's parks"
  (:gen-class))

(defrecord Park [name dogs bicycle skating refreshment sportsfield playground parking])
(def lst-park
 "It creates a vector where the data about the parks will be appended."
 [])

(defn make-park [name dog cycl sktng refr spfld pgr prkg]
  "It creates an instance of Park class so that other namespaces securely access to parkData."
  (Park. name dog cycl sktng refr spfld pgr prkg))

;;                       name     dogs cycl sktng refr sfied pgr prkg
(def Bertramka (Park. "Bertramka" nil true false true false nil  true))
(def lst-park (conj lst-park Bertramka ))

;;                                                 name    dogs cycl sktng refr sfied pgr prkg
(def Frantiskanska-zahrada (Park. "Františkánská zahrada" false true false true false true false))
(def lst-park (conj lst-park Frantiskanska-zahrada))
;;                              name    dogs cycl  sktng  refr sfied pgr   prkg
(def Obora-hvezda (Park. "Obora Hvězda" true true  false  true false true true))
(def lst-park (conj lst-park Obora-hvezda))
;;                 name   dogs cycl sktng refr sfied pgr   prkg
(def Kampa (Park. "Kampa" nil true  true  true false true  false))
(def lst-park (conj lst-park Kampa))
;;                                       name   dogs cycl sktng refr spfld pgr   prkg
(def Kinskeho-zahrada (Park. "Kínského zahrada" nil false false true false true false))
(def lst-park (conj lst-park Kinskeho-zahrada))
;;                       name   dogs cycl  sktng  refr sfied pgr prkg
(def Klamovka (Park. "Klamovka" true false false true false true true))
(def lst-park (conj lst-park Klamovka))
;;                         name   dogs cycl sktng  refr sfied pgr  prkg
(def Landronka (Park. "Landronka" true false true  true false true true))
(def lst-park (conj lst-park Landronka))
;;                  name dogs  cycl sktng refr sfied pgr prkg
(def Letna (Park. "Letná" nil false true true false true true))
(def lst-park (conj lst-park Letna))
;;                  name    dogs cycl sktng refr sfied pgr  prkg
(def Petrin (Park. "Petřín" nil  true  true true false true  true))
(def lst-park (conj lst-park Petrin))
;;                                  name    dogs cycl sktng refr sfied pgr   prkg
(def Riegrovy-sady (Park. "Riegrovy sady" true  true  true true  true true  false))
(def lst-park (conj lst-park Riegrovy-sady))
;;                        name    dogs cycl sktng refr  sfied pgr  prkg
(def Stromovka (Park. "Stromovka" true  true  true true false true true))
(def lst-park (conj lst-park Stromovka))
;;                      name    dogs cycl sktng refr sfied pgr prkg
(def Vysehrad (Park. "Vyšehrad" nil true false true false true true))
(def lst-park (conj lst-park Vysehrad))
;;                                name    dogs cycl sktng refr sfied pgr  prkg
(def Vojanovy-sady (Park. "Vojanovy sady" nil  nil  nil   nil  nil   nil  nil))
(def lst-park (conj lst-park Vojanovy-sady))
