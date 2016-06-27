package net.jmlproductions.alex.ui

import groovy.swing.SwingBuilder

import javax.swing.JFrame

import static javax.swing.SwingConstants.CENTER

class NamePrompt
{
    static class Controller
    {
        def user
        def persistentData
        def router

        def nameElement
        def frameElement

        def doneClicked =
        {
            updateUser()
            router.go("start")
            frameElement.dispose()
        }

        def updateUser() {
            user.setName(nameElement.text)
            persistentData.setCurrentUser(user)
        }
    }

    def NamePrompt(user, persistentData, router) {
        def controller = new Controller(user: user, persistentData: persistentData, router: router)

        new SwingBuilder().edt {
            frame(id: "promptFrame", pack: true, show: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE, undecorated: true, resizable: false) {
                panel(border: emptyBorder(10)) {
                    tableLayout(cellpadding: 5) {
                        tr {
                            td(align: "center") {
                                label(text: "What's your name?")
                            }
                        }
                        tr {
                            td(align: "center") {
                                textField(columns: 12, horizontalAlignment: CENTER, id: "name")
                            }
                        }
                        tr {
                            td(align: "center") {
                                button(text: "Done", actionPerformed: controller.doneClicked )
                            }
                        }
                    }
                }
            }

            controller.frameElement = promptFrame
            controller.nameElement = name
        }
    }
}
