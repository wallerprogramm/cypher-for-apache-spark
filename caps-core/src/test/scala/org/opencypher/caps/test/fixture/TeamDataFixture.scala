/*
 * Copyright (c) 2016-2017 "Neo4j, Inc." [https://neo4j.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.opencypher.caps.test.fixture

import org.apache.spark.sql.Row
import org.opencypher.caps.test.support.DebugOutputSupport

import scala.collection.Bag

trait TeamDataFixture extends TestDataFixture with DebugOutputSupport {

  override val dataFixture =
    """
       CREATE (a:Person:German {name: "Stefan", luckyNumber: 42})
       CREATE (b:Person:Swede  {name: "Mats", luckyNumber: 23})
       CREATE (c:Person:German {name: "Martin", luckyNumber: 1337})
       CREATE (d:Person:German {name: "Max", luckyNumber: 8})
       CREATE (a)-[:KNOWS {since: 2016}]->(b)
       CREATE (b)-[:KNOWS {since: 2016}]->(c)
       CREATE (c)-[:KNOWS {since: 2016}]->(d)
    """

  override def nbrNodes = 4

  override def nbrRels = 3

  def teamDataGraphNodes: Bag[Row] = Bag(
    Row(0, true, true, false, "Stefan", 42  ),
    Row(1, true, false, true,   "Mats", 23  ),
    Row(3, true, true, false,    "Max", 8   ),
    Row(2, true, true, false, "Martin", 1337)
  )

  def teamDataGraphRels: Bag[Row] = Bag(
    Row(0, 0, "KNOWS", 1, 2016),
    Row(1, 1, "KNOWS", 2, 2016),
    Row(2, 2, "KNOWS", 3, 2016)
  )
}
