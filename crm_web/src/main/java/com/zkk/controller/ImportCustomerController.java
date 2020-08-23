package com.zkk.controller;

import com.zkk.entity.Customer;
import com.zkk.entity.CustomerRoster;
import com.zkk.entity.CustomerSource;
import com.zkk.entity.Emp;
import com.zkk.entity.vo.CustomerAllocationVo;
import com.zkk.service.CustomerRosterService;
import com.zkk.service.CustomerService;
import com.zkk.service.EmpService;
import com.zkk.utils.CellValueUtils;
import com.zkk.utils.Pagination;
import com.zkk.utils.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/17 16:32
 */
@RestController
@RequestMapping("/customer")
public class ImportCustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmpService empService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CustomerRosterService customerRosterService;

    @PostMapping("/importCustomer")
    public Map<String, Object> importCustomer(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException, InvalidFormatException {
        //返回的消息对象
        Map<String, Object> msgMap = new HashMap<>();
        msgMap.put("status", false);
        String customerRosterName = request.getParameter("customerRosterName");
        if (StringUtils.isEmpty(customerRosterName)) {
            msgMap.put("msg", "请输入客户名单名称");
            return msgMap;
        }
        String username = request.getParameter("username");
        if (StringUtils.isEmpty(username)) {
            msgMap.put("msg", "请登录后进行导入操作");
            return msgMap;
        }
        //获取当前操作员工信息
        Emp emp = empService.getEmpByUsername(username);
        if (emp == null) {
            msgMap.put("msg", "当前登录账户不存在");
            return msgMap;
        }
        if (file.isEmpty()) {
            msgMap.put("msg", "请选择上传文件");
            return msgMap;
        }
        //通过POI读取上传表格文件，将数据新增到数据库中
        //获取上传文件的输入流
        InputStream inputStream = file.getInputStream();
        //使用POI解析上传表格文件
        Workbook workbook = WorkbookFactory.create(inputStream);
        //获取工作簿中的表
        Sheet sheet = workbook.getSheetAt(0);
        //判断文件数据是否为空
        int rowNum = sheet.getLastRowNum();
        if (rowNum <= 1) {//没有数据或只有标题行
            msgMap.put("msg", "上传的Excel文件没有数据");
            return msgMap;
        }
        //判断上传文件模板是否符合要求
        //判断是否有标题行
        Row firstRow = sheet.getRow(0);
        if (firstRow == null) {
            msgMap.put("msg", "上传的Excel文件,第一行必须有标题行");
            return msgMap;
        }
        //判断标题行是否有固定列
        short cellNum = firstRow.getLastCellNum();
        if (cellNum != 4) {
            msgMap.put("msg", "上传的Excel文件,数据列不满足要求，请核对模板添加数据");
            return msgMap;
        }
        //判断每列内容是否正确
        String cellValue1 = CellValueUtils.getStringCellValue(firstRow.getCell(0));
        String cellValue2 = CellValueUtils.getStringCellValue(firstRow.getCell(1));
        String cellValue3 = CellValueUtils.getStringCellValue(firstRow.getCell(2));
        String cellValue4 = CellValueUtils.getStringCellValue(firstRow.getCell(3));
        if (!"客户姓名".equals(cellValue1)) {
            msgMap.put("msg", "上传的Excel文件,第一行第一列必须为客户姓名");
            return msgMap;
        }
        if (!"手机号码".equals(cellValue2)) {
            msgMap.put("msg", "上传的Excel文件,第一行第二列必须为手机号码");
            return msgMap;
        }
        if (!"来源".equals(cellValue3)) {
            msgMap.put("msg", "上传的Excel文件,第一行第三列必须为来源");
            return msgMap;
        }
        if (!"备注".equals(cellValue4)) {
            msgMap.put("msg", "上传的Excel文件,第一行第四列必须为备注");
            return msgMap;
        }
        //创建客户对象集合
//        List<Customer> customers = new ArrayList<Customer>();
        //使用Map解决文件中重复的数据
        Map<String, Customer> customerMap = new HashMap<String, Customer>();
        //准备用户存储所有新增的客户电话列表，用于循环读取时进行去重
        List<String> customerPhones = new ArrayList<String>();
        //循环读取数据
        for (Row row : sheet) {
            if (row.getRowNum() > 0) {//从第二行开发读取
                Cell cell = null;
                //获取客户电话
                cell = row.getCell(1);
                String customerPhone = CellValueUtils.getStringCellValue(cell);
                //如果改行数据没有电话号码，就跳过
                if (StringUtils.isEmpty(customerPhone)) {
                    continue;
                }
                //获取客户姓名
                cell = row.getCell(0);
                String customerName = CellValueUtils.getStringCellValue(cell);
                //获取客户来源
                cell = row.getCell(2);
                String customerSource = CellValueUtils.getStringCellValue(cell);
                //获取备注
                cell = row.getCell(3);
                String otherInfo = CellValueUtils.getStringCellValue(cell);

                //组装客户对象
                Customer customer = new Customer();
                customer.setPhone(customerPhone);
                customer.setName(customerName);
                customer.setCustomerSource(new CustomerSource(Integer.parseInt(customerSource)));
                customer.setOtherInfo(otherInfo);

                //使用集合添加客户
                //需要在添加前判断是否重复
//                if(customerPhones.contains(customerPhone)){
//                    continue;
//                }
//                customers.add(customer);
                //使用Map方式添加客户
                customerMap.put(customer.getPhone(), customer);

                //添加电话到集合中
                customerPhones.add(customerPhone);
            }
        }
        System.out.println("一共循环读取" + customerMap.size() + "个客户数据");
        if (customerMap.size() == 0) {
            msgMap.put("msg", "上传的Excel文件,没有有效客户信息");
            return msgMap;
        }
        // 获取重复的客户电话集合
        List<String> repeatPhones = customerService.getRepeatPhones(customerPhones);
        System.out.println("重复的电话个数：" + repeatPhones.size());
        if (repeatPhones.size() == customerPhones.size()) {
            msgMap.put("msg", "上传的Excel文件,所有客户电话号码都已存在");
            return msgMap;
        }

        //记录添加成功的个数
        int successNum = 0;
        //记录添加失败的个数
        int errorNum = 0;

        //排除文件和数据库中重复电话的客户
        for (String repeatPhone : repeatPhones) {
            customerMap.remove(repeatPhone);
        }

        //将Map转为List集合
        List<Customer> customers = customerMap.values().stream().collect(Collectors.toList());
        System.out.println(customers.size());
        //新增名单对象
        CustomerRoster customerRoster = customerRosterService.add(new CustomerRoster(customerRosterName,new java.util.Date()));

        //获取导入类型参数
        Integer importType = Integer.parseInt(request.getParameter("importType"));

        //批量添加客户
        for (Customer customer : customers) {
            //获取电话
            String phone = customer.getPhone();

            //判断选择的导入类型
            if (importType == 2) {
                customer.setState(2);
            }
//
            // 设置客户相关默认值
            customer.setAddPersonId(emp.getId());//设置录入人员id
            customer.setAddPersonName(emp.getName());//设置录入人员名称
            customer.setAddType(1);//设置当前客户为批量导入客户
            customer.setCustomerRoster(customerRoster);//设置批量导入客户的名单名称
            customer.setCompanyId(emp.getCompany().getId());//设置客户所属公司id
            customer.setCompanyName(emp.getCompany().getName());//设置客户所属公司名称

            successNum++;//成功数量加1
        }
        //批量操作优化
        String sql = "insert into customer(attention_level, call_count,customer_level, name, other_info, phone, sign_state, state, visit_count, customer_source_id,add_person_id,add_person_name,add_type,customer_roster_id,company_id,company_name,create_date,create_time) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        //准备录入日期和时间：需要SQL的Date类型

        Date date = new Date(new java.util.Date().getTime());
        Timestamp dateTime = new Timestamp(new java.util.Date().getTime());
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                //('C',0,'中','导入客户1','备注信息','18898765432','未签单',0,0,1)
                preparedStatement.setString(1, customers.get(i).getAttentionLevel());
                preparedStatement.setInt(2, customers.get(i).getCallCount());
                preparedStatement.setString(3, customers.get(i).getCustomerLevel());
                preparedStatement.setString(4, customers.get(i).getName());
                preparedStatement.setString(5, customers.get(i).getOtherInfo());
                preparedStatement.setString(6, customers.get(i).getPhone());
                preparedStatement.setInt(7, customers.get(i).getSignState());
                preparedStatement.setInt(8, customers.get(i).getState());
                preparedStatement.setInt(9, customers.get(i).getVisitCount());
                preparedStatement.setInt(10, customers.get(i).getCustomerSource().getId());
                preparedStatement.setInt(11, customers.get(i).getAddPersonId());
                preparedStatement.setString(12, customers.get(i).getAddPersonName());
                preparedStatement.setInt(13, customers.get(i).getAddType());
                preparedStatement.setInt(14, customers.get(i).getCustomerRoster().getId());
                preparedStatement.setInt(15, customers.get(i).getCompanyId());
                preparedStatement.setString(16, customers.get(i).getCompanyName());
                preparedStatement.setDate(17, date);
                preparedStatement.setTimestamp(18, dateTime);
            }

            @Override
            public int getBatchSize() {
                return customers.size();
            }
        });

        msgMap.put("status", true);//标识上传成功
        //重复个数
        msgMap.put("repeatCount", repeatPhones.size());
        //新增成功
        msgMap.put("successCount", successNum);

        return msgMap;
    }

    @GetMapping("/getPage/{username}")
    public Pagination getPage(Integer pageSize, Integer current, @PathVariable String username) {
        Emp emp = empService.getEmpByUsername(username);
        return customerService.getPage(emp.getId(), pageSize, current);
    }

    @PostMapping("/toEmp/{companyId}/{deptId}/{empId}")
    public String toEmp(@PathVariable Integer companyId, @PathVariable Integer deptId, @PathVariable Integer empId, @RequestBody Integer[] ids) {
        customerService.updateEmp(ids, companyId, deptId, empId);
        return "ok";
    }

    @PostMapping("/toDept/{companyId}/{deptId}")
    public String toDept(@PathVariable Integer companyId, @PathVariable Integer deptId, @RequestBody Integer[] ids) {
        customerService.updateDept(ids, companyId, deptId);
        return "ok";
    }

    @PostMapping("/toEmpAll/{companyId}/{deptId}/{empId}")
    public String toEmpAll(@PathVariable Integer companyId, @PathVariable Integer deptId, @PathVariable Integer empId) {
        customerService.toEmpAll(companyId, deptId, empId);
        return "ok";
    }

    @PostMapping("/toDeptAll/{companyId}/{deptId}")
    public String toDeptAll(@PathVariable Integer companyId, @PathVariable Integer deptId) {
        customerService.toDeptAll(companyId, deptId);
        return "ok";
    }

    @GetMapping("/page/{pageSize}/{current}/{account}")
    public Pagination getNewPage(@PathVariable Integer pageSize,@PathVariable Integer current,@PathVariable String account){
        Emp emp = empService.getEmpByUsername(account);
        return customerService.getPageNew(emp.getId(),pageSize,current);
    }

    @PostMapping("/update")
    public String update(@RequestBody Customer customer){
        Integer state = 3;
        customerService.update(customer,state);
        return "ok";
    }

    @PostMapping("/setInvalid")
    public String updateToInvalid(@RequestBody Customer customer){
        Integer state = 8;
        customerService.update(customer,state);
        return "ok";
    }

    @GetMapping("/rosters/{username}")
    public List<CustomerAllocationVo> getCustomerRosterByName(@PathVariable String username){
        Emp emp = empService.getEmpByUsername(username);
        Integer empId = emp.getId();
        List<Customer> customerList = customerService.getAllCustomerByEmpId(empId);
        Set<Integer> hashSet = new HashSet<>();
        List<CustomerAllocationVo> customerAllocationVos = new ArrayList<>();
        CustomerAllocationVo customerAllocationVo = new CustomerAllocationVo();
        for (Customer customer : customerList) {
            Integer id = customer.getCustomerRoster().getId();
            hashSet.add(id);
        }
        Iterator<Integer> iterator = hashSet.iterator();
        while (iterator.hasNext()){
            Integer id = iterator.next();
            CustomerRoster customerRoster = customerRosterService.get(id);
            customerAllocationVo.setName(customerRoster.getName());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(customerRoster.getDate());
            customerAllocationVo.setDate(format);
            Integer importNum = customerService.getImportNum(id);
            customerAllocationVo.setImportNum(importNum);
            Integer notAllocatedNum = customerService.getNotAllocatedNum(id);
            customerAllocationVo.setNotAllocatedNum(notAllocatedNum);
            customerAllocationVo.setAllocatedNum(importNum-notAllocatedNum);
            customerAllocationVos.add(customerAllocationVo);
        }
        return customerAllocationVos;
    }
    @GetMapping("/validCustomer/{username}/{current}/{pageSize}")
    public Pagination getValidCustomer(@PathVariable String username,@PathVariable Integer current,@PathVariable Integer pageSize){
        Emp emp = empService.getEmpByUsername(username);
        Integer id = emp.getId();
        Integer state = 3;
        return customerService.getPage(id,pageSize,current,state);
    }
    @GetMapping("/getAdd/{username}")
    public List<Customer> getAdd(@PathVariable String username){
        Emp emp = empService.getEmpByUsername(username);
        Integer id = emp.getId();
        return customerService.getAdd(id);
    }
    @PostMapping("/addValidCustomer")
    public String addValidCustomer(@RequestBody Customer customer){
        customerService.addValidCustomer(customer);
        return "ok";
    }
    @PostMapping("/validCustomer/keep")
    public String keepCustomer(@RequestBody Integer id[]){
        customerService.updateVisibility(id);
        return "ok";
    }

}


