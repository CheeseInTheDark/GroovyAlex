package net.jmlproductions.alex.ui

import groovy.swing.SwingBuilder

import javax.swing.JFrame

class StartPage
{

    static class Controller {
        def user
    }

    def StartPage(user) {
        new SwingBuilder().edt {
            frame(pack: true, show: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE, resizable: false) {
                panel(border: emptyBorder(5)) {
                    tableLayout(cellpadding: 5) {
                        tr {
                            td(align: "center", colspan: 2) {
                                label(text: "Hello ${user.getName()}")
                            }
                        }
                        tr {
                            td {
                                label(text: "Address")
                            }
                            td {
                                textField(id: "address", columns: 12)
                            }
                        }
                        tr {
                            td(align: "center", colspan: 2) {
                                button(id: "connect", text: "Connect")
                            }
                        }
                    }
                }
            }
        }
    }
}
