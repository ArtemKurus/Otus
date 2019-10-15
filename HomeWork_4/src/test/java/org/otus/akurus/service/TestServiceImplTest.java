package org.otus.akurus.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TestServiceImplTest {

    @Autowired
    private TestService testService;

    @Test
    public void testTestService() {
        Scanner mockScanner = Mockito.mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("12742")
                .thenReturn("Луна")
                .thenReturn("8")
                .thenReturn("4")
                .thenReturn("5");
        assertEquals(5, testService.run(mockScanner));
    }

}