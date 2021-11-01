# Simple Search Engine Challenge
The approach to solving the backend coding exercise is done in the [Scala](https://scala-lang.org/) programming language.

### Requirements
This test assumes that you have 
- [Java](https://www.java.com/es/) 1.8 
- and [sbt](https://www.scala-sbt.org/) installed on your machine

in order to build and run this project. Also, a [Make build tool](https://www.gnu.org/software/make/) is available for you
to run basic commands if you don't want to dig inside sbt, albeit not paramount. 
If you want to see what you can do, type `make help` in the project root.

### Run procedure
- In case you **want** to use **Make**
    - type `make` . This will execute the build procedure, that
        - runs the test suite, then
        - compiles the program in a fat jar, then
        - executes that fat jar whose first argument is the `data` folder, present in the folder root
- in case you **don't want** to use Make
    - execute the commands below in the order they appear
        - `sbt assembly` 
        - `java -jar target/scala-2.12/SimpleSearchChallenge-assembly-0.1.jar data/`

### Q&A from challenge considerations
- _What constitutes a word?_ : Every English alphabetical character that matches a `(\w+)` regex expression
- _What constitutes two words being equal?_ : Two lowercased words with the same **exact** lettering are considered the same (no word stemming).
- _What is the ranking scoring mechanism?_ : 
    -  **for a 100% match** : any **word** or **sentence** entered, that appear either **contiguously** or **not contiguously**.
    -  **for a match less than 100%** : score% = (100 * numberOfCoincidentDocuments/numberOfWordsInTheQuery)
    

### Reasonings behind architecture
- Provided the time constraint, the structure of the project tries to mimic Hexagonal Architecture, although not in a very strict way. 
The application is small and the folder structure is a stepping stone for further evolutions.
- There are Unit and Integration tests on the project. They all run at once without any suite differentiation at test time. 

### Inverted index for fast in-memory searches
As there might be the possibility of storing millions of words from various files, an in-memory [Inverted Index](https://www.geeksforgeeks.org/inverted-index/) structure
is used for fast searches. This index is ready to store a unique representation of what I considered that constitutes a "word" once in the search engine, and have a list 
of places where it appears attached to it. 

The inverted index usage comes at the expense of indexing slowly, as we create the structure as we read the files.
The main caveat is that the slowness is almost linear to the amount of files needed to be scanned 
After that, the searches are very fast , provided that for each unique word (or token) we have the list of coincidences already.

**Benchmarks on indexation process**
 
(These benchmarks were only ran once per testcase)

| File                                               | Size  | Words   | Time     |
|----------------------------------------------------|-------|---------|----------|
| file1.txt                                          | 124B  | 20      | 0.002ms  |
| horatio.txt                                        | 1,2KB | 225     | 0.003ms  |
| shepherd.txt                                       | 6,9kb | 1245    | 0.003ms  |
| TheCompleteWorksOfWilliamShakespeare.txt           | 5,5mb | 961443  | ~1.2 s   |
| TheCompleteWorksOfWilliamShakespeare.txt six times | 33mb  | 5768658 | 4,69 s   |
  
 using this function to measure nanoseconds of elapsed time
```
  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block    // call-by-name
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) + "ns")
    result
  }
```
