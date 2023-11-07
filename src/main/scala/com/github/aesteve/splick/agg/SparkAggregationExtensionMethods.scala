package com.github.aesteve.splick.agg

import slick.ast.Library.SqlAggregateFunction
import slick.ast.TypedType
import slick.lifted.FunctionSymbolExtensionMethods.functionSymbolExtensionMethods
import slick.lifted.{Query, Rep}
final class SparkAggregationExtensionMethods[B1, P1, C[_]](val q: Query[Rep[P1], _, C]) extends AnyVal {
  private type ArrayTM = TypedType[Array[B1]]
  // private type OptionTM = TypedType[Option[B1]]

  // TODO: see https://spark.apache.org/docs/latest/sql-ref-functions-builtin.html#aggregate-functions

  /** Aggregates the values into a set */
  def collectSet(implicit tm: ArrayTM): Rep[Array[B1]] =
    AggregationLibrary.CollectSet.column[Array[B1]](q.toNode)
}

private object AggregationLibrary {
  val CollectSet: SqlAggregateFunction = new SqlAggregateFunction("collect_set")
}

