package net.jmlproductions.alex

class Alex
{
    def router
    def user

    def start()
    {
        if (user.hasName())
        {
            router.go("start", user)
        } else {
            router.go("name-prompt")
        }
    }
}