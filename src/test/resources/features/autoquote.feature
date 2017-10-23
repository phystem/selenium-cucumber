Feature: Add a quote in AllState

  Scenario Outline: AddQuote
    Given I open AllState
    And I select Auto from GetAQuote
    Then I Fill the quote using Excel from row <rowIndex>
  
    Examples: 
      | rowIndex |
      | 1 |
      | 2 |      