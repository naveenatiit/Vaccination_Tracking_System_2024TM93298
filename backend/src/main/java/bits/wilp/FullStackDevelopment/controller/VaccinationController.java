package bits.wilp.FullStackDevelopment.controller;

import bits.wilp.FullStackDevelopment.dto.VaccinationMarkRequest;
import bits.wilp.FullStackDevelopment.service.VaccinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/vaccinations")
@RequiredArgsConstructor
public class VaccinationController {

    @Autowired
    private final VaccinationService vaccinationService;

    @PostMapping("/mark")
    public ResponseEntity<Void> markVaccinated(@RequestBody VaccinationMarkRequest request) {
        vaccinationService.markVaccinated(request);
        return ResponseEntity.ok().build();
    }
}

