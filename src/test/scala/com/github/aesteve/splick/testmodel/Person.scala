package com.github.aesteve.splick.testmodel

import com.github.aesteve.splick.testmodel.Person.{FirstNameCol, IdCol, LastNameCol}
import slick.jdbc.H2Profile.api._ // TODO: something more generic than H2? Really needed?

// Definition of the COFFEES table
class Person(tag: Tag) extends Table[(Long, String, String)](tag, Person.TableName) {
  def id = column[Long](IdCol, O.PrimaryKey)
  def firstName = column[String](FirstNameCol)
  def lastName = column[String](LastNameCol)
  def * = (id, firstName, lastName)
  // A reified foreign key relation that can be navigated to create a join
  def contact = foreignKey("FK_PERSON_CONTACT", id, TableQuery[PersonContact])(_.id)
}

case class PersonData(id: Long, firstName: String, lastName: String)

object Person {
  val TableName = "PERSON"
  val IdCol = "id"
  val FirstNameCol = "firstName"
  val LastNameCol = "lastName"
}