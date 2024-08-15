package com.revature.controller;

import com.revature.model.Medicine;
import com.revature.model.enums.Status;
import com.revature.model.enums.Type;
import com.revature.service.MedicineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/medicines")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService){
        this.medicineService = medicineService;
    }

    @PostMapping
    public Medicine createMedicine(@RequestBody Medicine medicine){
        return medicineService.createMedicine(medicine);
    }

    @GetMapping
    @CrossOrigin
    public List<Medicine> getAllMedicine(){
        return medicineService.getAllMedicine();
    }

    @GetMapping("/{id}")
    public Medicine getMedicineById(@PathVariable int id){
        return medicineService.getMedicineById(id);
    }

    @GetMapping("/status/{Status}")
    public List<Status> getAllByStatus(@PathVariable("Status") String status){
        Status statusId = Status.valueOf(status.toUpperCase(Locale.ROOT));
        return medicineService.getAllByStatus(statusId);
    }

    @GetMapping("/type/{Type}")
        public List<Type> getAllByType(@PathVariable("Type") String type){
        Type typeId = Type.valueOf(type.toUpperCase(Locale.ROOT));
        return medicineService.getAllByType(typeId);

    }

    @GetMapping("/filter")
    public List<Medicine> getAllMedicationsByFirstLetter(@RequestParam(required = false) String letter, String query) {
        if (letter != null && !letter.isEmpty()) {
            if (query != null && !query.isEmpty()) {
                return medicineService.getMedicationContaining(query);
            } else {
                return medicineService.getMedicationByLetter(letter.toUpperCase());
            }
        } else {
            if(query != null && !query.isEmpty()) {
                return medicineService.getMedicationContaining(query);
            } else {
            return medicineService.getAllMedicine();
        }
    }
        }

    @PutMapping
    public Medicine updateMedicine(@RequestBody Medicine medicine){
        return medicineService.updateMedicine(medicine);
    }

    @DeleteMapping
    public void deleteMedicine(@RequestBody Medicine medicine){
        medicineService.deleteMedicine(medicine);
    }

}
