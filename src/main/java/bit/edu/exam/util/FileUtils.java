package bit.edu.exam.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.json.simple.*;

/**
 * 파일과 관련된 유틸 클래스 입니다.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class FileUtils {

    private static final String FILE_PREFIX = System.getProperty("user.dir").concat("/data/");
    private static final String FILE_TYPE = ".json";

    /**
     * 인자로 받은 list를 json 형식으로 파일에 쓰는 메서드 입니다.
     *
     * @param list json 형식으로 나타낼 데이터 리스트
     * @param fileName 저장할 파일에 대한 이름
     * @author 강명관
     */
    public static <T> void writeJsonFile(List<T> list, String fileName) {
        try (FileWriter fileWriter = new FileWriter(FILE_PREFIX.concat(fileName + FILE_TYPE))) {
            fileWriter.write(JSONArray.toJSONString(list));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
