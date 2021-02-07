package gparap.apps.web_browser;

/**
 * Created by gparap on 2020-10-06.
 */
public class Utils {

    /**
     * Standardizes a url request.
     *
     * @param requestedUrl url
     * @return standardized url
     */
    public static String searchUrl(String requestedUrl) {
        String url = requestedUrl;

        try {
            url = url.replace("https://", ""); //remove (secure) protocol
            url = url.replace("http://", "");  //remove protocol
            url = url.replace("www.", "");     //remove www.
            String urlPlaceholder = url;

            //check if url has no .com or .whatever
            boolean hasDotCom = urlPlaceholder.contains(".com");
            boolean hasDotOther;
            try {
                hasDotOther = urlPlaceholder.substring(urlPlaceholder.lastIndexOf(".")).length() > 1;
            } catch (Exception e) {
                hasDotOther = false;
            }

            //construct final url
            if (hasDotCom || hasDotOther) {
                url = "https://www." + urlPlaceholder;
            } else {
                //add .com by default
                url = "https://www." + urlPlaceholder + ".com";
            }

        } catch (Exception ignored) {
        }

        return url;
    }
}
