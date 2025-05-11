package bits.wilp.FullStackDevelopment.controller;

import bits.wilp.FullStackDevelopment.dto.StudentRequest;
import bits.wilp.FullStackDevelopment.dto.StudentResponse;
import bits.wilp.FullStackDevelopment.service.StudentService;
import bits.wilp.FullStackDevelopment.util.CsvUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    @Autowired
    private final StudentService studentService;


    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping("/add")
    public ResponseEntity<StudentResponse> addStudent(@RequestBody StudentRequest request) {
        return ResponseEntity.ok(studentService.addStudent(request));
    }

    @PostMapping("/bulkUpload")
    public ResponseEntity<Void> bulkUpload(@RequestParam("file") MultipartFile file) throws IOException {
        List<StudentRequest> students = CsvUtils.parseCsv(file.getInputStream());
        studentService.bulkUploadStudents(students);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentResponse>> searchStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String grade,
            @RequestParam(required = false) String vaccine
    ) {
        return ResponseEntity.ok(studentService.searchStudents(name, grade, vaccine));
    }
}


