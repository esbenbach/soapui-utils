# soapui-utils
Collection of various soapui related snippets, mostly groovy helper scripts.

Feel free to copy/steal from the various stuff :)

Currently it contains the following:

* A groovy script snippet to generate a random time during the "current" month and format it as an ISO 8601 UTC string.
* A groovy script snippet that parses expiration time of an OAuth token and enables/disables test steps according to the expiration time. Used to do test runs without issuing a million tokens.
* A groovy script snippet that reads an int from an Oracle database using the JDBC driver. This CAN be achieved using a JDBC test step if required (and its probably simpler!)
* A groovy script snippet that reads information from a txt file, and randomly picks a few lines and assigns them to properties in the test case.
