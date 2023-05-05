package com.example.restjava.database;

import com.example.restjava.entity.Numbers;
import com.example.restjava.entity.ResultPair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class RepositoryServiceTest {
    @Mock
    private IRepository repository;

    @InjectMocks
    RepositoryService repositoryService = new RepositoryService(repository);

    @Test
    public void test(){
        repositoryService.save(new Numbers(new int[] {1,2,3,4}), new ResultPair(2.0, 2.0));
        repositoryService.save(new Numbers(new int[] {5,6,7,8}), new ResultPair(6.0, 6.0));

        List<DbEntity> list = repositoryService.getAll();
        Assertions.assertNotNull(list);
        Assertions.assertTrue(list.isEmpty());
    }
}
