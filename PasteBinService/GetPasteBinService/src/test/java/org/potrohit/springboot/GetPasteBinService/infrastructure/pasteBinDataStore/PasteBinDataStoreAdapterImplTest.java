package org.potrohit.springboot.GetPasteBinService.infrastructure.pasteBinDataStore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.potrohit.springboot.GetPasteBinService.infrastructure.dynamodb.DynamoDbImpl;
import org.potrohit.springboot.GetPasteBinService.infrastructure.s3.S3AdapterImpl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PasteBinDataStoreAdapterImplTest {
    @Mock
    private DynamoDbImpl dynamoDb;

    @Mock
    private S3AdapterImpl s3AdapterImpl;

    @InjectMocks
    private PasteBinDataStoreAdapterImpl pasteBinDataStoreAdapterImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerPasteBinTest() {
//        // Given
//        PasteBinVao pasteBinVao = mock(PasteBinVao.class); // Create a mock object or setup an actual instance with data
//        S3Details s3Details = mock(S3Details.class);
//        PasteBinMetaData metaData = mock(PasteBinMetaData.class);
//        when(s3AdapterImpl.getS3Details(pasteBinVao)).thenReturn(s3Details);
//        when(dynamoDb.getPasteBinMetaDataS3(pasteBinVao, s3Details, true)).thenReturn(metaData);
//        when(s3AdapterImpl.getPreSignedUploadURL(s3Details, pasteBinVao.getContentType())).thenReturn("sampleURL");
//
//        // When
//        String result = pasteBinDataStoreAdapterImpl.registerPasteBin(pasteBinVao);
//
//        // Then
//        assertEquals("sampleURL", result);
    }

    // You will have to repeat the pattern for other methods like:
    // - confirmRegisterPasteBin
    // - updatePasteBin
    // - confirmUpdatePasteBin
    // - deletePasteBin

    // For methods that throw exceptions, you can use:
    // - verify to make sure certain methods were called on mocks
    // - assertThrows to make sure exceptions are thrown under correct conditions

    @Test
    void registerPasteBinTest_Exception() {
//        // Given
//        PasteBinVao pasteBinVao = mock(PasteBinVao.class);
//        when(s3AdapterImpl.getS3Details(pasteBinVao)).thenThrow(new RuntimeException("error"));
//
//        // When / Then
//        assertThrows(RuntimeException.class, () -> pasteBinDataStoreAdapterImpl.registerPasteBin(pasteBinVao));
    }
}
