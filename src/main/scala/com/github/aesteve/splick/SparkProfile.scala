package com.github.aesteve.splick

import slick.jdbc.PostgresProfile

/**
 * TODO: we've started from PgProfile a bit arbitrarily, check which of the existing profile would be closer to Spark SQL syntax
 * For instance, the escaping method in Spark is the same as the one from MySQL
 * Also, check which methods should be overriden
 */
class SparkProfile extends PostgresProfile {
  override def quoteIdentifier(id: String): String = s"`$id`"
}

object SparkProfile extends SparkProfile
