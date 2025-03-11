# Scala语法

入参是可变参数列表，`_*` 用于将可变参数展开

```scala
def json(paths: String*): DataFrame = format("json").load(paths : _*)
```

包对象，定义一些类型别名、常量和方法，整个包中的类都可直接使用这些定义。

```scala
package object sql {
  @DeveloperApi
  @Unstable
  type Strategy = SparkStrategy

  type DataFrame = Dataset[Row]

  private[sql] val SPARK_VERSION_METADATA_KEY = "org.apache.spark.version"

  private[sql] val SPARK_TIMEZONE_METADATA_KEY = "org.apache.spark.timeZone"
}
```

方法传入参数是代码块，传入代码块 `block` 后返回类型 `T`。

```scala
private[sql] def withActive[T](block: => T): T = {
  val old = SparkSession.activeThreadSession.get()
  SparkSession.setActiveSession(this)
  try block finally {
    SparkSession.setActiveSession(old)
  }
}
```

调用方法传入代码块。

```scala
def sql(sqlText: String): DataFrame = withActive {
  val tracker = new QueryPlanningTracker
  val plan = tracker.measurePhase(QueryPlanningTracker.PARSING) {
    sessionState.sqlParser.parsePlan(sqlText)
  }
  Dataset.ofRows(self, plan, tracker)
}
```

给当前对象实例起别名 `self`，作用类似于 `this`。

```scala
class SparkSession private(
    @transient val sparkContext: SparkContext,
    @transient private val existingSharedState: Option[SharedState],
    @transient private val parentSessionState: Option[SessionState],
    @transient private[sql] val extensions: SparkSessionExtensions,
    @transient private[sql] val initialSessionOptions: Map[String, String])
  extends Serializable with Closeable with Logging { self =>
}  
```

泛型递归，使该类能被具体的子类引用自身，增强类型的安全性和灵活性。

```scala
abstract class TreeNode[BaseType <: TreeNode[BaseType]] extends Product with TreePatternBits {
  self: BaseType =>
}
```

隐式类，扩展现有类的方法，不需要改动现有类。`LogicalPlan` 类型对象可直接调用 `optionalMap` 方法，编译器自动找到隐式类做转换。

```scala
implicit class EnhancedLogicalPlan(val plan: LogicalPlan) extends AnyVal {
  def optionalMap[C](ctx: C)(f: (C, LogicalPlan) => LogicalPlan): LogicalPlan = {
    if (ctx != null) {
      f(ctx, plan)
    } else {
      plan
    }
  }
}
```

集合合并操作，对集合中的元素按照函数两两合并成一个。

```scala
val from = ctx.relation.asScala.foldLeft(null: LogicalPlan) { (left, relation) =>
  val right = plan(relation.relationPrimary)
  val join = right.optionalMap(left) { (left, right) =>
    if (relation.LATERAL != null) {
      if (!relation.relationPrimary.isInstanceOf[AliasedQueryContext]) {
        throw QueryParsingErrors.invalidLateralJoinRelationError(relation.relationPrimary)
      }
      LateralJoin(left, LateralSubquery(right), Inner, None)
    } else {
      Join(left, right, Inner, None, JoinHint.NONE)
    }
  }
  withJoinRelations(join, relation)
}
```

`_` 表示匿名参数，接收的参数不会使用它。

```scala
object AlwaysProcess {
  val fn: TreePatternBits => Boolean = { _ => true}
}
```

自类型约束，`trait` 实例必须是 `TreeNode[T]` 类型，`asInstanceOf` 强制类型转换。

```scala
trait LeafLike[T <: TreeNode[T]] { self: TreeNode[T] =>
  override final def children: Seq[T] = Nil
  override final def mapChildren(f: T => T): T = this.asInstanceOf[T]
  override final def withNewChildrenInternal(newChildren: IndexedSeq[T]): T = this.asInstanceOf[T]
}
```

若只有一个参数且只对参数执行单一操作，用占位符 `_` 表示对每个元素操作。

```scala
val childrenSQL = children.map(_.sql).mkString(", ")
```



# Antlr4语法

向 `parser` 或 `lexer` 添加成员变量和语法

```
@parser::members {
	public boolean legacy_setops_precedence_enabled = false;
}
```

命名语法规则，给特定规则特定分支命名，可以在生成的 `AST` 中创建特定上下文类，并提供具体的访问方法，便于用特定命名访问。

