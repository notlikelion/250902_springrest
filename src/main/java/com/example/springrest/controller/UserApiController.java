package com.example.springrest.controller;

import com.example.springrest.dto.UserDto;
import com.example.springrest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@Controller
@RequiredArgsConstructor
@RequestMapping("/api/users") // prefix, 공통경로
// localhost:8080/api/users/...
public class UserApiController {
    private final UserService userService;

    // @Get... @Post...
    // public String index() { return "index"; }
    // public String index(Model model) { return "index"; }
    // public String index(RedirectAttributes...) { return "redirect..."; }
    // Session? Cookie? -> Response
//    @ResponseBody // ViewResolver로 가는게 아니라 Return 데이터 자체를 응답값으로 제공해라
    // -> 이건 @RestController에는 필요 없음 -> 혼용할 때는 필요할 수도...
    @GetMapping // GET localhost:8080/api/users
//    public List<UserDto.Response> getAllUsers() {
    public ResponseEntity<List<UserDto.Response>> getAllUsers() {
//        return userService.findAllUsers();
        // 응답종류를 처리하는 ResponseEntity로 감싸서 전달

        // DB 원래 데이터 -> Entity (JPA Repo) -> Service, Controller (DTO)
        // -> Restful API (JSON) / Thymeleaf (Model, Object)

        return ResponseEntity.ok(userService.findAllUsers());
    }
}
