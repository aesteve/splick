package com.github.aesteve.splick.udaf

import slick.ast.TypedType
import slick.lifted.FunctionSymbolExtensionMethods.functionSymbolExtensionMethods
import slick.lifted.{Query, Rep}
final class SparkUdafExtensionMethods[B1, P1, C[_]](val q: Query[Rep[P1], _, C]) extends AnyVal {
  private type OptionTM = TypedType[Option[B1]]

  /** Compute the minimum value of a single-column Query, or `None` if the Query is empty */
  def collectSet(implicit tm: OptionTM) = UdafLibrary.CollectSet.column[Option[B1]](q.toNode)
}


