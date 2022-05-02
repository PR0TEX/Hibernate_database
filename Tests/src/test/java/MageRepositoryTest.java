import entities.Mage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.MageRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MageRepositoryTest {
    private MageRepository repos;

    @BeforeEach
    void setUp(){ repos = new MageRepository(); }

    @Test
    public void deleteNotExistingRecord(){
        repos.save(new Mage(10,"John"));
        assertThrows(IllegalArgumentException.class, () ->{
            repos.delete("Tom");
        });
    }
    @Test
    public void addAlreadyExistingRecord(){
        repos.save(new Mage(10,"John"));
        assertThrows(IllegalArgumentException.class, () -> {
            repos.save(new Mage(10,"John"));
        });
    }
    @Test
    public void getNotExisitingRecord(){
        Optional<Mage> result = repos.find("Tom");
        assertEquals(Optional.empty(),result);
    }
    @Test
    public void getExisitingRecord(){
        Mage mage = new Mage(10,"John");
        repos.save(mage);
        Optional<Mage> result = repos.find("John");
        assertEquals(Optional.of(mage),result);
    }

}
