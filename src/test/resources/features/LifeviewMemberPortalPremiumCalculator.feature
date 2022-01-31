Feature: Lifeview Member Portal - Premium Calculator

  @WebRegression
  Scenario Outline: <MemberGroup> - Premium calculator
    Given the user is on "Telstra Premium Calculator" link
   And the user clicks the "Let's get started" link on Member portal page
   And verify "Telstra Super Member Portal" footer on the "Duty of Disclosure page"
   And the user clicks the "I Agree" link on Duty of Disclosure page
   And the user enters "<TypeOfBenefit>" for "Benefit Type" option on Premium Calculator page
  And verify "Telstra Super Member Portal" footer on the "Premium Calculator page"
   And the user clicks the "Next" link on Member portal page
   And the user enters "<Day>" for "day" option on Premium Calculator page2
   And the user enters "<Month>" for "month" option on Premium Calculator page2
   And the user enters "<Year>" for "year" option on Premium Calculator page2
   And the user enters "<AnnualIncome>" for "Your annual income" option on Premium Calculator page2
  And the user enters "click" for "<Gender>" option on Premium Calculator page2
  And the user enters "click" for "Next" option on Premium Calculator page2
   And the user enters "<MemberGroup>" for "Member Group" option on Premium Calculator page2
   And the user enters "<Company>" for "Company" option on Premium Calculator page2
   And the user enters "<Employment>" for "Employment" option on Premium Calculator page2
  And the user enters "<BaseInsurance>" for "Base Insurance" option on Premium Calculator page2
   And the user enters "<OccupationClass>" for "Occupation Class" option on Premium Calculator page2
  And the user enters "click" for "A bit about your job Page" option on Premium Calculator page2
   And verify "Telstra Super Member Portal" footer on the "Premium Calculator page2"
  And the user enters "click" for "Next" option on Premium Calculator page2
   Then the user must be on the premium calculator results page
   And verify "Telstra Super Member Portal" footer on the "premium calculator results page"
   And the user enters "<WaitingPeriod>" for "Waiting Period" option on Premium Calculator page2
   And the user enters "<BenefitPeriod>" for "Benefit Period" option on Premium Calculator page2
   And the user enters "<CoverAmount>" for "Cover Amount" option on Premium Calculator page2
    And the user enters "per-year" for "Total Premium" option on Premium Calculator page2
    And Verify Total premium per year should be "<ExpectedPremium>" in Premium Calculator page

    @primary
    Examples:
      | TypeOfBenefit                   | Day | Month | Year | AnnualIncome | Gender | MemberGroup                         | Company | BaseInsurance | Employment | OccupationClass | ExpectedPremium | CoverAmount | WaitingPeriod | BenefitPeriod |
      | Income Protection,Death and TPD | 01  | May   | 1971 | 80365.30        | Female | TelstraSuper Corporate Plus         | Foxtel  | NA            | Part-Time  | White Collar    | 2,140.66        | 260511      | 60Days        | 5Year         |
      | Death and TPD                   | 01  | May   | 2000 | 80365.30        | Male   | Sensis Super Plus – Defined Benefit | NA      | 155000        | NA         | White Collar    | 735             | 905000      | NA            | NA            |

    # Below marked @secondary to reduce primary scenario automation build times
    @secondary
    Examples:
      | TypeOfBenefit                   | Day | Month | Year | AnnualIncome | Gender | MemberGroup                                      | Company | BaseInsurance | Employment | OccupationClass    | ExpectedPremium | CoverAmount | WaitingPeriod | BenefitPeriod |
      | Income Protection,Death and TPD | 01  | May   | 1981 | 120547.95       | Male   | TelstraSuper Corporate Plus – Telstra 2014 Offer | Telstra | 100000        | Full-Time  | White Collar       | 319.29          | 225000      | NA            | NA            |
