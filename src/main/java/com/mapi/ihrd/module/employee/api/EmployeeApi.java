package com.mapi.ihrd.module.employee.api;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.exception.InvalidParameterValueException;
import com.mapi.ihrd.module.absence.model.LateRequest;
import com.mapi.ihrd.module.employee.form.EmployeeForm;
import com.mapi.ihrd.module.employee.form.RequestForm;
import com.mapi.ihrd.module.employee.model.*;
import com.mapi.ihrd.module.employee.service.EmployeeService;
import com.mapi.ihrd.module.leave.model.LeaveRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeApi extends BaseController {

    @Autowired
    private EmployeeService employeeService;

    @Value("${app.path.slipgaji}")
    private String slipGajiPath;

    @Value("${app.path.pic}")
    private String picPath;

    @GetMapping
    public ResponseEntity index(@RequestParam(required = false) Integer offset,
                                @RequestParam(required = false) Integer limit) {

        PageRequest pageRequest = createPageRequest(offset, limit);


        return ResponseEntity.ok(employeeService.find(pageRequest));
    }


    @PostMapping
    public ResponseEntity add(@RequestBody @Valid EmployeeForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new InvalidParameterValueException(bindingResult.getFieldError().getDefaultMessage());
        }

        Employee employee = new Employee();

        BeanUtils.copyProperties(form, employee);

        employeeService.save(employee);

        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable String id, @RequestBody @Valid EmployeeForm form, BindingResult bindingResult) {

        Employee employee = employeeService.findById(id);

        employeeService.save(employee);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable String id) {

        Employee employee = employeeService.findById(id);

        return ResponseEntity.ok(employee);
    }

    //TODO masih dihardcode
    @GetMapping("/{id}/absence")
    public ResponseEntity absence(@PathVariable String id, @RequestParam int month, @RequestParam(required = false) Integer year) {

        Employee employee = employeeService.findById(id);

        if (employee == null) {
            throw new DataNotFoundException("Pegawai tidak terdaftar");
        }

        List<AbsenceComponent> absenceComponents = new ArrayList<>();

        Random r = new Random();

        Integer p1[] = new Integer[]{20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
        Integer p2[] = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Integer p3[] = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Integer p4[] = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Integer p5[] = new Integer[]{2, 0, 1, 0, 2, 0, 1, 1, 1, 1, 1, 1};
        Integer p6[] = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        absenceComponents.add(new AbsenceComponent("Hari Masuk", p1[month], "Hari"));
        absenceComponents.add(new AbsenceComponent("Hari Absen", p2[month], "Hari"));
        absenceComponents.add(new AbsenceComponent("Sakit", p3[month], "Hari"));
        absenceComponents.add(new AbsenceComponent("Izin", p4[month], "Hari"));
        absenceComponents.add(new AbsenceComponent("OT Hour", p5[month], "Jam"));
        absenceComponents.add(new AbsenceComponent("Hari Cuti", p6[month], "Hari"));

        return ResponseEntity.ok(absenceComponents);
    }

    //TODO masih dihardcode
    @GetMapping("/{id}/salary")
    public ResponseEntity salary(@PathVariable String id, @RequestParam int month, @RequestParam(required = false) Integer year) {

        Employee employee = employeeService.findById(id);

        if (employee == null) {
            throw new DataNotFoundException("Pegawai tidak terdaftar");
        }


        Random r = new Random();

        Integer overtimeAmounts[] = new Integer[]{0, 0, 0, 100000, 50000, 0, 0, 41000, 0, 45000, 0, 0};
        Integer basicSalarys[] = new Integer[]{1500000, 1500000, 1500000, 1500000, 1500000, 1500000, 1500000, 1500000, 1500000, 1500000, 1500000, 1500000};
        Integer jobAllowances[] = new Integer[]{250000, 250000, 250000, 250000, 50000, 250000, 250000, 250000, 250000, 250000, 250000, 250000};
        Integer transports[] = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Integer bonusList[] = new Integer[]{0, 0, 0, 0, 9, 0, 0, 9, 0, 9, 0, 0};
        Integer thrs[] = new Integer[]{0, 0, 0, 0, 0, 0, 1500000, 0, 0, 0, 0, 0};
        Integer uangMakans[] = new Integer[]{100000, 100000, 100000, 100000, 50000, 50000, 50000, 24000, 30000, 45000, 100000, 0};
        Integer uangKerajinans[] = new Integer[]{25000, 30000, 21000, 34000, 50000, 0, 0, 11000, 0, 25000, 0, 0};


        BigDecimal overtimeAmount = new BigDecimal(overtimeAmounts[month]).setScale(2);
        BigDecimal basicSalary = new BigDecimal(basicSalarys[month]).setScale(2);
        BigDecimal jobAllowance = new BigDecimal(jobAllowances[month]).setScale(2);
        BigDecimal transport = new BigDecimal(transports[month]).setScale(2);
        BigDecimal bonus = new BigDecimal(bonusList[month]).setScale(2);
        BigDecimal thr = new BigDecimal(thrs[month]).setScale(2);
        BigDecimal uangMakan = new BigDecimal(uangMakans[month]).setScale(2);
        BigDecimal uangKerajinan = new BigDecimal(uangKerajinans[month]).setScale(2);
        //BigDecimal total = overtimeAmount.add(basicSalary).add(jobAllowance).add(transport).add(bonus).add(thr).add(uangMakan).add(uangKerajinan).setScale(2);


        List<SalaryComponent> salaryComponents = new ArrayList<>();

        salaryComponents.add(new SalaryComponent("Lembur", overtimeAmount));
        salaryComponents.add(new SalaryComponent("Gaji Pokok", basicSalary));
        salaryComponents.add(new SalaryComponent("Tunjangan Jabatan", jobAllowance));
        salaryComponents.add(new SalaryComponent("Tranport", transport));
        salaryComponents.add(new SalaryComponent("Bonus", bonus));
        salaryComponents.add(new SalaryComponent("THR", thr));
        salaryComponents.add(new SalaryComponent("Uang Makan", uangMakan));
        salaryComponents.add(new SalaryComponent("Uang Kerajinan", uangKerajinan));


        return ResponseEntity.ok(salaryComponents);
    }

    //TODO masih dihardcode
    @GetMapping("/{id}/potongan")
    public ResponseEntity potongan(@PathVariable String id, @RequestParam int month, @RequestParam(required = false) Integer year) {

        Employee employee = employeeService.findById(id);

        if (employee == null) {
            throw new DataNotFoundException("Pegawai tidak terdaftar");
        }

        Random r = new Random();

        Integer p1[] = new Integer[]{50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 0, 0, 0};
        Integer p2[] = new Integer[]{70000, 70000, 70000, 70000, 70000, 70000, 70000, 70000, 70000, 70000, 70000, 70000};
        Integer p3[] = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Integer p4[] = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Integer p5[] = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Integer p6[] = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        List<Potongan> potonganList = new ArrayList<Potongan>();
        potonganList.add(new Potongan("Cicilan Pinjaman", new BigDecimal(p1[month])));
        potonganList.add(new Potongan("Potongan Jamsostek", new BigDecimal(p2[month])));
        potonganList.add(new Potongan("Potongan Absen", new BigDecimal(p3[month])));
        potonganList.add(new Potongan("Potongan Absen", new BigDecimal(p4[month])));
        potonganList.add(new Potongan("Potongan Telat", new BigDecimal(p5[month])));
        potonganList.add(new Potongan("PPH21", new BigDecimal(p6[month])));

        return ResponseEntity.ok(potonganList);
    }

    @GetMapping("/{id}/slipgaji")
    public void downloadSlipGaji(@PathVariable String id, @RequestParam int month, @RequestParam(required = false) Integer year, HttpServletResponse response) throws IOException {
        FileInputStream fis = new FileInputStream(slipGajiPath + "/" + "slipgaji.jpg");//TODO masih dihardcode
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(fis, response.getOutputStream());
    }


    /**
     * download profile picture
     *
     * @param id
     * @param response
     * @throws IOException
     */
    @GetMapping("/{id}/pic")
    public void downloadPic(@PathVariable String id, HttpServletResponse response) throws IOException {
        FileInputStream fis = new FileInputStream(picPath + "/" + "avatar.png");//TODO masih dihardcode
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(fis, response.getOutputStream());
    }

    @GetMapping("/{id}/performance")
    public ResponseEntity performance(@PathVariable String id) {

        Employee employee = employeeService.findById(id);

        if (employee == null) {
            throw new DataNotFoundException("Pegawai tidak terdaftar");
        }

        Map<String, Object> result = new HashMap<>();

        List<PerformanceComponent> performanceComponents = new ArrayList<>();

        Random random = new Random();
        performanceComponents.add(new PerformanceComponent("Kehadiran", random.nextInt(5)));
        performanceComponents.add(new PerformanceComponent("Displin", random.nextInt(5)));
        performanceComponents.add(new PerformanceComponent("Kerjasama", random.nextInt(5)));
        performanceComponents.add(new PerformanceComponent("Komitmen", random.nextInt(5)));


        Map<String, Integer> absenceMap = new HashMap<>();
        absenceMap.put("alphaCount", random.nextInt(5));
        absenceMap.put("sickCount", random.nextInt(5));
        absenceMap.put("lateCount", random.nextInt(5));

        int grade = getGrade(performanceComponents);
        String gradeName = getGradeName(grade);
        result.put("nik", employee.getNik());
        result.put("fullName", employee.getFullName());
        result.put("jobName", employee.getCurrentJob().getName());
        result.put("branchName", "Jakarta");
        result.put("absence", absenceMap);
        result.put("grade", grade);
        result.put("gradeName", gradeName);
        result.put("scores", performanceComponents);

        return ResponseEntity.ok(result);
    }

    private int getGrade(List<PerformanceComponent> performanceComponents) {
        int total = 0;
        for (PerformanceComponent p : performanceComponents) {
            total += p.getScore();
        }

        return new BigDecimal(total).divide(new BigDecimal(performanceComponents.size()), 0, RoundingMode.HALF_UP).intValue();
    }

    private String getGradeName(int grade) {
        switch (grade) {
            case 1:
                return "Verfy Poor";
            case 2:
                return "poor";
            case 3:
                return "Average";
            case 4:
                return "Good";
            case 5:
                return "Execllent";
        }


        return "";
    }

    private String getGradeName(List<PerformanceComponent> performanceComponents) {

        int grade = getGrade(performanceComponents);

        return getGradeName(grade);

    }


    @GetMapping("/{id}/request")
    public ResponseEntity request(@PathVariable String id, @RequestParam String type) {

        Employee employee = employeeService.findById(id);

        if (employee == null) {
            throw new DataNotFoundException("Pegawai tidak terdaftar");
        }

        List<Map<String, Object>> result = new ArrayList<>();

        Random random = new Random();

        Map<String, Object> map = new HashMap<>();

        if (LeaveRequest.Type.LEAVE.name().equals(type)) {
            map = new HashMap<>();
            map.put("status", LeaveRequest.Status.NEW.name());
            map.put("count", random.nextInt(5));
            result.add(map);

            map = new HashMap<>();
            map.put("status", LeaveRequest.Status.APPROVED.name());
            map.put("count", random.nextInt(5));
            result.add(map);

            map = new HashMap<>();
            map.put("status", LeaveRequest.Status.REJECTED.name());
            map.put("count", random.nextInt(5));
            result.add(map);

        } else if (LeaveRequest.Type.SICK.name().equals(type)) {

            map = new HashMap<>();
            map.put("status", LeaveRequest.Status.NEW.name());
            map.put("count", random.nextInt(5));
            result.add(map);

            map = new HashMap<>();
            map.put("status", LeaveRequest.Status.APPROVED.name());
            map.put("count", random.nextInt(5));
            result.add(map);

            map = new HashMap<>();
            map.put("status", LeaveRequest.Status.REJECTED.name());
            map.put("count", random.nextInt(5));
            result.add(map);

        } else if (LeaveRequest.Type.PERMISSION.name().equals(type)) {
            map = new HashMap<>();
            map.put("status", LeaveRequest.Status.NEW.name());
            map.put("count", random.nextInt(5));
            result.add(map);

            map = new HashMap<>();
            map.put("status", LeaveRequest.Status.APPROVED.name());
            map.put("count", random.nextInt(5));
            result.add(map);

            map = new HashMap<>();
            map.put("status", LeaveRequest.Status.REJECTED.name());
            map.put("count", random.nextInt(5));
            result.add(map);
        } else if ("OVERTIME".equalsIgnoreCase(type)) {
            map = new HashMap<>();
            map.put("status", LeaveRequest.Status.NEW.name());
            map.put("count", random.nextInt(5));
            result.add(map);

            map = new HashMap<>();
            map.put("status", LeaveRequest.Status.APPROVED.name());
            map.put("count", random.nextInt(5));
            result.add(map);

            map = new HashMap<>();
            map.put("status", LeaveRequest.Status.REJECTED.name());
            map.put("count", random.nextInt(5));
            result.add(map);
        } else if ("LATE".equalsIgnoreCase(type)) {
            map = new HashMap<>();
            map.put("status", LateRequest.Status.NEW.name());
            map.put("count", random.nextInt(5));
            result.add(map);

            map = new HashMap<>();
            map.put("status", LateRequest.Status.APPROVED.name());
            map.put("count", random.nextInt(5));
            result.add(map);

            map = new HashMap<>();
            map.put("status", LateRequest.Status.REJECTED.name());
            map.put("count", random.nextInt(5));
            result.add(map);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/request")
    public ResponseEntity request(@Valid RequestForm form) {

        Employee employee = employeeService.findById(form.getRequestBy());

        if (employee == null) {
            throw new DataNotFoundException("Pegawai tidak terdaftar");
        }


        return ResponseEntity.ok().build();
    }
}