```
statement
    : query                                                            #statementDefault
```

`?` 表示可选，前面的元素可出现零次或一次

```
query
    : ctes? queryTerm queryOrganization
    ;
```



# 应用提交

执行 `SparkSubmit` 类的 `main` 方法，解析提交的参数，创建对应的 `SparkApplication`。

```scala
val app: SparkApplication = if (classOf[SparkApplication].isAssignableFrom(mainClass)) {
  mainClass.getConstructor().newInstance().asInstanceOf[SparkApplication]
} else {
  new JavaMainApplication(mainClass)
}
```

`yarn` 的集群模式，提交给 `ResourceManager` 应用创建 `ApplicationMaster`，启动 `Driver`。

`Driver` 执行创建 `SparkContext`， 是与 `Spark` 集群的连接入口，可用于创建 `RDD`、累加器和广播变量。`SparkContext` 初始化过程包括环境变量的初始化和一些 `Component` 的创建，如 `TaskScheduler` 和 `DAGScheduler`。

# RDD

弹性分布式数据集，最基本的抽象，代表一个不变的分区数据集，可以并行操作。通过隐式转换让 `RDD` 自动支持各种操作，不需要显式指定方法。

`RDD` 的主要特征：一个分区列表；一个函数用来计算每个分区；和其他 `RDD` 的依赖关系；对键值 `RDD` 有一个分区器；计算每个分区的首选位置列表。

隐式类型转换，当调用某个方法类型不匹配，自动寻找隐式方法调用。

```scala
object RDD {
  implicit def doubleRDDToDoubleRDDFunctions(rdd: RDD[Double]): DoubleRDDFunctions = {
    new DoubleRDDFunctions(rdd)
  }

  implicit def numericRDDToDoubleRDDFunctions[T](rdd: RDD[T])(implicit num: Numeric[T])
    : DoubleRDDFunctions = {
    new DoubleRDDFunctions(rdd.map(x => num.toDouble(x)))
  }
}
```

懒执行，遇到 `action` 算子触发。

```scala
  def reduce(f: (T, T) => T): T = withScope {
    //确保RDD能序列化
    val cleanF = sc.clean(f)
    //在分区内执行的结果
    val reducePartition: Iterator[T] => Option[T] = iter => {
      if (iter.hasNext) {
        Some(iter.reduceLeft(cleanF))
      } else {
        None
      }
    }
    //分区间合并结果
    var jobResult: Option[T] = None
    val mergeResult = (_: Int, taskResult: Option[T]) => {
      if (taskResult.isDefined) {
        jobResult = jobResult match {
          case Some(value) => Some(f(value, taskResult.get))
          case None => taskResult
        }
      }
    }
    //执行分布式作业，对RDD每个分区调用
    sc.runJob(this, reducePartition, mergeResult)
    // Get the final result out of our Option, or throw an exception if the RDD was empty
    jobResult.getOrElse(throw new UnsupportedOperationException("empty collection"))
  }
```

`::`，列表构造操作符，将新元素追加到已有列表前面。

```scala
def collectJsonValue(tn: BaseType): Unit = {
  val jsonFields = ("class" -> JString(tn.getClass.getName)) ::
    ("num-children" -> JInt(tn.children.length)) :: tn.jsonFields
  jsonValues += JObject(jsonFields)
  tn.children.foreach(collectJsonValue)
}
```

# SparkSQL

`SQL` 到 `RDD` 的执行，需要经过逻辑计划和物理计划。逻辑计划将 `SQL` 转换成逻辑算子树，包含的逻辑映射到不同节点。逻辑算子树的三个子阶段：未解析、解析后、优化后。物理计划将逻辑算子树转换成物理算子树，物理算子树的节点是 `RDD`，包含三个子阶段：根据逻辑算子树生成物理算子树列表，可能对应多个物理算子树；从列表选取最优物理算子树；对选取的物理算子树进行提交前的准备工作，执行代码生成得到准备后的物理算子树。

`SQL` 解析到提交都在 `Driver` 进行，`SparkSession` 执行 `sql` 方法调用 `SessionState` 中的各种对象，最后封装成 `QueryExecution`。

## Catalyst

### InternalRow

表示一行数据，物理算子树产生的类型为 `RDD[IntenvalRow]`，每列都是 `Catalyst` 内部定义的类型。根据下标访问操作列元素。

### TreeNode

