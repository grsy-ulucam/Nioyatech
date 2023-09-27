Feature: Second Project Steps

  Scenario Outline: Second Project Creating

    Given Set Base URLs
    When  Set expected "<projectName>" data and send request
    Then  Verify project created
    Examples:
      | projectName |
      | Project-3 |

  Scenario: Verify First Project with Selenium

    Given Go to Asana website
    When  Enter valid email address and password
    Then  Verify new project created

  Scenario Outline: Creating Tasks
    Given set base url tasks
    When  set expected "<taskName>" and request
    Then  verify new tasks with api
    Examples:
      | taskName |
      | Task-3   |

  Scenario Outline: Verify with Selenium for Task-1

    Given Go to Asana website
    When  Enter valid email address and password
    Then  Verify new task created with "<taskName>"

    Examples:
      | taskName |
      | Task-3 |

  Scenario: Deleting first task
    Given  Set Base URLs for deleting
    When  Set send request for deleting
    Then  Verify task deleted

  Scenario: Deleting first project
    Given  Set Base URLs for deleting project
    When  Set send request for deleting project
    Then  Verify project deleted