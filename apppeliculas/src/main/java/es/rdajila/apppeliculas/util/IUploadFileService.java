package es.rdajila.apppeliculas.util;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public interface IUploadFileService {
    public Resource load(String filename) throws MalformedURLException;

    public String copy(MultipartFile file) throws IOException;

    public boolean delete(String filename);

    public void deleteAll();

    public void init() throws IOException;

    public String getBase64(MultipartFile file);
    public String getBase64(File file);
}
