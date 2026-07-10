package commons;

import java.io.File;

public class GlobalConstants {
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");

    // App Infor User
    public static final String DEV_USER_URL = "http://dev.techpanda.org/";
    public static final String STAGING_USER_URL = "http://staging.techpanda.org/";
    public static final String LIVE_USER_URL = "http://live.techpanda.org/";

    // App Infor Admin
    public static final String DEV_ADMIN_URL = "http://dev.techpanda.org/index.php/backendlogin";
    public static final String STAGING_ADMIN_URL = "http://staging.techpanda.org/index.php/backendlogin";
    public static final String LIVE_ADMIN_URL = "http://live.techpanda.org/index.php/backendlogin";

    public static final String ADMIN_USERNAME = "user01";
    public static final String ADMIN_PASSWORD = "guru99com";

    // Wait Infor
    public static final long SHORT_TIMEOUT = 10;
    public static final long LONG_TIMEOUT = 30;

    // Download/ Upload file
    public static final String UPLOAD_PATH = PROJECT_PATH + File.separator +"uploadFiles" + File.separator;
    public static final String DOWNLOAD_PATH = PROJECT_PATH + File.separator + "downloadFiles" + File.separator;

    // Retry Case Failed
    public static final int RETRY_NUMBER = 3;

    // Browser Logs/ Extension
    public static final String BROWSER_LOG_PATH = PROJECT_PATH + File.separator+ "browserLogs" + File.separator;
    public static final String BROWSER_EXTENSION_PATH = PROJECT_PATH + File.separator + "browserExtensions" + File.separator;

    // HTML Report Folder
    public static final String REPORTNG_PATH = PROJECT_PATH + File.separator +"htmlReportNG" + File.separator;
    public static final String EXTENT_PATH = PROJECT_PATH + File.separator + "htmlExtent" + File.separator;
    public static final String ALLURE_PATH = PROJECT_PATH + File.separator + "htmlAllure" + File.separator;

    // Data Test/ Environment
    public static final String DATA_TEST_PATH = PROJECT_PATH + File.separator + "dataTest" + File.separator;
    public static final String ENVIRONMENT_CONFIG_PATH = PROJECT_PATH + File.separator + "environmentConfig" + File.separator;

    public static final String JIRA_SITE_URL = "https://thanhngan02.atlassian.net/" ;
    public static final String JIRA_USERNAME = "thanhngan060295@gmail.com";
    public static final String JIRA_API_KEY = "ATATT3xFfGF0A2nmpLeUQm5hTSaH28SY2ooQ1_aVyxWZK6ZEjgwZMVaobanTcqVhxGYAjrXHcpfggVcJfvS4-yelNorgyBeivgaqPzRM4kruTJVSJz1vm2-r9o1SC1ckyBQblQ6emk_Sdc90eM0rC_v6gJdTp7UCJ8kW_hNc7Bx5qIeOvx-z-I8=5257DF9C";
    public static final String JIRA_PROJECT_KEY = "RT02";
}
