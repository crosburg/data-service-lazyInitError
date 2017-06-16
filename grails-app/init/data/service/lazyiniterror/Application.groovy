package data.service.lazyiniterror

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import grails.gorm.multitenancy.Tenants
import grails.gorm.transactions.Transactional
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner

class Application extends GrailsAutoConfiguration implements ApplicationRunner {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Override
    @Transactional
    void run(ApplicationArguments args) throws Exception {
        Tenant.findByName('vb-test') ?: new Tenant(name: 'vb-test').save(flush: true)

        Tenants.withId('vb-test') {
            User.findByEmail("test@test.io") ?: new User(email: 'test@test.io', profile: new UserProfile(username: 'testio', website: 'http://test.io')).save(flush: true)
        }
    }
}