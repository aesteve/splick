package com.github.aesteve.splick.testmodel

import com.github.aesteve.splick.testmodel.PersonContact.{CityCol, IdCol, StateCol, StreetCol, ZipCol}
import slick.jdbc.H2Profile.api._ // TODO: something more generic than H2? Really needed?

class PersonContact(tag: Tag) extends Table[(Long, String, String, String, String)](tag, PersonContact.TableName) {
  def id = column[Long](IdCol, O.PrimaryKey) // This is the primary key column
  def street = column[String](StreetCol)
  def city = column[String](CityCol)
  def state = column[String](StateCol)
  def zip = column[String](ZipCol)
  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id, street, city, state, zip)
}

case class PersonContactData(id: Long, street: String, city: String, zip: String)

object PersonContact {
  val TableName = "PERSON_CONTACT"
  val IdCol = "id"
  val StreetCol = "street"
  val CityCol = "city"
  val StateCol = "state"
  val ZipCol = "zip"
}