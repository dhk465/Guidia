# symbolic_comp_one

The first ICA Project in the Symbolic Computation class of Computing B.Sc.
(grad. expected in 2021)

## Objectives

Creating a chatbot that answers a sentence(s) that correspond(s) to a specified
structure of the English language

The chatbot's main theme is to inform users of Prague's thirteen major parks
and their details.

## Requirement

* [Leiningen](https://leiningen.org/) version 2.0 or higher

## Installation

This application is hosted on GitHub. You can use the commands below to
download this chatbot.
```
git clone https://github.com/dhk465/symbolic_comp_one.git
cd symbolic_comp_one
```
Have fun around Prague's parks!

## Usage

Open a terminal or cmd in the repository where project.clj is located.

Then type the line below:
```bash
lein run
```

## Examples

TODO

```bash
User=> (TBA)
Chatbot=> (TBA)
```


## Limitations

Negative sentences from the user-input do not register as false in the record, meaning that users cannot make a negative wish.
phrases containing "or" are both considered as "and"
no verb conjugations that the chatbot does not recognize verb forms that are not present in the json (eat != eating)


## Dependencies

This chatbot is dependent on a multiple of libraries that provided with
code snippets or functions pre-defined by the coders/programmers listed below.

* Hinman, M. L.. (2018) '[Natural Language Processing in Clojure (opennlp)](https://github.com/dakrone/clojure-opennlp)', _GitHub_, 0.5.0.
* Hinman, M. L.. (2019) '[Cheshire Clojure JSON encoding/decoding](https://github.com/dakrone/cheshire)', _GitHub_, 5.9.0.
* Stewart, A.. (2014) '[plural.core/pluralize](https://github.com/stewart/plural.clj)', _GitHub_, 0.1.0.


## References

Praha.eu (2019) PRAŽSKÉ PARKY. Available at: http://www.praha.eu/jnp/cz/co_delat_v_praze/parky/index.html [Accessed: 14 November 2019].


## License

Copyright © 2019 Daehee Kim, Ilyas Sakhanov, Sulieman Al Rustom

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
