package test.florio.testapp.api;

import test.florio.testapp.common.Exception.ApiException;
import test.florio.testapp.model.domain.User;
import test.florio.testapp.model.dto.UserDto;
import test.florio.testapp.repository.UserRepository;
import test.florio.testapp.security.CurrentUser;
import test.florio.testapp.security.service.CustomUserDetails;
import test.florio.testapp.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static final String CHECK_ERROR_MESSAGE = "Incorrect password";

    @PostMapping(path = "/register")
    public ResponseEntity registerAccount(@Valid @RequestBody UserDto.Create userDto) {

        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        if (StringUtils.isEmpty(userDto.getPassword()) &&
                (userDto.getPassword().length() <= 4 && userDto.getPassword().length() > 10)) {
            return new ResponseEntity<>(CHECK_ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
        }
        userService.registerAccount(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/users/{email}")
    public ResponseEntity<UserDto.Response> getUser(@PathVariable String email) {
        return userRepository.findByEmail(email)
                .map(user -> modelMapper.map(user, UserDto.Response.class))
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
