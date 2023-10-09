package org.potrohit.springboot.ModifyPasteBinService.infrastructure.s3;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.potrohit.springboot.ModifyPasteBinService.infrastructure.s3.S3AdapterImpl;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;


public class S3AdapterImplTest {

    @Mock
    private S3Client s3Client;
    @Mock
    private S3Presigner s3Presigner;
    private S3AdapterImpl s3Adapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        s3Adapter = new S3AdapterImpl(s3Client, s3Presigner);
    }

//    @Test
//    public void testGetPreSignedDownloadURL() {
//        // Arrange
//        String expectedURL = "https://example.com/presigned-url";
//        when(s3Presigner.presignGetObject((GetObjectPresignRequest) any())).thenReturn(
//                PresignedGetObjectRequest.builder().url("https://example.com/presigned-url").build()
//        );
//
//        // Act
//        String actualURL = s3Adapter.getPreSignedDownloadURL(
//                S3Details.builder()
//                        .s3Bucket("test-bucket")
//                        .s3ObjectKey("test-object-key")
//                        .build()
//        );
//
//        // Assert
//        assertEquals(expectedURL, actualURL);
//    }
}
