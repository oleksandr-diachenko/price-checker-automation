package oleksandrdiachenko.pricechecker.pageobject;

import lombok.Getter;

import java.util.Arrays;

import static java.lang.String.format;

@Getter
public enum FileType {

    XLS(".xls"),
    XLSX(".xlsx");

    private final String extension;

    FileType(String extension) {
        this.extension = extension;
    }

    public static FileType getByExtension(String extension) {
        return Arrays.stream(FileType.values())
                .filter(fileType -> fileType.extension.equalsIgnoreCase(extension))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format("File type with extension %s not found", extension)));
    }
}
