package bits.wilp.FullStackDevelopment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DashboardMetricsDTO {
    private long totalStudents;
    private long vaccinatedStudents;
    private double vaccinationPercentage;
    private List<UpcomingDriveDTO> upcomingDrives;
}

