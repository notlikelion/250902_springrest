package com.example.springrest.controller;

import com.example.springrest.dto.UserDto;
import com.example.springrest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    // 사용자 생성
    // POST localhost:8080/api/users
    @PostMapping // ("/")
    public ResponseEntity<UserDto.Response> createUser(@RequestBody UserDto.CreateRequest dto) {
        // JSON Body -> Form 하나하나 input. JSON {키/프로퍼티이름:값}
        UserDto.Response response = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 특정 사용자 조회 : 숫자로 된 개별 id/pk
    @GetMapping("/{id}")
    public ResponseEntity<UserDto.Response> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id));
    }

    // 사용자 정보 수정 (PUT)
    // GET, POST -> 웹 PAGE
    // REST -> HTTP Verb. GET, POST, PUT, DELETE, ...
    @PutMapping("/{id}")
    public ResponseEntity<UserDto.Response> updateUser(@PathVariable Long id, @RequestBody UserDto.UpdateRequest dto) {
        UserDto.Response updatedUser = userService.updateUser(id, dto);
//        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
