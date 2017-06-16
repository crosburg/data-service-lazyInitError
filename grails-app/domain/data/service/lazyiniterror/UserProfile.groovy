package data.service.lazyiniterror

import grails.gorm.MultiTenant

class UserProfile implements MultiTenant<UserProfile> {
    String username
    String website

    static belongsTo = [
        user: User
    ]

    static constraints = {
    }
}
