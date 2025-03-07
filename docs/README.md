# Yoshi User Guide
## Yoshi

```
                 __   __  _______  _______  __   __  ___ 
                |  | |  ||       ||       ||  | |  ||   |
                |  |_|  ||   _   ||  _____||  |_|  ||   |
                |       ||  | |  || |_____ |       ||   |
                |_     _||  |_|  ||_____  ||       ||   |
                  |   |  |       | _____| ||   _   ||   |
                  |___|  |_______||_______||__| |__||___|
```

Yoshi is a chatbot designed for almost anyone who wants to keep track of their tasks and boost their
productivity levels. It is currently optimized for use via the command-line interface (CLI).

## Feature List
Currently, these are the fundamental features that you can utilize when chatting with Yoshi.
1. Adding tasks (up to 3 types of tasks)
2. Listing down tasks
3. Deleting tasks
4. Marking/unmarking tasks as done
5. Finding tasks

# Feature Guide
## Adding tasks
Adds a task into the task list. Take note that there are 3 types of tasks that 
you can add into the task list, as we will see below.

### Adding Todos
Adds a task of type "todo" into the task list.

Format: `todo {taskDescription}`

Example: `todo study for CS test`
```angular2html
    ____________________________________________________________
    Sure! I've added this task:
     [T][ ] study for CS test
    You now have 4 tasks in your list!
    ____________________________________________________________
    Yay! Your save file has been updated successfully :D
    ____________________________________________________________
```

### Adding Deadlines
Adds a task of type "deadline" into the task list. Note that the deadline
must contain a specific deadline time, else an error message will pop up.

Format: `deadline {taskDescription} /by {deadline}`

Example: `deadline do CS2113 Weekly Quiz /by 11.59pm`
```
    ____________________________________________________________
    Sure! I've added this task:
     [D][ ] do CS2113 Weekly Quiz (by: 11.59pm)
    You now have 5 tasks in your list!
    ____________________________________________________________
    Yay! Your save file has been updated successfully :D
    ____________________________________________________________
```

### Adding Events
Adds a task of type "event" into the task list. Note that an event must contain a start time
and an end time, else an error message will pop up.

Format: `event {taskDescription} /from {startTime} /to {endTime}`
```angular2html
    ____________________________________________________________
    Sure! I've added this task:
     [E][ ] CS2113 Lecture (from: 4pm to: 6pm)
    You now have 6 tasks in your list!
    ____________________________________________________________
    Yay! Your save file has been updated successfully :D
    ____________________________________________________________
```

## Listing the task list
Lists down all the tasks currently available in the task list, if there are any.

Format: `list`

Example: `list` (when the list is not empty)
```    
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] come to lecture
     2.[D][ ] do CS2113 Weekly quiz (by: Friday 23.59pm)
     3.[E][ ] CS2113 Lecture (from: 4pm to: 6pm)
    ____________________________________________________________
```
On the other hand, if the list is empty, then an error message will pop up.

Example: `list` (when the list is empty)
```angular2html
    ____________________________________________________________
    Oh no! Your task list is empty!
    ____________________________________________________________
```

## Deleting tasks 

Deletes a task from the list of tasks. Do ensure that the task number to be
deleted does not exceed your current number of tasks.

Format: `delete {taskNumber}`

Example: `delete 1`

If for example, task 1 is a valid task in the list as follows.
```angular2html
    1. [T][ ] come to lecture
```
then deleting this task will show the following result.

```
    ____________________________________________________________
    Sure! I've deleted this task:
     [T][ ] come to lecture
    You now have 0 tasks in your list!
    ____________________________________________________________
    Yay! Your save file has been updated successfully :D
    ____________________________________________________________
```



## Marking/unmarking tasks as done

Marks the corresponding task as done, or unmarks the corresponding task as done.

For marking, the format is: `mark {taskNumber}` and 

similarly, the format for unmarking is: `unmark {taskNumber}`

Example 1: `mark 1`
```angular2html
    ____________________________________________________________
     Great job! I've marked this task as done:
      [T][X] come to lecture
    ____________________________________________________________
```

Example 2: `unmark 1`
```angular2html
    ____________________________________________________________
     Sure! I've marked this task as not done yet:
      [T][ ] come to lecture
    ____________________________________________________________

```

## Finding tasks
Finds tasks that contain a certain keyword.

Format: `find {keyword}`

Example: `find {lecture}`
```angular2html
    ____________________________________________________________
    Here are the matching tasks containing "lecture" in your list:
    1. [T][ ] come to lecture
    2. [D][ ] watch lecture recording (by: 11.59pm)
    There are 2 tasks that matches your search
    ____________________________________________________________
```