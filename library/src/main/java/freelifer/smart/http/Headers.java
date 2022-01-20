package freelifer.smart.http;

import android.text.TextUtils;

import java.util.HashMap;

/**
 * Http Headers class
 * 支持延迟value获取
 * example:
 *      Headers.of().addHeader(key, value)
 *             .addHeader(key, ()->{});
 *
 * @author Ziv on 2022/1/20.
 */
public class Headers {

    private final HashMap<String, String> values = new HashMap<>();
    private final HashMap<String, Lazy> lazyValues = new HashMap<>();

    public static Headers of() {
        return new Headers();
    }

    public Headers addHeader(String key, String value) {
        if (TextUtils.isEmpty(key)) {
            return this;
        }
        if (value == null) {
            return this;
        }
        this.values.put(key, value);
        return this;
    }

    public Headers addHeader(String key, Lazy lazy) {
        if (TextUtils.isEmpty(key)) {
            return this;
        }
        if (lazy == null) {
            return this;
        }
        this.lazyValues.put(key, lazy);
        return this;
    }

    public HashMap<String, String> toMap() {
        HashMap<String, String> result = new HashMap<>(values);

        if (!lazyValues.isEmpty()) {
            for (String key : lazyValues.keySet()) {
                Lazy lazy = lazyValues.get(key);
                if (lazy != null) {
                    result.put(key, lazy.get());
                }
            }
        }
        return result;
    }

    public interface Lazy {
        /**
         * 懒获取值
         *
         * @return String value
         */
        String get();
    }
}
