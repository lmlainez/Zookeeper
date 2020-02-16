# Zookeeper
All the code in this repositoy is the result of the implementation of the examples and exercises in the Distributed Systems and Cloud Computing in Java.

Leader Election:Small program to coordinate 4 different Java processes using Zookeeper in order to find one as leader in the processes.
To run it , build the prohject ( maven clean package )  , go to the Project folder and run the compiled jar with dependencies.

java -jar target/leader.election-1.0-SNAPSHOT-jar-with-dependencies.jar

Before doing that, make sure that Zookeper server is running. So, go to the Zookeper distribution you have,  and run the zkServer excutable file in the bin folder.
