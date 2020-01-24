# Guidia: Chatbot to guide you through Prague

## Overview

(hypotetical)
Guidia is a chatbot designed to help Prague visitors and residents find a desired park destination(s)
as well as identify trees (specifically linden and spruce trees) in the parks.
It is equipped with two machine learning technologies: Natural Language Processing and Neural Network Deep Learning.

## Open Natural Language Processing

Guidia recognizes English words based on [Apache's OpenNLP](https://opennlp.apache.org/).
The process consists of tokenization of user's sentences and recognition of the tokens on the example mappings in JSON.
It then parses the recognized maps to Clojure records for Guidia to compare and recommend matching parks.

## Convolutional Neural Network

Guidia is also implemented with an image classification of trees, specifically spruce trees vs linden trees.
Convolutional neural network is the core of Guidia's deep learning process, which is built with Cortex in Clojure.
It takes an input of an image that is seen as an array of pixel values, then processed for patterns to be recognized.
The outputs are simply class labels and how much they resemble other examples of the same classes (i.e. pre-trained).
It has, however, not been tested enough with different architectures on spruce and linden trees.
It only has the accuracy of 63.67% for the correct recognition.

### Network Architecture

The architecture of Guidia's network is borrowed directly from [mnist classification of Cortex](https://github.com/originrose/cortex/tree/master/examples/mnist-classification).
It may be the cause of low accuracy of the network for this specific example since it was used to test specifically on mnist.

Input (training and test images) > Convolution > Max Pooling > DropOut > ReLu Activation > Convolution > Max Pooling  > Linear Convergence with Batch Nomalization > ReLu Activation > DropOut > Linear Convergence (as much as the number of classes) > Softmax Activation > Model Output

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
CUDA backend creation failed, reverting to CPU
Jan 23, 2020 11:54:38 PM com.github.fommil.netlib.BLAS <clinit>
WARNING: Failed to load implementation from: com.github.fommil.netlib.NativeSystemBLAS
Jan 23, 2020 11:54:38 PM com.github.fommil.netlib.BLAS <clinit>
WARNING: Failed to load implementation from: com.github.fommil.netlib.NativeRefBLAS
That's a spruce tree!
```
Cortex uses netlib to help with algebraic computation, but it is not implemented in Guidia.

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
