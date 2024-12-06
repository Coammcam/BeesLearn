package fpl.md07.beeslearn.GlobalVariable

import fpl.md07.beeslearn.models.UserModel

object UserSession {
    var currentUser: UserModel? = null
    var bonusHoneyJar: Int = 0
}
