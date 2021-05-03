# Common mistakes found in previous exams (don't do this!)

A list of common mistakes that have been found in previous exams. The following
is a list of things that you should *not* do.

- Not following the file structure described for the hand-in. Read it and follow it.

- Not really using concurrency. Some just ran multiple threads, each running a copy
of the same sequential algorithm. This is not the objective of the exam. The objective is to
use concurrency to optimise the execution of the algorithm.

- Changing code explicitly marked with `Do not change` in the skeleton Exam class.

- Not removing the `throw new UnsupportedMethodException()` statement given in the skeleton
program for the exam after a method has been implemented.

- Not taking care of race conditions on shared data structures. Some projects
used shared data structures to accumulate results during execution, but without
using proper synchronisation. As a result, some projects behaved erratically: some times
they would work, some times they would not.
