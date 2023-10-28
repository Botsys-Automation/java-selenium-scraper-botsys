package output;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Out {

    @CsvBindByName(column = "Author Name", required = true)
    private String authorName;

    @CsvBindByName(column = "Post Title", required = true)
    private String title;

    @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByName(column = "Date", required = false)
    private LocalDate date;

    @CsvBindByName(column = "Contents", required = false)
    private String contents;

}
