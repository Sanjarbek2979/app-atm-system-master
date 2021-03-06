package pdp.uz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pdp.uz.payload.ATMDto;
import pdp.uz.payload.ApiResponse;
import pdp.uz.payload.FillDto;
import pdp.uz.payload.LimitDto;
import pdp.uz.service.ATMService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/atm")
@RequiredArgsConstructor
public class ATMController {

    private final ATMService atmService;

    @GetMapping("/report/list/{id}")
    @PreAuthorize("hasRole('ROLE_DIRECTOR')")
    public ResponseEntity<?> getIntervalReport(@PathVariable Long id, @RequestParam String from, @RequestParam String to) {
        ApiResponse apiResponse = atmService.getIntervalReport(from, to, id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 401).body(apiResponse);
    }

    @GetMapping("/report/today/input/{id}")
    @PreAuthorize("hasRole('ROLE_DIRECTOR')")
    public ResponseEntity<?> getIntervalInputReport(@PathVariable Long id) {
        ApiResponse apiResponse = atmService.getIntervalInputReport(id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 401).body(apiResponse);
    }

    @GetMapping("/report/today/output/{id}")
    @PreAuthorize("hasRole('ROLE_DIRECTOR')")
    public ResponseEntity<?> getIntervalOutputReport(@PathVariable Long id) {
        ApiResponse apiResponse = atmService.getIntervalOutputReport(id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 401).body(apiResponse);
    }

    @GetMapping("/report/pennies/{id}")
    @PreAuthorize("hasRole('ROLE_DIRECTOR')")
    public ResponseEntity<?> getPenniesReport(@PathVariable Long id) {
        ApiResponse apiResponse = atmService.getPenniesReport(id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 400).body(apiResponse);
    }

    @GetMapping("/report/fill/{id}")
    @PreAuthorize("hasRole('ROLE_DIRECTOR')")
    public ResponseEntity<?> getFillReport(@PathVariable Long id) {
        ApiResponse apiResponse = atmService.getFillReport(id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 401).body(apiResponse);
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ROLE_DIRECTOR')")
    public ResponseEntity<?> register(@Valid @RequestBody ATMDto dto) {
        ApiResponse apiResponse = atmService.register(dto);
        return ResponseEntity.status(apiResponse.isStatus() ? 201 : 400).body(apiResponse);
    }

    @PostMapping("/fill")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<?> fill(@Valid @RequestBody FillDto dto) {
        ApiResponse apiResponse = atmService.fill(dto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 400).body(apiResponse);
    }

    @PostMapping("/set/max/limit/all")
    @PreAuthorize("hasRole('ROLE_DIRECTOR')")
    public ResponseEntity<?> setMaxLimit(@Valid @RequestBody LimitDto dto) {
        ApiResponse apiResponse = atmService.setMaxLimit(dto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 401).body(apiResponse);
    }

    @PostMapping("/set/min/limit/all")
    @PreAuthorize("hasRole('ROLE_DIRECTOR')")
    public ResponseEntity<?> setMinLimit(@Valid @RequestBody LimitDto dto) {
        ApiResponse apiResponse = atmService.setMinLimit(dto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 401).body(apiResponse);
    }

    @PostMapping("/set/max/limit/{id}")
    @PreAuthorize("hasRole('ROLE_DIRECTOR')")
    public ResponseEntity<?> setMaxLimit(@PathVariable Long id, @Valid @RequestBody LimitDto dto) {
        ApiResponse apiResponse = atmService.setMaxLimit(id, dto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 401).body(apiResponse);
    }

    @PostMapping("/set/min/limit/{id}")
    @PreAuthorize("hasRole('ROLE_DIRECTOR')")
    public ResponseEntity<?> setMinLimit(@PathVariable Long id, @Valid @RequestBody LimitDto dto) {
        ApiResponse apiResponse = atmService.setMinLimit(id, dto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 401).body(apiResponse);
    }
}
