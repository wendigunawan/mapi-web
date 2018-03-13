package com.mapi.ihrd.module.absence.controller;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.module.absence.form.AttendanceAddForm;
import com.mapi.ihrd.module.absence.model.Attendance;
import com.mapi.ihrd.module.absence.model.AttendanceView;
import com.mapi.ihrd.module.absence.queryfilter.AttendanceQueryFilter;
import com.mapi.ihrd.module.absence.service.AttendaceService;
import com.mapi.ihrd.module.absence.service.ShiftService;
import com.mapi.ihrd.module.employee.service.EmployeeService;
import com.mapi.ihrd.response.JQueryDataTable;
import com.mapi.ihrd.response.JQueryDataTableInput;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/web/absence")
public class AbsenceController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(AbsenceController.class);

    @Autowired
    AttendaceService attendaceService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ShiftService shiftService;

    @GetMapping
    public String index() {
        return "absence/absence.list";
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable dataTable(JQueryDataTableInput input) {

        AttendanceQueryFilter filter = new AttendanceQueryFilter();
        filter.setPageRequest(createPageRequest(input.getStart(), input.getLength()));
        filter.setSearchText(input.getSearchText());

        Page<AttendanceView> page = attendaceService.findView(filter);

        return new JQueryDataTable<>(page.getContent(), page.getTotalElements(), input.getDraw());
    }

    @GetMapping("/add")
    public String add(Model model) {
        AttendanceAddForm form = new AttendanceAddForm();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int count = 0;
        while (true) {
            count++;
            cal.add(Calendar.DATE, 1);

            Attendance attendance = new Attendance();
            attendance.setDate(cal.getTime());

            form.getAttendances().add(attendance);

            if (count == 20) {
                break;
            }
        }


        model.addAttribute("attendanceAddForm", form);
        return "absence/absence.form";
    }


    @PostMapping("/add")
    public String addSave(@ModelAttribute AttendanceAddForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "absence/absence.form";
        }

        return "redirect:/web/absence";
    }

    @GetMapping("/template")
    public void getTemplate(HttpServletResponse response) {

    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {

        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();

            while (rowIterator.hasNext()) {

                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                Attendance attendance = new Attendance();

                if (row.getRowNum() > 0) {
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        DataFormatter dataFormatter = new DataFormatter();
                        switch (cell.getColumnIndex()) {
                            case 0:
                                attendance.setDate(DateTimeFormat.forPattern("d/mm/yyyy").parseDateTime(dataFormatter.formatCellValue(cell)).toDate());
                                break;
                            case 1:
                                attendance.setEmployee(employeeService.findByNik(dataFormatter.formatCellValue(cell)));
                                break;
                            case 2:
                                attendance.setShift(shiftService.getByShiftId(dataFormatter.formatCellValue(cell)));
                                break;
                            case 3:
                                attendance.setAttend(false);
                                switch (cell.getStringCellValue()) {
                                    case "Y":
                                        attendance.setAttend(true);
                                        break;
                                    case "I":
                                        attendance.setReason(Attendance.Reason.LEAVE);
                                        break;
                                    case "A":
                                        attendance.setReason(Attendance.Reason.ALPHA);
                                        break;
                                    case "S":
                                        attendance.setReason(Attendance.Reason.SICK);
                                        break;
                                }
                                break;
                        }
                    }

                    attendaceService.save(attendance);
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }

        return "redirect:/web/absence";
    }

}
