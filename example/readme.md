Prediction Example of [HelloNico](https://github.com/hellonico)'s [Fruits Classification](https://github.com/hellonico/fruits-classification)

# Spruce Trees vs Linden Trees

***classify*** is a specific use of a convolutional neural network model tweaked by [Nicolas Modrzyk](https://github.com/hellonico) and designed by [Carin Meier](https://github.com/gigasquid).

The nippy model in this project is only as accurate as 63% at the moment.

## Usage

```
lein repl
```

```clojure
(require 'classify.simple)
(in-ns 'classify.simple)
(guess nippy "images/sprucesample2.jpg")
```

## Output

The output of (guess) function is a string, either "linden" or "spruce".