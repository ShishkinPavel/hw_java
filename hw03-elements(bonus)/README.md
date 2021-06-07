Homework assignment no. 3 (BONUS), Elements
====================================
This is a bonus part of the assignment. 
The base assignment of third homework can be found [in this repository](https://gitlab.fi.muni.cz/pb162/2021-hw03-csv)

**Publication date:**  May 23, 2021

**Submission deadline:** June 9, 2021 

General information
-------------------

In this assignment you will implement a very simple command line application which allows its user to query
[the periodic table](https://en.wikipedia.org/wiki/Periodic_table). 
The implementation will use  ``src/main/resources/elements.csv`` as its datasource. 

The application should support the following command line options.
| CLI option (long) | CLI option  (short) | Arguments | Description |
| ------ |   ------ | ------ | ------ |
| --help   |        |        | Print application usage |
| --name   |        | String | Finds element by name   |   
| --symbol | -s     | String | Finds element by symbol   |
| --number | -n     | int    | Finds element by atomic number   |
| --year   | -y     | int    | Finds elements by year   |

For more details about the CLI see the section "Running the application" in this README as well as JUnit tests.

**Hint:** *You will need a CSV parser, and you have to write it yourself.*
**Hint:** *Add dependency on your CSV parser from hw03 to pom.xml*



### Evaluation
Beside functional correctness this assignment is focused on object-oriented design.
This means that the way you structure your program will be the main part of its evaluation.
On the other hand the given set of tests is not trying to provide an elaborate test coverage and incorrect behaviour in corner-cases should not have a large impact on the evaluation.

Note that all this is at your seminar teacher's discretion.

The maximum number of points for this assignment is **8**.

- **2 points** for passing the tests (attached tests do not guarantee a 100% correctness).
- **6 points** for correct and clean implementation (evaluated by your class teacher).


### Project structure
The structure of project provided as a base for your implementation should meet the following criteria.

1. Package ```cz.muni.fi.pb162.hw03``` contains classes and interfaces provided as part of the assignment.
  - **Do not modify or add any classes or subpackages into this package.**
2. Package  ```cz.muni.fi.pb162.hw03.app.impl``` should contain your implementation.
  - **Anything outside this package will be ignored during evaluation.**

### Names in this document
Unless fully classified name is provided, all class names are relative to the package ```cz.muni.fi.pb162.hw03``` or ```cz.muni.fi.pb162.hw03.app.impl``` for classes implemented as part of your solution.


### Compiling the project
The project can be compiled and packaged in the same way you already know.

**Note:** *Before compiling this project you need to [install your CSV](https://gitlab.fi.muni.cz/pb162/2021-hw03-csv#compiling-the-project) parser project into your local maven repository. 

```bash
$ mvn clean install
```

The only difference is, that unlike with seminar project, this time checks for missing documentation and style violation will produce an error.
You can temporarily disable this behavior when running this command.

```bash
$ mvn clean install -Dcheckstyle.fail=false
```

### Running the application
Build command mentioned above will produce a runnable jar file ``target/application.jar``.
The following are example usages of developed application.

```bash
# Search by atomic number
$ java -jar elements.jar -n 1
-------------------------------------------------------------------------------------------------------
|         #|    Symbol|                Name|      Standard State|              Group Block|      Year|
=======================================================================================================
|         1|         H|            Hydrogen|                 gas|                 nonmetal|      1766|
-------------------------------------------------------------------------------------------------------

# Search by year of discovery
$ java -jar elements.jar --year 2003
-------------------------------------------------------------------------------------------------------
|         #|    Symbol|                Name|      Standard State|              Group Block|      Year|
=======================================================================================================
|       113|        Nh|            Nihonium|                    |    post-transition metal|      2003|
|       115|        Mc|           Moscovium|                    |    post-transition metal|      2003|
-------------------------------------------------------------------------------------------------------
```
**Note:** *These are just example, refer to tests in order to identify all possible usages.*

### Submitting the assignment
The procedure to submit your solution may differ based on your seminar group. However generally it should be OK to submit ```target/homework02-2021-1.0-SNAPSHOT-sources.jar``` to the homework vault.

## Implementation
Generally speaking there are no mandatory requirements on the structure of your code as long as the command line interface of ```Appplication``` class works correctly.


