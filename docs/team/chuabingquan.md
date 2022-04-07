---
layout: page
title: Bing Quan's Project Portfolio Page
---

### Project: LinkyTime

**LinkyTime** is a desktop application for NUS Computer Science students to manage their online meeting links. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability for meetings to be recurring on a weekly basis.
    * What it does: A recurring meeting will always reflect the ongoing/upcoming date and time of its next recurrence.
    * Justification: As modules in NUS recurs weekly, users shouldn't have to manually re-create such recurring meetings on a weekly basis as well.
    * Highlights: This feature required an in-depth analysis of design alternatives to avoid issues with keeping the storage in sync with the next recurring date and time of a meeting.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=AY2122S2-CS2103T-T13-3%2Ftp&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=chuabingquan&tabRepo=AY2122S2-CS2103T-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~other&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**:
    * Migrated the model and logic layer to work with `Meeting` objects (Pull requests [\#48](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/48))
    * Migrated the storage layer to serialize/deserialize LinkyTime user data (Pull requests [\#64](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/64))
    * Accepted and parsed meeting date and time from `String` to `LocalDateTime` (Pull requests [\#92](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/92)).
    * Updated the GUI to reflect the latest date and time of all recurring meetings upon the execution of any command (Pull requests [\#92](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/92)).
    * Updated the GUI to only show ongoing/recurring meetings by default (Pull requests [\#119](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/119)). 

* **Documentation**:
    * User Guide:
        * Added documentation for the `archive` feature [\#123](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/123).
    * Developer Guide:
        * Added implementation details for recurring meetings [\#108](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/108).

* **Community**:
    * TBD.
  
* **Tools**:

