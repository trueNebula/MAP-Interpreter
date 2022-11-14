# Toy Language Interpeter
## The MAP continuous assignment
Yeah so this bad boy

---
The following is an Interpreter for the Toy Language created as part of the MAP course.
Its main function is to parse the code of a program and output the result of said program.

---
## Table of Contents:
 - [Toy Language Description](#toy-language-description) 
 - [Program Structure](#program-structure)
   - [model](#model)
   - [controller](#controller)
   - [Repository](#repository)
   - [view](#view)
 - [Extending the Interpeter](#extending-the-interpeter)

---
## Toy Language Description
A **Program** consists of a **Statement** that gets executed.

### Statements:
 - CompStmt
 - VarDeclStmt
 - VarAssgnStmt
 - PrintStmt
 - IfStmt
 - nop

### Types:
 - int
 - bool

### Values:
 - Constant Integer
 - Constant Boolean

### Expressions:
 - ValueExpression
 - VariableExpression
 - ArithmeticExpression (+, -, *, /)
 - LogicalExpression (AND, OR)

---

### Program State:
The Program State consists of three collections:
 - ExecutionStack
 - SymbolTable
 - OutputStream

The ***ExecutionStack*** starts out holding all the statements separated by semicolons.
Statements get removed sequentially from the ExecutionStack once executed.
Execution ends when the ExecutionStack is empty.

The ***SymbolTable*** will hold all of the variabe-value pairs used in the execution.

The ***OutputStream*** will hold all of the messages printed by the program.

---
## Program Structure
The Interpreter is built using the model-view-controller-Repository architecture.


---
## model
The ***model*** contains classes that describe every element of the program (ProgramState, Statements, Expressions, Values, Types), as well as Exceptions and Collections.

### Exceptions:
 - CollectionException
 - ExpressionEvaluationException
 - StatementExecutionException

### Collections:
- GenericDictionary
- GenericList
- GenericStack


---
## controller
The ***controller*** holds a reference to the Repository.

The controller has two main methods: 
 - runOneStep
 - runAllSteps

runOneStep() executes a single statement at a time and displays the OutputStream.
runtAllSteps() calls runOneStep() continuously until the ExecutionStack is empty and only displays the final OutputStream.


---
## Repository
The ***Repository*** contains an ArrayList of ProgramStates.

Currently, its only purpose is to hold a reference to the current ProgramState. 

In the future, it will be extended to hold multiple threads of the same Program execution.

---
## view

The ***view*** is the CLI used to execute the Program.


---
## Extending the Interpeter
For future assignments, a few extentions will have to be made:
 - Adding more Statements
 - Adding more Values and Types
 - Adding more Expressions (I think)
 - Multithreading
   - Extending the controller to work with Threads
   -  Extending the Repository to hold all Threads
 - Probably some stuff to do with the CLI view


---