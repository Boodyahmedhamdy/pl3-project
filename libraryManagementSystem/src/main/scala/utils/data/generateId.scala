package utils.data

object Utils {

  def generateId(): Int = {
    // Generate a random number between 100000 and 999999 (inclusive)
    val randomInt = scala.util.Random.nextInt(900000) + 100000

    // Ensure the number is always 6 digits by padding with zeroes
    randomInt
  }

  def turnStringIntoList(input: String): List[Int] = {
    val regex = """List\((.*?)\)""".r
    input match {
      case regex(nums) => nums.split(",").map(_.trim.toInt).toList
      case _ => List.empty[Int] // Return an empty list if the input doesn't match the expected format
    }
  }

}
