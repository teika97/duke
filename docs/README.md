# User Guide
## 1. Introduction
Project Duke is a a Personal Assistant Chatbot that helps a person keep track of various things. 
In order to run Duke, please ensure you have Java versions 11 installed in your computer.

Download the latest version of Duke i.e. duke-0.3.jar.

To run the application:
* For Mac Users, run the jar file in terminal
* For Window Users, double-click the jar file

## 2. Features
This application contains the following features as described in this section. 
Please follow the set command syntax as shown below:

#### 2.1 Add new tasks into the task list
   There are several types of tasks that can be inserted into the task list.
   * To-Do
   * Event
   * Deadline

Command Syntax:

`todo`  `task_description`: 
A todo task will be added into the list

`event` `task_description` `/at` `date` `time`:
A event that happens at the date time stated will be added into the list

`deadline` `task_description` `/by` `date` `time`: 
A deadline that expires at the date time stated will be added into the list

Expected Usage | Expected Outcome
-------------- | ----------------
todo Buy books | [T][☓] Buy books
event Birthday party /at 11-08-2019 16:00 | [E][☓] Birthday party (at: 11 August, 2019, 04:00 PM)
deadline Project submission /by 19-10-2019 23:59 | [D][☓] Project submission (by: 19 August, 2019, 11:59 PM)

For date time format, please use `dd-MM-yyyy HH:mm`.

By default, all tasks are set as incomplete.

#### 2.2 Mark a task as completed
The user can indicate a task as completed in the list using this feature.

Command Syntax:

`done` `list_num`: The task at list_num will be set as done

Expected Usage | Expected Outcome
-------------- | ----------------
done 2 | Acknowledgement that task has been marked as done

#### 2.3 Delete a task from the list
The user can delete a task from the list using this feature.

Command Syntax:

`delete` `list_num`: The task at list_num will be removed from the list

Expected Usage | Expected Outcome
-------------- | ----------------
delete 2 | Acknowledgement that task has been removed from list

#### 2.4 Find all tasks that have a particular keyword
The user can find all tasks with task descriptions containing the specified keyword.

Command Syntax:

`find` `key_word`: Finds and lists all tasks with a task description containing the key_word

Expected Usage | Expected Outcome
-------------- | ----------------
find birthday | List of all tasks with the keyword 'birthday' 

#### 2.5 Sort the list according to task type as well as date and time
The user can sort a list according to task type, which each task type sorted according to the following order:
* Todo: By `task_description` in alphabetical order
* Event: By `date` `time` in chronological order
* Deadline: By `date` `time` in chronological order

Command Syntax:

`sort`: Sorts all tasks in list

Expected Usage | Expected Outcome
-------------- | ----------------
sort | Acknowledgement that command has been completed.

#### 2.6 Print all tasks in the task list
The user can print out the entire task list in the format as follows:
`Task_type` `Task_status` `Task_description`
In the event that the task is an event or deadline. The respective date and time will also be printed.

Command Syntax:

`list`: Prints all tasks in list

Expected Usage | Expected Outcome
-------------- | ----------------
list | List of all tasks in task list

Task | Task_type
---- | ---------
To-Do | [T]
Event | [E]
Deadline | [D]

Note that task_status is marked with a tick if completed, and a cross if incomplete.

#### 2.7 Exit application

The user can exit the program and save the new task list using this command. 
Note that if the user does not exit properly, the changes will not be saved.

Command Syntax:

`bye`: Exits the application

Expected Usage | Expected Outcome
-------------- | ----------------
bye | Exit application

## 3. External Storage of Task List
Duke saves all tasks in the task list into a file called `data.txt` found in the `data` directory that can be found in the `duke` directory.

In the case that there is no such directory, a new directory and file will be created accordingly. However, if the file and directory already exist,
then all tasks in the task list will be loaded into the application upon startup. 

Do note that any changes made to the file externally will be reflected in the application upon startup.
