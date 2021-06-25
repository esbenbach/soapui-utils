/*
* This groovy snippet is designed to be run from within SOAPUI.
* SOAPUI exposes som variables like `log`, `context`and `testRunner` which this script assumes exists
*
* It reads from an oracle database and puts the result into a test case property.
*/

// This driver needs to be downloaded from somewhere and put into bin/ext or some other class path.
com.eviware.soapui.support.GroovyUtils.registerJdbcDriver( "oracle.jdbc.driver.OracleDriver" )
import java.sql.Connection
import java.sql.DriverManager
import javax.sql.DataSource
import groovy.sql.*
import oracle.jdbc.driver.OracleTypes

driver = oracle.jdbc.driver.OracleDriver

sql = Sql.newInstance('jdbc:oracle:thin:@10.0.0.1:1521:orcl', 'username', 'password', 'oracle.jdbc.driver.OracleDriver')
try {
  sql.eachRow('select customernumber from customers where rownum <= 1'){ row ->
    context.testCase.setPropertyValue("CustomerNumber", row['customernumber'].toString());
  }
} finally {
  sql.close()
}
