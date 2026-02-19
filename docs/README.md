# Christopher User Guide

Hey guys, this is Christopher! He is named after Alan Turing's supposed childhood love in the movie "The Imitation Game."

# Supported Features

## Adding a ToDo Task

You can add a ToDo task with the following format. It is the most basic task type with no time factor involved. 

`todo <task name>`

## Adding a Deadline Task

A deadline task is a task that has to be done by a specific deadline. You can add such a task by the following format:

`deadline <task name> /by <date and time>`

_The usage of "/by" is very crucial here_ 

The date and time **MUST** be in the `yyyy-mm-dd hh:mm` format

## Adding an Event Task

An event is a task which has a start date and time and an end date and time. It can be added like this:

`event <task name> /from <date and time> /to <date and time>`

_Again, the use of "/from" and "/by" in this exact form is important_

The date and time **MUST** be in the `yyyy-mm-dd hh:mm` format

## Asking for the current tasks

You can see your current task list by the following command:

`list`

## Marking a task complete 

If you are done with a task, you can mark it complete with following command:

`mark <index>`

Index is a number, with a number n being the nth item on your task list. For computer scientists, it is 1 indexed

## Unmark

This is essentially doing the reverse as marking a task complete. 

`unmark <index>`

## Delete 

You can delete a task from your list by the following command 

`delete <index>`

Index works the same way as mark and unmark 

## Find 

You can find tasks in the following format:

`find <keyword> <keyword> <keyword> ...`

It will return a list of tasks, each of which containing any of the supplied "keyword."

## Sort 

You can type `sort` and Christopher will return you a task list sorted by when the task ends 

For events, this is the end time; deadlines for deadline tasks; and since ToDos are lax, they are usually last

## Bye

`Bye` quits the bot

# Saving

Christopher remembers your list automatically so you don't have to worry, you can just quit. 

