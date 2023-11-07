package com.github.aesteve.splick

import com.github.aesteve.splick.testmodel.Person
import org.apache.spark.sql.AnalysisException
import org.apache.spark.sql.catalyst.parser.ParseException

class CheckTestParser extends SparkTestBase {

  test("Must be fine with a valid Spark SQL query") {
    assertValidSparkSQL(s"SELECT ${Person.IdCol} from ${Person.TableName} WHERE ${Person.IdCol} > 1")
  }


  test("Must raise an error with a totally invalid SQL query") {
    assertThrows[ParseException] {
      assertValidSparkSQL("NONSENSE")
    }
  }

  test("Must raise an error with an unknown spark aggregation") {
    assertThrows[ParseException] {
      assertValidSparkSQL(s"SELECT MAX(${Person.IdCol}) FROM ${Person.TableName} GROUPEDBY ${Person.FirstNameCol}")
    }
  }

  test("Must not raise an error with a proper spark aggregation") {
    assertValidSparkSQL(s"SELECT MAX(${Person.IdCol}) FROM ${Person.TableName} GROUP BY ${Person.FirstNameCol}")
  }

  test("Must raise an Analysis error when querying a field that does not exist") {
    assertThrows[AnalysisException] {
      assertValidSparkSQL(s"SELECT doesnotexist FROM ${Person.TableName}")
    }
  }

  test("Must raise an Analysis error when using an unknown aggregation function") {
    assertThrows[AnalysisException] {
      assertValidSparkSQL(s"SELECT unknown_aggregate(${Person.IdCol}) FROM ${Person.TableName} GROUP BY ${Person.FirstNameCol}")
    }
  }

}
