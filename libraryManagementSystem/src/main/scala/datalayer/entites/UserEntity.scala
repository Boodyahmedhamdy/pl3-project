package datalayer.entites


// roleId = 2 is normal user
case class UserEntity(id: Int = 0, name: String = "username", roleId: Int = 2)
