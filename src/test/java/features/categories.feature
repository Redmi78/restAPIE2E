Feature: Categories

  Background:
    Given  client token is generated with "testData"
    Then  I validate the "clienttoken" status code should be 201


  Scenario: create a product with valid details

    Given a categories created with "createCategoryPayload" and "token"
    Then  I validate the "createUserCategories" status code should be 201
    Given a user created with "allValidDetails" and "token"
    Then  I validate the "createproduct" status code should be 201
    Given I retrieve 'allCategories'
    Then  I validate the "getSearchListCategories" status code should be 200
    And validate the response contains categories list
