$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("VechileVerifications.feature");
formatter.feature({
  "line": 2,
  "name": "Test to validate vechile make and colour",
  "description": "",
  "id": "test-to-validate-vechile-make-and-colour",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@VechicleRegistrationEnquiry"
    }
  ]
});
formatter.scenario({
  "line": 4,
  "name": "Read vehicle registration details from a file and compare it in DVLA website",
  "description": "",
  "id": "test-to-validate-vechile-make-and-colour;read-vehicle-registration-details-from-a-file-and-compare-it-in-dvla-website",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "I search for the file with \"xlsx\" extension",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I compare the vechile registraion details",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "The vechile details should match",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});