Feature: Creating and Deleting All Projects and Tasks

  Scenario Outline: Creating All Projects

    Given Set Base URLs for creating projects
    When Set expected data with  "<projectNames>"data and send request
    Then Verify all of created projects

    Examples:
      | projectNames |
      | Project-1    |
      | Project-2    |
      | Project-3    |

  Scenario Outline: Creating All Tasks for Project-1

    Given Set Base URLs for creating all tasks for First Project
    When  Set expected data with  "<taskNames>"data and send request for  First Project
    Then  Verify all of created tasks for  First Project

    Examples:
      | taskNames |
      | Task-1    |
      | Task-2    |
      | Task-3    |

    Scenario: Deleting All Tasks for Project-1

      Given Set Base URLs with for deleting all task on First Project
      When  Set send request for deleting all task on First Project
      Then  Verify deleted all of  on First Project

  Scenario Outline: Creating All Tasks for Project-2

    Given Set Base URLs for creating all tasks for Second Project
    When  Set expected data with  "<taskNames>"data and send request for  Second Project
    Then  Verify all of created tasks for  Second Project

    Examples:
      | taskNames |
      | Task-A    |
      | Task-B    |
      | Task-C    |

  Scenario: Deleting All Tasks for Project-2

    Given Set Base URLs with for deleting all task on Second Project
    When  Set send request for deleting all task on Second Project
    Then  Verify deleted all of  on Second Project

  Scenario Outline: Creating All Tasks for Project-3

    Given Set Base URLs for creating all tasks for Third Project
    When  Set expected data with  "<taskNames>"data and send request for  Third Project
    Then  Verify all of created tasks for  Third Project

    Examples:
      | taskNames |
      | Task-Q    |
      | Task-W   |
      | Task-Y    |

  Scenario: Deleting All Tasks for Project-3

    Given Set Base URLs with for deleting all task on Third Project
    When  Set send request for deleting all task on Third Project
    Then  Verify deleted all of  on Third Project

  Scenario: Deleting All Projects

    Given Set Base URLs  for deleting all of projects
    When  Set send request for deleting all of projects
    Then  Verify deleted all of projects
