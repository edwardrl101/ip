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
6. Ending the conversation

# Feature Guide

## Adding Todos
Adds a task of type "todo" into the task list.

Format: `todo {taskDescription}`

Example: `todo study for CS test`

Expected Output:
```angular2html
    ____________________________________________________________
    Sure! I've added this task:
     [T][ ] study for CS test
    You now have 4 tasks in your list!
    ____________________________________________________________
    Yay! Your save file has been updated successfully :D
    ____________________________________________________________
```

## Adding Deadlines
Adds a task of type "deadline" into the task list. Note that the deadline
must contain a specific deadline time, else an error message will pop up.

Format: `deadline {taskDescription} /by {deadline}`

Example: `deadline do CS2113 Weekly Quiz /by 11.59pm`

Expected Output:
```
    ____________________________________________________________
    Sure! I've added this task:
     [D][ ] do CS2113 Weekly Quiz (by: 11.59pm)
    You now have 5 tasks in your list!
    ____________________________________________________________
    Yay! Your save file has been updated successfully :D
    ____________________________________________________________
```

## Adding Events
Adds a task of type "event" into the task list. Note that an event must contain a start time
and an end time, else an error message will pop up.

Format: `event {taskDescription} /from {startTime} /to {endTime}`

Example: `event CS2113 Lecture /from 4pm /to 6pm`

Expected Output:
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

Expected Output:
```    
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] come to lecture
     2.[D][ ] do CS2113 Weekly quiz (by: Friday 23.59pm)
     3.[E][ ] CS2113 Lecture (from: 4pm to: 6pm)
    ____________________________________________________________
```
On the other hand, if the list is empty, then the following message will pop up.

Example: `list` (when the list is empty)

Expected Output:
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
then the expected output is:

```
    ____________________________________________________________
    Sure! I've deleted this task:
     [T][ ] come to lecture
    You now have 0 tasks in your list!
    ____________________________________________________________
    Yay! Your save file has been updated successfully :D
    ____________________________________________________________
```



## Marking tasks as done

Marks the corresponding task as done.

Format: `mark {taskNumber}` 


Example: `mark 1`

Expected Output:
```angular2html
    ____________________________________________________________
     Great job! I've marked this task as done:
      [T][X] come to lecture
    ____________________________________________________________
```

## Unmarking tasks as done
Unmarks a previously completed task as incomplete.

Format: `unmark {taskNumber}`

Example: `unmark 1`

Expected Output:
```angular2html
    ____________________________________________________________
     Sure! I've marked this task as not done yet:
      [T][ ] come to lecture
    ____________________________________________________________

```

## Finding tasks
Finds tasks that contain a certain keyword.

Format: `find {keyword}`

Example: `find lecture`

Expected Output:
```angular2html
    ____________________________________________________________
    Here are the matching tasks containing "lecture" in your list:
    1. [T][ ] come to lecture
    2. [D][ ] watch lecture recording (by: 11.59pm)
    There are 2 tasks that matches your search
    ____________________________________________________________
```

## Printing tasks
Prints the corresponding task with the task number.

Format: `print {taskNumber}`

Example: `print 1`

Expected Output:
```angular2html
____________________________________________________________
 [T][ ] come to lecture
____________________________________________________________
```

## Ending the conversation
Ends the conversation with Yoshi and shuts down the chatbot.

Format: `bye`

Example: `bye`

Expected Output:
```angular2html
    ____________________________________________________________
    Bye! Hope to see you again soon! Keep working hard! :-)
    ____________________________________________________________
```

# Closing Remarks
Thank you for using Yoshi! We hope you have fun and continue being productive
with the help of Yoshi! Should you encounter any difficulties, please refer
to this user guide or the error messages to guide you on what went wrong :).
