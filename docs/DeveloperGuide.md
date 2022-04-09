---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).
* Libraries used: [JavaFX](https://openjfx.io/), [Jackson](https://github.com/FasterXML/jackson), [JUnit5](https://github.com/junit-team/junit5)
* Logo icon adapted from [AkashRajDahal](https://www.svgviewer.dev/s/12130/link).

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Introduction**

**LinkyTime** is a cross-platform desktop application designed for NUS Computer Science students to efficiently keep track of their online meetings and classes. The application is optimized for use via the Command Line Interface (CLI).

This Developer Guide (DG) serves to aid developers in explaining the design, architecture, and implementation of LinkyTime and its features. It also covers design considerations for specific features, and a guide on how to perform manual testing.

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `LinkyTimeParser` class to parse the user command.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddMeetingCommand`) which is executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to add a meeting).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Meetings
The Meetings component consists of the following set of features: List Meeting, Add Meeting, Edit Meeting, Delete Meeting, Find Meeting, and Opening Meeting URL.

#### The Meeting class
The Meeting class is made up of a `MeetingName`, `MeetingUrl`, `MeetingDateTime`, `Module`, `MeetingDuration`, `IsRecurring`,
`Set<Tag>`, and a set of getter methods that corresponds to these fields.

##### Recurring Meetings
A meeting is recurring if it's set to repeat on a weekly basis. Such meetings would never expire.

This section describes how the start and end date time of a recurring meeting is computed. The key implementation lies in
`getNextRecurrence`, a helper method which computes the next recurrence relative to the current date and time. Since
`getStartDateTime` and `getEndDateTime` utilises `getNextRecurrence`, they inherit the side effect of depending
on the current date and time as well.

Below is an activity diagram describing the execution of `getNextRecurrence`.

![`getNextRecurrence` sequence diagram](images/GetNextRecurrenceActivityDiagram.png)

#### Design considerations:
**Aspect: How the notion of recurrence is implemented:**

**Alternative 1 (current choice):** `getStartDateTime` and `getEndDateTime` will return their respective date times
relative to the current date time.
* Pros: Easier to implement and integrate with other components such as GUI and storage.
* Cons: Unit-testing is less trivial since `getStartDateTime` and `getEndDateTime` would return different date times
depending on when their test cases are executed.

**Alternative 2:** Generate the next set of recurring meetings to replace the existing ones. This is done at the start
of the program and after each command execution.
* Cons: Implementation would sprawl across different components and more effort is required to ensure correctness.

### Commands
This section explains the general implementation of all commands.
The implementation of all commands in LinkyTime can be split into two general implementation flows: commands with a command-specific parser, and commands without.

#### Commands with a parser
This section explains the general implementation of all commands that require a command-specific parser to handle additional user input.

Below is the sequence diagram for the execution of these commands (denoted by `XYZCommand`) after user input is sent to `LogicManager`. The execution of each command has been omitted due to their differences and will be covered in the respective command sections.

![`CommandsWithParser` sequence diagram](images/CommandsWithParserSequenceDiagram.png)

Step 1:
The user enters a command with additional required parameters (requires a command-specific parser) which is then passed to the `LogicManager`.

Step 2:
The `LogicManager` then calls `LinkyTimeParser::parseCommand` for it to figure out what command this is.

Step 3:
The `LinkyTimeParser` parses the user input and creates a command parser for that specific command. (denoted by `XYZCommandParser`)

Step 4:
The command parser is then returned to the `LinkyTimeParser` which then calls `XYZCommandParser::parse` to parse the additional parameters.

Step 5:
The `XYZCommandParser` then creates its respective command (denoted by `XYZCommand`) and returns it to `LogicManager`.

Step 6:
The `LogicManager` then calls `XYZCommand::execute` where the interaction between the command and the model is handled.

Step 7:
The `XYZCommand` then creates a successful `CommandResult` and returns it to the UI.

#### Commands without a parser
This section explains the general implementation of all commands that does not require a command-specific parser.

Below is the sequence diagram for the execution of these commands (denoted by `XYZCommand`) after user input is sent to `LogicManager`. The execution of each command has been omitted due to their differences and will be covered in the respective command sections.

![`CommandsWithoutParser` sequence diagram](images/CommandsWithoutParserSequenceDiagram.png)

Step 1:
The user enters a command which is then passed to the `LogicManager`.

Step 2:
The `LogicManager` then calls `LinkyTimeParser::parseCommand` for it to figure out what command this is.

Step 3:
The `LinkyTimeParser` parses the user input and creates the respective command object (denoted by `XYZCommand`).

Step 4:
The `XYZCommand` is then returned to the `LogicManager`.

Step 5:
The `LogicManager` then calls `XYZCommand::execute` where the interaction between the command and the model is handled.

Step 6:
The `XYZCommand` then creates a successful `CommandResult` and returns it to the UI.

#### List Meetings feature
This section explains the implementation of the List Meetings feature via the `list` command.
The `ListMeetingCommand` updates the UI to display the details of all upcoming meetings in `LinkyTime`.
It is a command that [does not require a parser](#Commands-without-a-parser). 

Below is the sequence diagram reference frame for the execution of `ListMeetingCommand`.

![`ListMeetingCommand` sequence diagram](images/ListMeetingSequenceDiagramReferenceFrame.png)

Step 1:
The `LogicManager` calls `ListMeetingCommand::execute` with the returned `ListMeetingCommand`.

Step 2:
The `ListMeetingCommand` then calls `Model::showCompletedMeetings` to update the meeting list to show only upcoming meetings.

Step 3:
The `ListMeetingCommand` then continues its execution as defined by [this](#Commands-without-a-parser) sequence diagram.

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `ListMeetingCommand` should continue out of this reference frame, but due to a limitation of PlantUML, the lifeline ends in this diagram.
</div>

#### Design considerations:
**Aspect: How `ListMeetingCommand` executes:**

**Alternative 1 (current choice):** `LinkyTimeParser` returns a `ListMeetingCommand` without having a parser.
* Pros: Easier to implement and allows for more flexible user input.
* Cons: All user inputs that contains `list` as its first word will result in the execution of `ListMeetingCommand`,
  including those that don't make sense. E.g. `list abc`

**Alternative 2:** `LinkyTimeParser` uses a `ListMeetingCommandParser` to enforce that the user input cannot have additional params.
* Pros: Provides clear definition of what the user input for a `ListMeetingCommand` should be.
* Cons: Harder to implement and more rigid in nature.

#### Archive Meetings feature
This section explains the implementation of the Archive Meetings feature via the `archive` command.
The `ArchiveMeetingCommand` updates the UI to display the details of all completed meetings in `LinkyTime`.
It is identical in implementation to the `ListMeetingCommand` except for the flip in the boolean that is passed into `Model::showCompletedMeetings`.

#### Add Meeting feature

This section explains the implementation of the Add Meeting feature via the `add` command.
The `AddMeetingCommand` causes the specified meeting to be added to the application.
This command requires several compulsory fields such as the meeting name, URL, date time, duration, module, and whether it is recurring.
There is only one optional field which is the tags of the meeting.

Below is the sequence diagram for the execution of `AddMeetingCommand`

![`AddMeetingCommand` sequence diagram](images/AddMeetingSequenceDiagram.png)

Step 1:
The user enters the command for adding a meeting, e.g. `add n/Lecture ...`

Step 2:
The user input is parsed through the `LinkyTimeParser`, which will then pass the user input to `AddMeetingCommandParser`
to check if the user input is valid.

Step 3:
Once the user input is successfully parsed, the `AddMeetingCommandParser` creates a `AddMeetingCommand` containing the
meeting to be added.

Step 4:
The `LogicManager` subsequently invokes `AddMeetingCommand::execute`, which in turn calls `Model::addMeeting` to add the
new meeting into the list.

Step 5:
The `Model` will then call its own `updateFilteredMeetingList` method in order to update the model's filter to display
all meetings.

##### Design considerations:

**Aspect: How AddMeetingCommand executes:**

* **Alternative 1 (current choice):** Let the `LogicManager` pass the model to the command to execute.
    * Pros: Will not need to expose the model to the individual `AddMeetingCommand`.

* **Alternative 2:** Store the model in the `AddMeetingCommand` itself.
    * Pros: Easier to implement and trace.
    * Cons: The `AddMeetingCommand` might be able to abuse the model by calling the model's other methods.

#### Delete Meeting feature

This section explains the implementation of the Delete Meeting feature via the `delete` command.
The `DeleteMeetingCommand` removes the meeting with the given index from the meeting list. This command requires a
single field: the index of the meeting to be deleted.

Below is the sequence diagram for the execution of an `DeleteMeetingCommand`.

![`DeleteMeetingCommand` Sequence Diagram](images/DeleteMeetingSequenceDiagram.png)

Step 1:
The user enters a command for deleting a meeting, e.g. `delete 1`.

Step 2:
The user input is passed to `LogicManager`, which passes the user input to `LinkyTimeParser` to parse and identify the
command type.

Step 3:
`LinkyTimeParser` passes the user input to `DeleteMeetingCommandParser` to check if the user input is valid.

Step 4:
`DeleteMeetingCommandParser` parses the user input, creates a new `DeleteMeetingCommand` and returns it
to `LogicManager`.

Step 5:
The `LogicManager` calls `DeleteMeetingCommand::execute` which calls `Model::deleteMeeting`.

Step 6:
The `DeleteMeetingCommand` creates a `CommandResult` and passes it back to the `LogicManager`.

#### Design considerations:

**Aspect: How `DeleteMeetingCommand` executes:**

* Similar to the considerations of the `AddMeetingCommand`, this command is also concerned with the model storage and
  the modification of the underlying model object.

#### Find Meeting feature

This section explains the implementation of the Find Meeting feature via the `find` command. The `FindMeetingCommand`
causes the GUI to only show meetings that matches the given keywords.

Below is the sequence diagram for the execution of the `FindMeetingCommand`.

![`FindMeetingCommand` sequence diagram](images/FindMeetingSequenceDiagram.png)

Step 1:
The user enters the command for finding meetings, e.g. `find cs2103t tutorial`.

Step 2:
The user input is passed to the `LogicManager`, which passes it to the `LinkyTimeParser`.

Step 3:
`LinkyTimeParser` consumes the user input then passes the remaining input to a new `FindMeetingCommandParser`.

Step 4:
`FindMeetingCommandParser` consumes the user input and creates a new `FindMeetingCommand` with a predicate that
describes the criteria for the meetings to be shown.

Step 5:
The `FindMeetingCommand` is passed to `LogicManager`.

Step 6:
`LogicManager` calls `FindMeetingCommand::execute`, which calls `Model::updateFilteredMeetingList` with the predicate.

Step 7:
The `FindMeetingCommand` creates a new `CommandResult` and returns it to the `LogicManager`.

##### Design considerations:

**Aspect: How FindMeetingCommand executes:**

* Similar to the considerations of the `AddMeetingCommand`, this command is also concerned with the model storage and
  the modification of the underlying model object.

**Aspect : Behaviour of find command when multiple keywords are provided:**

* **Alternative 1 (current choice):** Command will return meetings which match **all** the given keywords.
    * Pros: Command can be used to narrow down a search to a small set of desired meetings.
    * Cons: Command may be too restrictive, requiring users to ensure that all keywords provided match the desired
      meetings.

* **Alternative 2:** Command will return meetings which match **at least one** of the given keywords.
    * Pros: Command can be used to find a diverse set of meetings that users may be interested in.
    * Cons: Command cannot be used to narrow down the search; adding more keywords may increase the number of meetings
      returned.

### Modules

The Modules component consists of the following set of features: Add Module, Edit Module, Delete Module.

All module-related commands are prefixed with an `m` to distinguish it from meeting-related commands.

#### The Module class

The Module class consists of a single field, and input validation happens directly inside the class via `Module::isValidModule`.

The `Module` objects are stored in a `UniqueModuleList` which is held by `LinkyTime`.

##### Design considerations:

* **Alternative 1 (current choice):** Store the module code string directly in the Module object.
  * Pros: Easy to implement and understand.
  * Cons: Difficult to add additional fields in the future.
* **Alternative 2:** Abstract the field out as a separate class.
  * Pros:
    * More object-oriented approach.
    * Responsibility of field verification would be done by the field class instead of the Module class.
  * Cons:
    * Over-abstraction of the Module class for the current implementation which consists of only one field.
    * May not be intuitive to understand at first glance.

As we do not intend to contain any additional fields within the Module object, we opted for a simpler approach in its design.

#### Delete Module feature

This section explains the implementation of the Delete Module feature via the `mdelete` command.

The `DeleteModuleCommand` causes the specified module to be deleted from the application.

If there are meetings that are tagged under this module, the command execution is blocked and an error message is displayed to the user. A proposed extension of this feature would be to include a flag that allows the user to force the deletion of the module and its associated meetings.

This process is summarized in the diagram below.

![DeleteModuleActivityDiagram](images/DeleteModuleActivityDiagram.png)

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

Aiken Dueet is a Year 2 NUS Computer Science student. He is currently taking his modules online due to COVID-19. He:

* has a need to manage a large number of online meeting links and details
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage online meeting links much quicker than a typical mouse/GUI driven app

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …                       | I want to …                                 | So that I can…                                             |
| -------- | ---------------------------- | ------------------------------------------- | ---------------------------------------------------------- |
| `* * *`  | new user                     | see usage instructions                      | refer to instructions if I forget how to use the App       |
| `* * *`  | user                         | view all my upcoming meetings               | see what meetings I have up next                           |
| `* * *`  | user                         | view all my archived meetings               | see my old meetings that I might have missed               |
| `* * *`  | user                         | add a new meeting                           | add my online lecture details                              |
| `* * *`  | user                         | open a meeting URL                          | open my meeting URL in my browser quickly                  |
| `* * *`  | user                         | edit an existing meeting                    | update my online lecture details                           |
| `* * *`  | user                         | delete a meeting                            | remove old meetings that I don’t need anymore              |
| `* *`    | user with multiple meetings  | search for meetings                         | find my online meetings easily                             |
| `* * *`  | user                         | add a new module                            | add my classes I am taking this semester                   |
| `* *`    | user                         | edit an existing module                     | update a module's details                                  |
| `* * *`  | user                         | delete a module                             | remove old modules I am no longer taking                   |
| `* *`    | user                         | delete a module and its associated meetings | quickly remove old modules and its meetings                |
| `* *`    | user starting a new semester | clear all data                              | remove old meetings and modules from the previous semester |

--------------------------------------------------------------------------------------------------------------------

## Use cases

For all use cases below, the **System** is `LinkyTime` and the **Actor** is the `user`, unless specified otherwise.
All use cases are prefixed with `UC`, followed by a three-digit use case number.

### UC-001: List all upcoming meetings

**MSS**

1. User requests to list all upcoming meetings.
2. LinkyTime shows a list of all upcoming meetings.

    Use case ends.

**Extensions**

* 2a. The meeting list is empty.

    Use case ends.

### UC-002: List all archived meetings

**MSS**

1. User requests to list all archived meetings.
2. LinkyTime shows a list of all archived meetings.

    Use case ends.

**Extensions**

* 2a. The meeting list is empty.

    Use case ends.

### UC-003: Add a meeting

**MSS**

1. User requests to add a new meeting to the meeting list.
2. LinkyTime adds a new meeting with given parameters in the meeting list.

    Use case ends.

**Extensions**

* 1a. The user does not include the required parameters with the command.
  * 1a1. LinkyTime shows an error message stating which parameters are required.

    Use case ends.

* 1b. The user does not follow the format of the command.
  * 1b1. LinkyTime shows an error message stating the correct format of the command.

    Use case ends.

### UC-004: Open a meeting

**MSS**

1. User requests to list meetings.
2. LinkyTime shows the list of meetings.
3. User requests to open a specific meeting's URL based on the index from the meeting list displayed in step 2.
4. LinkyTime opens the default browser with the given URL.

    Use case ends.

**Extensions**

* 2a. The meeting list is empty.

    Use case ends.

* 3a. The given index is invalid.
  * 3a1. LinkyTime shows an error message stating that the given index is invalid.

    Use case resumes at step 2.

* 3b. The application does not have permissions to open the browser.
  * 3b1. The application displays an error message.

    Use case ends.

### UC-005: Edit a meeting

**MSS**

1. User requests to list meetings.
2. LinkyTime shows the list of meetings.
3. User requests to edit a specific meeting based on the index from the meeting list displayed in step 2.
4. LinkyTime edits the meeting and saves the changes.

    Use case ends.

**Extensions**

* 2a. The meeting list is empty.

    Use case ends.

* 3a. The given index is invalid.
  * 3a1. LinkyTime shows an error message stating that the given index is invalid.

    Use case resumes at step 2.

* 3b. No arguments were given.
  * 3b1. LinkyTime shows an error message stating that at least one field must be edited.

    Use case resumes at step 2.

* 3c. The user does not follow the format of the command.
  * 3c1. LinkyTime shows an error message stating the correct format of the command.

    Use case resumes at step 2.

### UC-006: Delete a meeting

**MSS**

1. User requests to list meetings.
2. LinkyTime shows a list of meetings.
3. User requests to delete a specific meeting in the meeting list based on the index from the meeting list displayed in step 2.
4. LinkyTime deletes the meeting and saves the changes.

    Use case ends.

**Extensions**

* 2a. The meeting list is empty.

    Use case ends.

* 3a. The given index is invalid.
  * 3a1. LinkyTime shows an error message stating that the given index is invalid.

    Use case resumes at step 2.

### UC-007: Find meetings

**MSS**

1. User searches for a meeting by a search term.
2. LinkyTime shows a list of meetings whose name, tags, or module contains the search term.

    Use case ends.

**Extensions**

* 2a. There are no meetings matching the search term.
  * 2a1. An empty meeting list is displayed.

    Use case ends.


### UC-008: Add a module

**MSS**

1. User requests to add a new module to the module list.
2. LinkyTime adds a new module with given parameters in the module list.

    Use case ends.

**Extensions**

* 1a. The user does not include the required parameters with the command.
  * 1a1. LinkyTime shows an error message stating which parameters are required.

    Use case ends.

* 1b. The user does not follow the format of the command.
  * 1b1. LinkyTime shows an error message stating the correct format of the command.

    Use case ends.

### UC-009: Edit a module

**MSS**

1. User requests to edit a specific module based on the index from the module list displayed.
2. LinkyTime edits the module and all associated meetings and saves the changes.

    Use case ends.

**Extensions**

* 1a. The module list is empty.

    Use case ends.

* 1b. The given index is invalid.
  * 1b1. LinkyTime shows an error message stating that the given index is invalid.

    Use case resumes at step 1.

* 1c. No arguments were given.
  * 1c1. LinkyTime shows an error message stating that at least one field must be edited.

    Use case resumes at step 1.

* 1d. The user does not follow the format of the command.
  * 1d1. LinkyTime shows an error message stating the correct format of the command.

    Use case resumes at step 1.

### UC-010: Delete a module

**MSS**

1. User requests to delete a specific module in the module list based on the index from the module list displayed.
2. LinkyTime deletes the module and saves the changes.

    Use case ends.

**Extensions**

* 1a. The module list is empty.

    Use case ends.

* 1b. The given index is invalid.
  * 1b1. LinkyTime shows an error message stating that the given index is invalid.

    Use case resumes at step 1.

* 1c. The module has associated meetings in the meeting list.
  * 1c1. LinkyTime shows an error message stating that the module cannot be deleted due to associated meetings.

    Use case resumes at step 1.

### UC-011: Delete a module and its associated meetings

**MSS**

1. User requests to force delete a specific module and its associated meetings based on the index from the module list displayed.
2. LinkyTime deletes the module and its associated meetings and saves the changes.

    Use case ends.

**Extensions**

* 1a. The module list is empty.

    Use case ends.

* 1b. The given index is invalid.
  * 1b1. LinkyTime shows an error message stating that the given index is invalid.

    Use case resumes at step 1.

### UC-012: Clear all data

**MSS**

1. User chooses to clear all data in LinkyTime.
2. LinkyTime deletes all meetings and modules and saves the changes.

    Use case ends.

**Extensions**

* 2a. The meeting and module lists are empty.

    Use case ends.

### UC-013: Access help

**MSS**

1. User requests to see the list of commands available.
2. LinkyTime opens a dialog box with a link to the User Guide.

    Use case ends.

--------------------------------------------------------------------------------------------------------------------

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 meetings without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. A user should be familiar with the basic commands within half an hour of usage.
5. Should be portable to allow transferring of data between different computers.
6. Should not require an installer.
7. Should not depend on external/remote servers.
8. Should not depend on a Database Management System (DBMS).

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X.
* **Meeting**: An online class containing the details such as the meeting link, name, date, and other fields.
* **Module**: A self-contained structured unit of study in a specific subject. Also known as course, class.
* **NUS**: The National University of Singapore.
* **Command**: A keyword that defines an action for the program to perform when entered into the command box.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   2. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Meeting

#### Adding Meetings

1. Test case: `add n/Lecture u/https://www.zoom.com d/25-03-2022 1400 dur/1.5 m/1 r/Y t/recorded t/lecturequiz`<br>
   Prerequisites:
      1. There is no other meetings with the exact same fields.
      2. There is one module in the module list.<br>
   Expected: Meeting is added.

2. Incorrect commands:
   1. Duplicate meeting<br>
      Prerequisite:
         1. The test case command was just entered.<br>
      Command: `add n/Lecture u/https://www.zoom.com d/25-03-2022 1400 dur/1.5 m/1 r/Y t/recorded t/lecturequiz`.
   2. Incorrect name<br>
      Command: `add n/Lectur$ u/https://www.zoom.com d/25-03-2022 1400 dur/1.5 m/1 r/Y t/recorded t/lecturequiz`.
   3. Incorrect url<br>
      Command: `add n/Lecture u/zoom d/25-03-2022 1400 dur/1.5 m/1 r/Y t/recorded t/lecturequiz`.
   4. Incorrect date<br>
      Command: `add n/Lecture u/https://www.zoom.com d/40-03-2022 1400 dur/1.5 m/1 r/Y t/recorded t/lecturequiz`.
   5. Incorrect time<br>
      Command: `add n/Lecture u/https://www.zoom.com d/25-03-2022 2500 dur/1.5 m/1 r/Y t/recorded t/lecturequiz`.
   6. Incorrect duration<br>
      Command: `add n/Lecture u/https://www.zoom.com d/25-03-2022 1400 dur/25 m/1 r/Y t/recorded t/lecturequiz`.
   7. Incorrect module index<br>
      Command: `add n/Lecture u/https://www.zoom.com d/25-03-2022 1400 dur/1.5 m/10 r/Y t/recorded t/lecturequiz`.
   8. Incorrect recurrence<br>
      Command: `add n/Lecture u/https://www.zoom.com d/25-03-2022 1400 dur/1.5 m/1 r/A t/recorded t/lecturequiz`.
   9. Incorrect tag<br>
      Command: `add n/Lecture u/https://www.zoom.com d/25-03-2022 1400 dur/1.5 m/1 r/Y t/recorded t/!ecturequiz`.<br>
   
   For each of the incorrect commands, there will be an error message included on how to rectify the issue.

#### Editing Meetings

1. Test case: `edit 1 n/Lecture`<br>
   Prerequisites:
      1. There is at least one meeting in the meeting list.<br>
   Expected: The meeting at the first index is edited.

2. Incorrect commands:<br>
   For each field of the meeting, you may refer to [Adding Meetings](#add-meetings) as they are exactly the same.
   1. Duplicate meeting<br>
      Prerequisite:
         1. There is a meeting in the meeting list at index 4 which was added using the command `add n/Lecture u/https://www.zoom.com d/25-03-2022 1400 dur/1.5 m/1 r/Y t/recorded t/lecturequiz`.
         2. There is a meeting in the meeting list at index 1 which was added using the command `add n/Tutorial u/https://www.zoom.com d/25-03-2022 1400 dur/1.5 m/1 r/Y t/recorded t/lecturequiz`<br>
      Command: `edit 1 n/Lecture`<br>
      Expected: There will be an error message included on how to rectify the issue.
         
### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Module

#### Adding Modules

1. Test case: `madd n/CS2103T`<br>
   Prerequisite:
      1. There is no other module with the exact same name.<br>
   Expected: Module is added.

2. Incorrect commands to try:
   1. Duplicate module<br>
      Prerequisite:
         1. The test case command was just entered.<br>
      Command: `madd n/CS2103T`.
   2. Incorrect name<br>
      Command: `madd n/C$2!03&`.<br>
   Expected: An error message indicating the problem is displayed, and how to rectify the issue.

#### Editing Modules

1. Test case: `medit 1 n/CS2103T`<br>
   Prerequisite:
      1. There is at least one module displayed and no existing module named `CS2103T`.<br>
   Expected: Module at the first index is edited.

2. Incorrect commands:
   1. Duplicate module<br>
      Prerequisite:
         1. There is a module at the second index with the name `CS2103T`.<br>
      Command: `medit 1 n/CS2103T`.
   2. Incorrect name<br>
      Command: `medit 1 n/C$2!03&`.
   3. Incorrect index<br>
      Command: `medit abc n/CS2103T`.
   4. Out of bounds index<br>
      Command: `medit 0 n/CS2103T`.<br>
   Expected: An error message indicating the problem is displayed, and how to rectify the issue.

#### Deleting Modules

1. Test case: `mdelete 1`<br>
   Prerequisite:
      1. There is at least one module displayed and there are no associated meetings.<br>
   Expected: Module at the first index is deleted.

2. Incorrect commands:
   1. Incorrect index<br>
      Command: `mdelete abc`
   2. Out of bounds index<br>
      Command: `mdelete 0`<br>
   Expected: An error message indicating the problem is displayed, and how to rectify the issue.
