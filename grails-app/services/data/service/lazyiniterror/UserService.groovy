package data.service.lazyiniterror

import grails.gorm.multitenancy.CurrentTenant
import grails.gorm.services.Join
import grails.gorm.services.Service
import grails.transaction.Transactional

@Service(User)
@CurrentTenant
@Transactional
abstract class UserService {
    @Join('profile')
    abstract User findById(Serializable id)
}