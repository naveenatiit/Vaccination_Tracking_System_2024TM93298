package bits.wilp.FullStackDevelopment.service;

import bits.wilp.FullStackDevelopment.dto.DashboardMetricsDTO;
import bits.wilp.FullStackDevelopment.dto.UpcomingDriveDTO;
import bits.wilp.FullStackDevelopment.entity.VaccinationDrive;
import bits.wilp.FullStackDevelopment.repository.StudentRepository;
import bits.wilp.FullStackDevelopment.repository.VaccinationDriveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final StudentRepository studentRepo;
    private final VaccinationDriveRepository driveRepo;

    public DashboardMetricsDTO  getDashboardMetrics() {
        long total = studentRepo.countTotalStudents();
        long vaccinated = studentRepo.countVaccinatedStudents();

        double percentage = (total == 0) ? 0.0 : ((double) vaccinated / total) * 100;

        List<VaccinationDrive> upcomingDrives = driveRepo.findUpcomingDrivesWithin30Days();

        List<UpcomingDriveDTO> upcomingDriveDTOs = upcomingDrives.stream()
                .map(d -> new UpcomingDriveDTO(d.getId(), d.getVaccineName(), d.getDriveDate(), d.getApplicableGrades()))
                .collect(Collectors.toList());

        return new DashboardMetricsDTO(total, vaccinated, percentage, upcomingDriveDTOs);
    }
}

