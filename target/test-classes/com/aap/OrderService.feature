@runOrderService @runAllServices @runAll
Feature: Testing of Order Services

  @createOrderSanityCheck
  Scenario Outline: Sanity Test cases for order Service - Create order
    Given I build the request for the order service with "<field>" as "<fieldValue>"
    And trigger the service
    Then I validate the create order service response for StatusCode <statusCode>

    Examples: 
      | LineNo     | field           | fieldValue  | statusCode |
      | TestCase 1 | itemID,itemName | ID-984326,Alternator |        200 |

  @runOrderTests
  Scenario Outline: Test cases for order Service - Create order
    Given I build the request for the order service with "<field>" as "<fieldValue>"
    And trigger the service
    Then I validate the create order service response for StatusCode <statusCode>

    Examples: 
      | LineNo     | field                     | fieldValue        | statusCode |
      | TestCase 1 | itemID,itemName,itemPrice | ID-12545,BrakePad,200.0 |        200 |
      | TestCase 2 | itemID,itemName           | ,SideMirror              |        400 |
      | TestCase 3 | itemID,itemName           | ID-23441,           |        400 |
      | TestCase 4 | itemID,itemName,itemPrice | ID-34555,SparkPlug,     |        400 |
      | TestCase 5 | itemID,itemName           | ID-45215,OilFilter     |        200 |
      | TestCase 6 | itemID,itemName           | ID-34345,MotorOil     |        200 |
      | TestCase 7 | itemID,itemName,itemPrice | ID-34455,Wiper,356  |        400 |
      | TestCase 8 | itemID,itemName,itemPrice | ID-34002,,500.0     |        200 |
