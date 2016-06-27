package net.jmlproductions.alex

class Router
{
    def routes = [:]

    def addRoute(name, route) {
        routes[name] = route
    }

    def go(destination, arguments = null) {
        if (routes.containsKey(destination)) {
            if (arguments == null) {
                routes[destination]()
            } else {
                routes[destination](arguments)
            }
        }
    }
}
