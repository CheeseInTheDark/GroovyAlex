package net.jmlproductions.alex

class Alex
{
    def router
    def user
    def persistentData

    def start()
    {
        if (persistentData.savedUserExists()) {
            loadSavedUser()
            router.go("start")
        } else {
            router.go("name-prompt")
        }
    }

    def loadSavedUser()
    {
        def savedUser = persistentData.getSavedUser()
        user.name = savedUser.name
    }
}