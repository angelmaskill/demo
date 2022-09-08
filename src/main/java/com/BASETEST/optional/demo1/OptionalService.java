package com.basetest.optional.demo1;

import com.alipay.api.domain.FileInfo;
import org.junit.Test;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author ：yanlu.myl
 * @date ：Created in 2022-9-7 9:53
 * @description：
 * @modified By：
 * @version:
 */
public class OptionalService {

    /**
     * 演示创建Optional的方法：
     * empty()：	构造一个无任何实际对象值的空Optional对象（可以理解为业务层面的null）
     * of(T t)：	根据给定的对象，构造一个此对象的封装Optional对象，注意入参t不能为null，否则会空指针
     * ofNullable(T t)：	根据传入的入参t的值构造Optional封装对象，如果传入的t为null，则等同于调用empty()方法，如果t不为null，则等同于调用of(T t)方法
     */
    @Test
    public void testCreateOptional() {
        // 使用Optional.of构造出具体对象的封装Optional对象
        System.out.println(Optional.of(new Content("111", "JiaGouWuDao")));
        // 使用Optional.empty构造一个不代表任何对象的空Optional值
        System.out.println(Optional.empty());
        System.out.println(Optional.ofNullable(null));
        System.out.println(Optional.ofNullable(new Content("222", "JiaGouWuDao22")));
    }

    /**
     * 演示Optional.of方法如果传null会抛异常
     */
    public void testCreateOptional2() {
        // 使用Optional.of方法时，需要确保入参不为null，否则会空指针
        System.out.println("-----下面会有空指针----");
        try {
            System.out.println(Optional.of(null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("------上面会有空指针------");
    }

    /**
     * 演示Optional的错误用法
     */
    @Test
    public void testCallOptional() {
        Optional<Content> optional = getContent();
        System.out.println("-------下面代码会报异常--------");
        try {
            // 【错误用法】直接从Optional对象中get()实际参数，这种效果与返回null对象然后直接调用是一样的效果
            Content content = optional.get();
            System.out.println(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("-------上面代码会报异常--------");
    }

    /**
     * 演示Optional的不太优雅的用法
     * 这样跟直接返回null然后使用前判空（下面的写法）其实也没啥区别，也并不会让调用方使用起来更加的优雅与靠谱：
     */
    public void testCallOptional2() {
        Optional<Content> optional = getContent();
        // 使用前先判断下元素是否存在
        if (optional.isPresent()) {
            Content content = optional.get();
            System.out.println(content);
        }
    }

    private Optional<Content> getContent() {
        return Optional.ofNullable(null);
    }

    public void testNullReturn() {
        Content content = getContent2();
        System.out.println("-------下面代码会报异常--------");
        try {
            // 【错误用法】调用前没有判空
            String contentValue = content.getValue();
            System.out.println(contentValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("-------上面代码会报异常--------");
    }

    public void testNullReturn2() {
        Content content = getContent2();
        if (content != null) {
            System.out.println(content.getValue());
        }
    }

    private Content getContent2() {
        return null;
    }

    /**
     * 演示多级调用的时候可能的空指针问题
     */
    @Test
    public void getCompanyFromEmployee() {
        Employee employee = getEmployee();
        Company company = employee.getTeam().getDepartment().getCompany();
        System.out.println(company);
    }

    /**
     * 演示多级调用的时候空指针异常保护
     */
    @Test
    public void getCompanyFromEmployee2() {
        Employee employee = getEmployee();
        if (employee == null) {
            // do something here...
            return;
        }
        Team team = employee.getTeam();
        if (team == null) {
            // do something here...
            return;
        }

        Department department = team.getDepartment();
        if (department == null) {
            // do something here...
            return;
        }
        Company company = department.getCompany();
        System.out.println(company);
    }

    private Employee getEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeName("JiaGouWuDao");
        employee.setTeam(new Team("DevTeam4"));
        return employee;
    }

    @Test
    public void testMapAndFlatMap() {
        Optional<User> userOptional = getUser();
        Optional<Employee> employeeOptional = userOptional.map(user -> {
            Employee employee = new Employee();
            employee.setEmployeeName(user.getUserName());
            // map与flatMap的区别点：此处return的是具体对象类型
            return employee;
        });
        System.out.println(employeeOptional);

        Optional<Employee> employeeOptional2 = userOptional.flatMap(user -> {
            Employee employee = new Employee();
            employee.setEmployeeName(user.getUserName());
            // map与flatMap的区别点：此处return的是具体对象的Optional封装类型
            return Optional.of(employee);
        });
        System.out.println(employeeOptional2);
    }

    private Optional<User> getUser() {
        User user = new User();
        user.setId("111");
        user.setUserName("JiaGouWuDao");
        return Optional.of(user);
    }

    /**
     * 先通过map的方式一层一层的去进行类型转换，最后使用orElse去获取Optional中最终处理后的值，并给定了数据缺失场景的默认值。
     * 需要通过某个比较长的调用链路一层一层去调用获取某个值的时候，使用上述方法，可以避免空指针以及减少冗长的判断逻辑。
     *
     * orElse	    与get方法类似，都是获取Optional实际的对象值，区别在于orElse必须传入一个默认值，当Optional没有实际值的时候返回默认值而非抛异常
     * orElseGet	可以理解为orElse方法的升级版，区别在于orElse仅允许传入一个固定的默认值，而orElseGet的入参是一个函数方法，当Optional无实际值时，会执行给定的入参函数，返回动态值。
     */
    @Test
    public void getCompanyFromEmployeeTest() {
        Employee employeeDetail = getEmployee();
        String companyName = Optional.ofNullable(employeeDetail)
                .map(employee -> employee.getTeam())
                .map(team -> team.getDepartment())
                .map(department -> department.getCompany())
                .map(company -> company.getCompanyName())
                .orElse("No Company");
        System.out.println(companyName);
    }

    /**
     * 传统方式，做判空处理
     *
     * @param request
     * @return
     */
    public String getClientIp(HttpServletRequest request) {
        String clientIp = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isEmpty(clientIp)) {
            return clientIp;
        }
        clientIp = request.getHeader("X-Real-IP");
        return clientIp;
    }

    /**
     * 优化方式：不用做判空
     * 优先执行某个操作尝试获取数据，如果没获取到则去执行另一逻辑获取，或者返回默认值的场景。
     *
     * @param request
     * @return
     */
    public String getClientIp2(HttpServletRequest request) {
        String clientIp = request.getHeader("X-Forwarded-For");
        return Optional.ofNullable(clientIp).orElseGet(() -> request.getHeader("X-Real-IP"));
    }


    public Team getTeamInfo() throws Exception {
        Employee employee = getEmployee();
        Team team = employee.getTeam();
        if (team == null) {
            throw new Exception("team is missing");
        }
        return team;
    }

    public static void main(String[] args) {
        OptionalService service = new OptionalService();
        service.testCreateOptional();
        service.testCreateOptional2();
        service.testCallOptional();
        service.testCallOptional2();
        service.testNullReturn();
        service.testNullReturn2();
        service.testMapAndFlatMap();
        service.getCompanyFromEmployeeTest();
    }
}