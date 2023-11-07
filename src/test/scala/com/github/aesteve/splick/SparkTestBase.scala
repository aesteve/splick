package com.github.aesteve.splick

import com.github.aesteve.splick.testmodel.{Person, PersonContact, PersonContactData, PersonData}
import org.apache.spark.sql.SparkSession
import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers
import slick.lifted.Query
import com.github.aesteve.splick.SparkProfile.api._


trait SparkTestBase extends AnyFunSuite with BeforeAndAfterAll with Matchers {

  protected implicit var spark: SparkSession = _

  override def beforeAll(): Unit = {
    spark = SparkSession
      .builder()
      .master("local[*]")
      .appName("Splick unit tests")
      .getOrCreate()
    val session = spark
    import session.implicits._
    spark.createDataset(List[PersonData]()).createOrReplaceTempView(Person.TableName)
    spark.createDataset(List[PersonContactData]()).createOrReplaceTempView(PersonContact.TableName)
  }

  override def afterAll(): Unit = {
    if (spark != null)
      spark.close()
  }

  def assertValidSparkSQL(sql: String): Unit = {
    val logicalPlan = spark.sessionState.sqlParser.parsePlan(sql) // parses the query and builds the AST
    val queryExecution = spark.sessionState.executePlan(logicalPlan) // creates plans of phases (Analysis, logical optimization plan, physical plan, ...) without any execution
    queryExecution.assertAnalyzed() // triggers the Analysis phase
  }

  def assertValidSparkSQLQuery[E, U, C[_]](query: Query[E, U, C]): Unit =
    query.result.statements.foreach(assertValidSparkSQL)

}