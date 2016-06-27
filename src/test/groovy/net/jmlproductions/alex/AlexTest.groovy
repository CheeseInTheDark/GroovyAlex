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
    def destinationArguments
    def fakeRouter
    def fakeUser

    @Before
    def void setup() {
        goCalled = false;
        fakeRouter = [go: { destination, arguments=null ->
            goCalled = true
            goDestination = destination
            destinationArguments = arguments
        }]
        fakeUser = [hasName: { false }]
        subject = new Alex(router: fakeRouter, user: fakeUser)
    }

    @Test
    def void opensNamePromptIfNoNameIsAvailable() {
        subject.start()
        assert goCalled
        assert goDestination == "name-prompt"
    }

    @Test
    def void opensStartPageIfNameIsAvailable() {
        fakeUser.hasName = { true }
        subject.start()

        assert goDestination == "start"
        assert destinationArguments == fakeUser
    }
}
