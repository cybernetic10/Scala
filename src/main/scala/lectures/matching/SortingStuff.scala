package lectures.matching

import lectures.matching.SortingStuff.Knife

/**
  * Раскладывание вещей по коробкам
  *
  * У Вас есть вещи 3-х разных типов
  * * * * Watches - часы
  * * * * Boots - кросовки
  * * * * Book - книги
  *
  * Вы хотите привести свои вещи в порядок и оставить только нужные
  * Для этого Вы завели себе коробку с отделениями для каждого типа вещей
  * * * * * StuffBox
  *
  * В StuffBox вы клатеде вещи таким образом
  * * * * * Все часы дороже 1000 в StuffBox.watches
  * * * * * Все кросовки от Converse или Adidas в StuffBox.boots
  * * * * * Все интересные книги в StuffBox.books
  * * * * * Остальное в StuffBox.junk
  *
  * После того как Вы разложиди вещи, вы пытаетеь найти свой любимый нож,
  * который вы могли случайно положить в junk.
  *
  * После запуска SortingStuff должен напечатаьь ответ на вопрос
  * Is the knife in a junk? - "
  *
  * Что бы выполнить задание, раскомментируйте код и следуйте подсказкам
  *
  */

object SortingStuff extends App {

	object Knife extends Stuff

	trait Stuff

	case class Boots(brand: String, size: Int = 43) extends Stuff

	case class Watches(brand: String, cost: Float) extends Stuff

	case class Book(name: String, isInteresting: Boolean = false) extends Stuff

	val stuff = List(
		Boots("Adidas", 42),
		Book("Game of thrones 1", true),
		Boots("Reebok", 42),
		Watches("Nautica", 10000),
		Boots("Converse", 43),
		Boots("Converse", 43),
		Watches("Breitling", 100000),
		Watches("Electronika", 1000),
		Boots("Skorohod", 50),
		Boots("Noname", 45),
		Watches("Zarya", 15000),
		Book("Game of thrones 3"),
		Book("Game of thrones 4"),
		Watches("Casio", 5000),
		Watches("Citizen", 5000),
		Book("Game of thrones 2", true),
		Book("Idiot", true),
		Book("Lubovnaya lubov"),
		Knife
	)

	case class StuffBox(books: List[Book] = Nil,
	                    watches: List[Watches] = Nil,
	                    boots: List[Boots] = Nil,
	                    junk: List[Stuff] = Nil)

	// Метод сортивки вещей, возвращает наполненную коробку
	def sortJunk(stuff: List[Stuff]) = sort(stuff, StuffBox())

	// Замените знаки вопроса подходящим кодом
	// Поправьте логику метода
	private def sort(stuff: List[Stuff], stuffBox: StuffBox): StuffBox = {
		stuff match {
			// Если остался один элемент, то кладем его в коробку и возвращаем её
			case head :: Nil => putStuffInRightBox(head, stuffBox)
			// Если есть хвост, то кладем первый элемент в коробку, а для хвоста снова вызываем метод сортировки
			case head :: tail =>
				val newBox = putStuffInRightBox(head, stuffBox)
				sort(tail, newBox)
			case _ => stuffBox
		}
	}

	// Метод должен положить вещь в правильную коробку
	private def putStuffInRightBox(item: Stuff, stuffBox: StuffBox) = {
		item match {
			// Кладем обувь
			case it: Boots if (it.brand == "Adidas" || it.brand == "Converse") =>
				stuffBox.copy(boots = it :: stuffBox.boots)
			// Кладем часы
			case it: Watches if it.cost > 1000 =>
				stuffBox.copy(watches = it :: stuffBox.watches)
			// Кладем книги
			case it@Book(_, true) =>
				stuffBox.copy(books = it :: stuffBox.books)
			// Кладем все остальное
			case junk@_ =>
				stuffBox.copy(junk = junk :: stuffBox.junk)
		}
	}

	// Поиск ножа
	def findMyKnife(stuffBox: StuffBox): Boolean =
		if ( stuffBox.junk.contains( Knife) ) true else false
//		for( junk <- stuffBox.junk ) {
//			if( junk == Knife ) true
//			junk match {
//				case Knife =>
//			}
//		}
//		stuffBox match {
//			case junk@StuffBox if junk.junk.contains(Knife) => true
//			case _ => false
//		}

	// Сортируем списко вещей и ищем в ней нож
	val knifeIsInJunk = (sortJunk _ andThen findMyKnife) (stuff)

	print(s"Is knife in a junk? - $knifeIsInJunk")
}
