import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

/**
 * CSV Helper:
 * - parseLine: handles commas in quotes
 * - readAll: returns rows w/out header and skips first line
 * - appendLine: creates file with header if missing, then appends
 */

public final class CSVUtil
{
    private  CSVUtil()
    {

    }

    public static List<String> parseLine(String line)
    {
        /*
         * parses single CSV line into fields, supporting quotes:
         * - commas inside "..." are preserved
         * - double quotes inside a quoted field are allowed by doubling ("")
         */
        List<String> output = new ArrayList<>();
        if (line == null)
        {
            return output;
        }

        StringBuilder currentLine = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < line.length(); i++)
        {
            char ch = line.charAt(i);
            if (ch == '"')
            {
                // already in quotes + next char also quote -> is an escaped quote
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"')
                {
                    currentLine.append('"');
                    // skip escaped quote
                    i++;
                }
                else
                {
                    // toggle quoting state
                    inQuotes = !inQuotes;
                }
            }
            else if (ch == ',' && !inQuotes)
            {
                output.add(currentLine.toString());
                currentLine.setLength(0);
            }
            else
            {
                currentLine.append(ch);
            }
        }
        output.add(currentLine.toString());
        return output;
    }

    /**
     * Read all rows from CSV files and return data rows (header skipped)
     * @param path  path to CSV file
     * @param skipHeader    true to skip the first line
     */

    public static List<List<String>> readAll(Path path, boolean skipHeader) throws IOException
    {
        List<List<String>> rows = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8))
        {
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first && skipHeader)
                {
                    first = false;
                    continue;
                }
                rows.add(parseLine(line));
                first = false;
            }
        }
        return rows;
    }

    /**
     * Append CSV line - if file doesn't exist -> write header first
     */
    public static void appendLine(Path path, String headerIfNew, String csvLine) throws IOException
    {
        if (Files.notExists(path))
        {
            Files.createDirectories(path.getParent());
            Files.writeString(path, headerIfNew + System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        }
        Files.writeString(path, csvLine +  System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }
}
