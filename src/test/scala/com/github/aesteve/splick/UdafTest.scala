package com.github.aesteve.splick

import com.github.aesteve.splick.testmodel.{Person, PersonContact}
import org.scalatest.funsuite.AnyFunSuite
import slick.jdbc.H2Profile.api._

class UdafTest extends AnyFunSuite with SparkExtensions {

  // This variables could be factorized and re-used across many tests
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
        (groupingCol, (group.map { case (_, contact) => contact.city }).collectSet )
      }
    println(citiesGroupedByLastNames.result.statements.head)
  }

}
