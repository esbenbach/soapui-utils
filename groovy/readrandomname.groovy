/*
* This groovy snippet is designed to be run from within SOAPUI.
* SOAPUI exposes som variables like `log`, `context`and `testRunner` which this script assumes exists
*
* It reads three lines from a word file and constructs two test case properties called "Name" and "Email" based on the words read from the file.
*/

// Obtain a txt word list from somewhere, i.e. http://gwicks.net/dictionaries.htm
def filepath = "C:/path/to/textfile.txt"

// Read the entire file into memory. This may be in-efficinet but im a Groovy newbie so deal with it.
context.fileReader = new BufferedReader(new FileReader(filepath));
def rowsData = context.fileReader.readLines();

def randomizer = new Random();

int rowSize = rowsData.size();
def firstname = rowsData[randomizer.nextInt(rowSize)];
def lastname = rowsData[randomizer.nextInt(rowSize)];
def mailName = rowsData[randomizer.nextInt(rowSize)];
def phoneNumber = Math.abs( randomizer.nextInt() % (10000000 - 99999999) ) + 10000000;

context.testCase.setPropertyValue("Name", lastname + " " + firstname);
context.testCase.setPropertyValue("Email", mailName + "@example.org");
context.testCase.setPropertyValue("Phone", phoneNumber.toString());