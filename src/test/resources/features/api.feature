Feature: Creating and Deleting All Projects and Tasks

Scenario Outline: Creating All Projects

  Given Set Base URLs for creating projects
  When Set expected data with  "<projectNames>"data and send request
  Then Verify all of created projects

  Examples:
    | projectNames |
    | Project-1 |
    | Project-2 |
     |Project-3 |





