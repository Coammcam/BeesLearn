package fpl.md07.beeslearn.GlobalVariable

import fpl.md07.beeslearn.models.UserModel

object UserSession {
    var currentUser: UserModel? = null
    var bonusHoneyJar: Int = 0
    var bonusScore: Int = 0
    var bonusExp: Int = 0
    var expReq: Int = 0
}
