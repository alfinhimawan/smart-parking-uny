package util;

import java.util.Arrays;
import java.util.List;

public class UserTypeValidator {
    private static final List<String> VALID_TYPES = Arrays.asList("DOSEN", "MAHASISWA", "STAFF", "UMUM");
    public static boolean isValidUserType(String kode) {
        return VALID_TYPES.contains(kode.toUpperCase());
    }
}
