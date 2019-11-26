(ns ica.parkData)

(defrecord Park [name dogs bicycle skating refreshment sportsfield playground parking architecture])
(def lst-park
 "It creates a vector where the data about the parks will be appended."
 [])

;;                       name     dogs cycl sktng refr sfied pgr prkg                 arch
(def Bertramka (Park. "Bertramka" nil true false true false nil  true ["W.A Mozart museum"]))
(def lst-park (conj lst-park Bertramka ))

;;                                                 name    dogs cycl sktng refr sfied pgr prkg   arch
(def Frantiskanska-zahrada (Park. "Františkánská zahrada" false true false true false true false []))
(def lst-park (conj lst-park Frantiskanska-zahrada))
;;                              name    dogs cycl  sktng  refr sfied pgr   prkg          arch
(def Obora-hvezda (Park. "Obora Hvězda" true true  false  true false true true ["summer residence"] ))
(def lst-park (conj lst-park Obora-hvezda))
;;                 name   dogs cycl sktng refr sfied pgr   prkg   arch
(def Kampa (Park. "Kampa" nil true  true  true false true  false ["Kampa museum"]))
(def lst-park (conj lst-park Kampa))
;;                                       name   dogs cycl sktng refr spfld pgr   prkg  arch
(def Kinskeho-zahrada (Park. "Kínského zahrada" nil false false true false true false ["church of st. Michal" "monument to Hana Kvapilová"]))
(def lst-park (conj lst-park Kinskeho-zahrada))
;;                       name   dogs cycl  sktng  refr sfied pgr prkg       arch
(def Klamovka (Park. "Klamovka" true false false true false true true ["small castle represented as a restaurant"]))
(def lst-park (conj lst-park Klamovka))
;;                         name   dogs cycl sktng  refr sfied pgr  prkg arch
(def Landronka (Park. "Landronka" true false true  true false true true []))
(def lst-park (conj lst-park Landronka))
;;                  name dogs  cycl sktng refr sfied pgr prkg arch
(def Letna (Park. "Letná" nil false true true false true true ["monument to sculptor Vratislav Karl Novák" "small castle: Letenský zámeček"]))
(def lst-park (conj lst-park Letna))
;;                  name    dogs cycl sktng refr sfied pgr  prkg   arch
(def Petrin (Park. "Petřín" nil  true  true true false true  true ["astronomical observatory" ]))
(def lst-park (conj lst-park Petrin))
;;                                  name    dogs cycl sktng refr sfied pgr   prkg  arch
(def Riegrovy-sady (Park. "Riegrovy sady" true  true  true true  true true  false ["monument to F.L.Riegr"]))
(def lst-park (conj lst-park Riegrovy-sady))
;;                        name    dogs cycl sktng refr  sfied pgr  prkg arch
(def Stromovka (Park. "Stromovka" true  true  true true false true true ["Planetarium" "summer residence" "Rudolf gallery"]))
(def lst-park (conj lst-park Stromovka))
;;                      name    dogs cycl sktng refr sfied pgr prkg arch
(def Vysehrad (Park. "Vyšehrad" nil true false true false true true ["cemetory of famous people" "monument to st. Vaclav" "church of st.Petr and Pavel" "rotunda of st. Martin"]))
(def lst-park (conj lst-park Vysehrad))
;;                                name    dogs cycl sktng refr sfied pgr  prkg arch
(def Vojanovy-sady (Park. "Vojanovy sady" nil  nil  nil   nil  nil   nil  nil  []))
(def lst-park (conj lst-park Vojanovy-sady))
