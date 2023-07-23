package com.cydeo.streampractice.practice;

import com.cydeo.streampractice.model.*;
import com.cydeo.streampractice.service.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class Practice {

    public static CountryService countryService;
    public static DepartmentService departmentService;
    public static EmployeeService employeeService;
    public static JobHistoryService jobHistoryService;
    public static JobService jobService;
    public static LocationService locationService;
    public static RegionService regionService;

    public Practice(CountryService countryService, DepartmentService departmentService,
                    EmployeeService employeeService, JobHistoryService jobHistoryService,
                    JobService jobService, LocationService locationService,
                    RegionService regionService) {

        Practice.countryService = countryService;
        Practice.departmentService = departmentService;
        Practice.employeeService = employeeService;
        Practice.jobHistoryService = jobHistoryService;
        Practice.jobService = jobService;
        Practice.locationService = locationService;
        Practice.regionService = regionService;

    }

    // You can use the services above for all the CRUD (create, read, update, delete) operations.
    // Above services have all the required methods.
    // Also, you can check all the methods in the ServiceImpl classes inside the service.impl package, they all have explanations.

    // 1. Display all the employees
    public static List<Employee> getAllEmployees() {
        return employeeService.readAll();
    }

    // 2. Display all the countries
    public static List<Country> getAllCountries() {
        //TODO Implement the method
        return countryService.readAll();
    }

    // 3. Display all the departments
    public static List<Department> getAllDepartments() {
        //TODO Implement the method
        return departmentService.readAll();
    }

    // 4. Display all the jobs
    public static List<Job> getAllJobs() {
        //TODO Implement the method
        return jobService.readAll();
    }

    // 5. Display all the locations
    public static List<Location> getAllLocations() {
        //TODO Implement the method
        return locationService.readAll();
    }

    // 6. Display all the regions
    public static List<Region> getAllRegions() {
        //TODO Implement the method
        return regionService.readAll();
    }

    // 7. Display all the job histories
    public static List<JobHistory> getAllJobHistories() {
        //TODO Implement the method
        return jobHistoryService.readAll();
    }

    // 8. Display all the employees' first names
    public static List<String> getAllEmployeesFirstName() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .map(Employee::getFirstName) // .map(employee -> employee.getFirstName()) map: We are giving Stream<List> and taking Stream<String>
                .collect(Collectors.toList());
    }

    // 9. Display all the countries' names
    public static List<String> getAllCountryNames() {
        //TODO Implement the method
        return countryService.readAll().stream()
                .map(Country::getCountryName)
                .collect(Collectors.toList());
    }

    // 10. Display all the departments' managers' first names
    public static List<String> getAllDepartmentManagerFirstNames() {
        //TODO Implement the method
/*        return departmentService.readAll().stream()
                .map(Department::getManager)
                //.map(department -> department.getManager())
                .map(Employee::getFirstName)
                .collect(Collectors.toList());*/

        return departmentService.readAll().stream()
                .map(department -> department.getManager().getFirstName())
                .collect(Collectors.toList());

    }

    // 11. Display all the departments where manager name of the department is 'Steven'
    public static List<Department> getAllDepartmentsWhichManagerFirstNameIsSteven() {
        //TODO Implement the method
        return departmentService.readAll().stream()
                .filter(department -> department.getManager().getFirstName().equalsIgnoreCase("steven"))
                //We have Stream<Department> object now
                .collect(Collectors.toList());

    }

    // 12. Display all the departments where postal code of the location of the department is '98199'
    public static List<Department> getAllDepartmentsWhereLocationPostalCodeIs98199() {
        //TODO Implement the method
        return departmentService.readAll().stream()
                .filter(department -> department.getLocation().getPostalCode().equals("98199"))
                //We have Stream<Department> object now
                .collect(Collectors.toList());
    }

    // 13. Display the region of the IT department
    public static Region getRegionOfITDepartment() throws Exception {
        //TODO Implement the method
        return departmentService.readAll().stream()
                .filter(department -> department.getDepartmentName().equals("IT"))
//                .findFirst().get().getLocation().getCountry().getRegion();
                .findFirst().orElseThrow().getLocation().getCountry().getRegion();

//      return departmentService.readAll().stream()
//                .filter(department -> department.getDepartmentName().equals("asddad"))
//                .findFirst().orElseThrow(()-> new Exception("No department found"))
//                .getLocation().getCountry().getRegion();
    }

    // 14. Display all the departments where the region of department is 'Europe'
    public static List<Department> getAllDepartmentsWhereRegionOfCountryIsEurope() {
        //TODO Implement the method
        return departmentService.readAll().stream()
                .filter(department -> department.getLocation().getCountry().getRegion().getRegionName().equals("Europe"))
                .collect(Collectors.toList());
    }

    // 15. Display if there is any employee with salary less than 1000. If there is none, the method should return true
    public static boolean checkIfThereIsNoSalaryLessThan1000() {
        //TODO Implement the method
//        return employeeService.readAll().stream()
//                .allMatch(employee -> employee.getSalary()>1000);

        return employeeService.readAll().stream()
                .noneMatch(employee -> employee.getSalary() < 1000);

//        return !employeeService.readAll().stream()
//                .anyMatch(employee -> employee.getSalary()<1000);


    }

    // 16. Check if the salaries of all the employees in IT department are greater than 2000 (departmentName: IT)
    public static boolean checkIfThereIsAnySalaryGreaterThan2000InITDepartment() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(employee -> employee.getDepartment().getDepartmentName().equals("IT"))
                .allMatch(employee -> employee.getSalary() > 2000);

