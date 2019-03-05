# User guide

## Launch
Launch jar to start the program.  
Jar location: unit_pathfinder_tira/Jar/unit_pathfinder_tira.jar  
  
Alternatively, one can use command line tool to run the following command under unit_pathfinder_tira/unit_pathfinder_tira:  
`$ ./gradlew run`
  
To run unit tests, use the following command instead:  
`$ ./gradlew test`

## Input
Duskwood map is loaded by default. Search paths by clicking on the map. Previous end coordinates will be used as start coordinate and newly clicked coordinate will be the new end. Each pathfinders' path will be drawn on the map with brighter line being the path and lighter areas indicate the coordinate visited during the search. Use tools to hide layers for easier reading.

## Tools
Benchmarking information is found on the right side. The info is updated after each pathfinder has returned their paths. The data can be copied to clipboard by clicking 'Copy'. The copied format allows convenient pasting to a table (Excel for example).  
Data collected:  
1. Weight of the path (pixel distance)
1. Length of returned paths (nodes)
1. Nodes visited during search (ticks for every neighbor check)
1. Time spent in milliseconds
  
Ticking the check boxes next to the pathfinder name will hide/unhide the drawn layer for said pathfinder allowing you differentiate the paths and visited nodes.
  
New maps can be loaded by clicking 'Load New Map'. Maps used in testing are located in unit_pathfinder_tira/Maps/.  
  
![Image of program in running](https://github.com/jompero/unit_pathfinder_tira/blob/master/Documentation/Resources/program.png)
