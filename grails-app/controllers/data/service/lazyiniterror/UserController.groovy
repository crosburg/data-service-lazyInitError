package data.service.lazyiniterror

import org.springframework.http.HttpStatus

class UserController {
    UserService userService

	static responseFormats = ['json']
	
    def show(Long id) {
        User user = userService.findById(id)

        if (!user) {
            Map<String, String> result = [message: "Unable to find user with id '$id'."]
            respond result, status: HttpStatus.NOT_FOUND
            return
        }

        println "user.profile.username: ${user.profile.username}"

        respond user, [status: HttpStatus.OK, view: 'user']
    }
}
