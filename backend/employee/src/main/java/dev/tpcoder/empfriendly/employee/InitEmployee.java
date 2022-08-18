package dev.tpcoder.empfriendly.employee;

import dev.tpcoder.empfriendly.employee.model.Address;
import dev.tpcoder.empfriendly.employee.model.DriverDetail;
import dev.tpcoder.empfriendly.employee.model.Employee;
import dev.tpcoder.empfriendly.employee.utility.EmployeeType;
import dev.tpcoder.empfriendly.employee.utility.Tag;
import dev.tpcoder.empfriendly.employee.repository.EmployeeRepository;
import java.util.List;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.geo.Point;

@Configuration
public class InitEmployee {

  @Bean
  CommandLineRunner initTestEmployeeData(EmployeeRepository repository) {
    return args -> {
      repository.deleteAll();

      /*
       * WARNING: FORCE ID (PK)
       * */

      // EMPLOYEE 1
      // d37b2d0b-c06d-429c-b56d-7465c3959993
      // Me!
      var mart = new Employee();
      var martAddress = new Address();
      martAddress
          .setHouseNumber("109/1070")
          .setCity("Thanyaburi")
          .setState("Pathum Thani")
          .setPostalCode("12110")
          .setCountry("Thailand");
      mart.setId("d37b2d0b-c06d-429c-b56d-7465c3959993")
          .setFirstName("Thanaphoom")
          .setLastName("Babparn")
          .setAge(25)
          .setType(EmployeeType.ORDINARY.name())
          .setEmail("thanaphoom.babparn@empfriendly.dev")
          .setAddress(martAddress)
          .setAddressLoc(new Point(100.7433723, 14.0364895))
          .setTags(Set.of(Tag.SOFTWARE_ENGINEER.name(), Tag.BACKEND_DEVELOPER.name(),
              Tag.CLOUD_ENGINEER.name(), Tag.DEVOPS.name()));

      // EMPLOYEE 2
      // ddf5757a-ca21-41f4-b668-836d7755d70d
      // Kennedy Bowden - Name Generator
      var kennedyAddress = new Address();
      kennedyAddress.setHouseNumber("2222222")
          .setCity("Port")
          .setState("New York")
          .setPostalCode("13906-2123")
          .setCountry("United States");
      var kennedy = new Employee();
      kennedy.setId("ddf5757a-ca21-41f4-b668-836d7755d70d")
          .setFirstName("Kennedy")
          .setLastName("Bowden")
          .setAge(32)
          .setEmail("kennedy.bowden@empfriendly.dev")
          .setType(EmployeeType.ORDINARY.name())
          .setAddress(kennedyAddress)
          .setAddressLoc(new Point(21.948998, -70.192999))
          .setTags(Set.of(Tag.CLOUD_ENGINEER.name(), Tag.DEVOPS.name()));

      // EMPLOYEE 3
      // 1636a414-1f16-45b9-8e36-28507c108be9
      // Ptolemy Mccallum - Name Generator
      var ptolemyAddress = new Address();
      ptolemyAddress.setHouseNumber("3333333")
          .setCity("Port")
          .setState("Maine")
          .setPostalCode("49962-4346")
          .setCountry("United States");
      var ptolemy = new Employee();
      ptolemy.setId("1636a414-1f16-45b9-8e36-28507c108be9")
          .setFirstName("Ptolemy")
          .setLastName("Mccallum")
          .setAge(26)
          .setEmail("ptolemy.mccallum@empfriendly.dev")
          .setType(EmployeeType.ORDINARY.name())
          .setAddress(ptolemyAddress)
          .setAddressLoc(new Point(-29.604922, -38.118599))
          .setTags(Set.of(Tag.BUSINESS_ANALYST.name(), Tag.FRONTEND_DEVELOPER.name(),
              Tag.DESIGNER.name()));

      // EMPLOYEE 4 - Driver
      // 8985954f-7925-42c9-a1e5-e6b4bad5fd6c
      // Arun Jeffery - Name Generator
      // License - DR1111
      var arunAddress = new Address();
      arunAddress.setHouseNumber("4444444")
          .setCity("Port")
          .setState("New York")
          .setPostalCode("13906-2123")
          .setCountry("United States");
      var arun = new Employee();
      arun.setId("8985954f-7925-42c9-a1e5-e6b4bad5fd6c")
          .setFirstName("Arun")
          .setLastName("Jeffery")
          .setAge(35)
          .setEmail("arun.jeffery@empfriendly.dev")
          .setType(EmployeeType.DRIVER.name())
          .setAddress(arunAddress)
          .setAddressLoc(new Point(-165.205427, 4.07756))
          .setTags(Set.of(Tag.DRIVER.name()))
          .setDriverDetail(new DriverDetail("DR1111"));

      // EMPLOYEE 5 - Driver
      // 1b4a4de9-0eca-4c73-97c6-e1b9df06678e
      // Siddharth Wade - Name Generator
      // License - DR2222
      var siddharthAddress = new Address();
      siddharthAddress.setHouseNumber("5555555")
          .setCity("East")
          .setState("California")
          .setPostalCode("64200")
          .setCountry("United States");
      var siddharth = new Employee();
      siddharth.setId("1b4a4de9-0eca-4c73-97c6-e1b9df06678e")
          .setFirstName("Siddharth")
          .setLastName("Wade")
          .setAge(36)
          .setEmail("siddharth.wade@empfriendly.dev")
          .setType(EmployeeType.DRIVER.name())
          .setAddress(siddharthAddress)
          .setAddressLoc(new Point(-127.494467, 52.101911))
          .setTags(Set.of(Tag.DRIVER.name()))
          .setDriverDetail(new DriverDetail("DR2222"));

      // EMPLOYEE 6
      // 93e16962-57d0-4a27-b8f4-8db20f29b25a
      // Trevor Ritter - Name Generator
      // License - DR3333
      var trevorAddress = new Address();
      trevorAddress.setHouseNumber("6666666")
          .setCity("Lake")
          .setState("Idaho")
          .setPostalCode("10132-7595")
          .setCountry("United States");
      var trevor = new Employee();
      trevor.setId("93e16962-57d0-4a27-b8f4-8db20f29b25a")
          .setFirstName("Trevor")
          .setLastName("Ritter")
          .setAge(46)
          .setEmail("trevor.ritter@empfriendly.dev")
          .setType(EmployeeType.DRIVER.name())
          .setAddress(trevorAddress)
          .setAddressLoc(new Point(-52.199862, -57.06264))
          .setTags(Set.of(Tag.DRIVER.name()))
          .setDriverDetail(new DriverDetail("DR3333"));

      repository.saveAll(List.of(mart, kennedy, ptolemy, arun, siddharth, trevor));
    };
  }
}
