# Guidia Chatbot

The ICA Project in the Symbolic Computation class of Computing B.Sc.
(grad. expected in 2021)


## Objective

To create a chatbot that answers a sentence(s) that correspond(s) to a specified
structure of the English language
The chatbot's main theme is to recommend Prague's thirteen major parks to the
user in regard to the user's preferences.

A deep learning agent is also to be added to the chatbot made in [the ICA project](https://github.com/dhk465/symbolic_comp_one).

In addition to the Prague guide chatbot "Guidia", the application recognizes a picture given by the user, and determines if the object in the picture is either a **linden tree** or a **spruce tree**.


## Requirement

* [Leiningen](https://leiningen.org/) version 2.0 or higher


## Installation

Guidia chatbot is hosted on GitHub. You can use the commands below to
download this chatbot.
```
git clone https://github.com/dhk465/symbolic_comp_one.git
cd symbolic_comp_one
```


## Usage

Open a terminal or command-line in the repository where project.clj is located.
Then type the line below:
```
lein run
```
Leiningen will download and install all necessary libraries for a short moment.

```
Guidia=> If you want something more specific, tell me more what you wish.
User=> quit
Guidia=> Bye!
```
Guidia can be exited by certain keywords in [recog_phrases.json](https://github.com/dhk465/symbolic_comp_one/blob/master/src/ica/recog_phrases.json).


## Examples

### Introduction

In this part, a user is being greeted and offered to choose between parks and trees or exit the chatbot.In case if the user doesn't choose anything from this list, the programme will say goodbye to the user and turn off.
```
Guidia=> Hello, my name is Guidia, your guide to Prague.
Guidia=> Type 'park' if you want a guide to Prague's parks.
Guidia=> Or type 'tree' if you want a guide to Prague's trees.
Guidia=> Or type 'quit' if you want to say goodbye.
```


### Park example

Guidia gives the user a list of parks in Prague which match the wishes
that the user type.
The match-able keywords may include pets, cycling, skating, bars/restaurants,
sports, playgrounds for children, and parking.
More keywords for park recommindation can be added to [recog_phrases.json](https://github.com/dhk465/symbolic_comp_one/blob/master/src/ica/recog_phrases.json), from the begining till the line 10 inclusively  .
```
Guidia=> Tell me what you would like to see or like to do in Prague.
Guidia=> I can suggest a park in Prague for you.
User=> I would like to ride a bike and eat something in a park.
Guidia=> I would recommend Bertramka, Františkánská zahrada, Obora Hvězda, Kampa, Petřín, Riegrovy sady, Stromovka, and Vyšehrad.
Guidia=> If you want something more specific, tell me more what you wish.
```


### Tree example

Guidia offers a user a tree that fits users description
The match-able keywords for this part of chatbot include cones, flower, nuts, needles and board. From this part of the chatbot it is possible to switch to the next part, which is image classififcation.
Additional keywords for this part of chatbot can be added to [recog_phrases.json](https://github.com/dhk465/symbolic_comp_one/blob/master/src/ica/recog_phrases.json), starting from line 11.
```
Guidia=> Now I am your guide to Prague's trees.
Guidia=> Describe the tree you saw.
Guidia=> I will try to name it.
Guidia=> Type 'picture' and I will say what it is.
Guidia=> Type 'forget' if you want to start over again.
User=> The tree has needles.
Guidia=> I would say Spruce.
Guidia=> If you want something more specific, tell me more what you wish.
```


### Image classification

In this part of the chatbot it is possible to identify trees by a photo. A user can drag and drop the picture or put the path to the file. 
```
User=> picture
Write the path to your picture or drag and drop your picture:
images/sprucesample2.jpg
That's a spruce tree!
User=>
```


## Limitations

Negative sentences from the user-input do not register as false in the record,
meaning that users cannot make a negative wish
e.g. "I do not want dogs in the park".

Guidia also generalizes information on all categories. For example,
Guidia always returns parks with any kind of sports facilities in response
to a user-input like "swim" or "tennis". So the information given back to the
user may not be very accurate.

Linguistic conjunctions containing "or" is considered as "and".
Guidia does not have any complex understandings of conjunctions
but only track the phrases in [recog_phrases.json](https://github.com/dhk465/symbolic_comp_one/blob/master/src/ica/recog_phrases.json).

Since Guidia operates on lists of keywords, it also does not understand
differences of tenses or conjugations. It is recommended for the user to use
the present simple tense.

The accuracy of the image classification is only 63%.
It is highly likely that a user-given picture is wrongly identified.


## Dependencies

This chatbot depends on a multiple of libraries that provided code snippets
or functions pre-defined by the authors listed below.

* Anderson, M. (2019) '[imagez](https://github.com/mikera/imagez)', 0.12.0, _GitHub_.
* Hinman, M. L. (2019) '[Cheshire Clojure JSON encoding/decoding](https://github.com/dakrone/cheshire)', 5.9.0, _GitHub_.
* Hinman, M. L. (2018) '[Natural Language Processing in Clojure (opennlp)](https://github.com/dakrone/clojure-opennlp)', 0.5.0, _GitHub_.
* r0man (2014) '[Inflections](https://github.com/r0man/inflections-clj)', 0.13.2, _GitHub_.
* ThinkTopic, Anderson, M. (2018) '[Cortex](https://github.com/originrose/cortex)', 0.9.22, _GitHub_.


## References

* Deshpande, A. (2016) A Beginner’s Guide To Understanding Convolutional Neural Networks. Available at: https://adeshpande3.github.io/adeshpande3.github.io/A-Beginner's-Guide-To-Understanding-Convolutional-Neural-Networks/ [Accessed: 29 December 2019].
* Modrzyk, N. (2017) fruits classification using clojure cortex. Available at: http://blog.hellonico.info/clojure/using_cortex/ [Accessed: 10 January 2020].
* Praha.eu (2019) PRAŽSKÉ PARKY. Available at: http://www.praha.eu/jnp/cz/co_delat_v_praze/parky/index.html [Accessed: 14 November 2019].


## License

Copyright © 2019 Daehee Kim (dhk465 and MockTurtle7), Ilyas Sakhanov (ilyassakhanov and Ivan Ivanov), Sulieman Al Rustom (Slemanof)

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
