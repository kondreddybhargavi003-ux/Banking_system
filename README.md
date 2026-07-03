# Banking Management System

## Project Overview
A console-based banking application implemented in Java using a layered architecture. The system supports account creation, deposits, withdrawals, transfers, transaction history, deletion, and persistence across application restarts.

## Features
- Create savings, current, and fixed deposit accounts
- Deposit money into an account
- Withdraw money from an account
- Transfer funds between accounts
- View account details and all accounts
- View transaction history
- Delete accounts
- Persist account and transaction data to disk

## Technology Stack
- Java 21+
- Console-based UI
- Java serialization for persistence
- Layered package structure

## Folder Structure
- app: application entry point
- model: domain objects and account hierarchy
- service: business logic and persistence orchestration
- repository: in-memory storage and file-backed persistence
- exception: custom exception hierarchy
- validator: input validation rules
- util: helper utilities
- menu: console interaction layer
- config: application constants and configuration
- data: persisted data files
- test: verification and smoke-test classes

## Architecture
The project follows a simple layered design:
1. Menu layer handles console interaction.
2. Service layer implements banking rules and coordinates operations.
3. Repository layer stores account data in memory and persists it to disk.
4. Utility and validator layers support file handling and input validation.
5. Model and exception layers provide the domain entities and error handling.

## How to Run
1. Open the project in VS Code.
2. Compile the Java source files.
3. Run the main class from the app package.

Example:
```bash
javac --release 21 -d out $(find src -type f -name "*.java")
java -cp out app.Main
```

## Sample Output
```text
====================================
     BANKING MANAGEMENT SYSTEM
         Phase 1 - PS106
====================================
========================================
BANKING MANAGEMENT SYSTEM
========================================
1. Create Savings Account
2. Create Current Account
3. Create Fixed Deposit Account
4. View Account
5. View All Accounts
6. Deposit
7. Withdraw
8. Transfer Money
9. View Transaction History
10. Delete Account
11. Save Data
12. Exit
Enter your choice:
```

## Project Workflow
- Start the application.
- Choose an action from the console menu.
- The service layer validates input and executes the business operation.
- The repository updates the in-memory state and persists it to disk.
- The menu displays the result and continues until the user exits.

## Future Improvements
- Add a richer transaction audit trail
- Add stronger validation and input sanitization
- Introduce a database-backed persistence option
- Add automated unit and integration tests
