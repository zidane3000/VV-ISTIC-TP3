package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TLSSocketFactoryTestMocks {

    SSLSocket mockSSLSocket = mock(SSLSocket.class);

    // Test when the edge case when the both supported and enabled protocols are null.
    @Test
    public void preparedSocket_NullProtocols()  {
        TLSSocketFactory f = new TLSSocketFactory();
        f.prepareSocket(mockSSLSocket);
        verify(mockSSLSocket, times(1)).getSupportedProtocols();
        verify(mockSSLSocket, times(1)).getEnabledProtocols();
        verify(mockSSLSocket, never()).setEnabledProtocols(any());
    }

    @Test
    public void typical(){
        TLSSocketFactory f = new TLSSocketFactory();
        when(mockSSLSocket.getSupportedProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        when(mockSSLSocket.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));
        f.prepareSocket(mockSSLSocket);
        verify(mockSSLSocket, times(1)).getSupportedProtocols();
        verify(mockSSLSocket, times(1)).getEnabledProtocols();
        verify(mockSSLSocket, times(1)).setEnabledProtocols(new String[] {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" });
    }


    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }
}