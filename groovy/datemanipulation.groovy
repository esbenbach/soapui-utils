/*
* This groovy snippet is designed to be run from within SOAPUI.
* SOAPUI exposes som variables like `log`, `context`and `testRunner` which this script assumes exists
*
* The script gets the current date and does some manipulation to randomize the day and time inside the current month.
*/

def randomizer = new Random();

def date = new Date();
Calendar cal = Calendar.getInstance();
cal.setTime(date);

// Randomize time of day and day in month
cal.set(Calendar.HOUR_OF_DAY, randomizer.nextInt(24));
cal.set(Calendar.DAY_OF_MONTH, randomizer.nextInt(30));
cal.set(Calendar.MINUTE, randomizer.nextInt(59));

def isoTimeFormat = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
def time = cal.getTime();

// Set the time using the given format (appending Z as a string as including it in the format string will actually convert the local time and add something like +02:00 instead of the Z).
context.testCase.setPropertyValue("StartTime", isoTimeFormat.format(time) + "Z");