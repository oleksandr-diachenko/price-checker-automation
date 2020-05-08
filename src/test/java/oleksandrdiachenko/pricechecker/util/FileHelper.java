package oleksandrdiachenko.pricechecker.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.io.File;
import java.net.URL;

@Slf4j
public class FileHelper {

    public File getFile(String path) {
        URL resource = FileHelper.class.getClassLoader().getResource(path);
        if (ObjectUtils.isEmpty(resource)) {
            log.error("Can't read file {}", path);
            throw new IllegalArgumentException("Can't read file");
        }
        return new File(resource.getFile());
    }
}
