package lectures.functions

import javafx.scene.transform.MatrixType

/**
  * Цель упражнения, вычислить 9 - е число Фибоначчи
  * Для этого, раскомментируйте строчку в методе fibs и исправьте ошибку компиляции.
  *
  * Данная реализация вычисления чисел фибоначчи крайне не оптимальна (имеет показатеьную сложность O(a.pow(n)) )
  * Для того, что бы в этом убедиться Вы можете раскомментировать
  * строчку с вычислением 1000-ого числа фибоначчи
  *
  */
object Fibonacci extends App {

  // Task 2
  def fibs(num: Int) : Int = {
    if (num == 1) 1
    else
    if (num == 2) 1
    else {
      fibs(num - 1) + fibs(num - 2)
    }
  }

  println(fibs(8))
  //println(fibs(1000))
}

/**
  * Цель упражнения, используя приемы динамического программирования
  * реаилзовать более оптимальный алгоритм подсчета чисел фибоначчи
  * Для этого нужно реализовать функцию fibsImpl.
  * Сигнатуру функции Вы можете расширять по своему усмотрению,
  * но реализация должна удовлетварять следующим требованиям
  * * * * метод fibsImpl - должен быть tail recursive
  * * * * параметр acc - аккумулятор посчитанных значений
  *
  */
object Fibonacci2 extends App {

  def fibs2(num: Int) =
    if (num <= 2) Array(1, 1, 2)(num - 1)
    else fibsImpl(num: Int, Array(1, 1, 2))(num - 1)

  private def fibsImpl(num: Int, acc: Array[Int]): Array[Int] = {
    MatrixType
    var localArray: Array[Int] = acc
    for( i <- 3 to num ) {
      val newValue = localArray(i-2) + localArray(i-1)
      localArray = localArray :+ newValue
    }
    localArray
  }

  println(fibs2(16)) // 16 число
  println(fibs2(1000))
}



