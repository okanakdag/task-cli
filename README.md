# Task Tracker CLI

A small Java command-line app for tracking tasks in a local JSON file.

This project is my Java implementation of the [Task Tracker](https://roadmap.sh/projects/task-tracker) challenge from roadmap.sh.

## Highlights

- Plain Java implementation with no external libraries
- Layered structure with separate CLI, service, and repository logic
- Custom JSON file persistence for local task storage
- Bash helper scripts for compiling and running the project

## Features

- Add a task
- Update a task description
- Delete a task
- List all tasks
- Filter tasks by status
- Mark tasks as `in-progress` or `done`
- Persist tasks to `data/tasks.json`

## Requirements

- Java 23 or newer
- `javac` available on your `PATH`

## Project Structure

- `Main.java`: application entry point
- `TaskCli.java`: CLI command dispatch
- `TaskService.java`: task operations
- `repository/TaskRepositoryImpl.java`: file-based persistence
- `repository/TaskJsonMapperImpl.java`: JSON serialization/parsing

## Build And Run

### Option 1: Bash scripts

This is the easiest way to use the project on WSL, Linux, or macOS.

```bash
chmod +x compile.sh run.sh
./compile.sh
./run.sh help
```

You can also compile into a custom output directory:

```bash
./compile.sh build/classes
```

Note: `run.sh` expects compiled classes in `out`.

### Option 2: Compile manually with `javac`

From the project root:

```powershell
New-Item -ItemType Directory -Force -Path out | Out-Null
javac -d out (Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName })
java -cp out taskcli.Main help
```

What this does:

- `javac -d out ...` compiles all `.java` files into the `out` directory
- `java -cp out taskcli.Main ...` runs the compiled application using `out` as the classpath

## Commands

```text
add <description>           Add a new task
update <id> <description>   Update a task
delete <id>                 Delete a task
list [status]               List all tasks or filter by status
mark-in-progress <id>       Mark a task as in progress
mark-done <id>              Mark a task as done
help                        Show help
```

Supported statuses:

- `todo`
- `in-progress`
- `done`

## Examples

```bash
./run.sh add "Buy groceries"
./run.sh add "Read Effective Java"
./run.sh list
./run.sh update 1 "Buy groceries and fruit"
./run.sh mark-in-progress 2
./run.sh mark-done 2
./run.sh list done
./run.sh delete 1
```

Manual Java command equivalents:

```powershell
java -cp out taskcli.Main add "Buy groceries"
java -cp out taskcli.Main list
java -cp out taskcli.Main mark-done 1
```

## Data Storage

Tasks are stored in:

```text
data/tasks.json
```

The file path is relative to the project root, so run the app from the repository root directory.

## Notes

- Errors are printed to the console as user-friendly messages.
- This project currently uses a handwritten JSON mapper instead of an external JSON library.
- There is not a test suite yet.
