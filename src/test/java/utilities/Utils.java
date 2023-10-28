package utilities;

import output.Out;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Utils {

    public static void sort(List<Out> outs) {

        Collections.sort(outs, new Comparator<Out>() {

            @Override
            public int compare(Out o1, Out o2) {
                if (o1.getDate() == null || o2.getDate() == null) {
                    return -1;
                }
                LocalDate d1 = o1.getDate();
                LocalDate d2 = o2.getDate();

                if (d1.isBefore(d2)) {
                    return 1;
                }

                return d1.isAfter(d2) ? -1 : 0;
            }
        });
    }

    public static List<String[]> listToArray(List<List<Object>> listOfList) {
        List<String[]> rows = new ArrayList<>();
        if (listOfList != null && !listOfList.isEmpty()) {
            for (List<Object> row : listOfList) {
                String[] rowData = row.stream().map(Object::toString).toArray(String[]::new);
                rows.add(rowData);
            }
        }

        return rows;
    }
}
