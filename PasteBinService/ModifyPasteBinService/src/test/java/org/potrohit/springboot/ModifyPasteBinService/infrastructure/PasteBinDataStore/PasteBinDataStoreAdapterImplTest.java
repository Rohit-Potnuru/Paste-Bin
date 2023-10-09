package org.potrohit.springboot.ModifyPasteBinService.infrastructure.PasteBinDataStore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.potrohit.springboot.ModifyPasteBinService.domain.vao.PasteBinVao;
import org.potrohit.springboot.ModifyPasteBinService.infrastructure.dynamodb.DynamoDbImpl;
import org.potrohit.springboot.ModifyPasteBinService.infrastructure.dynamodb.PasteBinMetaData;
import org.potrohit.springboot.ModifyPasteBinService.infrastructure.s3.S3AdapterImpl;
import org.potrohit.springboot.ModifyPasteBinService.infrastructure.s3.S3Details;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PasteBinDataStoreAdapterImplTest {
    @Mock
    private DynamoDbImpl dynamoDb;

    @Mock
    private S3AdapterImpl s3AdapterImpl;
    private final PasteBinDataStoreAdapterImpl pasteBinDataStoreAdapter = new PasteBinDataStoreAdapterImpl(dynamoDb, s3AdapterImpl);
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterPasteBin() {
//        PasteBinVao pasteBinVao =  PasteBinVao.builder().build(); // populate with sample data
//        S3Details s3Details = S3Details.builder().build(); // populate with sample data
//        PasteBinMetaData pasteBinMetaData = new PasteBinMetaData(); // populate with sample data
//
//        when(s3AdapterImpl.getS3Details(pasteBinVao)).thenReturn(s3Details);
//        when(dynamoDb.getPasteBinMetaDataS3(pasteBinVao, s3Details, true)).thenReturn(pasteBinMetaData);
//
//        String result = pasteBinDataStoreAdapter.registerPasteBin(pasteBinVao);
//
//        assertNotNull(result);
//        verify(dynamoDb, times(1)).putItem(pasteBinMetaData);
//        // Add more verification or assertions as needed
    }

    @Test
    void testConfirmRegisterPasteBin() {
       // PasteBinVao pasteBinVao = new PasteBinVao(); // populate with sample data
        // ... similar to the above test, mock the needed objects and their interactions

        // Call the method
        // Assert and verify the interactions
    }

    // ... similar tests for updatePasteBin and confirmUpdatePasteBin

    // Add negative test cases, e.g., what happens if one of the dependencies throws an exception, etc.

}