所有树结构的基类，定义通用的集合操作和树遍历操作接口。包含两个子类：`Expression` 是表达式体系；`QueryPlan` 包含逻辑算子树和物理执行算子树。

`Expression` 指不需要触发执行引擎可直接计算的单元。`QueryPlan` 执行前先绑定，将表达式与输入属性对应起来，调用表达式处理逻辑。

### Parser

借助 `ANTLR4` 生成语法树，基于 `Visitor` 访问节点。访问者模式：将算法与对象结构分离。一个由许多对象构成的对象结构，每个对象都有 `accept` 方法接受访问者。访问者是一个接口拥有 `visit` 方法，对访问到的不同类型元素做出不同反应。

`ParseInterface` 包含对 `SQL`、`Expression` 等解析方法。`CatalystSqlParser` 仅用于 `Catalyst` 内部，`SparkSqlParser` 用于外部。最核心的是 `AstBuilder`，继承 `ANTLR4` 生成的 `SqlBaseBaseVisitor`，生成 `AST`。

开发新语法，先改动 `ANTLR4` 文件，然后在 `AstBuilder` 添加访问逻辑，最后添加执行逻辑。

### LogicalPlan

`SQL` 语句经过 `SparkSqlParser` 解析生成 `Unresolved LogicalPlan`，最终优化成 `Optimized LogicalPlan`。

`AstBuilder` 访问节点，将语法树的各种 `Context` 节点转换成 `LogicalPlan` 节点，成为 `Unresolved LogicalPlan`，此时不包含数据信息与列信息。

`Analyzer` 将规则作用在 `Unresolved LogicalPlan`，对节点绑定数据信息，生成解析后的 `Analyzed LogicalPlan`。

`Optimizer` 将优化规则作用到 `Analyzed LogicalPlan`，生成 `Optimized LogicalPlan`。

`LogicalPlan` 可分成三类：叶子节点、一元节点和二元节点。叶子节点对应数据表和命令相关的逻辑。一元节点用于对数据的逻辑转换操作，如过滤。二元节点用于 `join` 和集合操作。

`Unresolved LogicalPlan` 生成：在 `ParserDriver` 调用语法分析器的 `singleStatement` 构建语法树，用 `AstBuilder` 的访问者类访问语法树。

`Analyzed LogicalPlan` 生成：`Analyzer` 将 `UnresolvedRelation` 和 `UnresolvedAttribute` 解析成由类型的对象。

# 源码分析

根据一个简单的例子剖析 `sql` 解析过程。

```scala
private def runTest(spark : SparkSession): Unit = {
  val df = spark.read.json("examples/src/main/resources/people.json")
  df.createOrReplaceTempView("people")
  val sqlDF = spark.sql("SELECT * FROM people")
  sqlDF.show()
}
```

入口是 `SparkSession.sql` 方法

```scala
def sql(sqlText: String): DataFrame = withActive {
  val tracker = new QueryPlanningTracker
  val plan = tracker.measurePhase(QueryPlanningTracker.PARSING) {
    sessionState.sqlParser.parsePlan(sqlText)
  }
  Dataset.ofRows(self, plan, tracker)
}
```

将 `sql` 解析成 `LogicalPlan`，首先构造 `antlr4` 的 `lexer` 和 `parser`，生成 `AST`。

```scala
protected def parse[T](command: String)(toResult: SqlBaseParser => T): T = {
  logDebug(s"Parsing command: $command")
  //构建词法分析器，抽出关键词和表达式
  val lexer = new SqlBaseLexer(new UpperCaseCharStream(CharStreams.fromString(command)))
  lexer.removeErrorListeners()
  lexer.addErrorListener(ParseErrorListener)
  //lexer生成的token流
  val tokenStream = new CommonTokenStream(lexer)
  //构建语法分析器
  val parser = new SqlBaseParser(tokenStream)
  parser.getInterpreter.setPredictionMode(PredictionMode.SLL)
  toResult(parser)    
}
```

`SparkSQL` 继承 `SqlBaseBaseVisitor` 创建 `AstBuilder`，实现对 `AST` 各节点的访问操作，遍历 `AST` 生成 `LogicalPlan`。

```scala
override def parsePlan(sqlText: String): LogicalPlan = parse(sqlText) { parser =>
  astBuilder.visitSingleStatement(parser.singleStatement()) match {
    case plan: LogicalPlan => plan
    case _ =>
      val position = Origin(None, None)
      throw QueryParsingErrors.sqlStatementUnsupportedError(sqlText, position)
  }
}
```

