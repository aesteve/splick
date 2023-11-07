package com.github.aesteve.splick

import com.github.aesteve.splick.agg.SparkAggregationExtensionMethods
import jdk.jshell.spi.ExecutionControl.NotImplementedException
import slick.ast.BaseTypedType
import slick.jdbc.H2Profile.MappedColumnType
import slick.jdbc.H2Profile.api._
import slick.jdbc.JdbcType
import slick.lifted.{Query, Rep}

import scala.language.implicitConversions
import scala.reflect.ClassTag

trait SparkExtensions {
  /**
   * Import the
   */
  implicit def sparkAggregationExtensionMethods[B1 : BaseTypedType, C[_]](q: Query[Rep[B1], ?, C]): SparkAggregationExtensionMethods[B1, B1, C] =
    new SparkAggregationExtensionMethods[B1, B1, C](q)

  /**
   * The implementation won't be used since we are just generating SQL and not mapping it to DB types using the JDBC driver
   * But this implicit definition is needed for the code to compile
   */
  //
  implicit def arrayMapper[T: ClassTag]: JdbcType[Array[T]] with BaseTypedType[Array[T]] = MappedColumnType.base[Array[T], String](
    _ => throw new NotImplementedException("Can't deal with array types at the JDBC driver level"),
    _ => throw new NotImplementedException("Can't deal with array types at the JDBC driver level")
  )


}
