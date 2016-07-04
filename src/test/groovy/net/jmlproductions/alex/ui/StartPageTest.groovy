package net.jmlproductions.alex.ui

import net.jmlproductions.alex.User
import org.junit.Before
import org.junit.Test

class StartPageTest
{
    def subject
    def user
    def conversationStarter
    def conversationParticipant

    @Before
    def void setup() {
        conversationStarter = [talkTo: { address -> conversationParticipant = address } ]
        user = new User(name: "Fred")
        subject = new StartPage.Controller(conversationStarter: conversationStarter, user: user)

        subject.addressElement = [text: "127.0.0.1:1000"]
    }

    @Test
    def void opensANewConversationWhenAValidConnectionIsMade() {
        subject.connectClicked()
        assert conversationParticipant == "127.0.0.1:1000"
    }
}