例如对于查询

```sql
SELECT age FROM people WHERE age > 18;
```

生成的 `LogicalPlan` 为

```json
[ {
  "class" : "org.apache.spark.sql.catalyst.plans.logical.Project",
  "num-children" : 1,
  "projectList" : [ [ {
    "class" : "org.apache.spark.sql.catalyst.analysis.UnresolvedAttribute",
    "num-children" : 0,
    "nameParts" : "[age]"
  } ] ],
  "child" : 0
}, {
  "class" : "org.apache.spark.sql.catalyst.plans.logical.Filter",
  "num-children" : 1,
  "condition" : [ {
    "class" : "org.apache.spark.sql.catalyst.expressions.GreaterThan",
    "num-children" : 2,
    "left" : 0,
    "right" : 1
  }, {
    "class" : "org.apache.spark.sql.catalyst.analysis.UnresolvedAttribute",
    "num-children" : 0,
    "nameParts" : "[age]"
  }, {
    "class" : "org.apache.spark.sql.catalyst.expressions.Literal",
    "num-children" : 0,
    "value" : "18",
    "dataType" : "integer"
  } ],
  "child" : 0
}, {
  "class" : "org.apache.spark.sql.catalyst.analysis.UnresolvedRelation",
  "num-children" : 0,
  "multipartIdentifier" : "[people]",
  "options" : null,
  "isStreaming" : false
} ]
```

解析 `LogicalPlan` 的过程，先解析 `from` 子句。

```scala
override def visitRegularQuerySpecification(
    ctx: RegularQuerySpecificationContext): LogicalPlan = withOrigin(ctx) {
  val from = OneRowRelation().optional(ctx.fromClause) {
    visitFromClause(ctx.fromClause)
  }
  withSelectQuerySpecification(
    ctx,
    ctx.selectClause,
    ctx.lateralView,
    ctx.whereClause,
    ctx.aggregationClause,
    ctx.havingClause,
    ctx.windowClause,
    from
  )
}
```

再解析 `select` 子句，`where` 子句也在该过程解析，生成 `Project` 和 `Filter` 节点。

```scala
private def withSelectQuerySpecification(
    ctx: ParserRuleContext,
    selectClause: SelectClauseContext,
    lateralView: java.util.List[LateralViewContext],
    whereClause: WhereClauseContext,
    aggregationClause: AggregationClauseContext,
    havingClause: HavingClauseContext,
    windowClause: WindowClauseContext,
    relation: LogicalPlan): LogicalPlan = withOrigin(ctx) {
  val isDistinct = selectClause.setQuantifier() != null &&
    selectClause.setQuantifier().DISTINCT() != null

  val plan = visitCommonSelectQueryClausePlan(
    relation,
    visitNamedExpressionSeq(selectClause.namedExpressionSeq),
    lateralView,
    whereClause,
    aggregationClause,
    havingClause,
    windowClause,
    isDistinct)

  // Hint
  selectClause.hints.asScala.foldRight(plan)(withHints)
}
```

最终自下而上生成树结构

```
'Project ['age]
+- 'Filter ('age > 18)
   +- 'UnresolvedRelation [people], [], false
```

此时生成的 `LogicalPlan` 是 `Unresolved`，下一步需要将 `Unresolved` 对象解析成有类型的。该过程在 `QueryExecution` 初始化过程中进行。对 `UnresolvedLogicalPlan` 绑定、解析、优化操作，主要方法都是基于 `Rule`。



```scala
def ofRows(sparkSession: SparkSession, logicalPlan: LogicalPlan, tracker: QueryPlanningTracker)
  : DataFrame = sparkSession.withActive {
  //创建QueryExecution
  val qe = new QueryExecution(sparkSession, logicalPlan, tracker)
  qe.assertAnalyzed()
  new Dataset[Row](qe, RowEncoder(qe.analyzed.schema))
}
```

`Analyzer` 解析 `LogicalPlan`，确定数据源的表信息，对不同的节点执行 `rule`，映射到具体的属性和类型，构建 `Dataset`。

```scala
def assertAnalyzed(): Unit = analyzed

lazy val analyzed: LogicalPlan = executePhase(QueryPlanningTracker.ANALYSIS) {
  sparkSession.sessionState.analyzer.executeAndCheck(logical, tracker)
}
```

