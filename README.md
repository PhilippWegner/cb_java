# compilerbau

## Write a compiler for a new programming language in Java

### Step 1 - Create the Lexer
The first step to writing a compiler for a new programming language in Java is to create the Lexer. The Lexer is responsible for taking the source code of the programming language and transforming it into tokens. The Lexer will use a lexical analyzer, also known as a scanner, to scan through the characters of the source code and create tokens.

### Step 2 - Create the Parser
Once the Lexer has created the tokens, the next step is to create the Parser. The Parser is responsible for taking the tokens and constructing an Abstract Syntax Tree (AST). The AST is a tree-like structure that captures the structure of the source code. The Parser will use a context-free grammar to determine the structure of the source code and create the AST.

### Step 3 - Create the Semantic Analyzer
Once the Parser has created the AST, the next step is to create the Semantic Analyzer. The Semantic Analyzer is responsible for performing semantic analysis on the AST to ensure that the source code is semantically correct. This includes checking for type compatibility, variable declarations, and other semantic checks.

### Step 4 - Create the Code Generator
The final step to compiling the source code is to generate the executable code. The Code Generator is responsible for taking the AST and generating the executable code. The Code Generator can generate code for any target platform, such as x86, ARM, or any other platform.

### Step 5 - Test and Debug
The final step to writing a compiler for a new programming language in Java is to test and debug the compiler. This includes testing the Lexer, Parser, Semantic Analyzer, and Code Generator to ensure that the compiler is working correctly. Additionally, any bugs or errors that are found should be fixed.


#### Parser Methods
1. parse(): This is the entry point of the parser. It calls the expression() method, which starts the parsing process. It returns a Node object, which is the result of parsing the input tokens.

2. expression(): This method is used to parse expressions in the input. It starts by calling the term() method to get the left operand, then it looks for any plus or minus operators, and if found, it calls the term() method again to get the right operand. It then creates a new BinaryNode object with the left and right operands and the operator and sets it as the left operand for the next iteration. It returns the final Node object that represents the parsed expression.

3. term(): This method is used to parse terms in the input. It works similar to the expression() method but it looks for any multiply or divide operators.

4. factor(): This method is used to parse factors in the input. It starts by checking if the current token is a unary operator (plus or minus), if yes, it calls the factor() method again to get the right operand and creates a new UnaryNode object with the operator and the right operand. If the current token is an integer or float, it creates a new ValueNode object with the token's value. If the current token is a left parenthesis, it calls the expression() method to get the expression inside the parenthesis and returns it.

5. match(): This method is used to match the current token with a list of expected types. If a match is found, it advances the current token pointer and returns true, otherwise it returns false.

6. consume(): This method is used to consume a specific token and throw an exception if it is not found.

7. check(): This method is used to check if the current token is of a specific type.

8. advance(): This method is used to advance the current token pointer.

9. isAtEnd(): This method is used to check if the end of the token list has been reached.

10. peek(): This method is used to get the current token without advancing the current token pointer.

11. previous(): This method is used to get the previous token.

```properties
java -jar .\target\libs\compilerbau-1.0-SNAPSHOT-jar-with-dependencies.jar .\example-code-4.dmvp
```
