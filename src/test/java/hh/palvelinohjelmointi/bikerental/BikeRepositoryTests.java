package hh.palvelinohjelmointi.bikerental;

import hh.palvelinohjelmointi.bikerental.domain.Bike;
import hh.palvelinohjelmointi.bikerental.repository.BikeRepository;
import hh.palvelinohjelmointi.bikerental.repository.CategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testing BikeRepository
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class BikeRepositoryTests {

    @Autowired
    private BikeRepository bikeRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * create
     */
    @Test
    public void createNewBike() {
        Bike bike = new Bike("Tunturi-test", "Aura", 7.99, 1, categoryRepository.findCategoryByCategoryName("city").get());
        bikeRepository.save(bike);
        assertThat(bike.getBikeId()).isNotNull();
    }

    /**
     *  read, delete
     */
    @Test
    public void deleteBike() {

        Bike bike = new Bike("Tunturi-test", "Aura", 7.99, 1, categoryRepository.findCategoryByCategoryName("city").get());
        bikeRepository.save(bike);

        assertThat(bike.getBikeId()).isNotNull();

        List<Bike> bikes = bikeRepository.findBikesByManufacturer("Tunturi-false-test");
        assertThat(bikes).hasSize(0);

        bikes = bikeRepository.findBikesByManufacturer("Tunturi-test");
        Long bikeId = bikes.get(0).getBikeId();
        assertThat(bikes).hasSize(1);

        bikeRepository.deleteById(bikeId);
        Optional<Bike> bikesAfterDelete = bikeRepository.findById(bikeId);
        assertThat(bikesAfterDelete).isEmpty();
    }
}
