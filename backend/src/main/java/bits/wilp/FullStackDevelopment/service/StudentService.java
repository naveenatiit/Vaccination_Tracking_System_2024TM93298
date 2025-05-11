package bits.wilp.FullStackDevelopment.service;


import bits.wilp.FullStackDevelopment.dto.StudentRequest;
import bits.wilp.FullStackDevelopment.dto.StudentResponse;
import bits.wilp.FullStackDevelopment.entity.Student;
import bits.wilp.FullStackDevelopment.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentResponse addStudent(StudentRequest request) {
        Student student = (request.getId() != null)
                ? studentRepository.findById(request.getId()).orElse(new Student())
                : new Student();

        student.setName(request.getName());
        student.setGrade(request.getGrade());
        student.setParentContact(request.getParentContact());

        Student saved = studentRepository.save(student);
        return new StudentResponse(saved.getId(), saved.getName(), saved.getGrade(), saved.getParentContact());
    }

    public void bulkUploadStudents(List<StudentRequest> students) {
        List<Student> entities = students.stream()
                .map(req -> Student.builder()
                        .name(req.getName())
                        .grade(req.getGrade())
                        .parentContact(req.getParentContact())
                        .build())
                .toList();

        studentRepository.saveAll(entities);
    }

    public List<StudentResponse> searchStudents(String name, String grade, String vaccine) {
        if (name != null) {
            return studentRepository.findByNameContainingIgnoreCase(name).stream()
                    .map(this::mapToResponse).toList();
        }
        if (grade != null) {
            return studentRepository.findByGrade(grade).stream()
                    .map(this::mapToResponse).toList();
        }
        if (vaccine != null) {
            return studentRepository.findByVaccinationName(vaccine).stream()
                    .map(this::mapToResponse).toList();
        }
        return studentRepository.findAll().stream()
                .map(this::mapToResponse).toList();
    }

    private StudentResponse mapToResponse(Student student) {
        return new StudentResponse(student.getId(), student.getName(), student.getGrade(), student.getParentContact());
    }

    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(student -> new StudentResponse(student.getId(), student.getName(), student.getGrade(), student.getParentContact()))
                .collect(Collectors.toList());
    }
}


