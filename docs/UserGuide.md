---
layout: page
title: User Guide
---

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Introduction

**LinkyTime** is a desktop app for NUS students to organize their Zoom/online meeting links, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, **LinkyTime** can get your meeting management tasks done faster than traditional GUI apps.

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your computer.

2. Download the latest `linkytime.jar` from [here](https://github.com/AY2122S2-CS2103T-T13-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for the app.

4. Double-click the file to start the app. A GUI similar to the one shown below should appear in a few seconds. Note how the app contains some sample data.<br>
    
    ![Ui](images/Ui.png)

6. Type the command in the command box and press Enter to execute it. e.g. typing **`list`** and pressing Enter will list all the entries.<br>
   
    Some example commands you can try:

    * **`list`** : Lists all meeting entries.
    * **`add`**`l/https://meet.google.com n/CS2103T Lecture d/Friday 2pm` : Adds a meeting named `CS2103T Lecture` to the list of meeting entries.
    * **`delete`**`3` : Deletes the 3rd entry shown in the current list.
    * **`exit`** : Exits the app.

7. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Command Format

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add l/LINK`, `LINK` is a parameter which can be used as `add l/meet.google.com`.

* Items in square brackets are optional.<br>
  e.g. `l/LINK [t/TAG]` can be used as `l/meet.google.com t/lecture` or as `l/meet.google.com`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/lecture`, `t/lecture t/cs2103t` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `l/LINK n/MEETING_NAME`, `n/MEETING_NAME l/LINK` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `n/CS2103T Lecture n/CS2103T Tutorial`, only `n/CS2103T Tutorial` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

## Features

This section describes each of the commands and features available in **LinkyTime**.

### Meeting Entry Management

#### Add a meeting entry: `add`

Adds a meeting entry into the meeting list.

Format: `add n/MEETING_NAME l/LINK d/DATETIME [t/TAG]…​`

Parameters:
* `MEETING_NAME` The name of the meeting entry.
* `LINK` The link to the online meeting.
* `DATETIME` The date and starting time of the meeting.
* `TAG` The tags associated with the meeting entry.

Examples:
* `add n/CS2103T Lecture l/meet.google.com d/Friday 2pm t/lecture t/cs2103t`


#### List all meeting entries : `list`

View all meeting entries in the entry list and display their respective details.

Format: `list`

Details include:
* Zoom link
* Meeting name
* Meeting date/time


#### Delete a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.


#### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`


### General

#### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


#### Exiting the program : `exit`

Exits the program.

Format: `exit`


#### Saving the data

Your meeting entries are saved to your computer automatically upon running any command that changes the data. There is no need to save manually.


#### Editing the data file

The meeting entries are saved in a JSON file at `[JAR file location]/data/meetings.json`. Advanced users are welcome to update their entries directly by editing that file.  

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, LinkyTime will discard all data and start with an empty data file on the next run.
</div>

#### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/MEETING_NAME l/LINK d/DATETIME [t/TAG]…​` <br> e.g., `add n/CS2103T Lecture l/meet.google.com d/Friday 2pm t/lecture t/cs2103t`
**List** | `list`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Clear** | `clear`
**Edit** | Coming soon
**Find** | Coming soon
**Sort** | Coming soon
**Open** | Coming soon
**Help** | `help`
**Exit** | `exit`
