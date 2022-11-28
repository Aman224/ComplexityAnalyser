# ComplexityAnalyser

## Introduction

This application calculates the average and maximum complexity of classes / files using McConnell’s simplified approach for complexity analysis. We make use of [antlr4](https://www.antlr.org/) which is a tool that can be used to generate a parser based on a given grammar. This tool had been used to generate a parser for parsing Java and Python code (more language support may be added). The grammar used is the grammar for [java](https://github.com/antlr/grammars-v4/tree/master/java/java) and [python](https://github.com/antlr/grammars-v4/tree/master/python/python) provided by antlr4. More information about the parser generation is given in the section Parser Generation.

## Requirements

- Java 11+

## How to Build

This application uses gradle to build the jar file. To build the application follow the below steps
1. Navigate to the root directory of the zip file
2. Run the command

```
./gradlew build
```

This will generate a .jar file <i>task2-1.0-SNAPSHOT.jar</i>

## Running the application

Navigate to the directory with the .jar file that was build in the previous step.

    cd /path/to/jar/file

Run the command

    java -jar task2-1.0-SNAPSHOT.jar <directory_argument> <file_types_argument>

### Parameters

The application takes two command line arguments to run
- <b>directory_argument</b>: The directory to be considered for the complexity analysis. If the directory argument is not given the <b>current working directory</b> will be considered for the analysis
- <b>file_types_argument:</b> A list of the file extensions to be analysed. Currently supported [java, py]. If the argument is not given a default value of <b>java</b> is assumed if not specified.


## Run Tests

The unit tests for the application can be run using
```
./gradlew test
```

## Parser Generation

We use antlr4 to generate parsers for any given programming languages. This version of the application contains parsers for java and python. The .g4 files used for generating the parser has been provided in the src/main/antlr4 folder. The command to generate the parser is given below
```
antlr4 -o <output_path> -package <parser_output_package_name> -listener -visitor -lib <location_grammars> <grammar_path>
```
- <b>output_path</b>: Specifies the output location of the generated parser
- <b>parser_output_package_name</b>: Specifies the package name to be used for the generated parser java files
- <b>location_grammars</b>: Specifies the directory where the grammar files are located
- <b>grammar_path</b>: Specifies the path to the .g4 grammar file to be used to generate the parser

Please note this requires antlr4 to be installed in the system.

## Output

Upon running the application a report will be generated in the console with the average and maximum complexity of all classes in the specified directory. Please note the absolute path of each class will be used to display the results.