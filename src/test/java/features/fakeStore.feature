Feature: create fake store Token


  Scenario:create a client token
    Given  fake store client token is generated with "fakeStoreTestData"
    Then  I validate the "fakestoreclienttoken" status code should be 201
    Given I create a 'newuser' for the fake store
    Then I validate the "createUser" status code should be 201
