package bits.wilp.FullStackDevelopment.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if ("admin".equals(request.username()) && "password123".equals(request.password())) {
            return ResponseEntity.ok("logged in");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    record LoginRequest(String username, String password) {}
}

