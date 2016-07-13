package lectures.functions

/**
  * Представим себе, как бы мог выглядеть API для работы, например, с БД
  * Строить методы этого API будем через композицию уже определенных методов.
  *
  * Ваша задача реализовать метод execute, композируя методы из объекта SQLAPI
  * Метод execute из объекта SQLAPI должен выполнить следующие действия
  * * * * * залогировать ресурс
  * * * * * получить соединение с помощью метода connection
  * * * * * залогировать соединение
  * * * * * открыть соединение
  * * * * * выполнить SQL
  * * * * * залогиовать результат
  *
  *
  * Обратите внимание на то, что композиция функций учит писать код в декларативном виде
  * Благодаря этому мы можем отделить реализацию методов от их применения и, в конечном итоге, иметь переиспользуемую
  * декларацию, которую можно применить, например, для настоящей БД
  *
  *
  */
object SQLAPI extends App {

  case class Connection(resource: String, opened: Boolean = false) {

    private val result = "SQL has been executed. Congrats!"

    def open(): Connection = this.copy(opened = true)

    def execute(sql: String): String = if(opened) result else throw new Exception("You have to open connection before execute")

  }

  private def logParamter(prm: String): String  = {println(prm); prm}

  val connection = (resource: String) => Connection(resource)

  def execute(resource: String, sql: String): String = ???//{

      //logParamter( ( ( logParamter _ andThen connection ) _ andThen openConnection )(resource)(sql) )

    //val res1 = logParamter( resource )
    //val conn = connection( res1 )
    //val openConn = conn.open()
    //val res2 = openConn.execute(sql)
    //val res3 =logParamter( res2 )

    //val step0 = connection compose logParamter
//    val step1 = logParamter _ andThen connection
//    val conn = step1( resource )
//    (conn.execute andThen logParamter)
  //}


  def openConnection(connection: Connection): (String) => String =
    (sql: String) => {
      connection.open execute sql
  }

  execute("some DB", "some SQL")
}
