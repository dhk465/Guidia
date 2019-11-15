
(defrecord Park [name dogs bicycle skating refreshment sport-fields playground architecture parking]) 
(def lst-park [])
;;                       name     dogs cycl sktng refr sfied pgr arch prkg 
(def Bertramka (Park. "Bertramka" nil true false true false nil nil true))
(def lst-park (conj lst-park Bertramka ))
;;                                                name     dogs cycl sktng  refr sfied pgr arch prkg 
(def Frantiskanska-zahrada (Park. "Františkánská zahrada" false true false true false true nil false))
(def lst-park (conj lst-park Frantiskanska-zahrada ))
;;                              name    dogs cycl sktng refr sfied pgr arch prkg 
(def Obora-hvezda (Park. "Obora Hvězda" nil true  false  true false true nil  true))
(def lst-park (conj lst-park Obora-hvezda))
;;                 name   dogs cycl sktng refr sfied pgr arch prkg 
(def Kampa (Park. "Kampa" nil true  true  true false true nil  false))
(def lst-park (conj lst-park Kampa))
;;                                       name   dogs cycl sktng refr spfld pgr arch prkg 
(def Kinskeho-zahrada (Park. "Kínského zahrada" nil false false true false true nil false))
(def lst-park (conj lst-park Kinskeho-zahrada))
;;                       name   dogs cycl sktng refr sfied pgr arch prkg 
(def Klamovka (Park. "Klamovka" nil false false true false true nil true))
(def lst-park (conj lst-park Klamovka))
;;                         name   dogs cycl sktng refr sfied pgr arch prkg 
(def Landronka (Park. "Landronka" nil false true  true false true nil true))
(def lst-park (conj lst-park Landronka))
;;                  name dogs  cycl sktng refr sfied pgr arch prkg 
(def Letna (Park. "Letná" nil false true true false true nil true))
(def lst-park (conj lst-park Letna))
;;                  name    dogs cycl sktng refr sfied pgr arch prkg 
(def Petrin (Park. "Petřín" nil  true  true true false true nil true ))
(def lst-park (conj lst-park Petrin))
;;                                  name    dogs cycl sktng refr sfied pgr arch prkg 
(def Riegrovy-sady (Park. "Riegrovy sady" true  true  true true true true nil false))
(def lst-park (conj lst-park Riegrovy-sady))
;;                        name    dogs cycl sktng refr sfied pgr arch prkg 
(def Stromovka (Park. "Stromovka" nil  true  true true false true nil true))
(def lst-park (conj lst-park Stromovka))
;;                  name    dogs cycl sktng refr sfied pgr arch prkg 
(def Vysehrad (Park. "Vyšehrad" nil true false true false true nil true ))
(def lst-park (conj lst-park Vysehrad))
;;                                name    dogs cycl sktng refr sfied pgr arch prkg 
(def Vojanovy-sady (Park. "Vojanovy sady" nil  nil  nil   nil  nil   nil  nil nil))
(def lst-park (conj lst-park Vojanovy-sady))