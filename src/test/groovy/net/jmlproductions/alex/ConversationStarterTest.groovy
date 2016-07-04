package net.jmlproductions.alex

import net.jmlproductions.alex.mocks.MockNew
import net.jmlproductions.alex.ui.ConversationWindow
import org.junit.After
import org.junit.Before
import org.junit.Test

import static MockNew.*

class ConversationStarterTest
{
    def subject

    def fakeUser = new User(name: "Bungalow Bill")
    def otherPersonPassed
    def initiatorPassed
    def conversationPassed

    def newConversation = new ConversationTest()

    @Before
    def void setup() {
        subject = new ConversationStarter(initiator: fakeUser)

        mockConstructor(ConversationTest) { initiator, otherPerson ->
            initiatorPassed = initiator
            otherPersonPassed = otherPerson
            newConversation.otherPerson = otherPerson
            return newConversation
        }

        mockConstructor(ConversationWindow) { conversation ->
            conversationPassed = conversation
        }
    }

    @After
    def void teardown() {
        restoreConstructor(ConversationTest)
        restoreConstructor(ConversationWindow)
    }

    @Test
    def void startsNewConversations() {
        subject.talkTo("127.0.0.1:12345")
        assert otherPersonPassed == "127.0.0.1:12345"
        assert initiatorPassed == fakeUser
    }

    @Test
    def void displaysNewConversations() {
        subject.talkTo("127.0.0.1:12345")
        assert conversationPassed == newConversation
    }
}
