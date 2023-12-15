package datalayer.entites


case class BookEntity(id: Int,
                      title: String,
                      author: String,
                      borrowedBy: Int = -1)


