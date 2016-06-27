package net.jmlproductions.alex.ui

import net.jmlproductions.alex.User
import org.junit.Before
import org.junit.Test

class NamePromptTest
{
    def persistentData
    def user
    def router
    def subject

    def savedUser
    def frameWasDestroyed
    def routerDestination

    @Before
    def void setup() {
        savedUser = null
        routerDestination = null
        frameWasDestroyed = false

        user = new User()
        persistentData = [setCurrentUser: { user -> savedUser = user }]
        router = [ go: { routerDestination = it } ]

        subject = new NamePrompt.Controller(user: user, persistentData: persistentData, router: router)
        subject.frameElement = [dispose: { frameWasDestroyed = true }]
        subject.nameElement = [text: ""]
    }

    @Test
    def void updatesTheUserNameWhenDoneIsClicked() {
        subject.nameElement = [text: "Bobbert"]
        subject.doneClicked()

        assert user.getName() == "Bobbert"
    }

    @Test
    def void savesTheNewUserWhenDoneIsClicked() {
        subject.nameElement = [text: "Bobbert"]
        subject.doneClicked()

        assert savedUser.getName() == "Bobbert"
    }

    @Test
    def void opensTheStartPageWhenDoneIsClicked() {
        subject.doneClicked()

        assert routerDestination == "start"
    }

    @Test
    def void destroysItselfWhenDoneIsClicked() {
        subject.doneClicked()

        assert frameWasDestroyed
    }
}
