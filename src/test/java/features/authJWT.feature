Feature: get Token


  Scenario:create a client token
    Given  client token is generated with "testData"
    Then  I validate the "clienttoken" status code should be 201


