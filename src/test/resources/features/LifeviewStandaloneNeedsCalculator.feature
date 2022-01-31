Feature: Lifeview -Standalone Needs Calculator

  @WebRegression
  Scenario Outline: Standalone Needs calculator for <InitialNeeds>
    Given the user is on "Generic Needs Calculator" link
    And the user clicks the "Let's get started" link on Member portal page
    And the user clicks the "I Agree" link on Duty of Disclosure page
    And the user enters "<InitialNeeds>" for "Initial Needs" option on Needs Calculator page
    And the user clicks the "Next" link on Member portal page
    And the user enters dob on Needs Calculator page
      | Day | Month | Year |
      | 01  | May   | 2000 |
    And the user enters "click" for "<Gender>" option on Needs Calculator page
    And the user enters "<Dependents>" for "dependents" option on Needs Calculator page
    And the user enters "<YoungestChildAge>" for "Youngest Child Age" option on Needs Calculator page

    And the user enters "<AnnualIncome>" for "Your annual income" option on Needs Calculator page
    And the user enters "click" for "Next" option on Needs Calculator page
    And the user enters "<CurrentMortgageValue>" for "Current Mortgage Value" option on Needs Calculator page
    And the user enters "<CarLoan>" for "Car Loan" option on Needs Calculator page
    And the user enters "<CreditCard>" for "Credit Card" option on Needs Calculator page
    And the user enters "<AdditionalDebt>" for "Additional Debt" option on Needs Calculator page
    And the user enters "click" for "Next" option on Needs Calculator page
    And the user enters "<Cash>" for "Cash" option on Needs Calculator page
    And the user enters "<InvestmentProperty>" for "Investment Property" option on Needs Calculator page
    And the user enters "<Shares>" for "Shares" option on Needs Calculator page
    And the user enters "<CurrentSuperBalance>" for "Current Super Balance" option on Needs Calculator page
    And the user enters "<AddOtherAssets>" for "Add Other Assets" option on Needs Calculator page
    And the user enters "click" for "Next" option on Needs Calculator page
    And Verify "IP cover" should be "<IPCoverAmount>" in Needs Calculator results page
    And Verify "TPD cover" should be "<TPDCoverAmount>" in Needs Calculator results page
    And Verify "Death cover" should be "<DeathCoverAmount>" in Needs Calculator results page

    @primary
    Examples:
      | InitialNeeds                | Day | Month | Year | AnnualIncome | Gender | Dependents | YoungestChildAge | CurrentMortgageValue | CarLoan | CreditCard | AdditionalDebt | Cash   | InvestmentProperty | Shares | CurrentSuperBalance | AddOtherAssets | IPCoverAmount | TPDCoverAmount | DeathCoverAmount |
      | My partner , my kids and me | 01  | May   | 2000 | 80000        | Male   | 3          | 2                | 500000               | 20000   | 30000      | 10000          | 200000 | 1000000            | 20000  | 100000              | 20000          | 5,667         | 1,453,496      | 1,413,496        |



