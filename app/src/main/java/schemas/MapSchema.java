package schemas;

import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;


public class MapSchema extends BaseSchema {
    private boolean notNull;
    private Integer minSize;

    public MapSchema required() {
        this.notNull = true;
        return this;
    }

    public MapSchema sizeof(Integer count) throws Exception {
        if (count < 0) {
            throw new Exception("Длина не может быть меньше 0");
        } else {
            this.minSize = count;
        }
        return this;
    }

    public boolean isValid(Object object) throws Exception {
        ObjectMapper oMapper = new ObjectMapper();
        var data = oMapper.convertValue(object, Map.class);

        if (isRequired()) {
            if (data == null) {
                return false;
            }
        }
        if (hasMinSize()) {
            if (!data.isEmpty()) {
                if (data.size() < minSize) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasMinSize() throws Exception {
        var value = (Integer) getValue("minSize");
        if (value == null) {
            return false;
        }
        return true;
    }



}
