---
layout: page
title: Natalie's Project Portfolio Page
---

### Project: LinkyTime

**LinkyTime** is a desktop application for NUS Computer Science students to manage their online meeting links. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add and delete modules.
    * What it does: Allows the user to add and delete modules they may be taking at NUS, and assign meetings to modules via the module's index number.
    * Justification: This feature is needed to provide a way for users to categorize their meetings by its module. Users would be able to assign meetings to modules quicker, without having to type the full module name each time as well.
    * Highlights: This is a core feature of LinkyTime. The added enhancement to set a meeting's module by the module index required significant effort in ensuring the refactoring of the existing commands did not break.
    * Challenges: Due to the way data is structured in LinkyTime, I had to think of the most suitable way to store and retrieve the module in the meeting info effectively. The existing `add` meeting command had to be refactored to account for the module index as well.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=AY2122S2-CS2103T-T13-3%2Ftp&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=NatalieTanML&tabRepo=AY2122S2-CS2103T-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Managed releases `v1.2.0` - `v1.4.0` (4 releases) on GitHub.
    * Maintained [issue tracker](https://github.com/AY2122S2-CS2103T-T13-3/tp/issues?q=is%3Aissue+author%3ANatalieTanML+), and ensured PRs and issues are assigned to the proper milestones and authors.
    * Managed bug triaging and allocation for [PE-D](https://github.com/AY2122S2-CS2103T-T13-3/tp/issues?q=is%3Aissue+%5Bpe-d%5D).
    * Maintained team [weekly meeting notes](https://docs.google.com/document/d/1blOVPpajNMHmHRSajK4t9cl0r2PwMiO2j7FF4Xy-pO8/edit?usp=sharing).

* **Enhancements to existing features**:
    * Updated the GUI layout to include the module list and new meeting fields ([\#67](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/67), [\#83](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/83), [\#117](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/117))
    * Implemented Meeting and Module models, as well as its specific fields ([\#44](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/44))
    * Enhanced meeting URL validation ([\#173](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/173))

* **Documentation**:
    * User Guide:
        * Added documentation for add and delete module features ([\#113](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/113))
        * Added documentation for command parameters, typographical conventions table, and updated introduction paragraphs ([\#113](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/113), [\#128](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/128))
        * Created annotated GUI images ([\#128](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/128), [\#179](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/179))
        * Full list of UG PRs available [here](https://github.com/AY2122S2-CS2103T-T13-3/tp/pulls?q=is%3Apr+ug+author%3ANatalieTanML)
    * Developer Guide:
        * Added implementation details of the `mdelete` feature ([\#101](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/101))

* **Team tasks**:
    * Setup [Codecov](https://app.codecov.io/gh/AY2122S2-CS2103T-T13-3/tp) for the team repo.
    * Created a [Pull Request template](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/47) on the team repo to standardize the PR message body.
    * Updated project's README.md and Index/home page. ([\#11](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/11), [\#123](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/123))

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#59](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/59), [\#63](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/63), [\#91](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/91), [\#108](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/108); full list of [PRs reviewed](https://github.com/AY2122S2-CS2103T-T13-3/tp/pulls?q=is%3Apr+reviewed-by%3ANatalieTanML+)
    * Bug reports made during PE-D for another team were fully accepted, including a high severity functionality bug: [PE-D](https://github.com/NatalieTanML/ped/issues), [\#17](https://github.com/NatalieTanML/ped/issues/17)
