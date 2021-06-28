/*
* This groovy snippet is designed to be run from within SOAPUI.
* SOAPUI exposes som variables like `log`, `context`and `testRunner` which this script assumes exists
*
* The script gets a token expiry time from the current test case and determines if a new one is required.
* If not required it disables two test steps called "Get  Token" and "Set Token and Expiry Time".
* If required: it enables two test steps.
* This effectively handles OAuth token expiration times (provided that Get Token and Set Token actually gets and sets token properties :))
*/

import com.eviware.soapui.support.GroovyUtils;
import groovy.time.TimeCategory

def expiryTime = testRunner.testCase.getPropertyValue("TokenExpiryTime");
def getToken = true;

if (expiryTime?.trim())
{
	expiryTime = new Date().parse("yyyy-MM-dd HH:mm:ss", expiryTime)
	
    // We want to make sure that renewal time is done just a bit before the token actually expires - in this case 10 minutes before.
    use(TimeCategory)
	{
  		renewalTime = new Date() - 10.minutes
	}
	
	if ( renewalTime <= expiryTime )
	{
		log.info("Skipping token");
		getToken = false;
	}
}

if (getToken)
{
    // Enable test cases and set them as the next step as SoapUI will have decided not to run them if they were disabled when starting.
	testRunner.testCase.getTestStepByName("Get Token").setDisabled(false);
	testRunner.testCase.getTestStepByName("Set Token and Expiry Time").setDisabled(false);
   	testRunner.gotoStepByName( "Get Token")
   	return null;
}
else
{
	testRunner.testCase.getTestStepByName("Get Token").setDisabled(true);
	testRunner.testCase.getTestStepByName("Set Token and Expiry Time").setDisabled(true);
   	return;
}