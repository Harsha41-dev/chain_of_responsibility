# Chain of Responsibility LLD Code

A small Java LLD project that shows how to build a clean API request handling pipeline using the Chain of Responsibility design pattern.

## Project Version

`v1.0` - Chain of Responsibility LLD example for Todo API request validation and creation.

## Problem Statement

Design an API request handling system where every incoming request must pass through multiple checks before the actual business operation is executed.

For a `POST /todos` request, the system should validate the route, check the request body, authenticate the token, authorize the user role, and finally create the todo item.

If all these checks are written inside one controller method, the controller becomes long, tightly coupled, and difficult to extend. The goal is to keep each check separate, run the checks in a fixed order, and stop the request immediately when any check fails.

This LLD example solves the problem using the Chain of Responsibility pattern. Each step is implemented as an individual handler. A handler either returns an error response or passes the request to the next handler in the chain.

Flow:

```text
Main
 -> TodoController
 -> ValidateParamsHandler
 -> ValidateBodyHandler
 -> AuthenticationHandler
 -> AuthorizationHandler
 -> CreateTodoHandler
```

If any handler finds a problem, it returns the response from there only.
The next handlers will not run.

For example:

- if body is empty, request stops at `ValidateBodyHandler`
- if token is wrong, request stops at `AuthenticationHandler`
- if role is not `ADMIN`, request stops at `AuthorizationHandler`
- if everything is fine, `CreateTodoHandler` creates the todo

## Files

| File | Use |
| --- | --- |
| `Main.java` | Starts the program and creates sample requests |
| `Request.java` | Request data |
| `Response.java` | Response data |
| `RequestHandler.java` | Common interface for handlers |
| `BaseRequestHandler.java` | Common code for calling next handler |
| `ValidateParamsHandler.java` | Checks path and method |
| `ValidateBodyHandler.java` | Checks request body |
| `AuthenticationHandler.java` | Checks token |
| `AuthorizationHandler.java` | Checks user role |
| `CreateTodoHandler.java` | Creates todo at the end |
| `RequestHandlerFactory.java` | Creates the chain of handlers |
| `TodoController.java` | Calls the handler chain |
| `TokenService.java` | Interface for token checking |
| `SimpleTokenService.java` | Basic token checking code |
| `TodoService.java` | Creates todo object |
| `Todo.java` | Todo model |

## SOLID points

- Single Responsibility: every handler has one work only.
- Open Closed: new handler can be added without changing old handlers.
- Liskov Substitution: all handlers can be used as `RequestHandler`.
- Interface Segregation: `RequestHandler` has only one method.
- Dependency Inversion: `AuthenticationHandler` uses `TokenService` interface.

## Run

Compile:

```bash
javac *.java
```

Run:

```bash
java Main
```

## Output example

```text
--- GOOD REQUEST ---
1. Validating route params
2. Validating request body
3. Authenticating user
4. Checking user permission
5. Creating todo
Final Response: 201 - Todo created with id 1: Learn SOLID LLD
```

## Adding a new handler

Suppose we want to add rate limit checking.

Steps:

1. Create `RateLimitHandler.java`
2. Extend `BaseRequestHandler`
3. Write the rate limit check inside `handle`
4. If request is valid, call `callNext(request)`
5. Add this handler in `RequestHandlerFactory`

Example:

```java
return new ValidateParamsHandler(
        new RateLimitHandler(
                new ValidateBodyHandler(
                        new AuthenticationHandler(
                                new AuthorizationHandler(
                                        new CreateTodoHandler(todoService)
                                ),
                                tokenService
                        )
                )
        )
);
```
