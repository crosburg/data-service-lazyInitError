package data.service.lazyiniterror

import grails.gorm.multitenancy.Tenant
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import org.grails.datastore.mapping.multitenancy.web.CookieTenantResolver
import spock.lang.Specification

@Integration
@Rollback
@Tenant({ 'vb-test' })
class UserControllerSpec extends Specification {
    static final String TENANT_ID = 'vb-test'
    static final String TENANT_COOKIE_VALUE = CookieTenantResolver.COOKIE_NAME + "=" + TENANT_ID

    def "Test that user can retrieve his profile"() {
        setup:
            RestBuilder rest = new RestBuilder()
            Long userId = User.findByEmail('test@test.io')?.id

        when: "a user visit his profile"
            RestResponse resp = rest.get("http://localhost:${serverPort}/users/${userId}") {
                accept('application/json')
                contentType('application/json')
                header('Cookie', TENANT_COOKIE_VALUE)
            }

        then: "the complete profile should be returned"
            resp.status == 200
            resp.json.email == 'test@†est.io'
            resp.json.username == 'testio'
            resp.json.website == 'http://test.io'
    }
}