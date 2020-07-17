package oleksandrdiachenko.pricechecker.pageobject;

import lombok.Getter;

@Getter
public enum FileType {

    XLS(".xls"),
    XLSX(".xlsx");

    private final String extension;

    FileType(String extension) {
        this.extension = extension;
    }
}
