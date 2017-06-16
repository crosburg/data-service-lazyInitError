package data.service.lazyiniterror

class Tenant {
    String name

    static constraints = {
        name nullable: false, blank: false, unique: true, matches: '^[a-z]+(-[a-z]+)*$'
    }
}
