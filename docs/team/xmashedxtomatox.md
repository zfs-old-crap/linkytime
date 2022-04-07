---
layout: page
title: Chen Xiang's Project Portfolio Page
---

### Project: LinkyTime

**LinkyTime** is a desktop application for NUS Computer Science students to manage their online meeting links. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added `list` command to show all upcoming meetings.
    * What it does: allows the user to view all upcoming meetings.
    * Justification: This feature is fundamental to our product because it allows the user to reset the filter of the meeting list back to default after running other commands. Without this command, users would be unable to get an unfiltered view of all upcoming meetings.
    * Credits: *AB3's listCommand*

* **New Feature**: Added `archive` command to show all completed meetings.
    * What it does: allows the user to view all completed meetings.
    * Justification: This feature is for user convenience in case the user wants to look up any details of past meetings. The program will retain past meeting information until explicitly told to delete them.
    * Credits: *AB3's listCommand* 
  
* **New Feature**: Added auto sort functionality for meeting list
    * What it does: Automatically sorts the meeting list by chronological order
    * Justification: This feature is for user convenience as it displays the meetings in an intuitive format. Users tend to care more about meetings that are occurring soon, as such it is more convenient to already have them sorted in that order.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=xMashedxTomatox&tabRepo=AY2122S2-CS2103T-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:

* **Enhancements to existing features**:
    * Refactored AB3 base code and test code to work with AB3 (Pull requests [\#62](), [\#85]())
    * Updated the base GUI to work with LinkyTime data (Pull requests [\#60]())
    * Wrote additional tests for existing features (Pull requests [\#78](), [\#187](), [\#188]())

* **Documentation**:
    * User Guide:
        * Added documentation for the features `list` [\#25]()
    * Developer Guide:
        * Added implementation details of the `list` and `archive` feature.
        * Added general implementation details of Commands that uses parser and Commands that don't.
        * Added logic component diagram

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#90](), [\#111]()


