package bits.wilp.FullStackDevelopment.controller;

import bits.wilp.FullStackDevelopment.entity.VaccinationDrive;
import bits.wilp.FullStackDevelopment.service.VaccinationDriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/drives")
public class VaccinationDriveController {

    @Autowired
    private VaccinationDriveService service;

    @PostMapping
    public ResponseEntity<?> createDrive(@RequestBody VaccinationDrive drive) {
        try {
            VaccinationDrive saved = service.createDrive(drive);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDrive(@PathVariable Long id, @RequestBody VaccinationDrive drive) {
        try {
            return ResponseEntity.ok(service.updateDrive(id, drive));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllDrives() {
        return ResponseEntity.ok(service.getAllDrives());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDrive(@PathVariable Long id) {
        try {
            service.deleteDrive(id);
            return ResponseEntity.ok("Deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("no such drive exists");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDrive(@PathVariable Long id) {
        VaccinationDrive drive = service.getDrive(id);
        if(Objects.nonNull(drive))
            return ResponseEntity.ok(drive);
        return ResponseEntity.notFound().build();
    }
}

