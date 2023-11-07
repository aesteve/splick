package com.github.aesteve.splick.agg

import com.github.aesteve.splick.testmodel.{Person, PersonContact}
import com.github.aesteve.splick.{SparkExtensions, SparkTestBase}
import slick.jdbc.H2Profile.api._

class SparkAggTest extends SparkTestBase with SparkExtensions {

  // These variables could be factorized and re-used across many tests
  val personAndItsContacts = TableQuery[Person]
    .join(TableQuery[PersonContact])
    .on((person, contact) => person.id === contact.id)
  val firstNamesLikeMe = personAndItsContacts
    .filter { case (person, _) => person.firstName.like("%rna%") }
  val lastNamesOfNamesLikeMe = firstNamesLikeMe
    .groupBy { case (person, _) => person.lastName }

  test("Can collect_set") {
    val citiesGroupedByLastNames = lastNamesOfNamesLikeMe
      .map { case (groupingCol, group) =>
        (groupingCol,  group.map { case (_, contact) => contact.city }.collectSet)
      }
    assertValidSparkSQLQuery(citiesGroupedByLastNames)
  }

}
