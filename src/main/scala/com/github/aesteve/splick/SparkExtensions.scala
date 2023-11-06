package com.github.aesteve.splick

import com.github.aesteve.splick.udaf.SparkUdafExtensionMethods
import slick.ast.BaseTypedType
import slick.lifted.{Query, Rep}

import scala.language.implicitConversions

trait SparkExtensions {
  implicit def sparkUdafExtensionMethods[B1 : BaseTypedType, C[_]](q: Query[Rep[B1], ?, C]): SparkUdafExtensionMethods[B1, B1, C] =
    new SparkUdafExtensionMethods[B1, B1, C](q)
}
