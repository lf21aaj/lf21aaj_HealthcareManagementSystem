import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;


public final class PathsConfig
{
    private PathsConfig() {}

    public static final Path DATA_DIR           = Path.of("data");
    public static final Path PATIENTS_CSV       = DATA_DIR.resolve("patients.csv");
    public static final Path CLINICIANS_CSV     = DATA_DIR.resolve("clinicians.csv");
    public static final Path APPOINTMENTS_CSV   = DATA_DIR.resolve("appointments.csv");
    public static final Path PRESCRIPTIONS_CSV  = DATA_DIR.resolve("prescriptions.csv");
    public static final Path REFERRALS_CSV      = DATA_DIR.resolve("referrals.csv");
    public static final Path FACILITIES_CSV     = DATA_DIR.resolve("facilities.csv");
    public static final Path STAFF_CSV          = DATA_DIR.resolve("staff.csv");

    public static final Path OUTBOX_DIR         = Path.of("outbox");

    public static void verifyFiles()
    {
        System.out.println("\nVerifying CSV files in: " + DATA_DIR.toAbsolutePath());
        PathsConfig.checkFile(PATIENTS_CSV);
        PathsConfig.checkFile(CLINICIANS_CSV);
        PathsConfig.checkFile(APPOINTMENTS_CSV);
        PathsConfig.checkFile(PRESCRIPTIONS_CSV);
        PathsConfig.checkFile(REFERRALS_CSV);
        PathsConfig.checkFile(FACILITIES_CSV);
        PathsConfig.checkFile(STAFF_CSV);
        System.out.println("\nFiles verified.\n");
    }

    private static void checkFile(Path filePath)
    {
        if (Files.exists(filePath))
        {
            System.out.println("\nFound: " + filePath.toAbsolutePath());
        }
        else
        {
            System.out.println("\nFile not found: " + filePath.toAbsolutePath());
        }
    }

}
