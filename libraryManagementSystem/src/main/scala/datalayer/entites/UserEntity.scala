package datalayer.entites

import utils.data.Utils



case class UserEntity(
                       id: Int = Utils.generateId(),
                       name: String, role: String,
                       bookIds: List[Int])
