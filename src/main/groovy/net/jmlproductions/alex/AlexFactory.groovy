package net.jmlproductions.alex

import net.jmlproductions.alex.ui.NamePrompt
import net.jmlproductions.alex.ui.StartPage

class AlexFactory
{
    def create() {
        def user = new User()
        def persistentData = new PersistentData()

        def router = new Router()
        router.addRoute("name-prompt", { new NamePrompt(user, persistentData, router) } )
        router.addRoute("start", { new StartPage(user) })

        new Alex(router: router, user: user)
    }
}
