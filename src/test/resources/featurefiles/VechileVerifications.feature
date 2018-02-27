@VechicleRegistrationEnquiry
Feature: Test to validate vechile make and colour

  Scenario: Read vehicle registration details from a file and compare it in DVLA website
    Given I search for the file with "xlsx" extension
    When I compare the vechile registraion details
    Then The vechile details should match
