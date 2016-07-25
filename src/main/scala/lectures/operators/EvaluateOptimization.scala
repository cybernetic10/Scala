package lectures.operators

import lectures.functions.{Computation, Data}
import lectures.functions.CurriedComputation
import lectures.functions.FunctionalComputation

/**
  * В задачке из lectures.functions.Computations, мы реализовали
  * один и тот же метод 3-я разными способами
  *
  * Пришло время оценить на сколько разные имплементации
  * отличаются друг от друга по производительности
  *
  * Для этого, раскомментируйте код, выполните в циклах вызов 3-х имплементаций
  * Оцените разницу во времени выполнения и объясните ее происхожение
  *
  */
object EvaluateOptimization extends App with Data {

  val startTimestamp = System.currentTimeMillis()


  // ВЫПОЛНИТЬ В ЦИКЛЕ  ОТ 1 ДО 100 Computation.computation(
      for(i <- 1 to 100) {
        Computation.computation( Computation.filterData, Computation.dataArray )
      }
      println("elapsed time " + (System.currentTimeMillis() - startTimestamp))

  // ВЫПОЛНИТЬ В ЦИКЛЕ  ОТ 1 ДО 100 CurriedComputation.partiallyAppliedCurriedFunction(
  val startTime2 = System.currentTimeMillis()
  var curried = CurriedComputation.curriedComputation( Computation.filterData) _
  for(i <- 1 to 100) {
    curried(Computation.dataArray)
  }
  val curriedWorkTime = (System.currentTimeMillis() - startTime2)
  println("elapsed time " + curriedWorkTime)

  // ВЫПОЛНИТЬ В ЦИКЛЕ  ОТ 1 ДО 100 FunctionalComputation.filterApplied
  val startTime3 = System.currentTimeMillis()
  val part1 = FunctionalComputation.functionalComputation( Computation.filterData)
  for(i <- 1 to 100) {
    part1(Computation.dataArray)
  }
  val functionalWorkTime = (System.currentTimeMillis() - startTime3)
  println("elapsed time " + (System.currentTimeMillis() - startTime3))

  // ВЫВЕСТИ РАЗНИЦУ В ПРОДОЛЖИТЕЛЬНОСТИ ВЫПОЛНЕНИЯ МЕЖДУ КАРРИРОВАННОЙ ВЕРСИЕЙ
  // И ФУНКЦИОНАЛЬНОЙ

  val diff = functionalWorkTime - curriedWorkTime

  println(s"Difference is about $diff milliseconds")
}

