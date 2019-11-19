
(defrecord Park [name dogs bicycle skating refreshment sport-fields playground architecture parking]) 
(def lst-park 
 "It creates a vector where the data about the parks will be appended."
 [])
;;                       name     dogs cycl sktng refr sfied pgr arch prkg 
(def Bertramka (Park. "Bertramka" nil true false true false nil  ["W.A Mozart museum"] true))
(def lst-park (conj lst-park Bertramka ))
;;                                                name     dogs cycl sktng  refr sfied pgr arch prkg 
(def Frantiskanska-zahrada (Park. "Františkánská zahrada" false true false true false true [] false))
(def lst-park (conj lst-park Frantiskanska-zahrada ))
;;                              name    dogs cycl sktng refr sfied pgr arch prkg 
(def Obora-hvezda (Park. "Obora Hvězda" true true  false  true false true ["summer residence"]  true))
(def lst-park (conj lst-park Obora-hvezda))
;;                 name   dogs cycl sktng refr sfied pgr arch prkg 
(def Kampa (Park. "Kampa" nil true  true  true false true ["Kampa museum"] false))
(def lst-park (conj lst-park Kampa))
;;                                       name   dogs cycl sktng refr spfld pgr arch prkg 
(def Kinskeho-zahrada (Park. "Kínského zahrada" nil false false true false true ["church of st. Michal" "monument to Hana Kvapilová"] false))
(def lst-park (conj lst-park Kinskeho-zahrada))
;;                       name   dogs cycl sktng refr sfied pgr arch prkg 
(def Klamovka (Park. "Klamovka" true false false true false true ["a small castle represented as a restaurant"] true))
(def lst-park (conj lst-park Klamovka))
;;                         name   dogs cycl sktng refr sfied pgr arch prkg 
(def Landronka (Park. "Landronka" true false true  true false true [] true))
(def lst-park (conj lst-park Landronka))
;;                  name dogs  cycl sktng refr sfied pgr arch prkg 
(def Letna (Park. "Letná" nil false true true false true ["monument to sculptor Vratislav Karl Novák" "Letenský zámeček"] true))
(def lst-park (conj lst-park Letna))
;;                  name    dogs cycl sktng refr sfied pgr arch prkg 
(def Petrin (Park. "Petřín" nil  true  true true false true ["astronomical observatory" ] true ))
(def lst-park (conj lst-park Petrin))
;;                                  name    dogs cycl sktng refr sfied pgr arch prkg 
(def Riegrovy-sady (Park. "Riegrovy sady" true  true  true true true true   ["monument to F.L.Riegr"] false))
(def lst-park (conj lst-park Riegrovy-sady))
;;                        name    dogs cycl sktng refr sfied pgr arch prkg 
(def Stromovka (Park. "Stromovka" true  true  true true false true ["Planetarium" "summer residence" "Rudolf gallery"] true))
(def lst-park (conj lst-park Stromovka))
;;                      name    dogs cycl sktng refr sfied pgr arch prkg 
(def Vysehrad (Park. "Vyšehrad" nil true false true false true ["cemetory of famous people" "monument to st. Vaclav" "church of st.Petr and Pavel" "rotunda of st. Martin"] true ))
(def lst-park (conj lst-park Vysehrad))
;;                                name    dogs cycl sktng refr sfied pgr arch prkg 
(def Vojanovy-sady (Park. "Vojanovy sady" nil  nil  nil   nil  nil   nil  [] nil))
(def lst-park (conj lst-park Vojanovy-sady))