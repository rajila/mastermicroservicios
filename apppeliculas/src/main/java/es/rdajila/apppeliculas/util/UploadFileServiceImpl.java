package es.rdajila.apppeliculas.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements IUploadFileService {
    //private final Logger log = LoggerFactory.getLogger(getClass());

    private final static String UPLOADS_FOLDER = "uploads/peliculas";

    @Override
    public Resource load(String filename) throws MalformedURLException {
        Path pathFoto = getPath(filename);
        //log.info("pathFoto: " + pathFoto);

        Resource recurso = new UrlResource(pathFoto.toUri());

        if (!recurso.exists() || !recurso.isReadable()) {
            throw new RuntimeException("Error: no se puede cargar la imagen: " + pathFoto);
        }
        return recurso;
    }

    @Override
    public String copy(MultipartFile file) throws IOException {
        String uniqueFilename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path rootPath = getPath(uniqueFilename);

        //log.info("rootPath: " + rootPath);

        Files.copy(file.getInputStream(), rootPath);

        return uniqueFilename;
    }

    @Override
    public boolean delete(String filename) {
        Path rootPath = getPath(filename);
        File archivo = rootPath.toFile();

        if (archivo.exists() && archivo.canRead()) {
            return archivo.delete();
        }
        return false;
    }

    public Path getPath(String filename) {
        return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());
    }

    @Override
    public void init() throws IOException {
        // TODO Auto-generated method stub
        Files.createDirectory(Paths.get(UPLOADS_FOLDER));
    }

    @Override
    @Deprecated
    public String getBase64(MultipartFile file) {
        try{
            byte[] image = Base64.encodeBase64(file.getBytes(), true);
            return new String(image);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    @Deprecated
    public String getBase64(File file) {
        try {
            byte[] image;
            try (FileInputStream input = new FileInputStream(file)) {
                image = Base64.encodeBase64(input.readAllBytes(), true);
            }
            return new String(image);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
