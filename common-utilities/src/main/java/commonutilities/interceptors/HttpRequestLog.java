package commonutilities.interceptors;

import java.util.Map;
import lombok.Builder;

@Builder
record HttpRequestLog(String method, String uri, Map<String, String> headers) {

}
