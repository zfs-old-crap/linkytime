---
layout: page
title: User Guide
---

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Introduction

**LinkyTime** is a desktop app for NUS students to organize their online meeting links, optimized for use via a Command
Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast,
**LinkyTime** can get your meeting management tasks done faster than traditional GUI apps.

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your computer.

2. Download the latest `linkytime.jar` from [here](https://github.com/AY2122S2-CS2103T-T13-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for the app.

4. Double-click the file to start the app. A GUI similar to the one shown below should appear in a few seconds. Note how
   the app contains some sample data.<br>

   ![Ui](images/Ui.png)

6. Type the command in the command box and press Enter to execute it. e.g. typing **`list`** and pressing Enter will list all the meetings.<br>

   Some example commands you can try:

    * `list` : Lists all meetings.
    * `add n/Tutorial u/https://www.zoom.com d/25-03-2022 1400 dur/2 m/1 r/Y t/Quiz` : Adds a meeting named `Tutorial` to the list of meetings.
    * `delete 3` : Deletes the 3rd meeting shown in the current list.
    * `exit` : Exits the app.

7. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Command Format

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add u/URL`, `URL` is a parameter which can be used as `add u/https://meet.google.com`.

* Items in square brackets are optional.<br>
  e.g. `u/URL [t/TAG]` can be used as `u/https://meet.google.com t/midterm` or as `u/https://meet.google.com`.

* Items with `…`​ after them can be used zero or more times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/recorded`, `t/recorded t/lecturequiz` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `u/URL n/MEETING_NAME`, `n/MEETING_NAME u/URL` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `n/Lecture n/Tutorial`, only `n/Tutorial` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Command Parameters

Most LinkyTime commands use various parameters. Their formats and constraints are provided in the table below.

| Parameter      | Prefix | Used in      | Description                                                                                                                                                                                                                                                                                                                                      |
| -------------- | ------ | ------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `MEETING_NAME` | `n/`   | `add` `edit` | The name of a meeting. <ul><li> Accepts only alphanumeric characters and spaces. </li><li> Must not be blank. </li></ul>                                                                                                                                                                                                                         |
| `URL`          | `u/`   | `add` `edit` | The URL/link to a meeting. <ul><li> Must include the full URL link, i.e. starts with `https://`. </li><li> Must not be blank. </li></ul>                                                                                                                                                                                                         |
| `DATETIME`     | `d/`   | `add` `edit` | The date and time of a meeting. <ul><li> Must be of the following format: `dd-MM-yyyy HHmm`. </li><li> `dd` - 2 digit day, e.g. `01`, `28`. </li><li> `MM` - 2 digit month, e.g. `01`, `12`. </li><li> `yyyy` - 4 digit year, e.g. `2022`. </li><li> `HHmm` - 24-hour time, e.g. `0800`, `1430`, `2359`. </li><li> Must not be blank. </li></ul> |
| `DURATION`     | `dur/` | `add` `edit` | The duration of a meeting in hours. <ul><li> Must be a decimal number greater than 0 and less than 24, e.g. `1`, `2.5`. </li><li> Accepts up to 4 decimal places. </li><li> Must not be blank. </li></ul>                                                                                                                                        |
| `MODULE_INDEX` | `m/`   | `add` `edit` | The index number of a module as shown in the displayed list. <ul><li> Must be a positive integer, e.g. 1, 2, 3, ... </li><li> Must not be blank. </li></ul>                                                                                                                                                                                      |
| `IS_RECURRING` | `r/`   | `add` `edit` | The recurrence of a meeting. If specified as `Y`, i.e. set to recur every week, the meeting will not expire and will be set to automatically repeat weekly. <ul><li> Must be given as `Y` or `N`. </li><li> Input is not case-sensitive. </li><li> Must not be blank. </li></ul>                                                                 |
| `TAG`          | `t/`   | `add` `edit` | The tag(s) assigned to a meeting. <ul><li> Accepts only alphanumeric characters. </li><li> Spaces are not allowed. </li></ul>                                                                                                                                                                                                                    |

_More to be added..._

## Features

This section describes each of the commands and features available in **LinkyTime**.

### Meeting Management

#### Add a meeting: `add`

Adds a meeting into the meeting list.

Format: `add n/MEETING_NAME u/URL d/DATETIME dur/DURATION m/MODULE_INDEX r/IS_RECURRING [t/TAG]...`

Parameters:

* `MEETING_NAME` The name of the meeting.
* `URL` The URL to the online meeting.
* `DATETIME` The date and starting time of the meeting.
* `DURATION` The duration of the meeting in hours. Must be a decimal number between `0 < duration <= 24`. E.g., `2`, `1.5`.
* `MODULE_INDEX` The index of the module in the module list that the meeting is for.
* `IS_RECURRING` Whether the meeting recurs every week. Given as `Y` or `N`.
* `TAG` The tags associated with the meeting.

Examples:

* `add n/Lecture u/https://www.zoom.com d/25-03-2022 1400 dur/2 m/1 r/Y t/recorded t/lecturequiz`
* `add n/Midterm u/https://meet.google.com d/13-03-2022 1000 dur/1.5 m/2 r/N`

#### List all meetings : `list`

View all meetings in the meeting list and display their respective details.

Format: `list`

Some details include:

* Meeting URL
* Meeting name
* Meeting date and time
* Module the meeting is assigned to

#### Find a meeting : `find`

Find meetings in the meeting list that matches the provided keywords and displays their respective details.

Format: `find KEYWORD MORE_KEYWORDS...`

* Displays all meetings with name, module code or tags that matches all of the provided keywords.

Examples:

* `find CS2101 Aaron` displays all meetings with fields that matches `CS2101` and `Aaron`.

#### Open a meeting URL: `open`

Opens a meeting URL link in the default web browser.

#### Edit a meeting : `edit`

Edits a meeting in the meeting list.

Format: `edit INDEX [n/MEETING_NAME] [u/URL] [d/DATETIME] [dur/DURATION] [m/MODULE_INDEX] [r/IS_RECURRING] [t/TAG]...`

* Edits the meeting at the specified `INDEX`. The index refers to the index number shown in the displayed meeting list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the meeting will be removed, i.e., adding of tags is not cumulative.
* You can remove all the meeting’s tags by typing t/ without specifying any tags after it.

Examples:

* `edit 1 t/ ` Removes all tags from the meeting at `INDEX` 1
* `edit 2 n/Midterm` Changes the name of the meeting at `INDEX` 2 to `Midterm`
* `edit 3 n/Recitation dur/1 t/Optional t/Recorded` Changes the name of the meeting at `INDEX` 3 to `Recitation`, duration of the meeting to 1 hour, and the tags to be `Optional` and `Recorded`.

#### Delete a meeting : `delete`

Deletes the specified meeting from the meeting list.

Format: `delete INDEX`

* Deletes the meeting at the specified `INDEX`.
* The index refers to the index number shown in the displayed meeting list.
* The index **must be a positive integer** 1, 2, 3, …​ that is within the range of the meeting list.

Examples:
* `list` followed by `delete 2` deletes the 2nd meeting in the meeting list.

### Module Management

<div markdown="block" class="alert alert-info">

:information_source: Modules are always sorted in alphabetical order.

</div>

#### Add a module: `madd`

Adds a module into the module list.

Format: `madd m/MODULE_NAME`

Parameters:

* `MODULE_NAME` The name or identifier of the module.

Examples:

* `madd m/CS2103T`
* `madd m/Internship`

#### Edit a module: `medit`

Edits the module specified in the module list.

#### Delete a module: `mdelete`

Deletes the specified module from the module list.

Format: `mdelete INDEX`

* Deletes the module at the specified `INDEX`.
  * If there are meetings that are currently assigned to the specified module, deletion would fail due to dependencies.
* The index refers to the index number shown in the displayed module list.
* The index **must be a positive integer** 1, 2, 3, …​ that is within the range of the module list.

Examples:

* `mdelete 2` deletes the 2nd module in the module list, provided there are no dependent meetings.

### General

#### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

#### Clearing all data : `clear`

Clears all meetings and modules from the application.

Format: `clear`

#### Exiting the program : `exit`

Exits the program.

Format: `exit`


#### Saving the data

Your meetings are saved to your computer automatically upon running any command that changes the data. There is no need to save manually.


#### Editing the data file

The meetings are saved in a JSON file at `[JAR file location]/data/app.json`. Advanced users are welcome to update their meetings directly by editing that file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, LinkyTime will discard all data and start with an empty data file on the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty `app.json` data file it creates with the
equivalent `app.json` data file from your previous LinkyTime installation.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                 | Format, Examples                                                                                                                                                                                         |
| ---------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Add a meeting**      | `add n/MEETING_NAME u/URL d/DATETIME dur/DURATION m/MODULE_INDEX r/IS_RECURRING [t/TAG]...` <br> e.g., `add n/Lecture u/https://www.zoom.com d/25-03-2022 1400 dur/1.5 m/1 r/Y t/recorded t/lecturequiz` |
| **List all meetings**  | `list`                                                                                                                                                                                                   |
| **Find meetings**      | `find [keyword] [more keywords...]` <br> e.g., `find CS2103T CS2101 Aaron`                                                                                                                               |
| **Open a meeting URL** | `open INDEX`<br> e.g. `open 2`                                                                                                                                                                           |
| **Edit a meeting**     | `edit INDEX [n/MEETING_NAME] [u/URL] [d/DATETIME] [dur/DURATION] [m/MODULE_INDEX] [r/IS_RECURRING] [t/TAG]...` <br> e.g.,  `edit 1 n/Recitation dur/1`                                                   |
| **Delete a meeting**   | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                      |
| **Add a module**       | `madd m/MODULE_NAME`                                                                                                                                                                                     |
| **Edit a module**      | `medit`                                                                                                                                                                                                  |
| **Delete a module**    | `mdelete INDEX`<br> e.g., `mdelete 3`                                                                                                                                                                    |
| **Show help**          | `help`                                                                                                                                                                                                   |
| **Clear all data**     | `clear`                                                                                                                                                                                                  |
| **Exit**               | `exit`                                                                                                                                                                                                   |
