package net.jmlproductions.alex

import org.junit.Before
import org.junit.Test

class RouterTest
{
    def subject

    def wentToDestination
    def wentToAnotherDestination

    def testDestination = {
        wentToDestination = true
    }

    def anotherTestDestination = {
        wentToAnotherDestination = true
    }

    def macGuffinPassed
    def destinationWithArguments = { macGuffin ->
        macGuffinPassed = macGuffin
    }

    @Before
    def void setup()
    {
        subject = new Router()
        wentToDestination = false
        wentToAnotherDestination = false
        macGuffinPassed = "nothing"
    }

    @Test
    def void routesToDestinations() {
        subject.addRoute("destination", testDestination)
        subject.go("destination")

        assert wentToDestination
    }

    @Test
    def void doesNotRouteToNonexistentDestinations() {
        subject.addRoute("destination", testDestination)
        subject.go("this-does-not-exist")

        assert !wentToDestination
    }

    @Test
    def void tracksRoutesByName() {
        subject.addRoute("another-destination", anotherTestDestination)
        subject.addRoute("destination", testDestination)
        subject.go("another-destination")

        assert !wentToDestination
        assert wentToAnotherDestination
    }

    @Test
    def void passesArgumentToRoutes() {
        subject.addRoute("destination", destinationWithArguments)
        subject.go("destination", "valuableInfo")

        assert macGuffinPassed == "valuableInfo"
    }
}
