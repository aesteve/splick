package com.github.aesteve.splick.testmodel

import slick.jdbc.H2Profile.api._ // TODO: something more generic than H2? Really needed?

class PersonContact(tag: Tag) extends Table[(Long, String, String, String, String, String)](tag, "PERSON_CONTACT") {
  def id = column[Long]("ID", O.PrimaryKey) // This is the primary key column
  def name = column[String]("NAME")
  def street = column[String]("STREET")
  def city = column[String]("CITY")
  def state = column[String]("STATE")
  def zip = column[String]("ZIP")
  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id, name, street, city, state, zip)
}