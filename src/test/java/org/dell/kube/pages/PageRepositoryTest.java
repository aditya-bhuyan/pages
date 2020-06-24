package org.dell.kube.pages;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


public class PageRepositoryTest {
    private IPageRepository repo;

     @BeforeEach
    public void setUp() {
        repo = new InMemoryPageRepository();
    }

  @Test
    public void createInsertsAPageRecord() {
        Page newPage = new Page("XYZ", "Bangalore", 123, "1234567890");
        Page page = repo.create(newPage);

        Page foundPage = repo.read(page.getId());


      assertThat(foundPage.getId()).isEqualTo(page.getId());
      assertThat(foundPage.getBusinessName()).isEqualTo(page.getBusinessName());
      assertThat(foundPage.getAddress()).isEqualTo(page.getAddress());
      assertThat(foundPage.getCategoryId()).isEqualTo(page.getCategoryId());
      assertThat(foundPage.getContactNumber()).isEqualTo(page.getContactNumber());
    }

      @Test
    public void createReturnsTheCreatedPage() {
        Page newPage = new Page("XYZ", "Bangalore", 123, "1234567890");
        Page page = repo.create(newPage);

        assertThat(page.getId()).isNotNull();
        assertThat(page.getBusinessName()).isEqualTo("XYZ");
        assertThat(page.getAddress()).isEqualTo("Bangalore");
        assertThat(page.getCategoryId()).isEqualTo(123);
        assertThat(page.getContactNumber()).isEqualTo("1234567890");
    }

   @Test
    public void findFindsAPage() {
       Page newPage = new Page("XYZ", "Bangalore", 123, "1234567890");
       repo.create(newPage);
        Page page = repo.read(321L);

       assertThat(page.getId()).isEqualTo(321L);
       assertThat(page.getBusinessName()).isEqualTo("XYZ");
       assertThat(page.getAddress()).isEqualTo("Bangalore");
       assertThat(page.getCategoryId()).isEqualTo(123);
       assertThat(page.getContactNumber()).isEqualTo("1234567890");
    }

     @Test
    public void findReturnsNullWhenNotFound() {
        Page page = repo.read(321L);

        assertThat(page).isNull();
    }

   @Test
    public void listFindsAllTimeEntries() {
       Page newPage = new Page("XYZ", "Bangalore", 123, "1234567890");
       repo.create(newPage);

        List<Page> pages = repo.list();
        assertThat(pages.size()).isEqualTo(1);

       Page page = pages.get(0);
       assertThat(page.getId()).isEqualTo(321L);
       assertThat(page.getBusinessName()).isEqualTo("XYZ");
       assertThat(page.getAddress()).isEqualTo("Bangalore");
       assertThat(page.getCategoryId()).isEqualTo(123);
       assertThat(page.getContactNumber()).isEqualTo("1234567890");
    }

    @Test
    public void updateReturnsTheUpdatedRecord() {


        Page newPage = new Page(1000L,"ABC", "Bangalore", 321, "1876543210");
        repo.create(newPage);
        Page pageUpdates = new Page("ABC" , "Bangalore", 321, "987654321");

        Page updatedPage = repo.update(pageUpdates,1000L);

        assertThat(updatedPage.getId()).isEqualTo(1000L);
        assertThat(updatedPage.getBusinessName()).isEqualTo("ABC");
        assertThat(updatedPage.getAddress()).isEqualTo("Bangalore");
        assertThat(updatedPage.getCategoryId()).isEqualTo(321);
        assertThat(updatedPage.getContactNumber()).isEqualTo("987654321");
    }

     @Test
    public void updateUpdatesTheRecord() {
         Page newPage = new Page(1000L,"ABC", "Bangalore", 321, "1876543210");
         repo.create(newPage);

        Page updatedPage = new Page("ABC" , "Bangalore", 321, "987654321");

        Page page = repo.update(updatedPage,1000L);

        Page foundPage = repo.read( page.getId());

        assertThat(foundPage.getId()).isEqualTo(page.getId());
         assertThat(foundPage.getBusinessName()).isEqualTo("ABC");
         assertThat(foundPage.getAddress()).isEqualTo("Bangalore");
         assertThat(foundPage.getCategoryId()).isEqualTo(321);
         assertThat(foundPage.getContactNumber()).isEqualTo("987654321");
    }

    @Test
    public void deleteRemovesTheRecord() {
        Page newPage = new Page(1000L,"ABC", "Bangalore", 321, "1876543210");
        repo.create(newPage);

        repo.delete(1000L);

        Page notFoundPage = repo.read( 1000L);
        assertThat(notFoundPage).isNull();

    }
}
