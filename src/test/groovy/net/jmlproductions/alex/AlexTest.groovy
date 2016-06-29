package net.jmlproductions.alex

import org.junit.Before
import org.junit.Test

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.verify

class AlexTest
{
    def subject

    def goCalled
    def goDestination
    def fakeRouter
    def fakePersistentData

    def fakeUser = new User(name: "not set")

    def savedUser = new User(name: "doofus")

    @Before
    def void setup() {
        goCalled = false
        fakeRouter = [go: { destination ->
            goCalled = true
            goDestination = destination
        }]
        fakePersistentData = [
            savedUserExists: { false },
            getSavedUser: {}
        ]
        subject = new Alex(router: fakeRouter, user: fakeUser, persistentData: fakePersistentData)
    }

    @Test
    def void opensNamePromptIfNoNameIsAvailable() {
        subject.start()

        assert goCalled
        assert goDestination == "name-prompt"
    }

    def void savedUserExists() {
        fakePersistentData.savedUserExists = { true }
        fakePersistentData.getSavedUser = { savedUser }
    }

    @Test
    def void loadsTheUserIfAvailable() {
        savedUserExists()
        subject.start()

        assert fakeUser.name == "doofus"
    }

    @Test
    def void opensStartPageIfNameIsAvailable() {
        savedUserExists()
        subject.start()

        assert goDestination == "start"
    }
}
