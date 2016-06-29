package net.jmlproductions.alex

import com.fasterxml.jackson.databind.ObjectMapper

class PersistentData
{
    def mapper = new ObjectMapper()

    def savedUserExists() {
        fileExists("data/currentUser.json")
    }

    def setCurrentUser(user) {
        writeObjectToPath(user, "data/currentUser.json")
    }

    def getSavedUser() {
        readObjectFromPath(User.class, "data/currentUser.json")
    }

    def writeObjectToPath(object, path) {
        ensurePathExists(path)
        mapper.writeValue(new File(path), object)
    }

    def readObjectFromPath(type, path) {
        if (fileExists(path)) mapper.readValue(new File(path), type)
    }

    def fileExists(path) {
        new File(path).exists()
    }

    def ensurePathExists(path) {
        new File(path).createNewFile()
    }
}
