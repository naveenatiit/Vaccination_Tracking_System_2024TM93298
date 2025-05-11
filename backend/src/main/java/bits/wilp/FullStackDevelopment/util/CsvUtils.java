package bits.wilp.FullStackDevelopment.util;

import bits.wilp.FullStackDevelopment.dto.StudentRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {

    public static List<StudentRequest> parseCsv(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<StudentRequest> students = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 3) {
                StudentRequest student = new StudentRequest();
                student.setName(parts[0].trim());
                student.setGrade(parts[1].trim());
                student.setParentContact(parts[2].trim());
                students.add(student);
            }
        }
        return students;
    }
}
