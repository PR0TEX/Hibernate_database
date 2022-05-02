import controller.MageController;
import entities.Mage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.MageRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MageControllerTest{
    @Mock
    private MageRepository repos;
    private MageController controller;

    @BeforeEach
    void setUp(){
        controller = new MageController(repos);
    }
    @Test
    public void deleteNotExisitingRecord(){
        doThrow(new IllegalArgumentException()).when(repos).delete("John");
        assertEquals("entities.Mage not exist", controller.delete("John"));
        verify(repos).delete("John");
    }
    @Test
    public void deleteExistingRecord(){
        doNothing().when(repos).delete("John");
        assertEquals("deleted",controller.delete("John"));
        verify(repos).delete("John");
    }
    @Test
    public void saveRecord(){
        ArgumentCaptor<Mage> mageArgumentCaptor = ArgumentCaptor.forClass(Mage.class);
        assertEquals("saved",controller.save("John",10));
        verify(repos).save(mageArgumentCaptor.capture());
        assertEquals(new Mage(10,"John")
                , mageArgumentCaptor.getValue()
        );
    }
    @Test
    public void saveAlreadyExistingRecord(){
        ArgumentCaptor<Mage> mageArgumentCaptor = ArgumentCaptor.forClass(Mage.class);
        doThrow(new IllegalArgumentException()).when(repos).save(any(Mage.class));
        assertEquals("entities.Mage already exist", controller.save("John", 10));
        verify(repos).save(mageArgumentCaptor.capture());
        assertEquals(new Mage(10,"John")
                ,mageArgumentCaptor.getValue()
        );
    }
    @Test
    public void findNotExistingRecord(){
        when(repos.find("John")).thenReturn(Optional.empty());
        assertEquals("not found", controller.find("John"));
        verify(repos).find("John");
    }
    @Test
    public void findExisingRecord(){
        Mage mage = new Mage(10, "John");
        assertEquals("not found",controller.find("John"));
        verify(repos).find("John");
    }
}