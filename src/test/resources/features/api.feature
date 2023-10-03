Feature: Creating and Deleting All Projects and Tasks

  Scenario Outline: Creating All Projects

    Given Set Base URLs for creating projects
    When Set expected data with  "<projectNames>"data and send request
    Then Verify all of created projects

    Examples:
      | projectNames |
      | Project-A100 |
      | Project-A101 |
      | Project-A102 |

  Scenario Outline: Creating All Tasks for Project-1

    Given Set Base URLs for creating all tasks for First Project
    When  Set expected data with  "<taskNames>"data and send request for  First Project
    Then  Verify all of created tasks for  First Project

    Examples:
      | taskNames |
      | Task-1    |
      | Task-2    |
      | Task-3    |

    Scenario: Deleting First Tasks for Project-1

      Given Set Base URLs with for deleting first task on First Project
      When  Set send request for deleting  task on First Project
      Then  Verify deleted task on First Project

  Scenario: Deleting Second Tasks for Project-1

    Given Set Base URLs with for  deleting second task on First Project
    When  Set send request for deleting  task on First Project
    Then  Verify deleted task on First Project

  Scenario: Deleting Third Tasks for Project-1

    Given Set Base URLs with for  deleting third task on First Project
    When  Set send request for deleting  task on First Project
    Then  Verify deleted task on First Project


  Scenario Outline: Creating All Tasks for Project-2

    Given Set Base URLs for creating all tasks for Second Project
    When  Set expected data with  "<taskNames>"data and send request for  Second Project
    Then  Verify all of created tasks for  Second Project

    Examples:
      | taskNames |
      | Task-A    |
      | Task-B    |
      | Task-C    |

  Scenario: Deleting First Tasks for Project-2

    Given Set Base URLs with for deleting first task on Second Project
    When  Set send request for deleting  task on Second Project
    Then  Verify deleted task on Second Project

  Scenario: Deleting Second Tasks for Project-2

    Given Set Base URLs with for  deleting second task on Second Project
    When  Set send request for deleting  task on Second Project
    Then  Verify deleted task on Second Project

  Scenario: Deleting Third Tasks for Project-2

    Given Set Base URLs with for  deleting third task on Second Project
    When  Set send request for deleting  task on Second Project
    Then  Verify deleted task on Second Project


  Scenario Outline: Creating All Tasks for Project-3

    Given Set Base URLs for creating all tasks for Third Project
    When  Set expected data with  "<taskNames>"data and send request for  Third Project
    Then  Verify all of created tasks for  Third Project

    Examples:
      | taskNames |
      | Task-Q    |
      | Task-W    |
      | Task-Y    |

  Scenario: Deleting First Tasks for Project-3

    Given Set Base URLs with for deleting first task on Third Project
    When  Set send request for deleting  task on Third Project
    Then  Verify deleted task on Third Project

  Scenario: Deleting Second Tasks for Project-3

    Given Set Base URLs with for  deleting second task on Third Project
    When  Set send request for deleting  task on Third Project
    Then  Verify deleted task on Third Project

  Scenario: Deleting Third Tasks for Project-3

    Given Set Base URLs with for  deleting third task on Third Project
    When  Set send request for deleting  task on Third Project
    Then  Verify deleted task on Third Project


    Scenario: Deleting First Project

      Given Set Base URLs  for deleting first project
      When  Set send request for deleting project
      Then  Verify deleted the  project

  Scenario: Deleting Second Project

    Given Set Base URLs  for deleting second project
    When  Set send request for deleting project
    Then  Verify deleted the  project

  Scenario: Deleting Third Project

    Given Set Base URLs  for deleting third project
    When  Set send request for deleting project
    Then  Verify deleted the  project

