package data.service.lazyiniterror

import grails.gorm.MultiTenant

class User implements MultiTenant<User> {
    String email

    UserProfile profile

    static constraints = {
    }
}