//                .noneMatch(employee -> employee.getSalary()<2000);

//        return !employeeService.readAll().stream()
//                .anyMatch(employee -> employee.getSalary()<2000);
    }

    // 17. Display all the employees whose salary is less than 5000
    public static List<Employee> getAllEmployeesWithLessSalaryThan5000() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary() < 5000) //We are not using any none or all match, because it is not saying "check if .." it is saying "display"
                .collect(Collectors.toList());
    }

    // 18. Display all the employees whose salary is between 6000 and 7000
    public static List<Employee> getAllEmployeesSalaryBetween() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary() > 6000 && employee.getSalary() < 7000)
                .collect(Collectors.toList()); // for displaying List

    }

    // 19. Display the salary of the employee Grant Douglas (lastName: Grant, firstName: Douglas)
    public static Long getGrantDouglasSalary() throws Exception {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(employee -> employee.getFirstName().equals("Douglas"))
                .filter(employee -> employee.getLastName().equals("Grant"))
                .findFirst().orElseThrow(() -> new Exception("Douglas Grant")).getSalary();
    }

    // 20. Display the maximum salary an employee gets
    public static Long getMaxSalary() throws Exception {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .map(Employee::getSalary)
                .reduce(Long::max)
                .get();

//        return employeeService.readAll().stream()
//                .mapToLong(Employee::getSalary)
//                .max().getAsLong();

//        return employeeService.readAll().stream()
//                .collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary)))
//                .get().getSalary();

//        return employeeService.readAll().stream()
//                .map(Employee::getSalary)  // .map(employee -> employee.getSalary())
//                .collect(Collectors.maxBy(Comparator.comparing(Long::longValue)))
//                .get();

//        return employeeService.readAll().stream()
//                .map(Employee::getSalary)  // .map(employee -> employee.getSalary())
//                .reduce((salary1,salary2)-> salary1 > salary2 ? salary1 : salary2)
//                .get();

//        return employeeService.readAll().stream()
//                .max(Comparator.comparing(Employee::getSalary))
//                .get().getSalary();

//        return employeeService.readAll().stream()
//                .sorted(Comparator.comparing(Employee::getSalary).reversed())
//                .findFirst().get().getSalary();

//        return employeeService.readAll().stream()
//                .sorted(Comparator.comparing(Employee::getSalary).reversed())
//                .limit(1).collect(Collectors.toList()).get(0).getSalary();

    }

    // 21. Display the employee(s) who gets the maximum salary
    public static List<Employee> getMaxSalaryEmployee() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // 22. Display the max salary employee's job
    public static Job getMaxSalaryEmployeeJob() throws Exception {
        //TODO Implement the method
        return new Job();
    }

    // 23. Display the max salary in Americas Region
    public static Long getMaxSalaryInAmericasRegion() throws Exception {
        //TODO Implement the method
        return 1L;
    }

    // 24. Display the second maximum salary an employee gets
    public static Long getSecondMaxSalary() throws Exception {
        //TODO Implement the method
        return 1L;
    }

    // 25. Display the employee(s) who gets the second maximum salary
    public static List<Employee> getSecondMaxSalaryEmployee() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // 26. Display the minimum salary an employee gets
    public static Long getMinSalary() throws Exception {
        //TODO Implement the method
        return 1L;
    }

    // 27. Display the employee(s) who gets the minimum salary
    public static List<Employee> getMinSalaryEmployee() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // 28. Display the second minimum salary an employee gets
    public static Long getSecondMinSalary() throws Exception {
        //TODO Implement the method
        return 1L;
    }

    // 29. Display the employee(s) who gets the second minimum salary
    public static List<Employee> getSecondMinSalaryEmployee() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // 30. Display the average salary of the employees
    public static Double getAverageSalary() {
        //TODO Implement the method
        return 1d;
    }

    // 31. Display all the employees who are making more than average salary
    public static List<Employee> getAllEmployeesAboveAverage() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // 32. Display all the employees who are making less than average salary
    public static List<Employee> getAllEmployeesBelowAverage() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // 33. Display all the employees separated based on their department id number
    public static Map<Long, List<Employee>> getAllEmployeesForEachDepartment() {
        //TODO Implement the method
        return new HashMap<>();
    }

    // 34. Display the total number of the departments
    public static Long getTotalDepartmentsNumber() {
        //TODO Implement the method
        return 1L;
    }

    // 35. Display the employee whose first name is 'Alyssa' and manager's first name is 'Eleni' and department name is 'Sales'
    public static Employee getEmployeeWhoseFirstNameIsAlyssaAndManagersFirstNameIsEleniAndDepartmentNameIsSales() throws Exception {
        //TODO Implement the method
        return new Employee();
    }

    // 36. Display all the job histories in ascending order by start date
    public static List<JobHistory> getAllJobHistoriesInAscendingOrder() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // 37. Display all the job histories in descending order by start date
    public static List<JobHistory> getAllJobHistoriesInDescendingOrder() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // 38. Display all the job histories where the start date is after 01.01.2005
    public static List<JobHistory> getAllJobHistoriesStartDateAfterFirstDayOfJanuary2005() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // 39. Display all the job histories where the end date is 31.12.2007 and the job title of job is 'Programmer'
    public static List<JobHistory> getAllJobHistoriesEndDateIsLastDayOfDecember2007AndJobTitleIsProgrammer() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // 40. Display the employee whose job history start date is 01.01.2007 and job history end date is 31.12.2007 and department's name is 'Shipping'
    public static Employee getEmployeeOfJobHistoryWhoseStartDateIsFirstDayOfJanuary2007AndEndDateIsLastDayOfDecember2007AndDepartmentNameIsShipping() throws Exception {
        //TODO Implement the method
        return new Employee();
    }

    // 41. Display all the employees whose first name starts with 'A'
    public static List<Employee> getAllEmployeesFirstNameStartsWithA() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // 42. Display all the employees whose job id contains 'IT'
    public static List<Employee> getAllEmployeesJobIdContainsIT() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // 43. Display the number of employees whose job title is programmer and department name is 'IT'
    public static Long getNumberOfEmployeesWhoseJobTitleIsProgrammerAndDepartmentNameIsIT() {
        //TODO Implement the method
        return 1L;
    }

    // 44. Display all the employees whose department id is 50, 80, or 100
    public static List<Employee> getAllEmployeesDepartmentIdIs50or80or100() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // 45. Display the initials of all the employees
    // Note: You can assume that there is no middle name
    public static List<String> getAllEmployeesInitials() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // 46. Display the full names of all the employees
    public static List<String> getAllEmployeesFullNames() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // 47. Display the length of the longest full name(s)
    public static Integer getLongestNameLength() throws Exception {
        //TODO Implement the method
        return 1;
    }

    // 48. Display the employee(s) with the longest full name(s)
    public static List<Employee> getLongestNamedEmployee() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // 49. Display all the employees whose department id is 90, 60, 100, 120, or 130
    public static List<Employee> getAllEmployeesDepartmentIdIs90or60or100or120or130() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // 50. Display all the employees whose department id is NOT 90, 60, 100, 120, or 130
    public static List<Employee> getAllEmployeesDepartmentIdIsNot90or60or100or120or130() {
        //TODO Implement the method
        return new ArrayList<>();
    }

}
