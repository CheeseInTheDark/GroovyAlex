package net.jmlproductions.alex

import net.jmlproductions.alex.ui.ConversationWindow

class ConversationStarter
{
    def initiator

    def talkTo(otherPerson) {
        new ConversationWindow(new Conversation(initiator, otherPerson))
    }
}
