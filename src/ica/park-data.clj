
(defrecord Park [name dogs bicycle skating refreshment sport-fields playground architecture parking restroom]) 
(def lst-park [])
;;                       name     dogs cycl sktng refr sfied pgr arch prkg WC
(def Bertramka (Park. "Bertramka" nil true false true false nil nil true true ))
(def lst-park (conj lst-park Bertramka ))
;;                                                name     dogs cycl sktng  refr sfied pgr arch prkg WC
(def Frantiskanska-zahrada (Park. "Františkánská zahrada" false true false true false true nil false true))
(def lst-park (conj lst-park Frantiskanska-zahrada ))
;;                              name    dogs cycl sktng refr sfied pgr arch prkg WC
(def Obora-hvezda (Park. "Obora Hvězda" nil true  false  true false true nil  true true))
(def lst-park (conj lst-park Obora-hvezda))
;;                 name   dogs cycl sktng refr sfied pgr arch prkg WC
(def Kampa (Park. "Kampa" nil true  true  true false true nil  false true))
(def lst-park (conj lst-park Kampa))
;;                                       name   dogs cycl sktng refr sfied pgr arch prkg WC
(def Kinskeho-zahrada (Park. "Kínského zahrada" nil false false true false true nil false true))
(def lst-park (conj lst-park Kampa))
;;                       name   dogs cycl sktng refr sfied pgr arch prkg WC
(def Klamovka (Park. "Klamovka" nil false false true false true nil true true))
(def lst-park (conj lst-park Kampa))
;;                         name   dogs cycl sktng refr sfied pgr arch prkg WC
(def Landronka (Park. "Landronka" nil false true  true false true nil true true))
(def lst-park (conj lst-park Kampa))
;;                  name dogs  cycl sktng refr sfied pgr arch prkg WC
(def Letna (Park. "Letná" nil false true true false true nil true true))
(def lst-park (conj lst-park Letna))
;;                  name    dogs cycl sktng refr sfied pgr arch prkg WC
(def Petrin (Park. "Petřín" nil  true  true true false true nil true true))
(def lst-park (conj lst-park Petrin))
;;                                  name    dogs cycl sktng refr sfied pgr arch prkg WC
(def Reigerovy-sady (Park. "Reigerovy sady" true  true  true true true true nil false true))
(def lst-park (conj lst-park Letna))
;;                        name    dogs cycl sktng refr sfied pgr arch prkg WC
(def Stromovka (Park. "Stromovka" nil  true  true true false true nil true true))
(def lst-park (conj lst-park Stromovka))
;;                  name    dogs cycl sktng refr sfied pgr arch prkg WC
(def Vysehrad (Park. "Vyšehrad" nil true false true false true nil true true))
(def lst-park (conj lst-park Vysehrad))
;;                                name    dogs cycl sktng refr sfied pgr arch prkg WC
(def Vojanovy-sady (Park. "Vojanovy sady" nil  nil  nil   nil  nil   nil  nil nil  nil))
(def lst-park (conj lst-park Vojanovy-sady))