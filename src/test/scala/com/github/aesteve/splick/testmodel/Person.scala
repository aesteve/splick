package com.github.aesteve.splick.testmodel

import slick.jdbc.H2Profile.api._ // TODO: something more generic than H2? Really needed?

// Definition of the COFFEES table
class Person(tag: Tag) extends Table[(Long, String, String)](tag, "PERSON") {
  def id = column[Long]("ID", O.PrimaryKey)
  def firstName = column[String]("FIRST_NAME")
  def lastName = column[String]("LAST_NAME")
  def * = (id, firstName, lastName)
  // A reified foreign key relation that can be navigated to create a join
  def contact = foreignKey("FK_PERSON_CONTACT", id, TableQuery[PersonContact])(_.id)
}
