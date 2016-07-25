package lectures.collections

/**
  * Постарайтесь не использовать мутабильные коллекции и var
  * Подробнее о сортировке можно подсмотреть здесь - https://en.wikipedia.org/wiki/Merge_sort
  *
  *
  */
object MergeSortImpl extends App {

  def mergeSort(data: Seq[Int]): Seq[Int] = {
	  if( data.length == 1 ) data else {
		  val middle = data.length / 2

		  val sortedLeft = mergeSort( data.slice(0, middle) )
		  val sortedRight = mergeSort( data.slice(middle + 1, data.length) )


	  }
  }

}
