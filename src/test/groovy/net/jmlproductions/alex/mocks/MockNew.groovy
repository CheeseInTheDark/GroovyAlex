package net.jmlproductions.alex.mocks

class MockNew
{
    def static realConstructors = [:]

    def static mockConstructor(mockedClass, closure) {
        realConstructors.put(mockedClass, mockedClass.metaClass.constructor)
        mockedClass.metaClass.constructor = closure
    }

    def static restoreConstructor(mockedClass) {
        mockedClass.metaClass.constructor = realConstructors[mockedClass]
    }
}
