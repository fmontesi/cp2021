# Re-Exam

To pass the course, you have to hand in a single Java file called `Exam.java`. Instructions on how to hand in will be published soon and you will be notified with an announcement on ITS Learning. This is not a group project: every student must work on their own assignment by themselves. An assignment will be opened for handing in the project, with deadline **24 August 2021** at 16 (local time). Detailed instructions are given below.

You can find a list of common mistakes that people do in the exams for this course in [common mistakes](https://github.com/fmontesi/cp2021/blob/master/reexam/common-mistakes.md). Please read it after reading the following instructions.

## Instructions

To prepare for the exam, follow these steps.
- Download the file `Exam.java` from here: <https://raw.githubusercontent.com/fmontesi/cp2021/master/reexam/exam-project/src/Exam.java>
- Read the content of `Exam.java`, which describes the task that you have to complete to pass the exam. You will find in particular a few methods that throw `UnsupportedOperationException`: you have to remove the throw instruction and implement those methods by following their documentation.

To complete the exam, you have to edit the file `Exam.java` as requested by the instructions.

As described in `Exam.java`, your task is to implement a few methods of class `Exam`. Please note the following rules: violating any of them might make you fail the exam automatically.
- You cannot change the code of method `main`, nor any code marked with `Do not change`.
- You cannot change the types of what the methods that you have to implement take as parameters or return.
- You cannot create new files. Your entire implementation must be inside of `Exam.java`.
- You must hand in exactly one file, that is, `Exam.java`. Do not upload any other files, and do not put the file inside of any directory. Just upload that single file. If you have comments regarding your code, put them as Java comments in the methods that you implemented inside of `Exam.java`.
- Including external libraries is forbidden: you can only use the Java standard library.

You are welcome to create additional methods to structure your project, and also to create additional classes as long as they are in the same `Exam.java` file.

## Testing

Class `Exam` comes with a `main` method that implements a simple command line interface, which allows you to test your methods.
From the directory where you have stored `Exam.java`, run `java Exam help` to check whether you set up is working. (There is no need to compile it if you are using Java 11 or above.)

The methods that you have to implement have to search for text files in a directory. To test your project, you can use the directory `data` inside of this zip file: <https://raw.githubusercontent.com/fmontesi/cp2021/master/reexam/data.zip>. For example, you can unzip `data.zip` in the same directory where you have your `Exam.java` file and launch from that directory the command `java Exam findUniqueWords data`. The directory that I will use in the evaluation will follow the same format, but it will be different.

## Other remarks

You can use any IDE or editor you like during the development, as long as all you hand in is just `Exam.java` and the file keeps working by itself by invoking `java Exam` from the command line. No project files or similar can be uploaded.

When you start implementing a method in `Exam`, remove the `throw new UnsupportedOperationException();` line from it first. If you do not manage to
implement a method, then keep that line instead so that I know you chose not
to give an implementation for that particular method.

## Evaluation criteria

Evaluation will be based on the following criteria.

- *Correctness:* Are the methods implemented as requested, and do they operate correctly?
- *Efficiency:* Is concurrency used efficiently to achieve better performance than a  sequential program?
- *Scalability:* Does the implementation scale well with respect to the number of available cores?
- *Readability:* Is the code easy to read and understand?

## Frequently Asked Questions and Comments

- Be careful with thread termination: when I measure the time that your methods take to terminate, having threads that have not terminated may slow down the measurement.

- What encoding will the files be in?
UTF-8. I will not use any weird characters that require thinking of more than a Java `char`. I do not recommend using byte representations of strings.

- This is useful if you want to test how your project scales with respect to the number of cores: How do I de-activate CPU cores in Linux?
See [http://www.cyberciti.biz/faq/debian-rhel-centos-redhat-suse-hotplug-cpu/](http://www.cyberciti.biz/faq/debian-rhel-centos-redhat-suse-hotplug-cpu/).
