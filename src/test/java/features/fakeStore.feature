Feature: create fake store Token


  Scenario Outline:create a client token
    Given  fake store client token is generated with '<token request body>'
    Then  I validate the "fakestoreclienttoken" status code should be 201
    Given I create a 'newuser' for the fake store
    Then I validate the "createUser" status code should be 201
    Given I create a 'addCart' for the fake store user
    Then I validate the "addCart" status code should be 201
    Examples:
      | token request body |
      | fakeStoreTestData  |


