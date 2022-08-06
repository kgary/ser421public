# SER422 Kafka example
Kafka Examples

This repository contains Producer and Consumer codes written in Java. 
Many of the steps rely on the Kafka Quickstart here: https://kafka.apache.org/quickstart

BEFORE RUNNING THE APPLICATION

Step 0: Clone this repo on to your local system using an IDE (recomended). However if you want you may not use it.
If you face an error like "Error:java: error: release version 5 not supported" please go this website
https://stackoverflow.com/questions/59601077/intellij-errorjava-error-release-version-5-not-supported and follow the steps. 

Step 1: Download &amp; install Kafka - Theoretically it works for all operating systems however I would
suggest do it on a MAC or Linux system. If you do not have one you should consider downloading a Virtual Machine (VM).
We have not fond Windows to be very stable.

Step 2: After step 1 you should have your Kafka server setup on your local system. Test it by sending
messages (shown using the shell scripts on the Quickstart page)).

Step 3: For writing code it may help to use an IDE (any IDE of your choice), if you don&#39;t have one, I
would recommend IntelliJ IDEA https://www.jetbrains.com/idea/download/#section=linux
Download the free version as your operating system. There is a video to help here https://www.youtube.com/watch?v=bwXWIx5dZjw

Step 3b: Alternatively, you can simply compile at the command-line (from the root where you unpacked the repo):

--> javac -d classes -cp classes:$KAFKA_HOME/libs/* src/main/java/SampleConsumer.java

--> javac -d classes -cp classes:$KAFKA_HOME/libs/* src/main/java/SampleProducer.java

Note: $KAFKA_HOME is the root of your kafka install

This should result in a classes subdirectory having SampleProducer.class and SampleConsumer.class

RUNNING THE APPLICATIONS

Step 1: Build your Producer and Consumer apps as shown above.

Step 2: Start the Zookeeper as per the directions on this https://kafka.apache.org/quickstart

Step 3: Start the Server as per the directions on this https://kafka.apache.org/quickstart.

Step 4: Start the Consumer from the the terminal as per the directions on this https://kafka.apache.org/quickstart or via the command-line:

--> java -cp classes:$KAFKA_HOME/libs/* main.java.SampleConsumer

Step 5: Start the Producer from the the terminal as per the directions on this https://kafka.apache.org/quickstart or via the command-line:

--> java -cp classes:$KAFKA_HOME/libs/* main.java.SampleProducer name age location
where name is an person's name, age is a positive integer, and location is a city

Step 6: Check the output at the Consumer terminal.
