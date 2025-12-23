Feature: Categories

  Background:
    Given  client token is generated with "testData"
    Then  I validate the "clienttoken" status code should be 201


  Scenario :create a user product with valid details

    Given a categories created with "createCategoryPayload" and "token"
    Then  I validate the "createUserCategories" status code should be 201
    Given a user created with "allValidDetails" and "token"
    Then  I validate the "createproduct" status code should be 201


