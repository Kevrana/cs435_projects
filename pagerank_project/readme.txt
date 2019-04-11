// KEVIN RANA cs435 9719 mp 
// this program is still undergoing revisions to further fix bugs/errors/inaccuracy 

This file contains Compilation and Execution instructions,
 bug reports, the version of java compiler used, and other useful information.


Java Compiler version used on AFS (javac -version):

java version "10.0.2" 2018-07-17
Java(TM) SE Runtime Environment 18.3 (build 10.0.2+13)
Java HotSpot(TM) 64-Bit Server VM 18.3 (build 10.0.2+13, mixed mode)


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Option 3: Google's PageRank
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Compilation and Execution Instructions:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

1. Unzip mp_9719.zip, it contains two files, pagerank_9719.java and mp_9719.txt (this file) 

2. On afs machine change directory (cd) to the directory that mp_9719.zip's unzipped files are stored in.

3. to compile the java file pagerank_9719.java use:

	javac pagerank_9719.java 

4. Once the file is compiled you can execute the pagerank_9719.java
by using(*note*:graph.txt or largegraph.txt is provided by you):

	java pagerank_9719 15 0 graph.txt
	
	or:

	java pagerank_9719 15 1 graph.txt
	
	or:

	java pagerank_9719 15 -1 graph.txt
	
	or:

	java pagerank_9719 15 -2 graph.txt
	
	or:

	java pagerank_9719 0 -1 graph.txt
	
	or:

	java pagerank_9719 0 1 graph.txt
	
	or:

	java pagerank_9719 0 0 graph.txt
	
	or:

	java pagerank_9719 0 -2 graph.txt

	or(if largegraph.txt file exists with n>10 with n being number of vertices or webpages):

	java pagerank_9719 15 -1 largegraph.txt



~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
DETAILED BUGS REPORT:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

1. after compiling javac pagerank_9719.java you might get a notification such as:

	Note: pagerank_9719.java uses unchecked or unsafe operations.
	Note: Recompile with -Xlint:unchecked for details.

This is because of the data type of my linked adjacency list. Program does compile fine.
Found on line 20, my linked adjacency list is of type Integer, but might not be responding well to double value input.

2. if user enters a negative number for iterations like java page_9719 -1 -1 graph.txt
my program will turn the negative iterations argument value to positive to avoid errors 
in the system. Found at line 162.


3. if user had a largegraph.txt with number of vertices n > 10, 
the program will iterate once but prints the same value for each rank.
Found at lines 196-217.

for example: 

java pagerank_9719 15 -1 largegraph.txt  
  
gives:
 
 Iter :  15 :
P[0] = 0.090909
P[1] = 0.090909
P[2] = 0.090909
P[3] = 0.090909
P[4] = 0.090909
P[5] = 0.090909
P[6] = 0.090909
P[7] = 0.090909
P[8] = 0.090909
P[9] = 0.090909
P[10] = 0.090909

This bug is resulting from the value of the webpage in the 
adjacencyList possibly storing the same value for each pagerank.



4. The pagerank values will compute slightly different depending on the initial
 value and number of vertices from the graph.txt file. The base will perform correctly.

for example:

 java pagerank_9719 15 -1 graph.txt

gives:

 Base :  0 :P[0] = 0.250000 P[1] = 0.250000 P[2] = 0.250000 P[3] = 0.250000
 Iter :  1 :P[0] = 0.887500 P[1] = 1.737500 P[2] = 0.887500 P[3] = 0.037500
 Iter :  2 :P[0] = 0.791875 P[1] = 1.514375 P[2] = 0.791875 P[3] = 0.069375	
	.
	.
	.
 Iter :  15 :P[0] = 0.315516 P[1] = 0.402870 P[2] = 0.315516 P[3] = 0.228161



java pagerank_9719 15 -2 graph.txt

gives: 

 Base :  0 :P[0] = 0.500000 P[1] = 0.500000 P[2] = 0.500000 P[3] = 0.500000
 Iter :  1 :P[0] = 0.887500 P[1] = 1.737500 P[2] = 0.887500 P[3] = 0.037500
 Iter :  2 :P[0] = 0.791875 P[1] = 1.514375 P[2] = 0.791875 P[3] = 0.069375
	.
	.
	.
 Iter :  15 :P[0] = 0.315516 P[1] = 0.402870 P[2] = 0.315516 P[3] = 0.228161


This bug is most likely resulting from my pagerank algorithm attempt which is computed as 
((1-d)/n) + d*webpagevalue 
where n is the # of webpages or vertices and d is 0.85 and it is accessed from the
directed graph g with adjList at y.
 Found at lines 78-92.



5. if iteration is 0, then my program will automatically set iterations to 60 according to the average
iterations that it would take to converge the pagerank, between 60-70 iterations
The bug here is that it will run for 60 iterations rather than the # of iterations to converge the 
pagerank for that graph or until the fixed errorrate of 10^-4 is acheived. 
Found at lines 168-173.

for example:

java pagerank_9719 0 -2 graph.txt

gives:

 Base :  0 :P[0] = 0.500000 P[1] = 0.500000 P[2] = 0.500000 P[3] = 0.500000
 Iter :  1 :P[0] = 0.887500 P[1] = 1.737500 P[2] = 0.887500 P[3] = 0.037500
 Iter :  2 :P[0] = 0.791875 P[1] = 1.514375 P[2] = 0.791875 P[3] = 0.069375
 	.
	.
	.
 Iter :  60 :P[0] = 0.250044 P[1] = 0.250102 P[2] = 0.250044 P[3] = 0.249985


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Useful information (Possible bugs) report:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

1. if more than 4 command line arguments are inputted, the 4th one will not be read.

2. if the file name of the 3rd argument is spelled wrong, you will get a cannot find file message.

3. if 3rd argument is missing the code will not run.

4. The user will compile and execute the pagerank_9719.java with 3 commandline argument inputs
1st is the arguments which is stored in t, the initialvalue, which is stored in inval and the graph file
which is stored in file. It will read through the first line of the file to obtain the number of vertices
and edges.
My program starts by creating a graph, with the given number of vertices from the graph.txt.
 Then it will go through each line of the graph.txt file to get the edge (i,j) and input it into
the graph g, after that, the program will check cases for iterations and initial value, i.e. 
if iterations is == 0, then keep iterating the pagerank until it converges, iteration > 0 , then iteration
by given amount of user, if initialvalue is -1, it starts with 1/n, if 1, then 1, if 0, then 0, 
if -2, then 1/sqrt of n. where n is the number of webpages or vertices in the graph g. 
afterwards, it  performs the base pagerank with the initialvalue assigned. Then it will iterate
through the pagerank_9719 created to do the pagerank alogirthm computation ((1-d)/n) + d*webpagevalue 
until the iteration is met. If the iteration entered was 0, the program will iterate for 60 iteration to 
converge or achieve the fixed errorrate of 10^-4. If no arguments are in

