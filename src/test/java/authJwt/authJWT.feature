Feature: get Token

  Background:
    Given  client token is generated with "testData"
    Then  I validate the "clienttoken" status code should be 201


  Scenario:create a user product with valid details
    Given a user created with "allValidDetails" and "token"

