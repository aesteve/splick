package com.github.aesteve.splick.udaf

import slick.ast.Library.SqlAggregateFunction

object UdafLibrary {
 val CollectSet = new SqlAggregateFunction("collect_set")
}
