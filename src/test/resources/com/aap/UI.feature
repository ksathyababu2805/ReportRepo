@runUI @runAll
Feature: UI Testing - Google Search

Scenario: Test google search option
	Given Open chrome and start application
	When I search for "Infosys"
	Then User should be able to click the search result