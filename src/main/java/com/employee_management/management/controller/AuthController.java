package com.employee_management.management.controller;

import com.employee_management.management.dto.EmployeeLoginRequestDto;
import com.employee_management.management.dto.LoginRequestDto;
import com.employee_management.management.dto.RegisterRequestDto;
import com.employee_management.management.utility.DateHelper;
import com.employee_management.management.utility.ResponseHelper;
import com.employee_management.management.model.Employee;
import com.employee_management.management.model.User;
import com.employee_management.management.repository.EmployeeRepository;
import com.employee_management.management.repository.UserRepository;
import com.employee_management.management.service.AuthService;
import com.employee_management.management.utility.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthService authService;



    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Ok");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto body) {
        try {
            Optional<User> user = userRepository.findByUsername(body.getUsername());

            if (user.isEmpty() || !new BCryptPasswordEncoder().matches(body.getPassword(), user.get().getPasswordHash())) {
                return ResponseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid Credentials",false,null);
            }
            String token = jwtUtil.generateToken(user.get().getUsername(), user.get().getRole(),user.get().getEmail(),user.get().getUserId());
            return ResponseHelper.createResponse(HttpStatus.OK, "Login successful", token,null);

        } catch (Exception e) {
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),false,null);
        }
    }



    @PostMapping("/login/employee")
    public ResponseEntity<?> employeeLogin(@Valid @RequestBody EmployeeLoginRequestDto body) {
        try {
            Optional<Employee> user = employeeRepository.findByEmpId(body.getUsername());
            System.out.println(user.get().getDateOfBirth());
            DateHelper commonHelpers = new DateHelper();
            String password = commonHelpers.convertDate(user.get().getDateOfBirth().toString());
            System.out.println(password);
            if ( !password.equals(body.getPassword())) {
                return ResponseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid Credentials",false,null);
            }
            String token = jwtUtil.generateToken(user.get().getName(), "EMPLOYEE",user.get().getEmail(),user.get().getEmpId());
            return ResponseHelper.createResponse(HttpStatus.OK, "Login successful", token,null);

        } catch (Exception e) {
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),false,null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterRequestDto userRequestDto){
        try{
            User registerUser = authService.singUp(userRequestDto);
            userRepository.save(registerUser);
            return ResponseHelper.createResponse(HttpStatus.OK, "Successfully registered", true,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),false,null);
        }
    }
}
