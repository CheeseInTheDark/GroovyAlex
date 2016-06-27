package net.jmlproductions.alex

import com.fasterxml.jackson.databind.ObjectMapper

class PersistentData
{
    def mapper = new ObjectMapper()

    def setCurrentUser(user) {
        writeObjectToPath(user, "data/currentUser.json")
    }

    def writeObjectToPath(object, path) {
        ensurePathExists(path)
        mapper.writeValue(new File(path), object)
    }

    def ensurePathExists(path) {
        new File(path).mkdirs()
    }
}
