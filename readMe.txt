CZ2001 Algorithms
AY20/21 Sem 1
Project 2
Group 1

Group members:
- Joey Lim Soo Yee (U1921745L)
- Josephine Agatha Hemingway (U1920309C)
- Kenn Lim Zheng Jie (U1921807J)
- Leong Hao Zhi (U1920469K)
- Li Haocheng (U1920700J)
- Shannon Kate Wong Carlynn (U1921906D)

------------------

ReadMe:

Overview of files
*** import all these files into the same package

1. Hospital.java
	- Hospital class to store hospital node ID, shortest distance and shortest path
	- used in algorithm A
	
2. HospitalUserInput.java
	- gets user input on the number of hospital nodes in the network and hospital nodeID
	- stores hospital nodeID in file2.txt
	- will be read into the main driver file

3. Node.java 
	- Node class to store source node, nodeID, shortest distance and shortest path
	- used in algorithm B
	
4. RandomUserInput.java
	- gets user input for number of nodes in java file
	- creates random graph
	- stores the graph source and destination nodes into file1.txt
	- * requirements: need to import graphstream and its .jar files to generate random graph
	- ** or just use precomputed file1.txt file

** MAIN **	
1a. Input Real Road Network Data files: roadNet-PA.txt
1b. Input Random Data File: file1.txt
	- created via RandomUserInput.java
	
2.  file2.txt
	- created via HospitalUserInput.java
	- hospitals input file to be read in the main driver files
	- contains number of hospitals (#h in first line) & hospital node IDs
	
- Output Data files:
	- Output.txt
	
** MAIN DRIVER FILES: **
1. Algo_A
	- main driver file for Algorithm A
	- reads in road network file
	- generates output to Output.txt 
	
2. Algo_B
	- main driver file for Algorithm B
	- generates output to Output.txt

* just change the filename in Algo_A and Algo_B to file1.txt to use random graph 
or roadNet-PA.txt to use real road network

