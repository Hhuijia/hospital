package com.myHospital.hospital.controller;

import com.myHospital.hospital.entity.*;
import com.myHospital.hospital.service.*;
import com.myHospital.hospital.util.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.myHospital.hospital.util.GetFeatureDayUtil.getFeatureDate;

/**
 * @author QUEENEY
 * @date 2019/3/9
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    private ExcelUtil excelUtil = new ExcelUtil();

    @Autowired
    private CommonService commonService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private MedicineDepartmentService medicineDepartmentService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private DoctorService doctorService;

    @SuppressWarnings("unchecked")
    @GetMapping("/doctorManage")
    public ModelAndView doctorManage(){
        log.info("********管理员界面/医生管理*********");
        List<Doctors> doctors = commonService.findAll("doctor");
        List<String> departmentName= medicineDepartmentService.findAllDepartmentName();
        List<Role> roles = rolePermissionService.findAllRole();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","医生管理");
        modelAndView.addObject("doctors",doctors);log.info("[{}]",doctors);
        modelAndView.addObject("departmentName",departmentName);log.info("[{}]",departmentName);
        modelAndView.addObject("roles",roles);log.info("[{}]",roles);
        modelAndView.addObject("users",new Users());
        modelAndView.setViewName("admin/doctors");
        return modelAndView;
    }
    @SuppressWarnings("unchecked")
    @PostMapping("/addDoctor")
    public ModelAndView addDoctor(@ModelAttribute Users users, @RequestParam List<String> roleIds, @RequestParam String departmentName,
                                  @RequestParam String doctorTitle, @RequestParam String doctorProfession,
                                  @RequestParam int doctorMedicalServiceLife, @RequestParam String doctorIntroduction){
        log.info("********添加医生*********");
        Doctors doctors = new Doctors();
        doctors.setDoctorName(users.getUserName());
        doctors.setDoctorTitle(doctorTitle);
        doctors.setDoctorProfession(doctorProfession);
        doctors.setDoctorMedicalServiceLife(doctorMedicalServiceLife);
        doctors.setDoctorIntroduction(doctorIntroduction);
        doctors.setDepartmentName(departmentName);
        commonService.add(users,doctors,roleIds,"doctor");
        return new ModelAndView("redirect:doctorManage");
    }
    @GetMapping("/delDoctor")
    public ModelAndView delDoctor(@RequestParam String doctorId){
        log.info("********删除医生*********");
        commonService.delete(doctorId,"doctor");
        return new ModelAndView("redirect:doctorManage");
    }
    @PostMapping("/editDoctor")
    @ResponseBody
    public Doctors editDoctor(@RequestParam String doctorId){
        log.info("********编辑医生*********");
        Doctors doctors = doctorService.findDoctorById(doctorId);
        doctors.setUsers(usersService.findUserByID(doctors.getUserId()));
        return doctors;
    }
    @PostMapping("/importDoctor")
    public ModelAndView importDoctor(@RequestParam MultipartFile file) throws Exception{
        log.info("********excel批量上传医生信息*********");
        String fileName = file.getOriginalFilename();
        commonService.batchImport(fileName, file, "doctor");
        return new ModelAndView(("redirect:doctorManage"));
    }


    @SuppressWarnings("unchecked")
    @GetMapping("/adminManage")
    public ModelAndView adminManage(){
        log.info("********管理员界面/管理员管理*********");
        List<Admins> admins = commonService.findAll("admin");
        List<Role> roles = rolePermissionService.findAllRole();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","管理员管理");
        modelAndView.addObject("admins",admins);log.info("[{}]",admins);
        modelAndView.addObject("roles",roles);log.info("[{}]",roles);
        modelAndView.addObject("users",new Users());
        modelAndView.setViewName("admin/admins");
        return modelAndView;
    }
    @SuppressWarnings("unchecked")
    @PostMapping("/addAdmin")
    public ModelAndView addAdmin(@ModelAttribute Users users, @RequestParam List<String> roleIds, @RequestParam String adminTitle){
        log.info("********添加管理员*********");
        Admins admins = new Admins();
        admins.setAdminName(users.getUserName());
        admins.setAdminTitle(adminTitle);
        commonService.add(users,admins,roleIds,"admin");
        return new ModelAndView("redirect:adminManage");
    }
    @GetMapping("/delAdmin")
    public ModelAndView delAdmin(@RequestParam String adminId){
        log.info("********删除管理员*********");
        commonService.delete(adminId,"admin");
        return new ModelAndView("redirect:adminManage");
    }
    @GetMapping("/editAdmin")
    public ModelAndView editAdmin(@RequestParam String adminId){
        log.info("********编辑管理员*********");
        return new ModelAndView("redirect:adminManage");
    }
    @PostMapping("/importAdmin")
    public ModelAndView importAdmin(@RequestParam MultipartFile file) throws Exception{
        log.info("********excel批量上传管理员信息*********");
        String fileName = file.getOriginalFilename();
        commonService.batchImport(fileName, file,"admin");
        return new ModelAndView(("redirect:adminManage"));
    }


    @SuppressWarnings("unchecked")
    @GetMapping("/nurseManage")
    public ModelAndView nurseManage(){
        log.info("********管理员界面/护士管理*********");
        List<Nurses> nurses = commonService.findAll("nurse");
        List<Role> roles = rolePermissionService.findAllRole();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","护士管理");
        modelAndView.addObject("nurses",nurses);log.info("[{}]",nurses);
        modelAndView.addObject("roles",roles);log.info("[{}]",roles);
        modelAndView.addObject("users",new Users());
        modelAndView.setViewName("admin/nurses");
        return modelAndView;
    }
    @SuppressWarnings("unchecked")
    @PostMapping("/addNurse")
    public ModelAndView addNurse(@ModelAttribute Users users, @RequestParam List<String> roleIds,
                                 @RequestParam String nurseTitle, @RequestParam String nursePlace){
        log.info("********添加护士*********");
        Nurses nurses = new Nurses();
        nurses.setNurseName(users.getUserName());
        nurses.setNurseTitle(nurseTitle);
        nurses.setNursePlace(nursePlace);
        commonService.add(users,nurses,roleIds,"nurse");
        return new ModelAndView("redirect:nurseManage");
    }
    @GetMapping("/delNurse")
    public ModelAndView delNurse(@RequestParam String nurseId){
        log.info("********删除护士*********");
        commonService.delete(nurseId,"nurse");
        return new ModelAndView("redirect:nurseManage");
    }
    @GetMapping("/editNurse")
    public ModelAndView editNurse(@RequestParam String nurseId){
        log.info("********编辑护士*********");
        return new ModelAndView("redirect:nurseManage");
    }
    @PostMapping("/importNurse")
    public ModelAndView importNurse(@RequestParam MultipartFile file) throws Exception{
        log.info("********excel批量上传护士信息*********");
        String fileName = file.getOriginalFilename();
        commonService.batchImport(fileName, file, "nurse");
        return new ModelAndView(("redirect:nurseManage"));
    }


    @GetMapping("/userManage")
    public ModelAndView userManage(){
        log.info("********管理员界面/用户管理*********");
        List<Users> users = usersService.checkAllUser();
        List<Role> roles = rolePermissionService.findAllRole();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","用户管理");
        modelAndView.addObject("users",users);
        log.info("[{}]",users);
        modelAndView.addObject("roles",roles);
        log.info("[{}]",roles);
        modelAndView.setViewName("admin/users");
        return modelAndView;
    }


    @GetMapping("/rolePermissionManage")
    public ModelAndView rolePermissionManage(){
        log.info("********管理员界面/角色权限管理*********");
        ModelAndView modelAndView = new ModelAndView();
        List<Permission> permissions = rolePermissionService.findAllPermission();
        List<Role> roles = rolePermissionService.findAllRole();
        modelAndView.addObject("title","角色权限管理");
        modelAndView.addObject("permissions",permissions);
        log.info("[{}]",permissions);
        modelAndView.addObject("roles",roles);
        log.info("[{}]",roles);
        modelAndView.setViewName("admin/rolePermission");
        return modelAndView;
    }
    @PostMapping("/addRole")
    public ModelAndView addRole(@RequestParam String roleName, @RequestParam List<String> permissionIds){
        log.info("********管理员界面/角色管理*********");
        Role role = new Role();
        role.setRoleName(roleName);
        rolePermissionService.addRole(role,permissionIds);
        return new ModelAndView("redirect:rolePermissionManage");
    }
    @PostMapping("/addPermission")
    public ModelAndView addPermission(@RequestParam String permissionName){
        log.info("********管理员界面/角色管理*********");
        Permission permission = new Permission();
        permission.setPermissionName(permissionName);
        rolePermissionService.addPermission(permission);
        return new ModelAndView("redirect:rolePermissionManage");
    }
    @GetMapping("/delRole")
    public ModelAndView delRole(@RequestParam String roleId){
        log.info("**************删除角色*************");
        rolePermissionService.delRole(roleId);
        return new ModelAndView("redirect:rolePermissionManage");
    }
    @GetMapping("/delPermission")
    public ModelAndView delPermission(@RequestParam String permissionId){
        log.info("**************删除权限*************");
        rolePermissionService.delPermission(permissionId);
        return new ModelAndView("redirect:rolePermissionManage");
    }


    @GetMapping("/medicineManage")
    public ModelAndView medicineManage(){
        log.info("********管理员界面/药品管理*********");
        List<Medicine> medicines = medicineDepartmentService.findAllMedicine();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","药品管理");
        modelAndView.addObject("medicines",medicines);
        log.info("[{}]",medicines);
        modelAndView.addObject("medicine",new Medicine());
        modelAndView.setViewName("admin/medicine");
        return modelAndView;
    }
    @PostMapping("/addMedicine")
    public ModelAndView addMedicine(@ModelAttribute Medicine medicine){
        log.info("********添加药品*********");
        medicineDepartmentService.addMedicine(medicine);
        return new ModelAndView("redirect:medicineManage");
    }
    @GetMapping("/delMedicine")
    public ModelAndView delMedicine(@RequestParam String medicineId){
        log.info("********删除药品*********");
        medicineDepartmentService.deleteMedicineById(medicineId);
        return new ModelAndView("redirect:medicineManage");
    }
    @GetMapping("/editMedicine")
    public ModelAndView editMedicine(@RequestParam String medicineId){
        log.info("********编辑药品*********");
        return new ModelAndView("redirect:medicineManage");
    }
    @PostMapping("/importMedicine")
    public ModelAndView importMedicine(@RequestParam MultipartFile file) throws Exception{
        log.info("********excel批量上传药品信息*********");
        String fileName = file.getOriginalFilename();
        medicineDepartmentService.batchImportMedicine(fileName, file);
        return new ModelAndView(("redirect:medicineManage"));
    }


    @GetMapping("/departmentManage")
    public ModelAndView departmentManage(){
        log.info("********管理员界面/科室管理*********");
        List<Department> departments = medicineDepartmentService.findAllDepartment();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","科室管理");
        modelAndView.addObject("departments",departments);
        log.info("[{}]",departments);
        modelAndView.addObject("department",new Department());
        modelAndView.setViewName("admin/department");
        return modelAndView;
    }
    @PostMapping("/addDepartment")
    public ModelAndView addDepartment(@ModelAttribute Department department){
        log.info("********添加科室*********");
        medicineDepartmentService.addDepartment(department);
        return new ModelAndView("redirect:departmentManage");
    }
    @GetMapping("/delDepartment")
    public ModelAndView delDepartment(@RequestParam String departmentId){
        log.info("********删除科室*********");
        medicineDepartmentService.deleteDepartmentById(departmentId);
        return new ModelAndView("redirect:departmentManage");
    }
    @GetMapping("/editDepartment")
    public ModelAndView editDepartment(@RequestParam String departmentId){
        log.info("********编辑科室*********");
        return new ModelAndView("redirect:departmentManage");
    }
    @PostMapping("/importDepartment")
    public ModelAndView importDepartment(@RequestParam MultipartFile file) throws Exception{
        log.info("********excel批量上传科室信息*********");
        String fileName = file.getOriginalFilename();
        medicineDepartmentService.batchImportDepartment(fileName, file);
        return new ModelAndView(("redirect:departmentManage"));
    }

    @GetMapping("/recordManage")
    public ModelAndView recordManage(){
        log.info("********记录管理*********");
        List<Appointment> appointments = recordService.findAllAppointment();
        List<Pay> pays = recordService.findAllPay();
        List<GetMedicine> getMedicines = recordService.findAllGetMedicine();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","记录管理");
        modelAndView.addObject("appointments",appointments);
        log.info("[{}]",appointments);
        modelAndView.addObject("pays",pays);
        log.info("[{}]",pays);
        modelAndView.addObject("getMedicines",getMedicines);
        log.info("[{}]",getMedicines);
        modelAndView.setViewName("admin/record");
        return modelAndView;
    }
    @SuppressWarnings("unchecked")
    @GetMapping("/exportAppointment")
    public void exportAppointment(HttpServletResponse response) throws IOException {
        log.info("********excel导出科室信息*********");
        List<Appointment> appointments = recordService.findAllAppointment();
        List<String> columnNames = recordService.findColumnName("appointment");
        excelUtil.export(response,appointments,columnNames,"appointment");
    }
    @SuppressWarnings("unchecked")
    @GetMapping("/exportPay")
    public void exportPay(HttpServletResponse response) throws IOException {
        log.info("********excel导出缴费信息*********");
        List<Pay> pays = recordService.findAllPay();
        List<String> columnNames = recordService.findColumnName("paym");
        excelUtil.export(response,pays,columnNames,"pay");
    }
    @SuppressWarnings("unchecked")
    @GetMapping("/exportGetMedicine")
    public void exportGetMedicine(HttpServletResponse response) throws IOException {
        log.info("********excel导出取药信息*********");
        List<GetMedicine> getMedicines = recordService.findAllGetMedicine();
        List<String> columnNames = recordService.findColumnName("getMedicine");
        excelUtil.export(response,getMedicines,columnNames,"getMedicine");
    }

    @GetMapping("/doctorScheduleManage")
    public ModelAndView doctorScheduleManage(){
        log.info("********管理员界面/医生排班管理*********");
        List<Department> departments = medicineDepartmentService.findAllDepartment();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("departments",departments);
        ArrayList<String> featureDaysList = new ArrayList<>();
        for (int i = 0; i <7; i++) {
            featureDaysList.add(getFeatureDate(i));
        }
        modelAndView.addObject("featureDaysList",featureDaysList);
        modelAndView.setViewName("common/schedule");
        return modelAndView;
    }

    @PostMapping("/importSchedule")
    public ModelAndView importSchedule(@RequestParam MultipartFile file) throws Exception {
        log.info("********管理员界面/上传排版文件*********");
        String fileName = file.getOriginalFilename();
        scheduleService.batchImportSchedule(fileName, file);
        return new ModelAndView(("redirect:doctorScheduleManage"));
    }

    @PostMapping("/findDoctorNum")
    @ResponseBody
    public List<Doctors> findDoctorNum(@RequestParam String departmentName){
        log.info("********管理员界面/findDoctorNum*********");
        return doctorService.findDoctorInSameDepartment(departmentName);
    }

    @PostMapping("/findSchedule")
    @ResponseBody
    public List<Schedule> findSchedule(@RequestParam String departmentName) throws Exception{
        log.info("********管理员界面/findSchedule*********");
        ArrayList<String> featureDaysList = new ArrayList<>();
        for (int i = -1; i <6; i++) {
            featureDaysList.add(getFeatureDate(i).substring(0,8));
        }
        log.info("[{}]",featureDaysList);
        List<Schedule> schedules = new ArrayList<>();
        for (String featureDay : featureDaysList){
            schedules.addAll(scheduleService.findScheduleCurrentDay(featureDay,departmentName));
            log.info("[{}]",schedules);
        }
//        Date date;
//        Calendar calendar = new GregorianCalendar();
//        for (Schedule schedule : schedules){
//            calendar.setTime(schedule.getWorkDate());
//            calendar.add(calendar.DATE,1);
//            date = calendar.getTime();
//            schedule.setWorkDate(new java.sql.Date(date.getTime()));
//        }
        return schedules;
    }
}
