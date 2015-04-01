**markov-clojure**

In [_The Practice of
Programming_](http://cm.bell-labs.com/cm/cs/tpop/) Brian
Kerninghan and Rob Pike implement a random text generator using a
Markov chain algorithm in five different languages as a way to compare
the languages. Markov-clojure is an implementation of the same
algorithm in Clojure.

The design decisions made in _The Practice of Programming_ (PoP) have
been followed except for the sentinal values used. In PoP `"\"` is
used for both the starting prefix words and last suffix. Being
dynamically typed, markov-clojure is not limited to strings for the
sentinal values. Instead the keyword `:not-a-word` is used for the
starting prefix words. The terminating suffix is `nil` which naturally
flows from the use of `nil` to terminate sequences in Clojure.

A caution: I am new to Clojure programming, so this implementation may not
be the most idiomatic.

**usage**

    $ java -jar markov-0.1.0-SNAPSHOT.jar file-name how-many-words

**License**

Copyright © 2015 Shannon Severance
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are
met:

1. Redistributions of source code must retain the above copyright
notice, this list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright
notice, this list of conditions and the following disclaimer in the
documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
