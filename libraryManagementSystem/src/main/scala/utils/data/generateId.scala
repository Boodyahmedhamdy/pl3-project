package utils.data

object Utils {

  def generateId(): Int = {
    // Generate a random number between 100000 and 999999 (inclusive)
    val randomInt = scala.util.Random.nextInt(900000) + 100000

    // Ensure the number is always 6 digits by padding with zeroes
    randomInt
  }
}
